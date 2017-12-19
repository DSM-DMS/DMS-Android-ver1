package teamdms.dms_kotlin.Activity

import android.os.*
import android.support.v4.content.*
import android.text.*
import kotlinx.android.synthetic.main.activity_singup.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)

        button_signup_singup.isClickable = false
        button_signup_singup.isEnabled = false

        // 글자가 입력될 때마다 유효성 검사를 한다
        edit_signup_code.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                checkValidate()
            }
        })

        edit_signup_confirm_pw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                checkValidate()
            }
        })

        edit_signup_id.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                checkValidate()
            }
        })

        edit_signup_pw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                checkValidate()
            }
        })

        button_signup_singup.setOnClickListener {

            Connector.api.signUp(edit_signup_code.text.trim().toString(), edit_signup_id.text.trim().toString(), edit_signup_pw.text.trim().toString())
                    .enqueue(object : Res<Void>(this) {
                        override fun callBack(code: Int, body: Void?) {

                            showToast(when(code) {

                                201 -> {
                                    finish()
                                    "회원가입을 성공하셨습니다."
                                } 400 -> "회원가입에 실패하셨습니다"
                                else -> "오류 : $code"
                            })
                        }
                    })
        }
    }

    private fun checkValidate ()  {

        val done = ContextCompat.getColor(this, R.color.done)
        val warning = ContextCompat.getColor(this, R.color.warning)

        if(edit_signup_code.text.isEmpty() || edit_signup_confirm_pw.text.isEmpty() || edit_signup_id.text.isEmpty() || edit_signup_pw.text.isEmpty()) {
            text_signup_check_pw.text = "모두 입력하세요"
            button_signup_singup.isEnabled = false
            button_signup_singup.isClickable = false
            text_signup_check_pw.setTextColor(warning)
            image_signup_check_pw.setImageResource(R.drawable.signup_check_validate_warning)
        } else if(edit_signup_pw.text.toString() != edit_signup_confirm_pw.text.toString()) {
            text_signup_check_pw.text = "비밀번호가 일치하지 않습니다."
            button_signup_singup.isClickable = false
            button_signup_singup.isEnabled = false
            text_signup_check_pw.setTextColor(warning)
            image_signup_check_pw.setImageResource(R.drawable.signup_check_validate_warning)
        } else {
            text_signup_check_pw.text = "회원가입을 하실 수 있습니다"
            button_signup_singup.isEnabled = true
            button_signup_singup.isClickable = true
            text_signup_check_pw.setTextColor(done)
            image_signup_check_pw.setImageResource(R.drawable.signup_check_validate_done)
        }
    }
}
