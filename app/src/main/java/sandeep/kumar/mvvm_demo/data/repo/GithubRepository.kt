package sandeep.kumar.mvvm_demo.data.repo

import sandeep.kumar.mvvm_demo.data.api.Client

object GithubRepository {

    suspend fun getUsers() = Client.api.getUser()

    suspend fun searchUser(name: String) = Client.api.searchUser(name)
}