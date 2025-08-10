package rachman.forniandi.aerospaceflightnews.util


import rachman.forniandi.aerospaceflightnews.data.remote.response.ResultsItem
import rachman.forniandi.aerospaceflightnews.domain.AuthorContents
import rachman.forniandi.aerospaceflightnews.domain.Contents

fun List<ResultsItem>.toContentsEntity()=map {
    Contents(it.id,it.title,it.authors as List<AuthorContents>,it.url,it.imageUrl,it.newsSite,it.summary,it.publishedAt,it.updatedAt)
}

fun ResultsItem.toDetailContentsEntity()= Contents(id,title,authors as List<AuthorContents>,url,imageUrl,newsSite,summary,publishedAt,updatedAt)