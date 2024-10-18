package com.example.shareworkspace

import android.app.Application
import com.example.shareworkspace.core.di.dataSourceModule
import com.example.shareworkspace.core.di.networkModule
import com.example.shareworkspace.core.di.repositoryModule
import com.example.shareworkspace.core.di.sharedPreferencesModule
import com.example.shareworkspace.core.di.useCaseModule
import com.example.shareworkspace.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
//import org.koin.android.ext.koin.androidContext
//import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class ShareWorkSpaceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initDI(this)
    }

    private fun initDI(app: Application) {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(app)
            modules(
                listOf(
                    sharedPreferencesModule,
                    viewModelModule,
                    useCaseModule,
                    repositoryModule,
                    dataSourceModule,
                    networkModule
                )
            )
        }
    }
}