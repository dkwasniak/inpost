package pl.inpost.data.model

import androidx.room.Entity

@Entity(tableName = "customers")
data class CustomerDto(
    val email: String?,
    val phoneNumber: String?,
    val name: String?
)
