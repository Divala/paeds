package com.xtrem.peads_cardiac.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.xtrem.peads_cardiac.data.records.PatientRecord

@Dao
interface PatientRecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: PatientRecord)

    @Delete
    fun delete(data: PatientRecord)

    @Update
    fun update(data: PatientRecord)

    @Query("SELECT * FROM patient_records ORDER BY id DESC")
    fun getAllData(): LiveData<List<PatientRecord>>

    @Query("SELECT * FROM patient_records where synced = 0 AND uploadReady = 0 ORDER BY id DESC")
    fun getLastSavedRecord(): PatientRecord

    @Query("SELECT * FROM patient_records where synced = 0 AND uploadReady = 1 ORDER BY id DESC")
    fun getLastSavedRecordNotSync(): PatientRecord

    @Query("SELECT * FROM patient_records WHERE synced = 0 AND uploadReady = 1 ORDER BY id DESC LIMIT 1")
    fun getUploadReadyRecord(): LiveData<PatientRecord>

    @Query("SELECT * FROM patient_records WHERE synced = 0 AND uploadReady = 1 ORDER BY id DESC")
    fun getUploadReadyRecordList(): LiveData<List<PatientRecord>>

}