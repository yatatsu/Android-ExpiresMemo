package com.yatatsu.expiresmemo.presentation.input

import com.yatatsu.expiresmemo.model.Expire
import com.yatatsu.expiresmemo.presentation.BasePresenter
import com.yatatsu.expiresmemo.presentation.BaseView
import java.util.Date

interface EditContract {

  interface View : BaseView<Presenter> {

    fun showExpire(expire: Expire)

    fun onSaveExpire()
  }

  interface Presenter : BasePresenter {

    fun setId(id: String?)

    fun loadExpire()

    fun saveExpire(expire: Expire,
        name: String? = expire.name,
        description: String? = expire.description,
        expiredAt: Date? = expire.expiredAt)
  }
}