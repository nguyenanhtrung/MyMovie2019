package com.example.mymovie2019.data.local.model


sealed class ItemType {

    object Normal : ItemType()
    object ListLoading : ItemType()
    object ItemLoading : ItemType()

}