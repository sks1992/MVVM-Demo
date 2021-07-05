package sandeep.kumar.mvvm_demo.ui.view

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import sandeep.kumar.mvvm_demo.R
import sandeep.kumar.mvvm_demo.data.model.User
import sandeep.kumar.mvvm_demo.ui.adapter.UsersAdapter
import sandeep.kumar.mvvm_demo.ui.viewmodel.GithubViewModel

class MainActivity : AppCompatActivity() {

    val vm by lazy {
        ViewModelProvider(this).get(GithubViewModel::class.java)
    }

    private val list = arrayListOf<User>()
    val originalList = arrayListOf<User>()
    private val adapter = UsersAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        search_view.isSubmitButtonEnabled = true

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    findUsers(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    findUsers(it)
                }
                return true            }

        })

        search_view.setOnCloseListener {
            list.clear()
            list.addAll(originalList)
            adapter.notifyDataSetChanged()

            /*
            vm.users.value?.let {
                list.addAll(it)
            }*/
            return@setOnCloseListener true
        }

        vm.fetchUser()

        vm.users.observe(this, Observer {

            if (!it.isNullOrEmpty()) {
                list.addAll(it)
                originalList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun findUsers(query: String) {

        vm.searchUser(query).observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }
}