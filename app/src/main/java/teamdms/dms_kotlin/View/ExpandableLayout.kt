package teamdms.dms_kotlin.View

import android.content.*
import android.util.*
import android.view.*
import android.view.animation.*
import android.widget.*

/**
 * Created by root1 on 2017. 12. 17..
 */
class ExpandableLayout: LinearLayout {

    constructor(context: Context): super(context)
    constructor(context: Context, attr: AttributeSet?): super(context, attr)

    private val MATCH_PARENT = LayoutParams.MATCH_PARENT
    private val WRAP_CONTENT = LayoutParams.WRAP_CONTENT

    val param = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    lateinit var selectedBottomView: View

    fun addContentView(topView: View, bottomView: View){
        topView.setOnClickListener {
            if(selectedBottomView != bottomView){
                hide(selectedBottomView)
                show(bottomView)
                selectedBottomView = bottomView
            }
        }
        addView(topView, param)
        addView(bottomView, param)
    }

    fun setInitView(view: View){
        show(view)
    }

    private fun hide(view: View){
        view.measure(MATCH_PARENT, WRAP_CONTENT)
        val initHeight = view.measuredHeight
        view.startAnimation(getAnimation { time ->
            if(time == 1f) view.visibility = View.GONE
            else{
                view.layoutParams.height = initHeight - (initHeight * time).toInt()
                view.requestLayout()
            }
        })

    }

    private fun show(view: View){
        selectedBottomView = view
        view.measure(MATCH_PARENT, WRAP_CONTENT)
        val upperHeight = view.measuredHeight
        view.layoutParams.height = 1
        view.visibility = View.VISIBLE
        view.startAnimation(getAnimation { time ->
            view.layoutParams.height = (upperHeight * time).toInt()
            view.requestLayout()
        })
    }

    private fun getAnimation(func: (Float) -> Unit): Animation{
        val animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) = func(interpolatedTime)

            override fun willChangeBounds(): Boolean = true
        }
        animation.duration = 100L
        return animation
    }

}