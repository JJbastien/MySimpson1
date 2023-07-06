package com.example.mytest1.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytest1.R
import com.example.mytest1.databinding.ActivityMainBinding
import com.example.mytest1.databinding.FragmentCharacterListBinding
import com.example.mytest1.models.CharacterResponse
import com.example.mytest1.utils.CharacterAdapter
import com.example.mytest1.utils.UIState
import com.example.mytest1.viewModel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterList : Fragment() {

    private  val myViewModel: CharacterViewModel by viewModels()
    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!
    private val myAdapter by lazy {
        CharacterAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeCharacter()
    }

    private fun observeCharacter(){
        myViewModel.characterLiveData.observe(viewLifecycleOwner){state ->
            when(state){
                is UIState.Loading -> {
                    Log.d("Loading" , "this is loading")}
                is UIState.Success<*>  -> {
                    Log.d("success", "this is success")
                    val characterResponse = state.response as CharacterResponse
                    val relatedTopic = characterResponse.RelatedTopics
                    binding.myRecyclerView.apply{
                        layoutManager = LinearLayoutManager(requireContext())
                        myAdapter.getNewData(relatedTopic)
                        adapter = myAdapter
                    }
                    binding.searchButton.setOnClickListener {
                        val queryText = binding.searchText.text.toString()
                        val filteredList = relatedTopic.filter {
                            it.Text.contains(queryText, true)
                        }
                        myAdapter.filterList(filteredList)
                    }
                }
                is UIState.Error  -> { Log.d("error", "${state.error}")}
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}