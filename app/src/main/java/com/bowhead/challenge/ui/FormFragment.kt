package com.bowhead.challenge.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bowhead.challenge.MainActivity
import com.bowhead.challenge.R
import com.bowhead.challenge.api.Web3Client
import com.bowhead.challenge.models.LogItem
import com.bowhead.challenge.util.Status
import com.bowhead.challenge.util.Wallet
import com.bowhead.challenge.viewmodel.UserDataViewModel
import kotlinx.android.synthetic.main.fragment_form.view.*
import org.web3j.utils.Convert
import java.math.BigInteger

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormFragment : Fragment(){


    private var param1: String? = null
    private var param2: String? = null
    private lateinit var submitButton: Button
    private lateinit var feelRadioGroup: RadioGroup
    private lateinit var sleepRadioGroup: RadioGroup
    private lateinit var viewmodel: UserDataViewModel
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
        viewmodel=ViewModelProviders.of(requireParentFragment()).get(UserDataViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_form, container, false)
        alertDialog=SweetAlertDialog(requireContext())
        submitButton= view.fillform_submitButton
        feelRadioGroup=view.fillform_radioGroup_feel
        sleepRadioGroup=view.filllform_radioGroup_sleep
        submitButton.setOnClickListener(this::submitForm)
        feelRadioGroup.setOnCheckedChangeListener(this::enableSubmitButton)
        sleepRadioGroup.setOnCheckedChangeListener(this::enableSubmitButton)
        return view
    }

    fun getResponseFromQuestion(view:RadioGroup):String{
        var response=""
        when(view.checkedRadioButtonId){
            R.id.bowhead_no-> response= NO
            R.id.bowhead_yes-> response= YES
            R.id.fillform_howDoYouFeel_badAnswer->response= WORST
            R.id.fillform_howDoYouFeel_goodAnswer-> response=GREAT
            R.id.fillform_howDoYouFeel_regularAnswer->response= REGULAR
        }
        return response
    }

    fun submitForm(view: View?){
        val data=LogItem(getResponseFromQuestion(feelRadioGroup),getResponseFromQuestion(sleepRadioGroup))
        viewmodel.addUserData(data).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {

                    Status.SUCCESS -> {
                        val list=it.data
                        showSuccess()
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

    fun showLoading(){
        val alert= SweetAlertDialog(requireContext(),SweetAlertDialog.PROGRESS_TYPE)
        alert.setCancelable(true)
        alert.setTitleText("Loading...")
        alert.show()
    }

    fun showSuccess(){
       val alert= SweetAlertDialog(requireContext(),SweetAlertDialog.SUCCESS_TYPE)
           alert.setCancelable(true)
            alert.setTitleText("Oh yeah, submited!")
        alert.show()
    }

    fun showError(e:String?){
        val alert= SweetAlertDialog(requireContext(),SweetAlertDialog.ERROR_TYPE)
        alert.setCancelable(true)
        alert.setTitleText(if(e!=null)e else "Error")
        alert.show()
    }

    fun enableSubmitButton(radioGroup: RadioGroup, check:Int){
        if(sleepRadioGroup.checkedRadioButtonId!=-1&&feelRadioGroup.checkedRadioButtonId!=-1){
            submitButton.isEnabled=true
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FormFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FormFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        // TODO: Rename and change types of parameters
          const val  WORST="Worst day ever"
        const val REGULAR= "Meh,I´m alive"
        const val GREAT="Perfect, thank you!"
        const val YES="Yes"
        const val NO="No"
    }
}