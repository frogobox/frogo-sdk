package com.frogobox.appadmob.mvvm.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.frogobox.BaseFragment
import com.frogobox.databinding.FragmentMainAdmobBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : BaseFragment<FragmentMainAdmobBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainAdmobBinding {
        return FragmentMainAdmobBinding.inflate(inflater, container, false)
    }

}