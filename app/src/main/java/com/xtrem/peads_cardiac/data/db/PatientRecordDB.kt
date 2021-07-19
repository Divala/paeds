package com.xtrem.peads_cardiac.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xtrem.peads_cardiac.data.records.PatientRecord

@Database(entities = [PatientRecord::class], version = 1, exportSchema = false)
abstract class PatientRecordDB : RoomDatabase() {
    abstract fun patientRecordDao(): PatientRecordDao

    companion object {
        @Volatile
        private var INSTANCE: PatientRecordDB? = null

        fun getDatabase(context: Context): PatientRecordDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    PatientRecordDB::class.java,
                    "patients_db"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}