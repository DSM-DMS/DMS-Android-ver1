package team_dms.dms.Base

import android.app.*
import android.content.*
import android.widget.*
import teamdms.dms_kotlin.*
import java.util.logging.Handler


/**
 * Created by root1 on 2017. 11. 23..
 */
object Util {

    val classNameArr = arrayOf("가온실", "나온실", "다온실", "라온실", "3층 독서실", "4층 독서실", "열린교실")
    val noticeIDs = arrayOf("rule","notice","faq")
    val noticeTitles = arrayOf("기숙사 규정","공지사항","자주하는 질문", "설문조사")
    val noticeIcons =  arrayOf(R.drawable.notice_list_icon1, R.drawable.notice_list_icon2, R.drawable.notice_list_icon3)
    val noticeImages = arrayOf(R.drawable.notice_rule_icon,R.drawable.notice_notification_icon,R.drawable.notice_facility_icon)

    val introDeveloperTitle = arrayOf(R.string.designer_intro_title,R.string.mobile_intro_title,R.string.web_intro_title,R.string.server_intro_title,R.string.window_intro_title)
    val introDeveloperNames = arrayOf(R.string.mobile_intro_content,R.string.web_intro_content,R.string.server_intro_content,R.string.window_content,R.string.designer_content)
    val introDeveloperIcon= arrayOf(R.drawable.ic_design,R.drawable.ic_mobile,R.drawable.ic_front,R.drawable.ic_server,R.drawable.ic_desktop)

    private val introDeveloperApp= arrayOf("-이병찬","윤정현-","-이성현")
    private val introDeveloperWeb= arrayOf("-김건", "서윤호-","-윤효상","오인서-","-김형규")
    private val introDeveloperServer= arrayOf("-조민규","인상민-")
    private val introDeveloperWindow= arrayOf("-김동현","이종현-","-류근찬")
    private val introDeveloperDesign= arrayOf("-김동규")

    private val introDeveloperAppContent= arrayOf("iOS 메인, Android 메인 개발","Android 메인 개발","Android 서브 개발")
    private val introDeveloperWebContent= arrayOf("Web 학생 페이지 개발","Web 학생 페이지 개발","Web 관리자 페이지 개발","Web 관리자 페이지 개발","Web 관리자 페이지 개발")
    private val introDeveloperServerContent=arrayOf("DMS 서버 개발","DMS 서버 개발")
    private val introDeveloperWindowContent= arrayOf("관리자 App 메인 개발","관리자 App 서브 개발","급식 위젯 개발, 영상 제작")
    private val introDeveloperDesignContent= arrayOf("DMS 총괄 메인 디자이너")

    private val introDeveloperAppImage= arrayOf(R.drawable.ic_mobile,R.drawable.ic_mobile,R.drawable.ic_mobile)
    private val introDeveloperWebImage= arrayOf(R.drawable.ic_front,R.drawable.ic_front,R.drawable.ic_front,R.drawable.ic_front,R.drawable.ic_front)
    private val introDeveloperServerImage=arrayOf(R.drawable.ic_server,R.drawable.ic_server)
    private val introDeveloperWindowImage= arrayOf(R.drawable.ic_desktop,R.drawable.ic_desktop,R.drawable.ic_desktop)
    private val introDeveloperDesignImage= arrayOf(R.drawable.ic_design)

    val introConfirmName= arrayOf(introDeveloperDesign, introDeveloperApp,introDeveloperWeb,introDeveloperServer,introDeveloperWindow)
    val introConfirmContent= arrayOf(introDeveloperDesignContent, introDeveloperAppContent, introDeveloperWebContent, introDeveloperServerContent, introDeveloperWindowContent)
    val introConfirmImage =  arrayOf(introDeveloperDesignImage,introDeveloperAppImage,introDeveloperWebImage,introDeveloperServerImage,introDeveloperWindowImage)

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

    fun saveToken(context: Context, token: String, isAccess: Boolean = true){
        val editor = getPref(context).edit()
        editor.putString(getKey(isAccess), token)
        editor.commit()
    }

    fun removeToken(context: Context, isAccess: Boolean = true){
        val editor = getPref(context).edit()
        editor.remove(getKey(isAccess))
        editor.commit()
    }

    fun getToken(context: Context, isAccess: Boolean = true): String{
        return "JWT " + getPref(context).getString(getKey(isAccess), "")
    }

    private fun getKey(isAccess: Boolean): String = if (isAccess) "Access" else "Refresh"

    fun delayHandler(runnable: Runnable, delayTime: Long) {
        var handler = android.os.Handler()
        handler.postDelayed(runnable, delayTime)
    }
}