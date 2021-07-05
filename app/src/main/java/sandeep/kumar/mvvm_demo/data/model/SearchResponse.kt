package sandeep.kumar.mvvm_demo.data.model

data class SearchResponse(
    val totalCount: Int? = null,
    val incompleteResults: Boolean? = null,
    val items: List<User>? = null
)