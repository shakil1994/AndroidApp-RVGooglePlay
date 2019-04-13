package com.example.shakil.androidrvgoogleplay.Interface;

import com.example.shakil.androidrvgoogleplay.Model.ItemGroup;

import java.util.List;

public interface IFirebaseloadListener {
    void onFirebaseLoadSuccess(List<ItemGroup> itemGroupList);
    void onFirebaseLoadFailed(String message);
}
