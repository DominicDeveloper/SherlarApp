package com.dominic.sherlarapp.Data

import android.content.Context
import android.content.SharedPreferences
import com.dominic.sherlarapp.Models.Poem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MySharedPreference {
        private const val NAME = "Cache"
        private const val MODE = Context.MODE_PRIVATE

        private lateinit var mySharedPreferences: SharedPreferences

        fun init(context: Context){
            mySharedPreferences = context.getSharedPreferences(NAME, MODE)

        }
        private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor)-> Unit){
            val editor = edit()
            operation(editor)
            editor.apply()
        }
        var list:ArrayList<Poem>
        get() = gsontoArray(mySharedPreferences.getString("poemList","[]")!!)
        set(value) = mySharedPreferences.edit{
            it.putString("poemList", listToGson(value))
        }
       fun gsontoArray(gsonString:String):ArrayList<Poem>{
           var list = ArrayList<Poem>()

           val type = object : TypeToken<List<Poem>>(){}.type
           list.addAll(Gson().fromJson(gsonString,type))

           return list
       }
    fun listToGson(list:ArrayList<Poem>):String{
        var gsonString = "[]"
        gsonString = Gson().toJson(list)
        return gsonString
    }

}