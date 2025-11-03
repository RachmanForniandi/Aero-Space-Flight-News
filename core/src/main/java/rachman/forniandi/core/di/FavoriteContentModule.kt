package rachman.forniandi.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rachman.forniandi.core.domain.interactor.FavoriteContentsInteractor
import rachman.forniandi.core.domain.usecase.FavoriteContentUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoriteContentModule {

    @Binds
    @Singleton
    abstract fun bindFavoriteContentUseCase(
        favoriteContentsInteractor: FavoriteContentsInteractor
    ): FavoriteContentUseCase

}