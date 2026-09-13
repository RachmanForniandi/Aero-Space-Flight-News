package rachman.forniandi.aerospaceflightnews.util

import androidx.navigation.NavController
import rachman.forniandi.aerospaceflightnews.uiPresentation.articles.ArticlesFragmentDirections
import rachman.forniandi.aerospaceflightnews.uiPresentation.blogs.BlogsFragmentDirections
import rachman.forniandi.core.domain.entity.Contents
import rachman.forniandi.core.utilRemote.NavigationProvider
import javax.inject.Inject

class NavigationProviderImpl @Inject constructor(
    private val navController: NavController
) : NavigationProvider {

    override fun openArticleDetails(contents: Contents) {
        val action = ArticlesFragmentDirections
            .actionArticlesFragmentToArticleDetailsFragment(contents)
        navController.navigate(action)
    }

    override fun openBlogDetails(contents: Contents) {
        val action = BlogsFragmentDirections
            .actionBlogsFragmentToDetailBlogsFragment(contents)
        navController.navigate(action)
    }
}