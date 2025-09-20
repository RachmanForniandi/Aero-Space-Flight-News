package rachman.forniandi.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rachman.forniandi.aerospaceflightnews.domain.interactor.ArticlesInteractor
import rachman.forniandi.aerospaceflightnews.domain.interactor.BlogsInteractor
import rachman.forniandi.aerospaceflightnews.domain.useCase.ArticlesUseCase
import rachman.forniandi.aerospaceflightnews.domain.useCase.BlogsUseCase
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