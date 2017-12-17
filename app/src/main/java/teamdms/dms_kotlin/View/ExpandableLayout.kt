package teamdms.dms_kotlin.View

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.LinearLayout

/**
 * Created by dsm2016 on 2017-12-15.
 */

class ExpandableLayout @JvmOverloads constructor(context: Context, attrs: android.util.AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr){


    private val DURATION = 200L
    private var selectParentView : View? = null
    private var selectChildView : View? =null
    private var viewRemain : Boolean = false

    fun hello(){
        Log.d("xxx", "Xxx")
    }

    fun addViewExpand(parentView: View, childView: View) {
        addView(parentView, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))

        parentView!!.setOnClickListener{
            when(parentView.isSelected){
                true -> {
                    collapse(childView!!)
                    selectParentView =null
                    selectChildView = null
                    viewRemain=true
                }
                else ->  {
                    expand(parentView)
                    if(viewRemain){
                        collapse(selectChildView!!)
                    }
                    selectParentView=parentView
                    selectChildView=childView
                    viewRemain=false
                }
            }
        }
        childView!!.visibility = View.GONE
        addView(childView,LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT))

    }

    private fun expand(v: View){
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        val initialHeight = v.measuredHeight
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                v.layoutParams.height = (initialHeight * interpolatedTime).toInt()
                v.requestLayout()
            }
            override fun willChangeBounds(): Boolean {
                return true
            }
        }
        a.duration = DURATION
        v.startAnimation(a)
    }

    private fun collapse(v: View) {
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val initialHeight = v.measuredHeight
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) { //애니메이션이 끌날때 childView제거
                    v.visibility = View.GONE
                } else {
                    v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }
            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        a.duration = DURATION
        v.startAnimation(a)
    }
}
