package com.frogobox.sdk.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.frogobox.sdk.ext.*

/*
 * Created by faisalamir on 28/07/21
 * FrogoSDK
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
abstract class FrogoFragment<VB : ViewBinding> : Fragment(), IFrogoFragment {

    companion object {
        val TAG: String = FrogoFragment::class.java.simpleName
    }

    private var _binding: VB? = null

    protected val binding: VB get() = _binding!!

    protected val frogoActivity: FrogoActivity<*> by lazy {
        (activity as FrogoActivity<*>)
    }

    // ---------------------------------------------------------------------------------------------

    abstract fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    abstract fun setupOnViewCreated(view: View, savedInstanceState: Bundle?)

    // ---------------------------------------------------------------------------------------------

    open fun setupViewModel() {}

    // ---------------------------------------------------------------------------------------------

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = setupViewBinding(inflater, container)
        setupViewModel()
        Log.d(TAG, "View Binding : ${binding::class.java.simpleName}")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnViewCreated(view, savedInstanceState)
        Log.d(TAG, "Overriding on ViewCreated")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        Log.d(TAG, "Destroying View Binding")
    }

    override fun setupChildFragment(frameId: Int, fragment: Fragment) {
        childFragmentManager.beginTransaction().apply {
            replace(frameId, fragment)
            commit()
        }
    }

    override fun checkArgument(argsKey: String): Boolean {
        return requireArguments().containsKey(argsKey)
    }

    override fun setupEmptyView(view: View, isEmpty: Boolean) {
        view.emptyViewHandle(isEmpty)
    }

    override fun setupProgressView(view: View, isProgress: Boolean) {
        view.progressViewHandle(isProgress)
    }

    override fun showToast(message: String) {
        context?.showToast(message, Toast.LENGTH_SHORT)
    }

    override fun <Model> frogoNewInstance(argsKey: String, data: Model) {
        singleNewInstance(argsKey, data)
    }

    protected inline fun <reified Model> frogoGetInstance(argsKey: String): Model {
        return singleGetInstance(argsKey)
    }

    protected inline fun <reified ClassActivity> frogoStartActivity() {
        context?.singleStartActivity<ClassActivity>()
    }

    protected inline fun <reified ClassActivity, reified Model> frogoStartActivity(
        extraKey: String,
        data: Model
    ) {
        context?.singleStartActivity<ClassActivity, Model>(extraKey, data)
    }

}