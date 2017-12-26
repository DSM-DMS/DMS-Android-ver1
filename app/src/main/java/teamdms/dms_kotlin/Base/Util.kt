package team_dms.dms.Base

import android.app.*
import android.content.*
import android.widget.*
import teamdms.dms_kotlin.R

/**
 * Created by root1 on 2017. 11. 23..
 */
object Util {

    val classNameArr = arrayOf("가온실", "나온실", "다온실", "라온실", "3층 독서실", "4층 독서실", "열린교실")
    val notice = arrayOf("rule","notice","faq")
    val noticeTitles = arrayOf("기숙사 규정","공지사항","자주하는 질문")
    val noticeIcons =  arrayOf(R.drawable.notice_list_icon1, R.drawable.notice_list_icon2, R.drawable.notice_list_icon3)
    val noticeImages = arrayOf(R.drawable.notice_rule_icon,R.drawable.notice_notification_icon,R.drawable.notice_facility_icon)

    fun showToast(context: Context, message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    fun showDialog(context: Context, title: String): AlertDialog.Builder = AlertDialog.Builder(context).setTitle(title)


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