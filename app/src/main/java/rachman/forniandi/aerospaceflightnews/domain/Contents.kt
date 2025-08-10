package rachman.forniandi.aerospaceflightnews.domain

import java.io.Serializable


data class Contents(
    val id: Int?,
    val title: String?,
    val authors: List<AuthorContents?>?,
    val url: String?,
    val imageUrl: String?,
    val newsSite: String?,
    val summary: String?,
    val publishedAt: String?,
    val updatedAt: String?
): Serializable
