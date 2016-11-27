package com.yatatsu.expiresmemo

import android.content.Context
import dagger.Module
import dagger.Provides
import io.realm.Realm

@Module
class AppModule(app: App) {

  private val context: Context

  init {
    context = app
  }

  @Provides
  fun provideContext(): Context = context

  @Provides
  fun provideRealm(): Realm = Realm.getDefaultInstance()
}