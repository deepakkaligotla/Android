package `in`.kaligotla.list_and_grids.domain.repository

import `in`.kaligotla.list_and_grids.domain.model.Response

typealias DataResponse = Response<Boolean>

interface DataRepository {
    val isDataLoaded: Boolean
}