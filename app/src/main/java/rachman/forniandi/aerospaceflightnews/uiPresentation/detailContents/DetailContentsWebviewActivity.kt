package rachman.forniandi.aerospaceflightnews.uiPresentation.detailContents

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import rachman.forniandi.aerospaceflightnews.databinding.ActivityDetailContentsWebviewBinding

class DetailContentsWebviewActivity : AppCompatActivity() {

    private var binding: ActivityDetailContentsWebviewBinding? = null
    private val navArgs: DetailContentsWebviewActivityArgs by navArgs()
    private var linkContent: String? =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailContentsWebviewBinding.inflate(layoutInflater)

        setContentView(binding?.root)
        supportActionBar?.hide()

        showLinkContent()


    }

    private fun showLinkContent() {
        linkContent = if (intent.hasExtra(DETAIL_CONTENT_URL)){
            intent.getStringExtra(DETAIL_CONTENT_URL)
        }else{
            navArgs.urlWeb.url?:""
        }

        binding?.wbDetailContent?.webViewClient= object : WebViewClient() {}
        linkContent?.let { binding?.wbDetailContent?.loadUrl(it) }
    }

    companion object {
        const val DETAIL_CONTENT_URL = "detail_content_url"
    }

}