package io.github.alvarosanzrodrigo.fornitestatsapp.ViewModels


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.github.alvarosanzrodrigo.fornitestatsapp.APIs.ForniteAPI
import io.github.alvarosanzrodrigo.fornitestatsapp.Models.Player
import io.github.alvarosanzrodrigo.fornitestatsapp.Models.Stat
import io.github.alvarosanzrodrigo.fornitestatsapp.Repositories.Repository
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import kotlin.math.log

class MainViewModel(application: Application): AndroidViewModel(application) {
    private var mRepository = Repository.getInstance()
    private var mPlayerLiveData = MutableLiveData<ArrayList<Stat>>()

    public fun getPlayerLiveData() = mPlayerLiveData

    public fun searchPlayer(platform: String, playerName:String){
        mRepository.searchPlayer(platform, playerName)
            .subscribeOn(Schedulers.io())
            .subscribe({
                var statsArrayList = ArrayList<Stat>()
                statsArrayList.add(it.stats.p10.kills)
                statsArrayList.add(it.stats.p10.winRatio)
                statsArrayList.add(it.stats.p10.score)
                statsArrayList.add(it.stats.p10.kpg)
                mPlayerLiveData.postValue(statsArrayList)
            }, {
                it.printStackTrace()
            })
    }
}

