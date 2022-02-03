package com.example.hsdemosoft.di

import android.content.Context
import com.example.hsdemosoft.data.repository.RepositoryImpl
import com.example.hsdemosoft.db.CountryRepository
import com.example.hsdemosoft.ui.main.ListFragmentViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DBModule::class,
        AppModule::class]
)
interface AppComponents {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun applicationContext(context: Context): Builder
        fun build(): AppComponents
    }

    fun inject(module: ListFragmentViewModel)
    fun inject(module: RepositoryImpl)
    fun inject(module: CountryRepository)
}