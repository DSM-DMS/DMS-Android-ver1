package teamdms.dms_kotlin.Activity

import android.content.*
import android.os.*
import team_dms.dms.Base.*

/**
 * Created by root1 on 2017. 11. 28..
 */
class SplashActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}