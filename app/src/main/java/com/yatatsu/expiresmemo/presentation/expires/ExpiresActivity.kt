package com.yatatsu.expiresmemo.presentation.expires

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import com.yatatsu.expiresmemo.App
import com.yatatsu.expiresmemo.R
import com.yatatsu.expiresmemo.databinding.ActivityExpiresBinding
import com.yatatsu.expiresmemo.databinding.ListItemExpiresBinding
import com.yatatsu.expiresmemo.model.Expire
import com.yatatsu.expiresmemo.presentation.input.InputActivity
import com.yatatsu.expiresmemo.presentation.widget.BindingViewHolder
import java.util.ArrayList
import javax.inject.Inject

class ExpiresActivity : AppCompatActivity(), ExpiresContract.View {

  override fun showExpires(expireList: List<Expire>) {
    adapter.items = expireList
    adapter.notifyDataSetChanged()
  }

  @Inject lateinit var presenter: ExpiresContract.Presenter
  private lateinit var binding: ActivityExpiresBinding
  private lateinit var adapter: ExpiresAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_expires)
    setSupportActionBar(binding.toolbar)

    App.get(this).appComponent.plus(ExpiresModule(this)).inject(this)

    adapter = ExpiresAdapter(layoutInflater)
    binding.content.recyclerView.adapter = adapter
    binding.content.recyclerView.layoutManager = LinearLayoutManager(this)
    binding.fab.setOnClickListener({ view ->
      startActivity(Intent(this, InputActivity::class.java))
    })
  }

  override fun onStart() {
    super.onStart()
    presenter.start()
    presenter.loadExpires()
  }

  override fun onStop() {
    presenter.stop()
    super.onStop()
  }

  override fun onDestroy() {
    presenter.destroy()
    super.onDestroy()
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    val id = item.itemId

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true
    }

    return super.onOptionsItemSelected(item)
  }

  private class ExpiresAdapter(val inflater: LayoutInflater)
    : RecyclerView.Adapter<BindingViewHolder<ListItemExpiresBinding>>() {

    internal var items: List<Expire> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?,
        viewType: Int): BindingViewHolder<ListItemExpiresBinding> {
      return BindingViewHolder(inflater, parent!!, R.layout.list_item_expires)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ListItemExpiresBinding>?,
        position: Int) {
      holder?.binding?.expire = items[position]
      holder?.binding?.executePendingBindings()
    }

    override fun getItemCount(): Int {
      return items.size
    }

  }
}
