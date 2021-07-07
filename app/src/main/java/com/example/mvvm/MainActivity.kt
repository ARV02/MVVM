package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.adapters.CardsAdapter
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.models.CardsResponse
import com.example.mvvm.models.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var recyclerAdapter:CardsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        viewModel()
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
    }

    private fun showError(){
        Toast.makeText(this, "Error", Toast.LENGTH_LONG)
            .show()
    }

    companion object {
        @JvmStatic
        fun newInstance()=
            MainActivity()
    }
}