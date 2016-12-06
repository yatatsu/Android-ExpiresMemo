package com.yatatsu.expiresmemo.presentation.input

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yatatsu.autobundle.AutoBundle
import com.yatatsu.autobundle.AutoBundleField
import com.yatatsu.expiresmemo.App
import com.yatatsu.expiresmemo.R
import com.yatatsu.expiresmemo.databinding.ActivityInputBinding
import com.yatatsu.expiresmemo.model.Expire
import java.util.Date
import javax.inject.Inject

class EditActivity : AppCompatActivity(), EditContract.View {

  @Inject lateinit var presenter: EditContract.Presenter
  private lateinit var binding: ActivityInputBinding

  @AutoBundleField(required = false) var loaded: Boolean = false
  @AutoBundleField(required = false) var expireId: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_input)
    App.get(this).appComponent.plus(EditModule(this)).inject(this)

    AutoBundle.bind(this, intent)
    savedInstanceState?.let { AutoBundle.bind(this, it) }
    presenter.setId(expireId)

    binding.buttonCreate.setOnClickListener {
      presenter.saveExpire(binding.expire,
          binding.name.text.toString(),
          binding.description.text.toString(),
          Date())
    }
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)
    AutoBundle.pack(this, outState)
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
