package team_dms.dms.Base

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity

/**
 * Created by root1 on 2017. 11. 23..
 */

open class BaseActivity: AppCompatActivity(){

    fun showToast(message: String){
        Util.showToast(this, message)
    }

    private fun getPref(): SharedPreferences{
        val pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref
    }

    fun saveToken(token: String){
        val editor = getPref().edit()
        editor.putString("token", token)
        editor.commit()
    }

    fun removeToken(){
        val editor = getPref().edit()
        editor.remove("token")
        editor.commit()
    }

    fun getToken(): String{
        return "JWT " + getPref().getString("token", "")
    }
}