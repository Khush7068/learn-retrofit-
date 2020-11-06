package com.khush.learn_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khush.learn_retrofit.adapter.AvengerAdapter
import com.khush.learn_retrofit.data.model.HeroData
import com.khush.learn_retrofit.data.remote.Networking
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var compositeDisposable: CompositeDisposable
    lateinit var heroDataList: List<HeroData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable = CompositeDisposable()

        requestHeroDetails()
    }


    fun requestHeroDetails() {
        compositeDisposable.add(
            Networking.create()
                .queryHero()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    heroDataList = it
                    setAvengerAdapter()
                }, {
                    Log.d("KS", it.message.toString())
                })
        )
    }

    fun setAvengerAdapter() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val avengerAdapter = AvengerAdapter(heroDataList)
        rv_hero.layoutManager = layoutManager
        rv_hero.adapter = avengerAdapter
        avengerAdapter.notifyDataSetChanged()

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}