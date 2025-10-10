package rachman.forniandi.aerospaceflightnews.uiPresentation.detailContents

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import rachman.forniandi.aerospaceflightnews.R
import rachman.forniandi.aerospaceflightnews.databinding.ActivityDetailContentsWebviewBinding
class DetailContentsWebviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailContentsWebviewBinding

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
        /*val linkContent = intent.getStringExtra(DETAIL_WEB_CONTENT)

        binding.wbDetailContent.webViewClient= object : WebViewClient() {}
        if (linkContent!= null) {
            binding.wbDetailContent.loadUrl(linkContent)
        }*/
    }

}