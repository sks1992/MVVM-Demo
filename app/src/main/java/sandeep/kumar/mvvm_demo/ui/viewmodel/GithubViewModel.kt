package sandeep.kumar.mvvm_demo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import sandeep.kumar.mvvm_demo.data.model.User
import sandeep.kumar.mvvm_demo.data.repo.GithubRepository

class GithubViewModel : ViewModel() {

    val users=MutableLiveData<List<User>>()

    fun fetchUser() {
        viewModelScope.launch{
            val response = withContext(Dispatchers.IO){GithubRepository.getUsers()}

            if (response.isSuccessful){
                response.body()?.let {
                    users.postValue(it)
                    /*we write in post value not in value because value run on main thread
                    * and postValue run on default thread*/
                }
            }
        }
    }

    fun searchUser(name:String) = liveData(Dispatchers.IO) {
        val response = withContext(Dispatchers.IO){GithubRepository.searchUser(name)}

        if (response.isSuccessful){
            response.body()?.let {
               emit(it.items)
            }
        }

    }
}



/* Extension function for  viewModelScope */

fun ViewModel.runIo(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    function: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch(dispatcher) {
        function()
    }
}

