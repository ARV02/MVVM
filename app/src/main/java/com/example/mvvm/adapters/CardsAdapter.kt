package com.example.mvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.databinding.ItemViewBinding
import com.example.mvvm.data.models.CardDetails

class CardsAdapter : RecyclerView.Adapter<CardsAdapter.ViewHolder>() {
    var items = mutableListOf<CardDetails>()

    fun setUpdateData(items:MutableList<CardDetails>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):CardsAdapter.ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: CardsAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun delete(position:Int){
        items.removeAt(position)
        notifyItemRemoved(position)
    }
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val binding = ItemViewBinding.bind(view)
        fun bind(cards:CardDetails){
            binding.last4.text = cards.cardLast4
            binding.exp.text = cards.cardExpDetails

            if (cards.cardType == "Visa")
                binding.cardImage.setImageResource(R.drawable.ic_visa)
            else if (cards.cardType == "MasterCard")
                binding.cardImage.setImageResource(R.drawable.ic_mastercard)
            else if(cards.cardType == "American Express")
                binding.cardImage.setImageResource(R.drawable.ic_amex)
            else if(cards.cardType == "Discover")
                binding.cardImage.setImageResource(R.drawable.ic_discover)

        }
    }
}