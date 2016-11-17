package com.yatatsu.expiresmemo.presentation.expires

import com.yatatsu.expiresmemo.presentation.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(ExpiresModule::class))
interface ExpiresComponent {
  fun inject(expiresView: ExpiresContract.View)
}