package rachman.forniandi.aerospaceflightnews.uiPresentation.detailContents

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.navArgs
import rachman.forniandi.aerospaceflightnews.R
import rachman.forniandi.aerospaceflightnews.databinding.ActivityDetailContentsWebviewBinding

class DetailContentsWebviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailContentsWebviewBinding
    private val navArgs: DetailContentsWebviewActivityArgs by navArgs()
    private var linkContent: String? =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailContentsWebviewBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        showLinkContent()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    private fun showLinkContent() {
        linkContent = if (intent.hasExtra(DETAIL_CONTENT_URL)){
            intent.getStringExtra(DETAIL_CONTENT_URL)
        }else{
            navArgs.urlWeb.url?:""
        }

        binding.wbDetailContent.webViewClient= object : WebViewClient() {}
        linkContent?.let { binding.wbDetailContent.loadUrl(it) }
    }

    companion object {
        const val DETAIL_CONTENT_URL = "detail_content_url"
    }

}