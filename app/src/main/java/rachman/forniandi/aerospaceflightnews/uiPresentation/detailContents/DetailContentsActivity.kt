package rachman.forniandi.aerospaceflightnews.uiPresentation.detailContents

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import rachman.forniandi.aerospaceflightnews.R
import rachman.forniandi.core.data.network.RemoteResponse
import rachman.forniandi.aerospaceflightnews.databinding.ActivityDetailContentsBinding
import rachman.forniandi.aerospaceflightnews.domain.Contents
import rachman.forniandi.aerospaceflightnews.uiPresentation.articles.DetailArticlesViewModel
import rachman.forniandi.aerospaceflightnews.uiPresentation.blogs.DetailBlogsViewModel
import rachman.forniandi.aerospaceflightnews.util.animateLoadingProcessData
import kotlin.getValue

class DetailContentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailContentsBinding
    private val viewmodelArticles: DetailArticlesViewModel by viewModels ()
    private val viewmodelBlogs: DetailBlogsViewModel by viewModels ()
    private val navArgs:DetailContentsActivityArgs by navArgs()
    private var idContent:Int=0
    private var detailContentArticle:Contents?=null
    private var detailContentBlog:Contents?=null
    private var linkContent=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailContentsBinding.inflate(layoutInflater)

        idContent = if (intent.hasExtra(EXTRA_ARTICLES_ID)){
            intent.getIntExtra(EXTRA_ARTICLES_ID,0)
        }else if (intent.hasExtra(EXTRA_BLOGS_ID)){
            intent.getIntExtra(EXTRA_BLOGS_ID,0)
        }else{
            navArgs.contentDetails.id ?:0
        }

        setContentView(binding.root)
        if (savedInstanceState === null){
            if (intent.hasExtra(EXTRA_ARTICLES_ID)){
                viewmodelArticles.setValueIdArticles(idContent)
                setVIewDetailContentArticles()
            }else if (intent.hasExtra(EXTRA_BLOGS_ID)){
                viewmodelBlogs.setValueIdBlogs(idContent)
                setViewDetailContentBlogs()
            }
        }



    }

    private fun setVIewDetailContentArticles() {
        viewmodelArticles.detailArticles.observe(this,detailOfArticles)
    }
    private fun setViewDetailContentBlogs() {
        viewmodelBlogs.detailBlogs.observe(this,detailOfBlogs)
    }

    private val detailOfArticles = Observer<RemoteResponse<Contents?>?>{ response ->
        when(response){

            is RemoteResponse.Loading ->{
                applyLoadProgressStateDetail(true)
            }

            is RemoteResponse.Success ->{
                applyLoadProgressStateDetail(false)
                detailContentArticle = response.data
                Glide.with(this)
                    .load(detailContentArticle?.imageUrl)
                    .fitCenter()
                    .placeholder(R.drawable.place_holder)
                    .error(R.drawable.error_placeholder)
                    .into(binding.imgOfContent)
                binding.txtTitleContent.text = detailContentArticle?.title
                binding.txtSource.text = detailContentArticle?.newsSite
                binding.txtAuthor.text = detailContentArticle?.authors?.get(0)?.name
                binding.txtSummaryContent.text = detailContentArticle?.summary
                binding.txtPublishedAt.text = detailContentBlog?.publishedAt
                binding.txtUpdatedAt.text = detailContentBlog?.updatedAt
                linkContent = detailContentArticle?.url.toString()
                setupFunctionalLinkContent(linkContent)
            }

            is RemoteResponse.Error -> {
                applyLoadProgressStateDetail(false)
                binding.btnToDetailContentWeb.isEnabled = false
                Toast.makeText(this,response.errorMessage, Toast.LENGTH_SHORT).show()

            }
            else -> {}
        }

    }

    private val detailOfBlogs = Observer<RemoteResponse<Contents?>?> { response ->
        when (response) {

            is RemoteResponse.Loading -> {
                applyLoadProgressStateDetail(true)
            }

            is RemoteResponse.Success -> {
                applyLoadProgressStateDetail(false)
                detailContentBlog = response.data
                Glide.with(this)
                    .load(detailContentBlog?.imageUrl)
                    .fitCenter()
                    .placeholder(R.drawable.place_holder)
                    .error(R.drawable.error_placeholder)
                    .into(binding.imgOfContent)
                binding.txtTitleContent.text = detailContentBlog?.title
                binding.txtSource.text = detailContentBlog?.newsSite
                binding.txtAuthor.text = detailContentBlog?.authors?.get(0)?.name
                binding.txtSummaryContent.text = detailContentBlog?.summary
                binding.txtPublishedAt.text = detailContentBlog?.publishedAt
                binding.txtUpdatedAt.text = detailContentBlog?.updatedAt
                linkContent = detailContentArticle?.url.toString()
                setupFunctionalLinkContent(linkContent)

            }

            is RemoteResponse.Error -> {
                applyLoadProgressStateDetail(false)
                binding.btnToDetailContentWeb.isEnabled = false
                Toast.makeText(this,response.errorMessage, Toast.LENGTH_SHORT).show()

            }

            else -> {}
        }
    }




    private fun applyLoadProgressStateDetail(onProcess:Boolean){
        binding.btnToDetailContentWeb.isEnabled = !onProcess

        if (onProcess){
            binding.maskedViewPgDetail.animateLoadingProcessData(true)
        }else{
            binding.maskedViewPgDetail.animateLoadingProcessData(false)
        }
    }

    private fun setupFunctionalLinkContent(linkContent: String) {
        binding.btnToDetailContentWeb.setOnClickListener {
            val intent = Intent(this, DetailContentsWebviewActivity::class.java)
            intent.putExtra(DETAIL_WEB_CONTENT, linkContent)
            startActivity(intent)
        }
    }

    companion object{
        const val EXTRA_ARTICLES_ID = "extra_articles_id"
        const val EXTRA_BLOGS_ID = "extra_blogs_id"
        const val DETAIL_WEB_CONTENT="detail_web_content"
    }

}


