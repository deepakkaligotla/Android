package `in`.kaligotla.datastructures.di

import android.app.Application
import android.app.Service
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import `in`.kaligotla.datastructures.core.Constants.EXTRAS_RETROFIT_BUILD
import `in`.kaligotla.datastructures.core.Constants.LOCATION_REMOTE_DATA_SOURCE
import `in`.kaligotla.datastructures.core.Constants.LOCATION_SERVICE
import `in`.kaligotla.datastructures.core.LoadDataService
import `in`.kaligotla.datastructures.data.local.AppDatabase
import `in`.kaligotla.datastructures.data.local.LocationDao
import `in`.kaligotla.datastructures.data.remote.LocationRemoteDataSource
import `in`.kaligotla.datastructures.data.remote.LocationService
import `in`.kaligotla.datastructures.data.repository.LocationRepository
import `in`.kaligotla.datastructures.data.repository.LocationRepositoryImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    @Named(EXTRAS_RETROFIT_BUILD)
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl("http://192.168.0.14:3000/")
        .client(
            OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS).build()
        )
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Named(LOCATION_SERVICE)
    fun provideLocationService(
        @Named(EXTRAS_RETROFIT_BUILD)
        retrofit: Retrofit
    ): LocationService =
        retrofit.create(LocationService::class.java)

    @Provides
    @Named(LOCATION_REMOTE_DATA_SOURCE)
    fun provideLocationRemoteDataSource(
        @Named(LOCATION_SERVICE)
        locationService: LocationService
    ) =
        LocationRemoteDataSource(locationService)

    @Provides
    fun provideDatabase(appContext: Application) =
        AppDatabase.getDatabase(appContext)

    @Provides
    fun loadDataService(): Service = LoadDataService()

    @Provides
    fun provideLocationDao(db: AppDatabase) = db.DBLocationDao()

    @RequiresApi(Build.VERSION_CODES.Q)
    @Provides
    fun provideRepository(
        loadDataService: LoadDataService,
        @Named(LOCATION_REMOTE_DATA_SOURCE)
        remoteDataSource: LocationRemoteDataSource,
        localDataSource: LocationDao,
    ): LocationRepository = LocationRepositoryImpl(
        loadDataService, remoteDataSource, localDataSource
    )
}