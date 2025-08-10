package rachman.forniandi.aerospaceflightnews.uiPresentation.DetailContents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import rachman.forniandi.aerospaceflightnews.R
import rachman.forniandi.aerospaceflightnews.databinding.FragmentArticlesBinding
import rachman.forniandi.aerospaceflightnews.databinding.FragmentDetailContentsBinding
import rachman.forniandi.aerospaceflightnews.uiPresentation.articles.ArticlesViewModel
import kotlin.getValue


class DetailContentsFragment : Fragment() {

    private var _binding: FragmentDetailContentsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ArticlesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_contents, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

    }
}