package com.yatatsu.expiresmemo.presentation.input

import com.yatatsu.expiresmemo.model.ExpiresRepository
import com.yatatsu.expiresmemo.presentation.ActivityScope
import dagger.Module
import dagger.Provides


@Module class EditModule(private val editView: EditContract.View) {

  @ActivityScope @Provides fun provideEditView(): EditContract.View = editView

  @ActivityScope @Provides fun provideEditPresenter(repo: ExpiresRepository,
      view: EditContract.View): EditContract.Presenter {
    return EditPresenter(repo, view)
  }
}