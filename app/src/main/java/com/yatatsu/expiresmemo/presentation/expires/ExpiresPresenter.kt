package com.yatatsu.expiresmemo.presentation.expires

import com.yatatsu.expiresmemo.model.ExpiresRepository
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class ExpiresPresenter(private val expiresRepository: ExpiresRepository,
    private val expiresView: ExpiresContract.View,
    private val disposables: CompositeDisposable = CompositeDisposable()) : ExpiresContract.Presenter {

  override fun loadExpires() {
    val disposable = expiresRepository.aliveExpires()
        .subscribe({ expires -> expiresView.showExpires(expires) },
            { e -> Timber.e(e, "error in loadExpires") })
    disposables.add(disposable)
  }

  override fun onViewAttached() {
    loadExpires()
  }

  override fun onViewDetached() {
    disposables.clear()
  }
}
