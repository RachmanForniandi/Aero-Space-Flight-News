package rachman.forniandi.favorite.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import rachman.forniandi.aerospaceflightnews.di.FavoriteContentModuleDependencies
import rachman.forniandi.favorite.ui.FavoriteContentFragment

@Component(
    dependencies = [FavoriteContentModuleDependencies::class]
)
interface FavoriteContentComponent {
    fun inject(favoriteContentFragment: FavoriteContentFragment)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteContentModuleDependencies: FavoriteContentModuleDependencies): Builder
        fun build(): FavoriteContentComponent
    }
}
