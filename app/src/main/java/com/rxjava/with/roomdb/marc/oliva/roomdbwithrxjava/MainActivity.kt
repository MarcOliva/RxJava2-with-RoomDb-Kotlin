package com.rxjava.with.roomdb.marc.oliva.roomdbwithrxjava

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.rxjava.with.roomdb.marc.oliva.roomdbwithrxjava.data.Car
import com.rxjava.with.roomdb.marc.oliva.roomdbwithrxjava.data.CarDataBase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var carRecyclerAdapter: CarRecyclerAdapter?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        carRecyclerAdapter = CarRecyclerAdapter(arrayListOf())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = carRecyclerAdapter
        add_new_car.setOnClickListener {
            addCar(name_edit_text.text.toString(),price_edit_text.text.toString().toInt())
        }

        CarDataBase.getDataBase(this).daoCar().getAllCars()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { listCars->
                    carRecyclerAdapter!!.addCars(listCars)
                }
    }
    fun addCar(name:String, price:Int){
        val car = Car(0,name,price)
        Single.fromCallable {
            CarDataBase.getDataBase(this).daoCar().insertCar(car)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.clear_list_action->deleteAllCars()
        }
        return super.onOptionsItemSelected(item)
    }
    fun deleteAllCars(){
        CarDataBase.getDataBase(this).daoCar().deleteAllCars()
    }
}
