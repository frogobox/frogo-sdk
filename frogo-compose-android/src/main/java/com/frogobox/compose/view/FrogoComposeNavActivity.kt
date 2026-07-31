package com.frogobox.compose.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

abstract class FrogoComposeNavActivity : FrogoComposeActivity() {

    protected lateinit var navController: NavHostController

    @Composable
    override fun SetupCompose() {
        val controller = rememberNavController()
        androidx.compose.runtime.SideEffect {
            navController = controller
        }
        SetupNavigation(controller)
    }

    /**
     * Override this to define your navigation graphs / NavHost.
     */
    @Composable
    abstract fun SetupNavigation(navController: NavHostController)

}
