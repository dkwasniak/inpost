package pl.inpost.data.mapper

import pl.inpost.data.model.OperationsDto
import pl.inpost.domain.model.Operations

fun OperationsDto.toDomain(): Operations {
    return Operations(
        manualArchive = this.manualArchive,
        delete = this.delete,
        collect = this.collect,
        highlight = this.highlight,
        expandAvizo = this.expandAvizo,
        endOfWeekCollection = this.endOfWeekCollection
    )
}