package com.example.hsdemosoft.ui.details

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.hsdemosoft.R
import com.example.hsdemosoft.databinding.FragmentDetailsBinding
import com.example.hsdemosoft.ui.MainActivity
import com.example.hsdemosoft.utils.loadImg


class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding

    private var name: String? = ""
    private var native: String? = ""
    private var currency: String? = ""
    private var capital: String? = ""
    private var emoji: String? = ""
    private var languageName: String? = ""
    private var languageCode: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        if(context?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT){
            (context as MainActivity).binding.listFragment.visibility = View.GONE
            (context as MainActivity).binding.detailsFragment.visibility = View.VISIBLE
        } else {
            (context as MainActivity).binding.listFragment.visibility = View.VISIBLE
            (context as MainActivity).binding.detailsFragment.visibility = View.VISIBLE
        }

        arguments?.let {
            name = it.getString("name")
            native = it.getString("native")
            currency = it.getString("currency")
            capital = it.getString("capital")
            emoji = it.getString("emoji")
            languageName = it.getString("languagesname")
            languageCode = it.getString("languagescode")
        }
        return  binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.countryName.text = name
        binding.countryCapital.text = capital
        binding.countryNative.text = native
        binding.countryCurrency.text = currency
        binding.emojiIv.loadImg(emoji!!)
        binding.languageName.text = languageName
        binding.languageCode.text = languageCode


    }




}