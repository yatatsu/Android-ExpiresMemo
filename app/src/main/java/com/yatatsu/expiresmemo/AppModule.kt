package com.yatatsu.expiresmemo

import android.content.Context
import com.yatatsu.expiresmemo.data.RealmExpiresRepository
import com.yatatsu.expiresmemo.model.ExpiresRepository
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
class AppModule(app: App) {

  private val context: Context

  init {
    context = app
  }

  @Provides
  fun provideContext(): Context = context

  @Singleton @Provides
  fun provideRealmConfiguration(): RealmConfiguration {
    return RealmConfiguration.Builder()
        // FIXME
        .deleteRealmIfMigrationNeeded()
        .build()
        .apply { Realm.setDefaultConfiguration(this) }
  }

  @Provides
  fun provideRealm(config: RealmConfiguration): Realm = Realm.getInstance(config)

  @Provides fun provideExpiresRepository(config: RealmConfiguration): ExpiresRepository
      = RealmExpiresRepository(config)
}