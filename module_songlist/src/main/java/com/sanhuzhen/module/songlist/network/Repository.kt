package com.sanhuzhen.module.songlist.network

import com.sanhuzhen.lib.net.RetrofitRequest
import com.sanhuzhen.module.songlist.api.ApiService

class Repository {

    private val aoiService = RetrofitRequest.create(ApiService::class.java)


}