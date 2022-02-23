package com.example.entrevistatfhka.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*

import androidx.datastore.preferences.preferencesDataStore
import com.example.entrevistatfhka.data.domain.ModelPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


const val SETTING_PREFERENCES_NAME : String = "setting_preference"

class SettingPreference (private  val context :Context){

    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(SETTING_PREFERENCES_NAME)
        val USER_NAME_KEY = stringPreferencesKey("name")
        val LAST_NAME_KEY = stringPreferencesKey("apellido")
        val ID_DOCUMENT = intPreferencesKey("cedula")
        val RAZON_SOCIAL = stringPreferencesKey("razon_social")
        val TIPO_IDENTIFICADOR = stringPreferencesKey("tipo_identicador")
        val NUMERO_IDENTIFICADOR = intPreferencesKey("numero_identificador")

    }


    val userPreferences: Flow<ModelPreferences> = context.dataStore.data
        .catch { execption ->
            if (execption is IOException)
            {emit(emptyPreferences())
            }else {
                throw execption
            }
        }
        .map {  preferences ->
            val userName = preferences[USER_NAME_KEY]?:""
            val apellido = preferences[LAST_NAME_KEY]?:""
            val cedula = preferences[ID_DOCUMENT]?:0
            val razonSocial = preferences[RAZON_SOCIAL]?:""
            val tipoIdentificador = preferences[TIPO_IDENTIFICADOR]?:""
            val numeroIdentificador = preferences[NUMERO_IDENTIFICADOR]?:0


            ModelPreferences(
                userName = userName,
                apellido = apellido,
                cedula = cedula,
                razonSocialEmisor = razonSocial,
                tipoRif =tipoIdentificador,
                numeroDeRif=numeroIdentificador
            )
        }
    suspend fun guardarDatosUsuario(userName: String,lastName: String,idClient: Int) {

        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = userName
            preferences[LAST_NAME_KEY] = lastName
            preferences[ID_DOCUMENT] =idClient
        }
    }

    suspend fun guardaDatosIdentificador(razonSocialEmisor:String,tipoRif:String,numeroDeRif:Int) {

        context.dataStore.edit { preferences ->
            preferences[RAZON_SOCIAL] = razonSocialEmisor
            preferences[TIPO_IDENTIFICADOR] = tipoRif
            preferences[NUMERO_IDENTIFICADOR] = numeroDeRif
        }


    }
}

