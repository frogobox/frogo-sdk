package com.frogobox.sdk

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.gson.Gson

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

    abstract fun setupViewModel()

    abstract fun setupOnViewCreated(view: View, savedInstanceState: Bundle?)

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
        if (isEmpty) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
        Log.d(TAG, "Setup Empty View : $isEmpty")
    }

    override fun setupProgressView(view: View, isProgress: Boolean) {
        if (isProgress) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
        Log.d(TAG, "Setup Progress View : $isProgress")
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Toast message : $message")
    }

    override fun <Model> frogoNewInstance(argsKey: String, data: Model) {
        val argsData = Gson().toJson(data)
        val bundleArgs = Bundle().apply {
            putString(argsKey, argsData)
        }
        this.arguments = bundleArgs
    }

    protected inline fun <reified Model> frogoGetInstance(argsKey: String): Model {
        val argsData = this.arguments?.getString(argsKey)
        return Gson().fromJson(argsData, Model::class.java)
    }

    protected inline fun <reified ClassActivity> frogoStartActivity() {
        context?.startActivity(Intent(context, ClassActivity::class.java))
    }

    protected inline fun <reified ClassActivity, Model> frogoStartActivity(
        extraKey: String,
        data: Model
    ) {
        val intent = Intent(context, ClassActivity::class.java)
        val extraData = Gson().toJson(data)
        intent.putExtra(extraKey, extraData)
        context?.startActivity(intent)
    }

}