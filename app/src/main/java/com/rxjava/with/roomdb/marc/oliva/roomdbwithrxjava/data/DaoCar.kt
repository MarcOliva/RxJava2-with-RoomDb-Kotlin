package com.rxjava.with.roomdb.marc.oliva.roomdbwithrxjava.data

import android.arch.persistence.room.*
import io.reactivex.Flowable

/**
 * Created by ThinkSoft on 30/12/2017.
 */
@Dao
interface DaoCar {
    @Query("select * from cars")
    fun getAllCars():Flowable<List<Car>>

    @Query("select * from cars where idCar in (:id)")
    fun getCarById(id: Int):Car

    @Query("delete from cars")
    fun deleteAllCars()
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCar(car: Car)

    @Update
    fun updateCar(car: Car)

    @Delete
    fun deleteCar(car: Car)

}