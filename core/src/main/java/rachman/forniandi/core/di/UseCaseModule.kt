package rachman.forniandi.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rachman.forniandi.core.domain.interactor.ArticlesInteractor
import rachman.forniandi.core.domain.interactor.BlogsInteractor
import rachman.forniandi.core.domain.interactor.FavoriteContentsInteractor
import rachman.forniandi.core.domain.usecase.ArticlesUseCase
import rachman.forniandi.core.domain.usecase.BlogsUseCase
import rachman.forniandi.core.domain.usecase.FavoriteContentUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindArticlesUseCase(
        articlesInteractor: ArticlesInteractor
    ): ArticlesUseCase

    @Binds
    abstract fun bindBlogsUseCase(
        blogsInteractor: BlogsInteractor
    ): BlogsUseCase

    @Binds
    abstract fun bindFavoriteContentUseCase(
        favoriteContentsInteractor: FavoriteContentsInteractor
    ): FavoriteContentUseCase

}