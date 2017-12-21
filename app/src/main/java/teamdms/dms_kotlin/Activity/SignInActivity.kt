package teamdms.dms_kotlin.Activity

import android.content.*
import android.os.*
import com.google.gson.*
import kotlinx.android.synthetic.main.activity_signin.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*

/**
 * Created by root1 on 2017. 12. 5..
 */
class SignInActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        button_signin_signin.setOnClickListener {
            if (edit_signin_id.text.isEmpty() || edit_signin_pw.text.isEmpty()){
                showToast("값을 다 입력하세요.")
            }else{
                Connector.api.signIn(edit_signin_id.text.toString(), edit_signin_pw.text.toString())
                        .enqueue(object : Res<JsonObject>(this){
                            override fun callBack(code: Int, body: JsonObject?) {
                                showToast(when(code){
                                    201 -> {
                                        saveToken(body!!.get("access_token").asString)
                                        finish()
                                        "로그인 성공"
                                    } 204 -> "로그인 실패"
                                    else -> "오류 : $code"
                                })
                            }
                        })
            }
        }

        button_signin_go_to_signup.setOnClickListener { startActivity(Intent(this, SignUpActivity::class.java)) }

    }

}