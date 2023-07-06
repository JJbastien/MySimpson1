package com.example.mytest1.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mytest1.databinding.CharacterItemBinding
import com.example.mytest1.models.RelatedTopic
import com.example.mytest1.views.CharacterListDirections


class CharacterAdapter(private val dataSet:MutableList<RelatedTopic> = mutableListOf()) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(){
    private val fullDataSet:MutableList<RelatedTopic> = mutableListOf()

    inner class CharacterViewHolder(private val binding: CharacterItemBinding ):RecyclerView.ViewHolder(binding.root){
        fun bind(characterSet:RelatedTopic){
            binding.apply {
                characterItem.text = characterSet.Text
                root.setOnClickListener{
                   val action = CharacterListDirections.actionCharacterListToListDetails(characterSet)
                    it.findNavController().navigate(action)
                }
            }
        }
    }
    fun getNewData(character: List<RelatedTopic>){
        dataSet.clear()
        dataSet.addAll(character)
        fullDataSet.clear()
        fullDataSet.addAll(character)
        notifyDataSetChanged()
    }
    fun filterList(filteredCharater:List<RelatedTopic>){
        dataSet.clear()
        dataSet.addAll(filteredCharater)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterAdapter.CharacterViewHolder {
        return CharacterViewHolder(CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
      return  dataSet.size
    }
}