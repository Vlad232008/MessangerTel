package com.example.messangertel.ui.fragments


import androidx.fragment.app.Fragment
import com.example.messangertel.MainActivity
import com.example.messangertel.R
import com.example.messangertel.activity.RegisterActivity
import com.example.messangertel.utilits.AUTH
import com.example.messangertel.utilits.replaceActivity
import com.example.messangertel.utilits.replaceFragment
import com.example.messangertel.utilits.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*
import java.util.concurrent.TimeUnit

class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {

    private lateinit var mPhoneNumber:String
    private lateinit var mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onStart() {
        super.onStart()
        mCallBack = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential)
                    .addOnCompleteListener{
                        if (it.isSuccessful){
                            showToast("Добро пожаловать")
                            (activity as RegisterActivity).replaceActivity(MainActivity()                            )
                        } else {
                            showToast(it.exception?.message.toString())
                        }
                    }
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                replaceFragment(EnterCodeFragment(mPhoneNumber,id))
            }

            override fun onVerificationFailed(p0: FirebaseException) {

            }

        }
        register_btn_next.setOnClickListener{
            sendCode()
        }
    }

    private fun sendCode() {
        if(register_input_phone_number.text.toString().isEmpty()){
            showToast(getString(R.string.register_toast_enter_phone))
        }
        else {
            authUser()
        }
    }

    private fun authUser() {
        mPhoneNumber = register_input_phone_number.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mPhoneNumber,
            60,
            TimeUnit.SECONDS,
            activity as RegisterActivity,
            mCallBack
        )
    }
}