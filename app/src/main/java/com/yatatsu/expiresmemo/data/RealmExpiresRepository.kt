package com.yatatsu.expiresmemo.data

import com.yatatsu.expiresmemo.model.Expire
import com.yatatsu.expiresmemo.model.ExpiresRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.CompletableEmitter
import io.reactivex.CompletableOnSubscribe
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe
import io.realm.Realm

class RealmExpiresRepository(private val realm: Realm) : ExpiresRepository {

  override fun aliveExpires(): Flowable<List<Expire>> {
    return Flowable.create({ e -> e.onNext(realm.where(Expire::class.java).findAll()) },
        BackpressureStrategy.LATEST)
  }

  override fun saveExpire(expire: Expire): Completable {
    return Completable.create { e ->
      val realm = Realm.getDefaultInstance()
      realm.copyFromRealm(expire)
      realm.close()
      e.onComplete()
    }
  }

  override fun close() {
    realm.close()
  }
}
