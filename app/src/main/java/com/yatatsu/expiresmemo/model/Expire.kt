package com.yatatsu.expiresmemo.model

import io.realm.RealmModel
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.Date;


@RealmClass
open class Expire(
    @PrimaryKey open var id: Long = 0,
    open var name: String? = null,
    open var description: String? = null,
    open var createdAt: Date? = null,
    open var expiredAt: Date? = null,
    @Ignore open var status: ExpireStatus = ExpireStatus.ALIVE
): RealmModel {
  open var statusName: String
    get() = status.name
    set(value) {
      ExpireStatus.valueOf(value)
    }
}