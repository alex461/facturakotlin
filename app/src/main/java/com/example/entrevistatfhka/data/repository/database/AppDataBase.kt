package com.example.entrevistatfhka.data.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.entrevistatfhka.data.domain.ModelRecibo
import com.example.entrevistatfhka.data.repository.Dao.DaoRecibo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [ModelRecibo::class], version = 1,exportSchema = false)
abstract class AppDataBase: RoomDatabase(){

    abstract fun daoRecibo(): DaoRecibo


    private class AppDataBaseCallback(private val scope: CoroutineScope):RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        INSTANCE?.let { database ->
            scope.launch {
                populateDatabase(database.daoRecibo())
            }
        }


        }

        suspend fun populateDatabase(daoRecibo: DaoRecibo) {

            // Delete all content here.
          //  daoUsuario.()

            // Add sample words.



   /*         var usuario = Usuario("alex","ramirez")
            daoUsuario.insertarUsuario(usuario)
            usuario = Usuario("paola","cisnero")
            daoUsuario.insertarUsuario(usuario)
*/
            // TODO: Add your own words!


        }

    }




    companion object {

        private var INSTANCE: AppDataBase? = null

        //Para iniciar una corrutina, necesitas un CoroutineScope. se nececito Actualizar
        // el método getDatabase de la clase AppDataBase a fin de obtener también un
        // alcance de corrutina como parámetro:

        fun getDatabase(context: Context, scope: CoroutineScope): AppDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "practica_database"
                ).addCallback(AppDataBaseCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance

            }


        }

    }
    }