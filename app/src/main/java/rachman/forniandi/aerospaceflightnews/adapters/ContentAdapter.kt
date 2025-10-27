package rachman.forniandi.aerospaceflightnews.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rachman.forniandi.aerospaceflightnews.R
import rachman.forniandi.aerospaceflightnews.databinding.ItemContentBinding
import rachman.forniandi.aerospaceflightnews.util.ContentDiffUtil
import rachman.forniandi.core.domain.entity.Contents

class ContentAdapter (private val onItemClicked: (contents: Contents?) -> Unit):
PagingDataAdapter<Contents, ContentAdapter.ContentHolder>(DIFF_CALLBACK){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContentHolder {
        val binding = ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ContentHolder,
        position: Int
    ) {
        val contents = getItem(position)
        holder.bind(contents)

    }



    inner class ContentHolder (private val binding: ItemContentBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(contents: Contents?){
            binding.apply {
                Glide.with(itemView.context)
                    .load(contents?.imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.place_holder)
                    .error(R.drawable.error_placeholder)
                    .into(imgContent)

                txtTitleContent.text = contents?.title
            }
            itemView.setOnClickListener {
                onItemClicked(contents)
            }

        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Contents>() {
            override fun areItemsTheSame(oldItem: Contents, newItem: Contents): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Contents, newItem: Contents): Boolean {
                return oldItem == newItem
            }
        }
    }

}