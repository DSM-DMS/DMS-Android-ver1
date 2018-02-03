package team_dms.dms.Base

import android.app.*
import android.content.*
import android.widget.*
import teamdms.dms_kotlin.*



/**
 * Created by root1 on 2017. 11. 23..
 */
object Util {

    val classNameArr = arrayOf("가온실", "나온실", "다온실", "라온실", "3층 독서실", "4층 독서실", "열린교실")
    val noticeIDs = arrayOf("rule","notice","faq")

    val noticeTitles = arrayOf("기숙사 규정","공지사항","자주하는 질문", "설문조사")
    val noticeIcons =  arrayOf(R.drawable.notice_list_icon1, R.drawable.notice_list_icon2, R.drawable.notice_list_icon3)
    val noticeImages = arrayOf(R.drawable.notice_rule_icon,R.drawable.notice_notification_icon,R.drawable.notice_facility_icon)

    val introDeveloperTitle = arrayOf(R.string.mobile_intro_title,R.string.web_intro_title,R.string.server_intro_title,R.string.window_intro,R.string.designer_intro)
    val introDeveloperContent = arrayOf(R.string.mobile_intro_content,R.string.web_intro_content,R.string.server_intro_content,R.string.window_content,R.string.designer_content)
    val introDeveloperIcon= arrayOf(R.drawable.ic_mobile,R.drawable.ic_front,R.drawable.ic_server,R.drawable.ic_desktop,R.drawable.ic_design)

    val introBacks = arrayOf(R.drawable.intro_background_one,R.drawable.intro_background_two)

    fun showToast(context: Context, message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    fun showDialog(context: Context, title: String): AlertDialog.Builder = AlertDialog.Builder(context).setTitle(title)

    fun showCustomDialog(dialog: Dialog,type : Int){
        dialog.window.attributes.windowAnimations = type
        dialog.show()
    }

    private fun getPref(context: Context): SharedPreferences{
        val pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
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