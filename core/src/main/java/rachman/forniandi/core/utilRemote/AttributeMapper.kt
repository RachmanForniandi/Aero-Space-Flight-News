package rachman.forniandi.core.utilRemote


import rachman.forniandi.core.data.remote.response.ResultsItem
import rachman.forniandi.core.domain.entity.AuthorContents
import rachman.forniandi.core.domain.entity.Contents

fun List<ResultsItem>.toContentsEntity()=map {
    Contents(
        it.id,
        it.title,
        it.authors as List<AuthorContents>,
        it.url,
        it.imageUrl,
        it.newsSite,
        it.summary,
        it.publishedAt,
        it.updatedAt
    )
}

fun ResultsItem.toDetailContentsEntity()= Contents(id,title,authors as List<AuthorContents>,url,imageUrl,newsSite,summary,publishedAt,updatedAt)