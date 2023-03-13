package com.example.githubuserlist.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserlist.adapter.RecyclerAdapter
import com.example.githubuserlist.databinding.FragmentListBinding
import com.example.githubuserlist.db.DatabaseInstance
import com.example.githubuserlist.models.Users
import com.example.githubuserlist.models.UsersItem
import com.example.githubuserlist.repository.UserRepository
import com.example.githubuserlist.util.Resource
import com.example.githubuserlist.viewmodel.UserViewModel
import com.example.githubuserlist.viewmodel.UserViewModelProvider
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ListFragment: Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val db = DatabaseInstance.getInstance(requireContext())
        val repository = UserRepository(db)
        val factory = UserViewModelProvider(repository, requireContext())
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        recyclerAdapter = RecyclerAdapter()
        binding.rvList.adapter = recyclerAdapter

//        if (internetCheck(requireContext())) {
//            viewModel.usersLiveData.observe(viewLifecycleOwner, Observer { response ->
//                when(response) {
//                    is Resource.Success -> {
//                        response.data?.let {
//                            listItems.add(it)
//                            recyclerAdapter.differ.submitList(listItems)
//                        }
//                        }
//
//
//                    is Resource.Error -> {
//                        response.message?.let {
//                            Log.i(
//                                "MY TAG",
//                                "Data not received because of the error: ${response.message}"
//                            )
//                        }
//                    }
//                    is Resource.Loading -> {
//                        paginationProgressBar.visibility = View.VISIBLE
//                    }
//                }
//            })
//        }
//                viewModel.usersLiveData.observe(viewLifecycleOwner, Observer{
//                    recyclerAdapter.differ.submitList(it)
//                })

            viewModel.usersFromDB.observe(viewLifecycleOwner, {
                recyclerAdapter.differ.submitList(it)
            })


        return binding.root
    }
}