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
        return Util.getToken(this)
    }
}