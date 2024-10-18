package com.example.shareworkspace.core.di

import android.content.Context
import com.example.shareworkspace.data.data_source.local.prefs.PreferenceHelper
import com.example.shareworkspace.data.data_source.remote.interfaces.IWorkspaceRemoteDS
import com.example.shareworkspace.data.data_source.remote.network.ApiServices.Companion.createApiService
import com.example.shareworkspace.data.data_source.remote.network.AuthInterceptor
import com.example.shareworkspace.data.data_source.remote.network.RetrofitClient.provideOkHttpClient
import com.example.shareworkspace.data.data_source.remote.network.RetrofitClient.provideRetrofit
import com.example.shareworkspace.data.data_source.remote.remote_repository.WorkspaceRemoteDSImpl
import com.example.shareworkspace.domain.repository.IWorkspaceRepository
import com.example.shareworkspace.domain.repository.impl.WorkspaceRepositoryImpl
import com.example.shareworkspace.domain.usescase.GetNearByWorkspaceUseCase
import com.example.shareworkspace.domain.usescase.GetWorkspaceUseCase
import com.example.shareworkspace.presentation.authentication.AuthViewModel
import com.example.shareworkspace.presentation.main.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { AuthViewModel(get()) }
}

val useCaseModule = module {
    factory { GetWorkspaceUseCase(get()) }
    factory { GetNearByWorkspaceUseCase(get()) }
}
val repositoryModule = module {
    factory { WorkspaceRepositoryImpl(get(), get()) as IWorkspaceRepository }
}

val dataSourceModule = module {
    factory { WorkspaceRemoteDSImpl(get()) as IWorkspaceRemoteDS }
}

val sharedPreferencesModule = module {
    single { androidContext().getSharedPreferences("ShareSpaceApp", Context.MODE_PRIVATE) }
    single { PreferenceHelper(get()) }
}


val networkModule = module {
    single { AuthInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { createApiService(get()) }

}