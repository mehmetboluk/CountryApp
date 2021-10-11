package com.example.countryapp.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.R
import com.example.countryapp.databinding.ItemCountryBinding
import com.example.countryapp.model.Country
import com.example.countryapp.util.downloadFromUrl
import com.example.countryapp.util.placeHolderProgressBar
import com.example.countryapp.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdaptor(val countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdaptor.CountryViewHolder>(), CountryClickListener {

    class CountryViewHolder(var view : ItemCountryBinding) : RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.item_country, parent, false)
        val view = DataBindingUtil.inflate<ItemCountryBinding>(inflater, R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.view.country = countryList[position]
        holder.view.listener = this
        /*
        holder.view.ivCountryFlagAtItemCountry.downloadFromUrl(countryList[position].imageURL, placeHolderProgressBar(holder.view.context))
        holder.view.tvCountryNameAtItemCountry.text = countryList[position].countryName
        holder.view.tvCountryRegionAtItemCountry.text = countryList[position].region
        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }
        */

    }
    override fun getItemCount(): Int {
        return countryList.size
    }


    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val uuid = v.tvUuid.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }


}