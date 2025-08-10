package rachman.forniandi.aerospaceflightnews.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rachman.forniandi.aerospaceflightnews.R
import rachman.forniandi.aerospaceflightnews.databinding.ItemContentBinding
import rachman.forniandi.aerospaceflightnews.domain.Contents
import rachman.forniandi.aerospaceflightnews.util.ContentDiffUtil

class ContentAdapter (): RecyclerView.Adapter
<ContentAdapter.ContentHolder>(){

    private var events = listOf<Contents>()
    private var onClickListener: OnContentClickListener ?= null

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
        val eventData = events[position]
        holder.bind(eventData)
        holder.itemView.setOnClickListener {
            onClickListener?.onClick(position,eventData)
        }

    }

    override fun getItemCount(): Int {
        return events.size
    }

    inner class ContentHolder (private val binding: ItemContentBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(contents: Contents){
            binding.apply {
                Glide.with(itemView.context)
                    .load(contents.imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.place_holder)
                    .error(R.drawable.error_placeholder)
                    .into(imgContent)

                txtTitleContent.text = contents.title

            }

        }
    }

    fun setOnClickListener(onClickListener: OnContentClickListener ) {
        this.onClickListener = onClickListener
    }

    interface OnContentClickListener {
        fun onClick(position: Int, idContent: Contents)
    }

    fun setData(eventData: List<Contents>){
        val dataDiffUtil = ContentDiffUtil(events,eventData)
        val diffUtilResult = DiffUtil.calculateDiff(dataDiffUtil)
        events = eventData
        diffUtilResult.dispatchUpdatesTo(this)
    }

}