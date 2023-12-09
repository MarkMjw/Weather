package com.experiment.weather.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.weather.feature.managelocations.manageLocationsRoute
import com.weather.feature.managelocations.manageLocationsScreen
import com.weather.feature.managelocations.toManageLocations
import com.weather.feature.search.searchScreen
import com.weather.feature.search.toSearchScreen
import com.weather.feature.settings.settingsScreen
import com.weather.feature.settings.toSettings
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@FlowPreview
@ExperimentalAnimationApi
@Composable
fun WeatherNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Graph.Forecast.graph,
        modifier = modifier
    ) {
        homeNavGraph(
            navigateToManageLocations = {
                navController.toManageLocations(navOptions {
                })
            },
            navigateToSettings = {
                navController.toSettings()
            },
            navigateToOnboard = {
                navController.toSearchScreen()
            },
        )
        manageLocationsScreen(
            onNavigateToSearch = {
                navController.toSearchScreen(navOptions = navOptions {
                })
            },
            onBackPressed = {
                navController.popBackStack()
            },
            onItemSelected = { cityName ->
                navController.navigate(
                    route = Graph.Forecast.ForecastScreen,
                    navOptions = navOptions {
                    popUpTo(
                        route = Graph.Forecast.ForecastScreen,
                        popUpToBuilder = {
                            inclusive = true
                        }
                    )
                })
            }
        )
        searchScreen(onSearchItemSelected = {
            navController.toManageLocations(navOptions = navOptions {
                popUpTo(manageLocationsRoute){
                    inclusive = true
                }
            })
        })
        settingsScreen(onBackPress = {
            navController.popBackStack()
        })

    }
}

sealed class Screen(val route: String) {
    object MainForecast : Screen(route = "mainForecast?cityName={cityName}") {
        fun passString(cityName: String): String {
            return this.route.replace(
                "{cityName}", cityName
            )
        }
    }

    object Search : Screen(route = "search")
    object ManageLocation : Screen(route = "manageLocation")
    object Welcome : Screen(route = "welcome")
}

sealed class Graph(val graph: String) {
    object Forecast : Graph("forecast") {
        val ForecastScreen = "forecast?cityName={cityName}"
        fun passForecastArgument(cityName: String): String {
            return ForecastScreen.replace("{cityName}", cityName)
        }
    }

    object Search : Graph("search") {
        val ManageLocationScreen = "${graph}manageLocation"
        val SearchScreen = "${graph}search"
    }

    object GetStarted : Graph("getStarted") {
        val WelcomeScreen = "${graph}welcome"
        val ManageLocationScreen = "${graph}manageLocation"
        val SearchScreen = "${graph}search"
    }

    object Root : Graph("root") // this is show when user has no data yet
}