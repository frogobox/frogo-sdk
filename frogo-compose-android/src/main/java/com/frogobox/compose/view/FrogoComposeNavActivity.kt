package com.frogobox.compose.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

abstract class FrogoComposeNavActivity : FrogoComposeActivity() {

    protected lateinit var navController: NavHostController

    @Composable
    override fun SetupCompose() {
        navController = rememberNavController()
        SetupNavigation(navController)
    }

    /**
     * Override this to define your navigation graphs / NavHost.
     */
    @Composable
    abstract fun SetupNavigation(navController: NavHostController)

}
