package com.bowhead.challenge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.airbnb.lottie.LottieAnimationView
import com.bowhead.challenge.R
import com.bowhead.challenge.models.LogItem
import com.bowhead.challenge.ui.adapters.LogsRecyclerviewAdapter
import com.bowhead.challenge.util.Status
import com.bowhead.challenge.viewmodel.UserDataViewModel
import kotlinx.android.synthetic.main.fragment_log.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LogFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var backButton: ImageButton
    private lateinit var logRecyclerView: RecyclerView
    private lateinit var viewModel: UserDataViewModel
    private lateinit var  alertDialog: SweetAlertDialog
    private lateinit var lottieAnimationViewLoading: LottieAnimationView
    private lateinit var lottieAnimationViewEmpty: LottieAnimationView
    private  var adapter: LogsRecyclerviewAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_log, container, false)
        viewModel=ViewModelProviders.of(requireParentFragment()).get(UserDataViewModel::class.java)
        alertDialog=SweetAlertDialog(requireContext())
        backButton=view.log_backButton
        lottieAnimationViewLoading=view.log_lottie_loading
        lottieAnimationViewEmpty=view.log_lottie_empty
        backButton.setOnClickListener(this::goBack)
        logRecyclerView=view.log_recyclerview
        logRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        logRecyclerView.setHasFixedSize(true)
        logRecyclerView.visibility=View.INVISIBLE
        //logRecyclerView.adapter=LogsRecyclerviewAdapter(arrayListOf(LogItem("fdsf","fds"),LogItem("fdsf","fds"),LogItem("fdsf","fds")))
        viewModel.getUserData().observe(requireActivity(), Observer {
            it?.let { resource ->
                when (resource.status) {

                    Status.SUCCESS -> {
                        //showProgress(false)
                        val list=it.data
                        setupAdapter(list)
                    }
                    Status.ERROR -> {
                        showError(it.message)

                    }
                    Status.LOADING -> {
                        //showProgress(true)
                    }
                    //else ->
                }

            }
        })
        return view
    }

    fun goBack(view: View?){
        val navHost=parentFragment as NavHostFragment
        val holder=navHost.parentFragment as HomeFragment
        holder.returnToForm()
    }

    fun setupAdapter(logList: List<*>?){
        logRecyclerView.visibility=View.VISIBLE
        lottieAnimationViewLoading.visibility=View.GONE
        lottieAnimationViewEmpty.visibility=View.GONE
        if(adapter==null&&!logList.isNullOrEmpty()){
            adapter=LogsRecyclerviewAdapter(deserialisizeList(logList))
            logRecyclerView.adapter=adapter
        }else if(logList.isNullOrEmpty()){
            logRecyclerView.visibility=View.GONE
            lottieAnimationViewLoading.visibility=View.GONE
            lottieAnimationViewEmpty.visibility=View.VISIBLE

        }else{
            adapter?.addAll(deserialisizeList(logList))
        }

    }

    fun deserialisizeList(logList: List<*>):ArrayList<LogItem>{
        var listLog= arrayListOf<LogItem>()
        logList.forEach {
            listLog.add(it as LogItem)
        }
        return listLog
    }


    fun showError(e:String?){
        logRecyclerView.visibility=View.GONE
        lottieAnimationViewLoading.visibility=View.GONE
        lottieAnimationViewEmpty.visibility=View.VISIBLE
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.L
         * @param param2 Parameter 2.
         * @return A new instance of fragment LogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}