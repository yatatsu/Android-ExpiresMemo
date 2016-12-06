package com.yatatsu.expiresmemo.data

import com.yatatsu.expiresmemo.model.Expire
import com.yatatsu.expiresmemo.model.ExpireStatus
import com.yatatsu.expiresmemo.model.ExpiresRepository
import io.reactivex.BackpressureStrategy.LATEST
import io.reactivex.Flowable
import io.reactivex.Single
import io.realm.RealmConfiguration

class RealmExpiresRepository(private val config: RealmConfiguration, private val dateProvider: DateProvider)
  : ExpiresRepository {

  override fun aliveExpires(): Flowable<List<Expire>> {
    return Flowable.create({ e ->
      config.getUse { realm ->
        realm.where(RealmExpire::class.java)
            .equalTo(RealmExpire::statusName.name, ExpireStatus.ALIVE.name)
            .findAll()
            .map { it.data() }
            .let {
              e.onNext(it)
              e.onComplete()
            }
      }
    }, LATEST)
  }

  override fun saveExpire(expire: Expire): Single<Expire> {
    return Single.create { e ->
      config.getUse { realm ->
        val entity = RealmExpire.fromData(expire, dateProvider.now())
        realm.executeTransaction {
          it.copyToRealmOrUpdate(entity)
        }
        e.onSuccess(entity.data())
      }
    }
  }

  override fun loadExpire(id: String): Flowable<Expire?> {
    return Flowable.create({ e ->
      config.getUse { realm ->
        realm.where(RealmExpire::class.java)
            .equalTo(RealmExpire::id.name, id)
            .findFirstOrNull()?.data()
            .let {
              e.onNext(it)
              e.onComplete()
            }
      }
    }, LATEST)
  }

}