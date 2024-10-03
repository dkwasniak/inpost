package pl.inpost.recruitmenttask.model

import androidx.annotation.StringRes
import pl.inpost.recruitmenttask.R


sealed class DetailsStatusUiModel(@StringRes val resId: Int, open val formattedDate: String) {

    data class AwaitingCollection(override val formattedDate: String) :
        DetailsStatusUiModel(R.string.details_status_awaiting_collection, formattedDate)

    data class Delivered(override val formattedDate: String) :
        DetailsStatusUiModel(R.string.details_status_delivered, formattedDate)
}


