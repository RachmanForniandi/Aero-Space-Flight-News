package rachman.forniandi.core.utilRemote


import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
import rachman.forniandi.core.data.remote.response.ResultsItem
import rachman.forniandi.core.domain.entity.AuthorContents
import rachman.forniandi.core.domain.entity.Contents

/*fun List<ResultsItem>.toContentsEntity()=map { result ->
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
}*/

fun ResultsItem.toDetailContentsEntity(type: String) = Contents(
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
    updatedAt = updatedAt,
    type = type
)

fun mapResponseToEntities(
    input: List<ResultsItem>,
    type: String // "ARTICLE" atau "BLOG"
): List<Contents> {
    return input.map { data ->
        Contents(
            id = data.id ?: 0,
            title = data.title.orEmpty(),
            authors = data.authors?.map {
                AuthorContents(
                    name = it?.name.orEmpty(),

                )
            },
            url = data.url,
            imageUrl = data.imageUrl,
            newsSite = data.newsSite,
            summary = data.summary,
            publishedAt = data.publishedAt,
            updatedAt = data.updatedAt,
            type = type
        )
    }
}

fun mapEntityToDomain(input: Contents): Contents = input

fun mapDomainToEntity(input: Contents): Contents = input

fun Contents.toFavoriteEntity(type: String) = FavoriteContentsEntity(
    id = id,
    title = title,
    imageUrl = imageUrl,
    newsSite = newsSite,
    summary = summary,
    publishedAt = publishedAt,
    url = url,
    contentType = type,
    isFavorite = false
)

fun List<ResultsItem>.toContentsEntity(type: String): List<Contents> = map {
    Contents(
        id = it.id,
        title = it.title,
        authors = it.authors?.map { author ->
            AuthorContents(author?.name ?: "")
        } ?: emptyList(),
        url = it.url,
        imageUrl = it.imageUrl,
        newsSite = it.newsSite,
        summary = it.summary,
        publishedAt = it.publishedAt,
        updatedAt = it.updatedAt,
        type = type
    )
}
