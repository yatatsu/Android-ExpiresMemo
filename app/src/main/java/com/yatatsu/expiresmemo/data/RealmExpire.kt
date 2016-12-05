package com.yatatsu.expiresmemo.data

import com.yatatsu.expiresmemo.model.Expire
import com.yatatsu.expiresmemo.model.ExpireStatus
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.Date
import java.util.UUID


@RealmClass
open class RealmExpire(
    @PrimaryKey open var id: String = UUID.randomUUID().toString(),
    open var name: String? = null,
    open var description: String? = null,
    open var createdAt: Date? = null,
    open var expiredAt: Date? = null,
    open var statusName: String = ExpireStatus.ALIVE.name
): RealmModel {
  open var status: ExpireStatus
    get() = ExpireStatus.valueOf(statusName)
    set(value) {
      statusName = value.name
    }

  fun data() = Expire(id, name, description, createdAt, expiredAt, status)

  companion object {
    fun fromData(data: Expire, now: Date): RealmExpire
        = RealmExpire(data.id?: UUID.randomUUID().toString(),
        data.name,
        data.description,
        data.createdAt?: now,
        data.expiredAt,
        data.status.name)
  }
}