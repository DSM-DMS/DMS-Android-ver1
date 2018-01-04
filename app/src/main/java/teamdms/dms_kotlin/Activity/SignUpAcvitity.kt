package teamdms.dms_kotlin.Activity

import android.os.*
import android.telecom.Call
import android.text.*
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_singup.*
import team_dms.dms.Base.Util
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Base.CheckValidateActivity

class SignUpActivity : CheckValidateActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)

        setButtonValidate(false, button_signup_singup)

        // 글자가 입력될 때마다 유효성 검사를 한다
        edit_signup_code.addTextChangedListener(object : textWatcher() {
            override fun afterTextChanged(s: Editable?) {
                checkValidate()
                checkOverlap()
            }
        })

        edit_signup_confirm_pw.addTextChangedListener(object : textWatcher() {
            override fun afterTextChanged(s: Editable?) {
                checkValidate()
                checkOverlap()
            }
        })

        edit_signup_id.addTextChangedListener(object : textWatcher () {
            override fun afterTextChanged(s: Editable?) {
                checkValidate()
                checkOverlap()
            }
        })

        edit_signup_pw.addTextChangedListener(object : textWatcher () {
            override fun afterTextChanged(s: Editable?) {
                checkValidate()
                checkOverlap()
            }
        })

        button_signup_singup.setOnClickListener {

            Connector.api.checkUUID(edit_signup_code.text.toString().trim()).enqueue(object : Res<Void>(this) {

                override fun callBack(code: Int, body: Void?) {
                    when(code) {
                        200 -> signUp()
                        else -> Util.showToast(applicationContext, "code가 유효하지 않습니다.")
                    }
                }
            })
        }
    }

    private fun checkValidate() {
        val signupId = edit_signup_id.text.toString().trim()
        val signupPw = edit_signup_pw.text.toString().trim()
        val signupCode = edit_signup_code.text.toString().trim()
        val signupConfirmPw = edit_signup_confirm_pw.text.toString().trim()

        if (signupCode.isEmpty() || signupId.isEmpty() || signupPw.isEmpty() || signupConfirmPw.isEmpty()) {
            text_signup_check_pw.text = "모두 입력하세요"
            setButtonValidate(false, button_signup_singup) // false 버튼 무효화
            changeColor(false, text_signup_check_pw) // false 빨간 색 텍스트
            changeImage(false, image_signup_check_pw) // false warning이미지
        } else if (signupConfirmPw != signupPw) {
            text_signup_check_pw.text = "비밀번호가 일치하지 않습니다."
            setButtonValidate(false, button_signup_singup)
            changeColor(false, text_signup_check_pw)
            changeImage(false, image_signup_check_pw)
        } else {
            text_signup_check_pw.text = "회원가입을 하실 수 있습니다"
            setButtonValidate(true, button_signup_singup) // true 버튼 활성화
            changeColor(true, text_signup_check_pw) // true 초록 색 텍스트
            changeImage(true, image_signup_check_pw) // true 체크 이미지
        }
    }

    fun checkOverlap() { // 아이디 중복 검사
        val signUpId = edit_signup_id.text.toString()
        if (!signUpId.isEmpty()) {
            Connector.api.checkOverlap(signUpId).enqueue(object : Res<Void>(this) {
                override fun callBack(code: Int, body: Void?) {
                    when (code) {
                        200 -> {
                            text_signup_check_id.text = "사용 가능한 아이디입니다."
                            changeColor(true, text_signup_check_id)
                            changeImage(true, image_signup_check_id)
                        }
                        204 -> {
                            text_signup_check_id.text = "아이디가 중복되었습니다."
                            setButtonValidate(false, button_signup_singup)
                            changeColor(false, text_signup_check_id)
                            changeImage(false, image_signup_check_id)
                        }
                    }
                }
            })
        }else{
            showToast("아이디를 입력하세요")
        }
    }

    fun signUp () { // 회원가입

        Connector.api.signUp(edit_signup_code.text.trim().toString(), edit_signup_id.text.trim().toString(), edit_signup_pw.text.trim().toString())
                .enqueue(object : Res<Void>(this) {
                    override fun callBack(code: Int, body: Void?) {
                        showToast(when (code) {
                            201 -> {
                                finish()
                                "회원가입을 성공하셨습니다."
                            }
                            400 -> "회원가입에 실패하셨습니다"
                            else -> "오류 : $code"
                        })
                    }
                })
    }
}
