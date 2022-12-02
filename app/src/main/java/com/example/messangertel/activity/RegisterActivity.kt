package com.example.messangertel.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.messangertel.R

import com.example.messangertel.databinding.ActivityRegisterBinding
import com.example.messangertel.ui.fragments.EnterPhoneNumberFragment
import com.example.messangertel.utilits.replaceFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        mToolbar = mBinding.registerToolbar
        setSupportActionBar(mToolbar)
        title = "Ваш телефон"
        replaceFragment(EnterPhoneNumberFragment(),false)
    }
}