package teamdms.dms_kotlin.Activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.*
import android.text.*
import kotlinx.android.synthetic.main.activity_singup.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Base.*
import teamdms.dms_kotlin.ViewModel.SignUpViewModel
import teamdms.dms_kotlin.databinding.ActivitySingupBinding

class SignUpActivity : CheckValidateActivity(),Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySingupBinding>(this, R.layout.activity_singup)

        binding.setVariable(BR.signUpVm, SignUpViewModel(this))

    }

    override fun finishActivity() {
        finish()
    }

    override fun nextActivity(intent: Intent) {
        startActivity(intent)
    }
}
