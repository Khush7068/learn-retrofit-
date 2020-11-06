package com.khush.learn_retrofit.adapter

import android.view.LayoutInflater.*
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.khush.learn_retrofit.R
import com.khush.learn_retrofit.data.model.HeroData
import com.khush.learn_retrofit.databinding.ItemHeroBinding

class AvengerAdapter(val heroList: List<HeroData>) :

    RecyclerView.Adapter<AvengerAdapter.HeroItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroItemViewHolder {
        val binding = DataBindingUtil.inflate<ItemHeroBinding>(
            from(parent.context),
            R.layout.item_hero, parent, false
        )
        return HeroItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    override fun onBindViewHolder(holder: HeroItemViewHolder, position: Int) {
        holder.onBind(heroList[position])


    }

    inner class HeroItemViewHolder(var heroDataBinding: ItemHeroBinding) :
        RecyclerView.ViewHolder(heroDataBinding.root) {

        fun onBind(heroData: HeroData) {
            heroDataBinding.marvel = heroData
        }
    }
}