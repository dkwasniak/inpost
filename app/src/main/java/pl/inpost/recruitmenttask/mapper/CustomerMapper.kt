package pl.inpost.recruitmenttask.mapper

import pl.inpost.domain.model.Customer
import pl.inpost.recruitmenttask.model.CustomerUiModel

fun Customer.toUiModel(): CustomerUiModel {
    return CustomerUiModel(
        name = this.name ?: this.email ?: phoneNumber
    )
}