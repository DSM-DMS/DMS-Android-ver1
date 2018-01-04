package teamdms.dms_kotlin.Activity

import android.os.*
import android.text.*
import kotlinx.android.synthetic.main.activity_change_pw.*
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Base.CheckValidateActivity

/**
 * Created by dsm2017 on 2017-12-18.
 */
/**
 * Created by dsm2017 on 2017-12-17.
 */

class ChangePWActivity : CheckValidateActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pw)

        setButtonValidate(false, button_changePW_apply) // 초기 설정

        edit_changePW_existing_pw.addTextChangedListener(object : textWatcher() { // 글 될 때마다 유효성 검사

            override fun afterTextChanged(s: Editable?) { checkValidate() }
        })

        edit_changePW_new_pw.addTextChangedListener(object : textWatcher() {

            override fun afterTextChanged(s: Editable?) { checkValidate() }
        })


        edit_changePW_new_pw_confirm.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkValidate()
            }
        })

        button_changePW_apply.setOnClickListener {

            Connector.api.changePW(getToken(), edit_changePW_existing_pw.text.trim().toString(), edit_changePW_new_pw.text.trim().toString())
                    .enqueue(object : Res<Void>(this) {

                        override fun callBack(code: Int, body: Void?) {

                            showToast(when (code) {
                                200 -> {
                                    finish()
                                    "비밀번호가 성공적으로 변경되었습니다."
                                }
                                else -> "오류 $code "
                            })
                        }
                    })
        }

    }

    private fun checkValidate() { // 유효성 검사 함수

        val existPw = edit_changePW_existing_pw.text.toString()
        val changePw = edit_changePW_new_pw.text.toString()
        val changeConfirmPw = edit_changePW_new_pw_confirm.text.toString()

        if (existPw.isEmpty() || changePw.isEmpty() || changeConfirmPw.isEmpty()) {
            setButtonValidate(false, button_changePW_apply) // false 버튼 무효화
            changeColor(false, text_changePW_check_validate) // false 빨간 색깔
            changeImage(false, image_changePW_check_validate) // false 경고 이미지
            text_changePW_check_validate.text = "모두 다 입력해주세요"
        } else if (changePw != changeConfirmPw) {
            setButtonValidate(false, button_changePW_apply)
            changeColor(false, text_changePW_check_validate)
            text_changePW_check_validate.text = "새 비밀번호와 확인이 맞지 않습니다."
            changeImage(false, image_changePW_check_validate)
        } else {
            changeColor(true, text_changePW_check_validate) // true 버튼 활성화
            setButtonValidate(true, button_changePW_apply) // true 초록 색깔
            changeImage(true, image_changePW_check_validate) // true 체크 이미지
            text_changePW_check_validate.text = "비밀번호를 교체할 수 있습니다."

            if (existPw == changePw || existPw == changeConfirmPw) {
                setButtonValidate(false, button_changePW_apply)
                changeColor(false, text_changePW_check_validate)
                changeImage(false, image_changePW_check_validate)
                text_changePW_check_validate.text = "기존 비밀번호와 새 비밀번호가 일치합니다."
            }
        }
    }
}