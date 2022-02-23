package com.example.entrevistatfhka.data.repository.Dao

import androidx.room.*
import com.example.entrevistatfhka.data.domain.ModelRecibo
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoRecibo {

    @Query("SELECT * FROM recibo_table")
    fun getListRecibo(): Flow<MutableList<ModelRecibo>>

    @Insert
    suspend fun insert(recibo: ModelRecibo)

    @Query("DELETE FROM recibo_table")
    suspend fun deleteAll()

}