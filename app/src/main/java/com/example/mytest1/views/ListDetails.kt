package com.example.mytest1.views


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.mytest1.R
import com.example.mytest1.databinding.FragmentListDetailsBinding
import com.example.mytest1.models.RelatedTopic
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListDetails : Fragment() {

  private lateinit var binding:  FragmentListDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
   private fun getDetail(detail:RelatedTopic){
       val title = detail.Text.split("-").first().trim()
        binding.description.text = detail.Text
        binding.title.text = title
       val baseUrl = detail.FirstURL.substringBeforeLast('/')
       val fullImageUrl = "$baseUrl${detail.Icon.URL}"
        context?.let {
            Glide.with(it)
                .load(fullImageUrl)
                .placeholder(R.drawable.baseline_assignment_ind_24)
                .into(binding.image)
        }
       Log.d("pictures", "${detail.Icon.URL}")
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
       super.onViewCreated(view, savedInstanceState)
        val relatedTopic = arguments?.getParcelable<RelatedTopic>("relatedTopic")
        relatedTopic?.let{
                getDetail(it)
            }
    }

}