package rachman.forniandi.core.utilRemote



import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
import rachman.forniandi.core.data.remote.response.ResultsItem
import rachman.forniandi.core.domain.entity.AuthorContents
import rachman.forniandi.core.domain.entity.ContentType
import rachman.forniandi.core.domain.entity.Contents



fun ResultsItem.toDetailContentsEntity(type: ContentType) = Contents(
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



fun FavoriteContentsEntity.toContentsDomain(): Contents = Contents(
    id = this.id,
    title = this.title,
    authors = listOf(),
    url = this.url,
    imageUrl = this.imageUrl,
    newsSite = this.newsSite,
    summary = this.summary,
    publishedAt = this.publishedAt,
    updatedAt = this.updateAt,
    type = contentType
)

fun List<ResultsItem>.toContentsEntity(type: ContentType): List<Contents> = map {
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
