package teamdms.dms_kotlin.Activity

import android.os.*
import android.support.design.widget.*
import android.support.v4.view.*
import android.support.v7.app.*
import kotlinx.android.synthetic.main.activity_main.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Model.*
import teamdms.dms_kotlin.ViewPagerAdapter.*

/**
 * Created by root1 on 2017. 11. 25..
 */
class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tab.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view_pager_main))
        view_pager_main.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab))

        view_pager_main.adapter = MainViewPagerAdapter(supportFragmentManager)
        view_pager_main.offscreenPageLimit = 4
        view_pager_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) { }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }
            override fun onPageSelected(position: Int) {
                (view_pager_main.adapter as MainViewPagerAdapter).reload(position)
            }
        })

        tokenRefresh()
    }

    private fun tokenRefresh(){
        Connector.api.refreshToken(getToken(false))
                .enqueue(object : Res<AuthModel>(this) {
                    override fun callBack(code: Int, body: AuthModel?) {
                        when(code){
                            200 -> { saveToken(body!!.token); null }
                            205 -> { "다시 로그인 하세요" }
                            else -> { "오류 : $code" }
                        }.let { if(it != null){ showToast(it!!) } }
                    }
                })
    }

    private val version = "1.6.4"

    override fun onStart() {
        super.onStart()
        Connector.api.versionCheck("android").enqueue(object : Res<VersionModel>(this){
            override fun callBack(code: Int, body: VersionModel?) {
                if (code == 200){
                    if (body!!.newest_version != version){
                        showAlert()
                    }
                }
            }
        })
    }

    private fun showAlert(){
        AlertDialog.Builder(this, R.style.AlertTheme)
                .setTitle("업데이트가 필요합니다.")
                .setMessage("DMS의 새로운 업데이트가 준비되었습니다.\n지금 업데이트 하세요.")
                .setPositiveButton("확인", { dialog, which ->
                    dialog.dismiss()
                }).create().show()
    }

    override fun onRestart() {
        super.onRestart()
        (view_pager_main.adapter as MainViewPagerAdapter).reload(view_pager_main.currentItem)
    }

}