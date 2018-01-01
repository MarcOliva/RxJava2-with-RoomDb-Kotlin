package com.rxjava.with.roomdb.marc.oliva.roomdbwithrxjava.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by ThinkSoft on 30/12/2017.
 */
@Database(entities = [(Car::class)],version = 1,exportSchema = false)
abstract class CarDataBase : RoomDatabase() {
    companion object {
        private var INSTANCE: CarDataBase? = null
        fun getDataBase(context: Context): CarDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, CarDataBase::class.java, "cars-db")
                        .allowMainThreadQueries().build()
            }
            return INSTANCE as CarDataBase
        }
    }

    abstract fun daoCar(): DaoCar
}