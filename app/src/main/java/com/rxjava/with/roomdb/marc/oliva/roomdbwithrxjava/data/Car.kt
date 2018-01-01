package com.rxjava.with.roomdb.marc.oliva.roomdbwithrxjava.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by ThinkSoft on 30/12/2017.
 */
@Entity(tableName = "cars")
data class Car (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "idCar")
        var id : Int,
        @ColumnInfo(name = "name")
        var name: String,
        @ColumnInfo(name = "price")
        var price : Int
)