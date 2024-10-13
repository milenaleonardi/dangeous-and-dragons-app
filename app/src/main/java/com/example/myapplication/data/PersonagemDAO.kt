package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.backend.Personagem

@Dao
interface PersonagemDAO {

    @Insert
    suspend fun insert(personagem: Personagem)

    @Query("SELECT * FROM personagem")
    suspend fun getAllPersonagens(): List<Personagem>

    @Delete
    suspend fun delete(personagem: Personagem)



}