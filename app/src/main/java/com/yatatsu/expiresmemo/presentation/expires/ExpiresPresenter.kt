package com.yatatsu.expiresmemo.presentation.expires

import com.yatatsu.expiresmemo.data.ExpiresRepository
import javax.inject.Inject

class ExpiresPresenter
@Inject
constructor(private val expiresRepository: ExpiresRepository,
    expiresView: ExpiresContract.View) : ExpiresContract.Presenter {


  override fun loadExpires() {

  }

  override fun start() {

  }
}
