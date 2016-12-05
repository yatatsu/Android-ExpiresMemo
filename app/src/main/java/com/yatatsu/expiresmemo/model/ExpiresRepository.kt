package com.yatatsu.expiresmemo.model

import io.reactivex.Flowable
import io.reactivex.Single

interface ExpiresRepository {

  fun aliveExpires(): Flowable<List<Expire>>

  fun saveExpire(expire: Expire): Single<Expire>

  fun loadExpire(id: String): Flowable<Expire?>
}
