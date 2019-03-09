package com.example.mymovie2019.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface BaseDao<T> {
    @Insert
    fun insertDatas(datas : List<T>)

    @Delete
    fun deleteData(data : T): Int
}