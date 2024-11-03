package com.frogobox.coreutil.movie.model

data class PeopleChangeItem(
    val action: String?,
    val id: String?,
    val original_value: com.frogobox.coreutil.movie.model.PeopleChangeOriginalValue?,
    val time: String?
)