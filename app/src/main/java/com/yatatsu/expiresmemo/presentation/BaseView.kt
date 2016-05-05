package com.yatatsu.expiresmemo.presentation


interface BaseView<T> {
  fun setPresenter(presenter: T)
}
