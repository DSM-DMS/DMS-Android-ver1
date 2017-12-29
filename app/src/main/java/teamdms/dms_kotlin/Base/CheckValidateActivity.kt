package teamdms.dms_kotlin.Base

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import team_dms.dms.Base.BaseActivity
import teamdms.dms_kotlin.R

/**
 * Created by dsm2017 on 2017-12-27.
 */
open class CheckValidateActivity : BaseActivity() {

    protected fun changeColor (state : Boolean, text : TextView) = text.setTextColor(resources.getColor(if(state) R.color.done else R.color.warning))

    protected fun changeImage (state : Boolean, imageView : ImageView) = imageView.setImageResource(if(state) R.drawable.signup_check_validate_done else R.drawable.signup_check_validate_warning)

    protected fun setButtonValidate (state : Boolean, button : Button) {
        button.isEnabled = state
        button.isClickable = state
    }

    abstract class textWatcher : TextWatcher {

        override fun afterTextChanged(s: Editable?) {

        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }


    }
}