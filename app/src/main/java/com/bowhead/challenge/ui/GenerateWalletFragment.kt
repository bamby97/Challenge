package com.bowhead.challenge.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bowhead.challenge.MainActivity
import com.bowhead.challenge.R
import com.bowhead.challenge.api.Web3Client
import com.bowhead.challenge.util.Status
import com.bowhead.challenge.util.Wallet
import com.bowhead.challenge.viewmodel.UserAccountViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_generate_wallet.view.*
import org.web3j.tx.Transfer
import org.web3j.utils.Convert
import java.io.File
import java.math.BigDecimal

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GenerateWalletFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GenerateWalletFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var password:EditText
    private lateinit var passwordLayout:TextInputLayout
    private lateinit var go:Button
    private lateinit var viewModel: UserAccountViewModel
    private lateinit var alertDialog: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(UserAccountViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_generate_wallet, container, false)
        alertDialog=SweetAlertDialog(requireContext(),SweetAlertDialog.PROGRESS_TYPE)
        password=view.generateWallet_password
        passwordLayout=view.generatewallet_inputLayout
        go=view.generatewallet_goButton
        go.setOnClickListener(this::generateWallet)
        return view
    }


    fun generateWallet(view: View?){
        if(password.text.isNullOrBlank()) {
            passwordLayout.error = "This field can´t be empty"
        }else {

            viewModel.generateWallet(password.text.toString(),requireActivity().filesDir.absolutePath+"/Bowhead").observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {

                        Status.SUCCESS -> {
                            val intent = Intent(requireContext(), MainActivity::class.java)
                            startActivity(intent)
                            requireActivity().finishAfterTransition()
                        }
                        Status.ERROR -> {
                            showError(it.message)
                        }
                        Status.LOADING -> {
                            showLoading()
                        }
                        //else ->
                    }

                }
            })

        }
    }

    fun showLoading(){
        alertDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE)
        alertDialog.titleText="Loading"
        alertDialog.setCancelable(false)
        alertDialog.show()
    }


    fun showError(e:String?){
        alertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE)
        alertDialog.titleText= e ?: "Error"
        alertDialog.setCancelable(true)
        alertDialog.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GenerateWalletFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GenerateWalletFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}