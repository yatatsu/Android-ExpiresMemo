package com.yatatsu.expiresmemo.presentation.input

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yatatsu.expiresmemo.App
import com.yatatsu.expiresmemo.R
import com.yatatsu.expiresmemo.databinding.ActivityInputBinding
import com.yatatsu.expiresmemo.model.Expire
import com.yatatsu.expiresmemo.presentation.getStringExtraOrNull
import java.util.Date
import javax.inject.Inject

class EditActivity : AppCompatActivity(), EditContract.View {

  @Inject lateinit var presenter: EditContract.Presenter
  private lateinit var binding: ActivityInputBinding

  private var loaded: Boolean = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_input)
    App.get(this).appComponent.plus(EditModule(this)).inject(this)
    intent.getStringExtraOrNull("id").let {
      presenter.setId(it)
    }
    binding.buttonCreate.setOnClickListener {
      presenter.saveExpire(binding.expire,
          binding.name.text.toString(),
          binding.description.text.toString(),
          Date())
    }
  }

  override fun onStart() {
    super.onStart()
    presenter.onViewAttached()
    if (!loaded) {
      presenter.loadExpire()
    }
  }

  override fun onStop() {
    presenter.onViewDetached()
    super.onStop()
  }

  override fun onSaveExpire() {
    finish()
  }

  override fun showExpire(expire: Expire) {
    binding.expire = expire
    loaded = true
  }
}
