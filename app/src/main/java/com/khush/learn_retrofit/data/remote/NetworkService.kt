package com.khush.learn_retrofit.data.remote

import com.khush.learn_retrofit.data.model.HeroData
import com.khush.learn_retrofit.data.model.HeroResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET(EndPoints.HERO_ENDPOINT)
    fun queryHero(): Single<List<HeroData>>
}