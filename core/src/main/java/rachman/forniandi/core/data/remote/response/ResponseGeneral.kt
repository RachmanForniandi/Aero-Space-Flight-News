package rachman.forniandi.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseGeneral(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("previous")
	val previous: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem>
)

data class ResultsItem(

	@field:SerializedName("summary")
	val summary: String? = null,

	@field:SerializedName("news_site")
	val newsSite: String? = null,


	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("published_at")
	val publishedAt: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("authors")
	val authors: List<AuthorsItem?>? = null
)

data class AuthorsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("socials")
	val socials: Any? = null
)
