package teamdms.dms_kotlin.ViewModel

import android.content.Context
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import team_dms.dms.Base.Util
import team_dms.dms.Base.Util.showToast
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import teamdms.dms_kotlin.Base.Navigator

class SignUpViewModel(val navigator: Navigator) {

    val code = ObservableField<String>("")
    val id = ObservableField<String>("")
    val password = ObservableField<String>("")
    val confirm = ObservableField<String>("")

    val checkId = ObservableField("")
    val checkPw = ObservableField("")

    val checkIdBoolean = ObservableInt(-1) // 1: 초기값 0: 거짓 1: 참
    val checkPwBoolean = ObservableInt(-1) // -1: 초기값 0: 거짓 1: 참


    fun checkCode(view: View) {
        navigator.finishActivity()
        if (confirm.get().trim() == password.get().trim()) {
            Connector.api.checkUUID(code.get().toString().trim()).enqueue(object : Res<Void>(view.context) {
                override fun callBack(code: Int, body: Void?) {
                    when (code) {
                        200 -> signUp(view)
                        else -> Util.showToast(view.context, "code가 유효하지 않습니다.")
                    }
                }
            })
        }
    }

    fun check(context: Context) {
        checkValidate()
        checkOverlap(context)
    }

    fun checkValidate() {
        val signupId = id.get()
        val signupPw = password.get()
        val signupCode = code.get()
        val signupConfirmPw = confirm.get()
        if (signupCode.isEmpty() || signupId.isEmpty() || signupPw.isEmpty() || signupConfirmPw.isEmpty()) {
            checkPw.set("모두 입력하세요")
            checkPwBoolean.set(0)
        } else if (signupConfirmPw != signupPw) {
            checkPw.set("비밀번호가 일치하지 않습니다.")
            checkPwBoolean.set(0)
        } else {
            checkPw.set("회원가입을 하실 수 있습니다")
            checkPwBoolean.set(1)
        }
    }

    fun checkOverlap(context: Context) { // 아이디 중복 검사
        val signUpId = id.get().toString()
        val signUpCode = code.get().toString()
        if (!signUpId.isEmpty()) {
            Connector.api.checkOverlap(signUpId).enqueue(object : Res<Void>(context) {
                override fun callBack(code: Int, body: Void?) {
                    when (code) {
                        200 -> {
                            checkId.set("사용 가능한 아이디입니다.")
                            checkIdBoolean.set(1)
                        }
                        204 -> {
                            checkId.set("아이디가 중복되었습니다.")
                            checkIdBoolean.set(0)
                        }
                    }
                }
            })
        } else if (!signUpCode.isEmpty()) {
            checkId.set("아이디를 입력해주세요")
            checkIdBoolean.set(0)
        }
    }

    fun signUp(view: View) { // 회원가입
        Connector.api.signUp(code.get().trim(), id.get().trim(), password.get().trim())
                .enqueue(object : Res<Void>(view.context) {
                    override fun callBack(code: Int, body: Void?) {
                        showToast((view.context), when (code) {
                            201 -> {
                                navigator.finishActivity()
                                "회원가입을 성공하셨습니다."
                            }
                            400 -> "회원가입에 실패하셨습니다"
                            else -> "오류 : $code"
                        })
                    }
                })
    }
}