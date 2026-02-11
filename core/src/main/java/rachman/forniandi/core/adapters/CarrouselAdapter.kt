package rachman.forniandi.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import rachman.forniandi.core.adapters.ContentAdapter.Companion.DIFF_CALLBACK
import rachman.forniandi.core.databinding.ItemContentCarrouselBinding
import rachman.forniandi.core.domain.entity.Contents
import rachman.forniandi.core.utilRemote.showImageSliderInto

class CarrouselAdapter (
    private val onClick: (Contents) -> Unit
) : ListAdapter<Contents, CarrouselAdapter.CarrouselHolder>(DIFF_CALLBACK){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarrouselHolder {
        val binding = ItemContentCarrouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarrouselHolder(binding)
    }

    override fun onBindViewHolder(holder: CarrouselHolder, position: Int) {
        val itemContent = getItem(position)
        itemContent?.let { holder.bind(it) }
    }

    inner class CarrouselHolder(val binding: ItemContentCarrouselBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(contents: Contents){
            binding.imgContent.showImageSliderInto(itemView.context, contents.imageUrl)
            binding.txtTitleNameContent.text = contents.title
            itemView.setOnClickListener { onClick(contents) }
        }
    }

}