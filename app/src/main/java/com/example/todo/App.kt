package com.example.todo

import android.app.Application
import com.example.todo.data.DataBase

class App : Application() {

    lateinit var db: DataBase

    override fun onCreate() {
        super.onCreate()
        db = DataBase.getDataBase(this)
    }
}