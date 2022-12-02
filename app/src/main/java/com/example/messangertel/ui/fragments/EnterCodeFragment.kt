package com.example.messangertel.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.messangertel.MainActivity
import com.example.messangertel.R
import com.example.messangertel.activity.RegisterActivity
import com.example.messangertel.utilits.AUTH
import com.example.messangertel.utilits.AppTextWatcher
import com.example.messangertel.utilits.replaceActivity
import com.example.messangertel.utilits.showToast
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment(val mPhoneNumber: String, val id: String) :
    BaseFragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = mPhoneNumber
        register_input_code.addTextChangedListener(AppTextWatcher {
            val string = register_input_code.text.toString()
            if (string.length == 6) {
                enterCode()
            }
        })
    }

    private fun enterCode() {
        val code = register_input_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast("Добро пожаловать")
                    (activity as RegisterActivity).replaceActivity(MainActivity())
                } else {
                    showToast(it.exception?.message.toString())
                }
            }

    }
}