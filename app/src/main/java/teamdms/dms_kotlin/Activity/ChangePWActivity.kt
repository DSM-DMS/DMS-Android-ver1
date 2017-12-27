package teamdms.dms_kotlin.Activity

import android.os.*
import android.support.v4.content.*
import android.text.*
import kotlinx.android.synthetic.main.activity_change_pw.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*

/**
 * Created by dsm2017 on 2017-12-18.
 */
/**
 * Created by dsm2017 on 2017-12-17.
 */

class ChangePWActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pw)

        button_changePW_apply.isEnabled = false
        button_changePW_apply.isClickable = false

        edit_changePW_existing_pw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                checkValidate()
            }
        })

        edit_changePW_new_pw.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                checkValidate()
            }
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

                        override fun callBack(code: Int, body : Void?) {

                            showToast(when(code) {

                                201 -> {
                                    finish()
                                    "비밀번호가 성공적으로 변경되었습니다."
                                }
                                403 -> "비밀번호 변경에 실패했습니다"
                                else -> "오류 $code "
                            })
                        }
                    })
        }
    }

    private fun checkValidate () {

        val done = ContextCompat.getColor(this, R.color.done)
        val warning = ContextCompat.getColor(this, R.color.warning)
        val existPw = edit_changePW_existing_pw.text.toString()
        val changePw = edit_changePW_new_pw.text.toString()
        val changeConfirmPw = edit_changePW_new_pw_confirm.text.toString()

        if(existPw.isEmpty() || changePw.isEmpty() || changeConfirmPw.isEmpty()) {

            button_changePW_apply.isClickable = false
            button_changePW_apply.isEnabled = false
            text_changePW_check_validate.text = "모두 다 입력해주세요"
            text_changePW_check_validate.setTextColor(warning)
            image_changePW_check_validate.setImageResource(R.drawable.signup_check_validate_warning)
        } else if (changePw != changeConfirmPw) {

            button_changePW_apply.isClickable = false
            button_changePW_apply.isEnabled = false
            text_changePW_check_validate.text = "새 비밀번호와 확인이 맞지 않습니다."
            text_changePW_check_validate.setTextColor(warning)
            image_changePW_check_validate.setImageResource(R.drawable.signup_check_validate_warning)
        } else {

            button_changePW_apply.isClickable = true
            button_changePW_apply.isEnabled = true
            text_changePW_check_validate.text = "비밀번호를 교체할 수 있습니다."
            text_changePW_check_validate.setTextColor(done)
            image_changePW_check_validate.setImageResource(R.drawable.signup_check_validate_done)

            if(existPw == changePw || existPw == changeConfirmPw) {

                button_changePW_apply.isClickable = false
                button_changePW_apply.isEnabled = false
                text_changePW_check_validate.text = "기존 비밀번호와 새 비밀번호가 일치합니다."
                text_changePW_check_validate.setTextColor(warning)
                image_changePW_check_validate.setImageResource(R.drawable.signup_check_validate_warning)
            }
        }
    }
}