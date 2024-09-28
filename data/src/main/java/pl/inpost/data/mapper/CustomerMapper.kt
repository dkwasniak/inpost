package pl.inpost.data.mapper

import pl.inpost.data.model.CustomerDto
import pl.inpost.domain.model.Customer

fun CustomerDto.toDomain(): Customer {
    return Customer(
        email = this.email,
        phoneNumber = this.phoneNumber,
        name = this.name
    )
}