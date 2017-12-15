package teamdms.dms_kotlin.View

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

/**
 * Created by dsm2016 on 2017-12-15.
 */

class ExpandableLayout @JvmOverloads constructor(context: Context, attrs: android.util.AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr){


    init{

    }

    fun expand(v: View){
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)



    }
}
