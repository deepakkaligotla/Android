package `in`.kaligotla.list_and_grids.data.repository

import `in`.kaligotla.list_and_grids.domain.model.Response.Failure
import `in`.kaligotla.list_and_grids.domain.model.Response.Success
import `in`.kaligotla.list_and_grids.domain.repository.DataRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepositoryImpl @Inject constructor(
    override val isDataLoaded: Boolean
) : DataRepository {

}