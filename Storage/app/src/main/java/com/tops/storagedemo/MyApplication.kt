package com.tops.storagedemo

import android.app.Application
import com.tops.storagedemo.db.AppDatabase

class MyApplication : Application() {
    init {
        instance = this
    }
    companion object {
        private var instance: MyApplication? = null

        val database by lazy { AppDatabase.getInstance(instance!!.applicationContext) }
    }

}