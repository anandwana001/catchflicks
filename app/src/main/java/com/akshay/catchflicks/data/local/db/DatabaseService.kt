package com.akshay.catchflicks.data.local.db

import androidx.room.RoomDatabase
import javax.inject.Singleton

/**
 * Created by akshaynandwana on
 * 23, December, 2019
 **/

@Singleton
abstract class DatabaseService : RoomDatabase() {
}