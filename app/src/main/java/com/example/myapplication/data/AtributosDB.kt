package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.backend.Atributos
import com.example.myapplication.backend.Personagem

@Database(entities = [Atributos::class, Personagem::class], version = 2, exportSchema = false)
abstract class AtributosDB: RoomDatabase() {

    abstract fun atributosDAO(): AtributosDAO
    abstract fun personagemDAO(): PersonagemDAO

    companion object{

        @Volatile
        private var INSTANCIA: AtributosDB? =null

        fun getDatabase(context: Context): AtributosDB{
            return INSTANCIA ?: synchronized(this){
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    AtributosDB::class.java,
                    "atributosDB"
                ).fallbackToDestructiveMigration().build()
                INSTANCIA = instancia
                return instancia
            }
        }
    }
}