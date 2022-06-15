package com.oleksandrkarpiuk.fragmentwithnotification.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oleksandrkarpiuk.fragmentwithnotification.R
import com.oleksandrkarpiuk.fragmentwithnotification.ui.models.FragmentModel
import kotlinx.android.synthetic.main.fragment_notification.view.*

class NotificationAdapter(
    fragments: List<FragmentModel>
): RecyclerView.Adapter<NotificationHolder>() {

    var fragments = fragments
    var onCreateNotificationListener: ((FragmentModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        return NotificationHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_notification, parent, false))
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        holder.setOnCreateNotificationListener(
            fragments[position],
            onCreateNotificationListener
        )
    }

    fun updateData(fragments: List<FragmentModel>) {
        this.fragments = fragments
        this.notifyDataSetChanged()
    }

}

class NotificationHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun setOnCreateNotificationListener(fragment: FragmentModel,
                                        listener: ((FragmentModel) -> Unit)?) {
        itemView.createNotificationButton.setOnClickListener {
            listener?.invoke(fragment)
        }
    }

}