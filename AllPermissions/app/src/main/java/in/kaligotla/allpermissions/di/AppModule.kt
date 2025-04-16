package `in`.kaligotla.allpermissions.di

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
import `in`.kaligotla.allpermissions.core.Constants.EXTRAS_RETROFIT_BUILD
import `in`.kaligotla.allpermissions.core.Constants.PERMISSION_REMOTE_DATA_SOURCE
import `in`.kaligotla.allpermissions.core.Constants.PERMISSION_SERVICE
import `in`.kaligotla.allpermissions.core.LoadDataService
import `in`.kaligotla.allpermissions.data.local.AppDatabase
import `in`.kaligotla.allpermissions.data.local.PermissionDao
import `in`.kaligotla.allpermissions.data.remote.PermissionRemoteDataSource
import `in`.kaligotla.allpermissions.data.remote.PermissionService
import `in`.kaligotla.allpermissions.data.repository.permission.PermissionRepository
import `in`.kaligotla.allpermissions.data.repository.permission.PermissionRepositoryImpl
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
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
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
    @Named(PERMISSION_SERVICE)
    fun providePermissionService(
        @Named(EXTRAS_RETROFIT_BUILD)
        retrofit: Retrofit
    ): PermissionService =
        retrofit.create(PermissionService::class.java)

    @Provides
    @Named(PERMISSION_REMOTE_DATA_SOURCE)
    fun providePermissionRemoteDataSource(
        @Named(PERMISSION_SERVICE)
        permissionService: PermissionService
    ) =
        PermissionRemoteDataSource(permissionService)

    @Provides
    fun provideDatabase(appContext: Application) =
        AppDatabase.getDatabase(appContext)

    @Provides
    fun loadDataService(): Service = LoadDataService()

    @Provides
    fun providePermissionDao(db: AppDatabase) = db.dBPermissionDao()

    @RequiresApi(Build.VERSION_CODES.Q)
    @Provides
    fun provideRepository(
        loadDataService: LoadDataService,
        @Named(PERMISSION_REMOTE_DATA_SOURCE)
        remoteDataSource: PermissionRemoteDataSource,
        localDataSource: PermissionDao,
    ): PermissionRepository = PermissionRepositoryImpl(
        loadDataService, remoteDataSource, localDataSource
    )
}