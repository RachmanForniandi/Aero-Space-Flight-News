package rachman.forniandi.core.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rachman.forniandi.core.data.network.NetworkService
import rachman.forniandi.core.data.remote.response.RemoteSourceData
import rachman.forniandi.core.domain.interactor.ArticlesInteractor
import rachman.forniandi.core.domain.interactor.BlogsInteractor
import rachman.forniandi.core.domain.usecase.ArticlesUseCase
import rachman.forniandi.core.domain.usecase.BlogsUseCase
import rachman.forniandi.core.repositories.ContentsRepository
import rachman.forniandi.core.repositories.ContentsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideArticleUseCase(articlesInteractor: ArticlesInteractor): ArticlesUseCase

    @Binds
    @Singleton
    abstract fun provideBlogUseCase(blogsInteractor: BlogsInteractor): BlogsUseCase

}