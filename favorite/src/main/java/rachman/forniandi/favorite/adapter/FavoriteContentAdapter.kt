package rachman.forniandi.favorite.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rachman.forniandi.core.data.local.entity.FavoriteContentsEntity
import androidx.recyclerview.widget.ListAdapter
import rachman.forniandi.core.R.drawable.*
import rachman.forniandi.core.databinding.ItemContentBinding

class FavoriteContentAdapter (
    private val onItemClicked: (FavoriteContentsEntity) -> Unit
) : ListAdapter<FavoriteContentsEntity, FavoriteContentAdapter.FavoriteContentHolder>(DIFF_CALLBACK){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteContentHolder {
        val binding = ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteContentHolder(binding,onItemClicked)
    }

    override fun onBindViewHolder(
        holder: FavoriteContentHolder,
        position: Int
    ) {
        val contents = getItem(position)
        holder.bind(contents)

    }



    class FavoriteContentHolder (private val binding: ItemContentBinding,
                                 private val onItemClicked: (FavoriteContentsEntity) -> Unit): RecyclerView.ViewHolder(binding.root){
        fun bind(contents: FavoriteContentsEntity){
            binding.apply {
                Glide.with(itemView.context)
                    .load(contents.imageUrl)
                    .centerCrop()
                    .placeholder(place_holder)
                    .error(error_placeholder)
                    .into(imgContent)

                txtTitleContent.text = contents.title
            }
            itemView.setOnClickListener {
                onItemClicked(contents)
            }

        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteContentsEntity>() {
            override fun areItemsTheSame(oldItem: FavoriteContentsEntity, newItem: FavoriteContentsEntity): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: FavoriteContentsEntity, newItem: FavoriteContentsEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

}