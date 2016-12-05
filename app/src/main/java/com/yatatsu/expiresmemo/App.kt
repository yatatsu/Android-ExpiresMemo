package com.yatatsu.expiresmemo

import android.app.Application
import android.content.Context
import io.realm.Realm
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Application
 */
open class App : Application() {

  lateinit open var appComponent: AppComponent

  override fun onCreate() {
    super.onCreate()
    Realm.init(this)
    Timber.plant(DebugTree())
    appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
  }

  companion object {
    /**
     * return app instance from context
     *
     * @return App
     */
    fun get(context: Context) = context.applicationContext as App
  }
}