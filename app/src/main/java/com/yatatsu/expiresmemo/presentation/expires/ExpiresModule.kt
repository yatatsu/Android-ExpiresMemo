package com.yatatsu.expiresmemo.presentation.expires

import com.yatatsu.expiresmemo.data.RealmExpiresRepository
import com.yatatsu.expiresmemo.model.ExpiresRepository
import dagger.Module
import dagger.Provides
import io.realm.Realm

@Module class ExpiresModule(private val expiresView: ExpiresContract.View) {

  @Provides fun provideExpiresView(): ExpiresContract.View = expiresView

  @Provides fun provideExpiresRepository(realm: Realm): ExpiresRepository {
    return RealmExpiresRepository(realm)
  }

  @Provides fun provideExpiresPresenter(repo: ExpiresRepository,
      view: ExpiresContract.View): ExpiresContract.Presenter {
    return ExpiresPresenter(repo, view)
  }
}