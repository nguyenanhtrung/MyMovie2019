package com.example.mymovie2019.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDatas(datas : List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDatasWithReplace(datas: List<T>)

    @Insert
    fun insertData(data: T)

    @Delete
    fun deleteData(data : T): Int
}