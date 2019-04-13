package com.example.shakil.kotlinrvgplay.Interface

import com.example.shakil.kotlinrvgplay.Model.ItemGroup

interface IFirebaseloadListener {

    fun onFirebaseLoadSuccess(itemGroupList: List<ItemGroup>)
    fun onFirebaseLoadFailed(message: String)
}