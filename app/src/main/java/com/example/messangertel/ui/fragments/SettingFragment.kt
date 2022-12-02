package com.example.messangertel.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.messangertel.MainActivity
import com.example.messangertel.R
import com.example.messangertel.activity.RegisterActivity
import com.example.messangertel.utilits.AUTH
import com.example.messangertel.utilits.replaceActivity

class SettingFragment : BaseFragment(R.layout.fragment_setting) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
        }
        return true
    }
}