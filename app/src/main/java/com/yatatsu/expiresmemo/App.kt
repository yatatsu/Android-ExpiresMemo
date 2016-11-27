package com.yatatsu.expiresmemo

import android.app.Application
import android.content.Context
import io.realm.Realm

/**
 * Application
 */
class App : Application() {

  lateinit var appComponent: AppComponent

  override fun onCreate() {
    super.onCreate()
    Realm.init(this)
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