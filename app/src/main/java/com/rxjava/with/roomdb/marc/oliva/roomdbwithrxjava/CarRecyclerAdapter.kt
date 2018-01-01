package com.rxjava.with.roomdb.marc.oliva.roomdbwithrxjava

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rxjava.with.roomdb.marc.oliva.roomdbwithrxjava.data.Car

/**
 * Created by ThinkSoft on 31/12/2017.
 */
class CarRecyclerAdapter(cars:ArrayList<Car>): RecyclerView.Adapter<CarRecyclerAdapter.RecyclerViewHolder>() {

    private var listCars: List<Car> = cars

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent!!.context)
                .inflate(R.layout.list_item,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        var currentCar: Car = listCars[position]

        holder.mName.text= currentCar.name
        holder.mPrice.text = currentCar.price.toString()
    }

    override fun getItemCount(): Int {
        return listCars.size
    }

    fun addCars( listCars : List<Car>){
        this.listCars = listCars
        //the most important here
        notifyDataSetChanged()
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mName = itemView.findViewById<TextView>(R.id.name_car)
        var mPrice = itemView.findViewById<TextView>(R.id.price_car)

    }
}