package com.yatatsu.expiresmemo.model

import io.reactivex.Completable
import io.reactivex.Flowable

interface ExpiresRepository {

  fun aliveExpires(): Flowable<List<Expire>>

  fun saveExpire(expire: Expire): Completable

  fun close()
}
