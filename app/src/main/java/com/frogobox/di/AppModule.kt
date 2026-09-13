package com.frogobox.di

import android.content.Context
import com.frogobox.appadmob.source.AdmobLocalDataSource
import com.frogobox.appadmob.source.AdmobRemoteDataSource
import com.frogobox.appadmob.source.AdmobRepository
import com.frogobox.appsdk.source.AppDatabase
import com.frogobox.appsdk.source.AppLocalDataSource
import com.frogobox.appsdk.source.AppRemoteDataSource
import com.frogobox.appsdk.source.AppRepository
import com.frogobox.appsdk.source.dao.ArticleDao
import com.frogobox.appsdk.util.AppConstant.PREF_NAME
import com.frogobox.sdk.delegate.preference.PreferenceDelegatesImpl
import com.frogobox.sdk.util.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePreferenceDelegates(@ApplicationContext context: Context): PreferenceDelegatesImpl {
        return PreferenceDelegatesImpl(context, PREF_NAME)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideArticleDao(database: AppDatabase): ArticleDao {
        return database.articleDao()
    }

    @Provides
    @Singleton
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors()
    }

    @Provides
    @Singleton
    fun provideAppLocalDataSource(
        appExecutors: AppExecutors,
        preferences: PreferenceDelegatesImpl,
        articleDao: ArticleDao
    ): AppLocalDataSource {
        return AppLocalDataSource(appExecutors, preferences, articleDao)
    }

    @Provides
    @Singleton
    fun provideAppRemoteDataSource(@ApplicationContext context: Context): AppRemoteDataSource {
        return AppRemoteDataSource(context)
    }

    @Provides
    @Singleton
    fun provideAppRepository(
        @ApplicationContext context: Context,
        remoteDataSource: AppRemoteDataSource,
        localDataSource: AppLocalDataSource
    ): AppRepository {
        return AppRepository(context, remoteDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun provideAdmobLocalDataSource(
        appExecutors: AppExecutors,
        preferences: PreferenceDelegatesImpl
    ): AdmobLocalDataSource {
        return AdmobLocalDataSource(appExecutors, preferences)
    }

    @Provides
    @Singleton
    fun provideAdmobRemoteDataSource(): AdmobRemoteDataSource {
        return AdmobRemoteDataSource()
    }

    @Provides
    @Singleton
    fun provideAdmobRepository(
        remoteDataSource: AdmobRemoteDataSource,
        localDataSource: AdmobLocalDataSource
    ): AdmobRepository {
        return AdmobRepository(remoteDataSource, localDataSource)
    }

}
