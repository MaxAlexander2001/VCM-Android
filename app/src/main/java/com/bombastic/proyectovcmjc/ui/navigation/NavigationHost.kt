package com.bombastic.proyectovcmjc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//import androidx.navigation.compose.navArgument
import androidx.navigation.navArgument
import com.bombastic.proyectovcmjc.ui.presentation.screens.Pantalla1
import com.bombastic.proyectovcmjc.ui.presentation.screens.Pantalla2
import com.bombastic.proyectovcmjc.ui.presentation.screens.Pantalla3
import com.bombastic.proyectovcmjc.ui.presentation.screens.Pantalla4
import com.bombastic.proyectovcmjc.ui.presentation.screens.persona.PersonaForm
import com.bombastic.proyectovcmjc.ui.presentation.screens.persona.PersonaUI
import com.bombastic.proyectovcmjc.ui.presentation.screens.PantallaQR as PantallaQR
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NavigationHost(
    navController: NavHostController,
    darkMode: MutableState<Boolean>
) {
    NavHost(navController = navController, startDestination =
    Destinations.Pantalla1.route) {
        composable(Destinations.Pantalla1.route) {
            Pantalla1(
                navegarPantalla2 = { newText ->

                    navController.navigate(Destinations.Pantalla2.createRoute(newText))
                }
            )
        }
        composable(
            Destinations.Pantalla2.route,
            arguments = listOf(navArgument("newText"){ defaultValue =
                "Pantalla 2" })
        ) { navBackStackEntry ->
            var newText =
                navBackStackEntry.arguments?.getString("newText")
            requireNotNull(newText)
            Pantalla2(newText, darkMode)
        }
        composable(Destinations.Pantalla3.route) {
            Pantalla3()
        }
        composable(Destinations.PantallaQR.route) {
            PantallaQR()
        }

        composable(Destinations.Pantalla4.route) {
            Pantalla4()
        }

        composable(Destinations.PersonaUI.route){
            PersonaUI(navegarEditarPer = { newText ->
                navController.navigate(Destinations.PersonaForm.passId(newText))
            }
            )
        }
        composable(
            Destinations.PersonaForm.route,
            arguments = listOf(navArgument("perId") {
                defaultValue =
                    "perId"
            })
        ) { navBackStackEntry ->
            var perId =
                navBackStackEntry.arguments?.getString("perId")
            requireNotNull(perId)
            PersonaForm(perId, darkMode, navController)
        }



    }
}