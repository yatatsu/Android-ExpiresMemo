package com.yatatsu.expiresmemo.presentation.input

import com.yatatsu.expiresmemo.presentation.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(EditModule::class))
interface EditComponent {
  fun inject(activity: EditActivity)
}
