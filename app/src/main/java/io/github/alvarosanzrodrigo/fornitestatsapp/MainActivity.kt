package io.github.alvarosanzrodrigo.fornitestatsapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import io.github.alvarosanzrodrigo.fornitestatsapp.Adapters.PlayerAdapter
import io.github.alvarosanzrodrigo.fornitestatsapp.Models.Stat
import io.github.alvarosanzrodrigo.fornitestatsapp.ViewModels.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var spinner: Spinner
    private lateinit var playerText: EditText

    private lateinit var viewModel: MainViewModel
    private var statsArrayList: ArrayList<Stat> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get the viewModel
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        bindView()
        //Here is the things you need for a RecylcerView to work
        viewManager = GridLayoutManager(this, 2)
        viewAdapter = PlayerAdapter(statsArrayList)
        recyclerView = this.findViewById<RecyclerView>(R.id.rv_player).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        viewModel.getPlayerLiveData().observe(this, Observer {
            it.let { stats ->
                if (stats != null) {
                    Log.d("Dato", stats.size.toString())
                        setAdapter(stats)

                }else {
                    Toast.makeText(this, "Player not found", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun setAdapter(statsArrayList: ArrayList<Stat>){
        recyclerView.adapter = PlayerAdapter(statsArrayList)
    }

    fun searchPlayer(view: View){
        val platform: String = spinner.selectedItem.toString()
        val playerName: String = playerText.text.toString()
        viewModel.searchPlayer(platform, playerName)

    }

    private fun bindView(){
        spinner = findViewById(R.id.spinner)
        playerText = findViewById(R.id.edit_text_search)
    }

}
