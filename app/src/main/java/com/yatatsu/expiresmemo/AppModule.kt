package com.yatatsu.expiresmemo

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(app: App) {

  private val context: Context

  init {
    context = app
  }

  @Provides
  fun provideContext(): Context = context
}