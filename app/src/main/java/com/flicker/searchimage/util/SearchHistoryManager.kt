package com.flicker.searchimage.util

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SearchHistoryManager(context: Context) {

    private val preferences = context.getSharedPreferences("Search_Image", Context.MODE_PRIVATE)

    fun setList(text: String){
        val oldList = getList()
        oldList.add(text)
        val gson = Gson()
        val json = gson.toJson(oldList) //converting list to Json
        val editor = preferences.edit()
        editor.putString("searchList",json)
        editor.apply()
    }


    //getting the list from shared preference
    fun getList():ArrayList<String>{
        val gson = Gson()
        val json = preferences.getString("searchList",null)
        return if (json != null) {
            val type = object : TypeToken<ArrayList<String>>(){}.type
            gson.fromJson(json,type)
        } else {
            ArrayList()
        }
    }


}