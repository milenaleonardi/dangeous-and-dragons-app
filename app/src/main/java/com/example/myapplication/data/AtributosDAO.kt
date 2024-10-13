package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.backend.Atributos

@Dao
interface AtributosDAO {

    @Insert
    suspend fun insert(atributos: Atributos)

    @Query("SELECT * FROM atributos")
    suspend fun getAllAtributos(): List<Atributos>

    @Delete
    suspend fun delete(atributos: Atributos)
}