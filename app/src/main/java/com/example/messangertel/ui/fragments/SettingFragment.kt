package com.example.messangertel.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import com.example.messangertel.R

class SettingFragment : BaseFragment(R.layout.fragment_setting) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)

    }
}