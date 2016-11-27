package com.yatatsu.expiresmemo.presentation.expires

import com.yatatsu.expiresmemo.model.ExpiresRepository
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class ExpiresPresenter(private val expiresRepository: ExpiresRepository,
    private val expiresView: ExpiresContract.View,
    private val disposables: CompositeDisposable) : ExpiresContract.Presenter {

  override fun loadExpires() {
    val disposable = expiresRepository.aliveExpires()
        .subscribe({ expires -> expiresView.showExpires(expires)},
            {e -> Timber.e(e, "error in loadExpires")})
    disposable.dispose()
    disposables.add(disposable)
  }

  override fun start() {
  }

  override fun stop() {
    disposables.clear()
  }

  override fun destroy() {
    disposables.dispose()
    expiresRepository.close()
  }
}
