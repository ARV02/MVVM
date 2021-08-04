package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.NetworkOnMainThreadException
import android.view.View
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.adapters.CardsAdapter
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.models.CardDetails
import com.example.mvvm.models.CardsResponse
import com.example.mvvm.models.MainViewModel
import com.example.mvvm.network.NetworkConnectionInterceptor
import com.example.mvvm.utils.SwipeToDelete
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var recyclerAdapter:CardsAdapter
    private lateinit var networkConnectionInterceptor:NetworkConnectionInterceptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        viewModel()
        deleteCard()
        swipeRefresh()
        networkConnection()
    }

    private fun ConnectivityMonitor(isNetworkAvailable:Boolean){
        var connectionString = "Valid connection"
        if(!isNetworkAvailable) {
            connectionString = "No network connection"
            binding.network.text = connectionString
            binding.linearLayout.visibility = View.VISIBLE
        } else if(isNetworkAvailable){
            GlobalScope.launch(Dispatchers.Main) {
                binding.network.text = connectionString
                binding.linearLayout.visibility = View.VISIBLE
                delay(5000)
                binding.linearLayout.visibility = View.GONE
            }
        }
    }

    private fun networkConnection(){
        networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        networkConnectionInterceptor.observe(this, { isNetworkAvailable ->
            ConnectivityMonitor(isNetworkAvailable = isNetworkAvailable)
        })
    }

    private fun initViewModel(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = CardsAdapter()
        binding.recyclerView.adapter = recyclerAdapter
    }

    private fun viewModel(){
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(this, Observer<CardsResponse> {
            if (it != null){
                recyclerAdapter.setUpdateData(it.cardDetails)
            } else {
                showError()
            }
        })
        viewModel.getAllCards()
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun deleteCard(){
        val item = object : SwipeToDelete(this,0, ItemTouchHelper.LEFT){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                super.onSwiped(viewHolder, direction)
                recyclerAdapter.delete(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(item)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun showError(){
        Toast.makeText(this, "Error", Toast.LENGTH_LONG)
            .show()
    }
    private fun swipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance()=
            MainActivity()
    }
}