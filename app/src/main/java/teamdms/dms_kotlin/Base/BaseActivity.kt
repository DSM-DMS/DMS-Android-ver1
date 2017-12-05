package team_dms.dms.Base

import android.content.*
import android.support.v7.app.*

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
        Util.saveToken(this, token)
    }

    fun removeToken(){
        Util.removeToken(this)
    }

    fun getToken(): String{
        return "JWT " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MTI2MTU1MDUsImlkZW50aXR5IjoibmlsIiwiZnJlc2giOmZhbHNlLCJqdGkiOiJmYzY2YzQ5OC1jNDJmLTQxMDQtOGQxOS1kN2U2ZjkwNTI1YjciLCJuYmYiOjE1MTIzNTYzMDUsInR5cGUiOiJhY2Nlc3MiLCJpYXQiOjE1MTIzNTYzMDV9.T1h04LiVT8UKDYQDYgGBVukHxFJorbgXuhXlcLADlU8"
        //return Util.getToken(this)
    }
}