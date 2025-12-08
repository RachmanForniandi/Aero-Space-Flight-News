package rachman.forniandi.core.utilRemote

import rachman.forniandi.core.domain.entity.Contents

interface NavigationProvider {
    fun openArticleDetails(contents: Contents)
    fun openBlogDetails(contents: Contents)
}