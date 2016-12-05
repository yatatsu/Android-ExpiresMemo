package com.yatatsu.expiresmemo.presentation.expires

import com.yatatsu.expiresmemo.model.ExpiresRepository
import com.yatatsu.expiresmemo.presentation.ActivityScope
import dagger.Module
import dagger.Provides

@Module class ExpiresModule(private val expiresView: ExpiresContract.View) {

  @ActivityScope @Provides fun provideExpiresView(): ExpiresContract.View = expiresView

  @ActivityScope @Provides fun provideExpiresPresenter(repo: ExpiresRepository,
      view: ExpiresContract.View): ExpiresContract.Presenter {
    return ExpiresPresenter(repo, view)
  }
}