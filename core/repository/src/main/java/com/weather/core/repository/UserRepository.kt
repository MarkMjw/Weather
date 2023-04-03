package com.weather.core.repository

import com.eweather.core.datastore.LocalUserPreferences
import com.weather.model.Coordinate
import com.weather.model.TemperatureUnits
import com.weather.model.WindSpeedUnits
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

interface UserRepository {
    fun getFavoriteCityCoordinate(): Flow<Coordinate?>

    fun getTemperatureUnitSetting(): Flow<TemperatureUnits?>

    fun getWindSpeedUnitSetting(): Flow<WindSpeedUnits?>
    suspend fun setFavoriteCityCoordinate(value: String)

    suspend fun setTemperatureUnitSetting(tempUnit: TemperatureUnits)

    suspend fun setWindSpeedUnitSetting(windSpeedUnit: WindSpeedUnits)

}