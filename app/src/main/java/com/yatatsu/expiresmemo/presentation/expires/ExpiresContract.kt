package com.yatatsu.expiresmemo.presentation.expires

import com.yatatsu.expiresmemo.model.Expire
import com.yatatsu.expiresmemo.presentation.BasePresenter
import com.yatatsu.expiresmemo.presentation.BaseView

interface ExpiresContract {

  interface View : BaseView<Presenter> {
    fun showExpires(expireList: List<Expire>)
  }

  interface Presenter : BasePresenter {
    fun loadExpires()
  }
}
