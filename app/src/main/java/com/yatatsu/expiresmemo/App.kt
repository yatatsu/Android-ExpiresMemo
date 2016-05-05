package com.yatatsu.expiresmemo

import android.app.Application
import android.content.Context

/**
 * Application
 */
class App : Application() {

  lateinit var appComponent: AppComponent

  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
  }

  /**
   * return app instance from context
   *
   * @return App
   */
  fun get(context: Context) = context.applicationContext as App
}