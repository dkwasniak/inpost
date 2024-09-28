package pl.inpost.recruitmenttask.mapper

import pl.inpost.domain.model.Customer
import pl.inpost.recruitmenttask.model.CustomerUiModel

fun Customer.toUiModel(): CustomerUiModel {
    return CustomerUiModel(
        email = this.email,
        phoneNumber = this.phoneNumber,
        name = this.name
    )
}