package com.yatatsu.expiresmemo.presentation.input

import com.yatatsu.expiresmemo.model.Expire
import com.yatatsu.expiresmemo.model.ExpiresRepository
import com.yatatsu.expiresmemo.presentation.input.EditContract.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.Date

class EditPresenter(private val expiresRepository: ExpiresRepository,
    private val editView: View,
    private val disposables: CompositeDisposable = CompositeDisposable()): EditContract.Presenter {

  private var id: String? = null

  override fun onViewAttached() {

  }

  override fun onViewDetached() {
    disposables.clear()
  }

  override fun setId(id: String?) {
    this.id = id
  }

  override fun loadExpire() {
    id?.let { expiresRepository.loadExpire(it)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ it?.let { editView.showExpire(it) } },
            { Timber.e(it, "error loading") })
        .let { disposables.add(it) }
    }
    editView.showExpire(Expire())
  }

  override fun saveExpire(expire: Expire, name: String?, description: String?, expiredAt: Date?) {
    val newData = expire.copy(name = name, description = description, expiredAt = expiredAt)

    // TODO validate data

    expiresRepository.saveExpire(newData)
        .subscribeOn(Schedulers.io())//FIXME
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ expire ->
          editView.showExpire(expire)
        }, { t ->
          Timber.e(t, "save expire error")
          // TODO show error message
        })
        .let { disposables.add(it) }
  }
}
