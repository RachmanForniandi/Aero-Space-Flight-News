package rachman.forniandi.aerospaceflightnews.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rachman.forniandi.core.domain.usecase.FavoriteContentUseCase

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteContentModuleDependencies {
    fun provideFavoriteContentUseCase(): FavoriteContentUseCase
}