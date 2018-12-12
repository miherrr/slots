package com.example.prime.testapplication

import android.app.Application
import android.content.ContextWrapper
import com.example.prime.testapplication.network.IPService
import com.example.prime.testapplication.network.RetrofitFactory
import com.example.prime.testapplication.network.URLService
import com.github.salomonbrys.kodein.*
import org.jetbrains.anko.ctx

class App : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        bind<IPService>() with provider { RetrofitFactory.getForIp("http://www.ip-api.com").create(IPService::class.java) }
        bind<URLService>() with provider { RetrofitFactory.getForUrlDetect("https://us-central1-slots-api.cloudfunctions.net").create(URLService::class.java) }
    }

    override fun onCreate() {
        super.onCreate()

    }
}