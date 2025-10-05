package rachman.forniandi.core.utilRemote


import rachman.forniandi.core.data.remote.response.ResultsItem
import rachman.forniandi.core.domain.entity.AuthorContents
import rachman.forniandi.core.domain.entity.Contents

fun List<ResultsItem>.toContentsEntity()=map { result ->
    Contents(
        result.id,
        result.title,
        result.authors?.map { authorsItem ->
            AuthorContents(
                name = authorsItem?.name
            )
        },
        result.url,
        result.imageUrl,
        result.newsSite,
        result.summary,
        result.publishedAt,
        result.updatedAt
    )
}

fun ResultsItem.toDetailContentsEntity() = Contents(
    id = id,
    title = title,
    authors = authors?.map { authorItem ->
        AuthorContents(
            name = authorItem?.name
        )
    },
    url = url,
    imageUrl = imageUrl,
    newsSite = newsSite,
    summary = summary,
    publishedAt = publishedAt,
    updatedAt = updatedAt
)