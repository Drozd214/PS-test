package com.oleksandrkarpiuk.fragmentwithnotification.components.rectangle_counter

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.oleksandrkarpiuk.fragmentwithnotification.databinding.RectangleCounterBinding

class RectangleCounter @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: RectangleCounterBinding = RectangleCounterBinding.inflate(LayoutInflater.from(context), this, true)
    private var number: Int = 0

    fun setup(
        number: Int,
        callback: CounterChangeListener
    ) {
        this.number = number
        initButtons(callback)
    }

    private fun initButtons(callback: CounterChangeListener) {
        binding.plusButton.setOnClickListener {
            callback.onPlusClicked()
        }
        binding.minusButton.setOnClickListener {
            callback.onMinusClicked()
        }
    }

    fun setNumber(number: Int) {
        this.number = number
        binding.counterTextView.text = this.number.toString()
    }

    fun setMinusVisibility(isMinusButtonVisible: Boolean) {
        binding.minusButton.isVisible = isMinusButtonVisible
    }

    interface CounterChangeListener {
        fun onPlusClicked()
        fun onMinusClicked()
    }

}