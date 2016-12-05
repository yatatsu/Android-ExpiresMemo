package com.yatatsu.expiresmemo

import com.yatatsu.expiresmemo.presentation.expires.ExpiresComponent
import com.yatatsu.expiresmemo.presentation.expires.ExpiresModule
import com.yatatsu.expiresmemo.presentation.input.EditComponent
import com.yatatsu.expiresmemo.presentation.input.EditModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
  fun plus(expiresModule: ExpiresModule): ExpiresComponent

  fun plus(editModule: EditModule): EditComponent
}