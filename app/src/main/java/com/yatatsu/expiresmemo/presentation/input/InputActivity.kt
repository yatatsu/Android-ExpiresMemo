package com.yatatsu.expiresmemo.presentation.input

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yatatsu.expiresmemo.R
import com.yatatsu.expiresmemo.databinding.ActivityInputBinding
import com.yatatsu.expiresmemo.model.Expire
import io.realm.Realm
import java.util.Date

class InputActivity : AppCompatActivity() {

  private lateinit var binding: ActivityInputBinding
  private lateinit var realm: Realm

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_input)
    realm = Realm.getDefaultInstance()

    binding.buttonCreate.setOnClickListener {
      val expire = Expire(name = binding.name.text.toString(),
          description = binding.description.text.toString(),
          createdAt = Date(),
          expiredAt = Date())
      realm.executeTransactionAsync({
        it.copyToRealm(expire)
      }, {
        finish()
      }, null)
    }
  }

  override fun onDestroy() {
    realm.close()
    super.onDestroy()
  }
}
