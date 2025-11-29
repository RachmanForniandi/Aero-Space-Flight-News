package rachman.forniandi.aerospaceflightnews.di

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import rachman.forniandi.aerospaceflightnews.util.NavigationProviderImpl
import rachman.forniandi.core.utilRemote.NavigationProvider

@Module
@InstallIn(FragmentComponent::class)
object NavigationModule {
    @Provides
    fun provideNavigationProvider(
        fragment: Fragment
    ): NavigationProvider {
        return NavigationProviderImpl(
            NavHostFragment.findNavController(fragment)
        )
    }
}