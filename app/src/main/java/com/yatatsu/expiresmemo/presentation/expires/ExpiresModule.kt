package com.yatatsu.expiresmemo.presentation.expires

import dagger.Module
import dagger.Provides

@Module class ExpiresModule(private val expiresView: ExpiresContract.View) {

  @Provides fun provideExpiresView(): ExpiresContract.View = expiresView
}