package team_dms.dms.Base

import android.app.*
import android.content.*
import android.widget.*

/**
 * Created by root1 on 2017. 11. 23..
 */
object Util {

    fun showToast(context: Context, message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showDialog(context: Context, title: String): AlertDialog.Builder{
        return AlertDialog.Builder(context).setTitle(title)
    }

    private fun getPref(context: Context): SharedPreferences{
        val pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref
    }

    fun saveToken(context: Context, token: String){
        val editor = getPref(context).edit()
        editor.putString("token", token)
        editor.commit()
    }

    fun removeToken(context: Context){
        val editor = getPref(context).edit()
        editor.remove("token")
        editor.commit()
    }

    fun getToken(context: Context): String{
        return "JWT " + getPref(context).getString("token", "")
    }

}