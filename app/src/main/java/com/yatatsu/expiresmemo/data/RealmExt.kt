package com.yatatsu.expiresmemo.data

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmModel
import io.realm.RealmQuery


inline fun RealmConfiguration.getUse(using: (realm: Realm) -> Unit) {
  Realm.getInstance(this).use { using(it) }
}

fun <E: RealmModel> RealmQuery<E>.findFirstOrNull(): E? = findAll().firstOrNull()