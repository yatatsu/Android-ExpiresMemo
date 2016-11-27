package com.yatatsu.expiresmemo.presentation.expires

import android.util.Log
import com.yatatsu.expiresmemo.model.ExpiresRepository
import io.reactivex.disposables.CompositeDisposable

class ExpiresPresenter(private val expiresRepository: ExpiresRepository,
    private val expiresView: ExpiresContract.View) : ExpiresContract.Presenter {

  private var disposables: CompositeDisposable = CompositeDisposable()

  override fun loadExpires() {
    val disposable = expiresRepository.aliveExpires()
        .subscribe({ expires -> expiresView.showExpires(expires)},
            {e -> Log.e("error", "log" + e)})
    disposables.add(disposable)
  }

  override fun start() {
  }

  override fun stop() {
    disposables.clear()
  }

  override fun destroy() {
    expiresRepository.close()
  }
}
