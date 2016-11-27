package com.yatatsu.expiresmemo.presentation.widget

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


open class BindingViewHolder<out T : ViewDataBinding>(inflater: LayoutInflater, parent: ViewGroup,
    @LayoutRes layoutResId: Int) : RecyclerView.ViewHolder(
    inflater.inflate(layoutResId, parent, false)) {

  val binding: T

  init {
    binding = DataBindingUtil.bind<T>(itemView)
  }
}
