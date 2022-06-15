package com.oleksandrkarpiuk.fragmentwithnotification.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color.blue
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.oleksandrkarpiuk.fragmentwithnotification.MainApplication
import com.oleksandrkarpiuk.fragmentwithnotification.R
import com.oleksandrkarpiuk.fragmentwithnotification.components.rectangle_counter.RectangleCounter
import com.oleksandrkarpiuk.fragmentwithnotification.databinding.ActivityMainBinding
import com.oleksandrkarpiuk.fragmentwithnotification.ui.models.FragmentModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    @Inject lateinit var factory: MainViewModelFactory
    private lateinit var notificationAdapter: NotificationAdapter

    private val counterListener = object: RectangleCounter.CounterChangeListener {
        override fun onPlusClicked() {
            viewModel.plusClicked()
        }

        override fun onMinusClicked() {
            deleteNotification(viewModel.getCurrentNumber())
            viewModel.minusClicked()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        preInit()
        initViews()
        initData()
        initObservers()
    }

    private fun inject() {
        (applicationContext as MainApplication)
            .getComponent()
            .createMainComponent()
            .create(this)
            .inject(this)
    }

    private fun preInit() {
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    private fun initViews() {
        initCounter()
        initViewPager()
    }

    private fun initCounter() {
        binding.fragmentsCounter.setup(
            number = 0,
            callback = counterListener
        )
    }

    private fun initViewPager() = with(viewPager) {
        adapter = NotificationAdapter(mutableListOf()).apply {
            onCreateNotificationListener = { fragment ->
                setNotification(fragment.number)
            }
        }.also {
            notificationAdapter = it
        }

        registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.currentIndex = position
                binding.fragmentsCounter.setNumber(viewModel.getCurrentNumber())
            }
        })
    }

    private fun initData() {
        val currentItem = intent.getIntExtra(FRAGMENT_KEY, DEFAULT_FRAGMENT_NUMBER)
        viewModel.init(currentItem)
    }

    private fun initObservers() {
        viewModel.fragments.observe(this, Observer {
            notificationAdapter.updateData(it)
            binding.viewPager.setCurrentItem(viewModel.currentIndex, true)
            binding.fragmentsCounter.setNumber(viewModel.getCurrentNumber())
            binding.fragmentsCounter.setMinusVisibility(it.size > 1)
        })
    }

    private fun setNotification(fragmentsNumber: Int) {

        val notification = NotificationCompat.Builder(this, NOTIFICATION_ID)
            .setSmallIcon(R.drawable.blue)
            .setContentTitle(getString(R.string.notification_title))
            .setContentText(getString(R.string.notification_text, fragmentsNumber))
            .setContentIntent(createPendingIntent(fragmentsNumber))
            .build()

        createNotificationManager().notify(fragmentsNumber, notification)
    }

    private fun createNotificationManager(): NotificationManager {
        val notificationManager = (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_ID, getString(R.string.notification_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = getString(R.string.notification_channel_description)
            notificationManager.createNotificationChannel(channel)
        }

        return notificationManager
    }

    private fun createPendingIntent(fragmentsNumber: Int): PendingIntent {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(FRAGMENT_KEY, fragmentsNumber)
        }

        return PendingIntent.getActivity(this, fragmentsNumber, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    fun deleteNotification(fragmentsNumber: Int) {
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            .cancel(fragmentsNumber)
    }

    companion object {
        const val FRAGMENT_KEY = "fragment_key"
        const val NOTIFICATION_ID = "1"
        const val DEFAULT_FRAGMENT_NUMBER = -1
    }
}