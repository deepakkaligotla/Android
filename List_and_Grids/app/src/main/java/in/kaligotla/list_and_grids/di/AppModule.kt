package `in`.kaligotla.list_and_grids.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import `in`.kaligotla.list_and_grids.data.repository.DataRepositoryImpl
import `in`.kaligotla.list_and_grids.domain.repository.DataRepository

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {
    @Provides
    fun provideDataRepository(

    ): DataRepository = DataRepositoryImpl(
        isDataLoaded = true
    )
}