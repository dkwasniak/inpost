package pl.inpost.recruitmenttask.mapper

import pl.inpost.domain.model.Operations
import pl.inpost.recruitmenttask.model.OperationsUiModel

fun Operations.toUiModel(): OperationsUiModel {
    return OperationsUiModel(
        manualArchive = this.manualArchive,
        delete = this.delete,
        collect = this.collect,
        highlight = this.highlight,
        expandAvizo = this.expandAvizo,
        endOfWeekCollection = this.endOfWeekCollection
    )
}