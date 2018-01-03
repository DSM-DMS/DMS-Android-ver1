package teamdms.dms_kotlin.View

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by 윤정현 on 2018-01-03.
 */
class UnScrollViewPager : ViewPager{


    private var onScroll =true


    constructor(context: Context): super(context)
    constructor(context: Context, attr: AttributeSet?): super(context, attr)

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return onScroll && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return this.onScroll && super.onInterceptTouchEvent(ev)
    }

    fun setPageingScroll(b :Boolean){
        this.onScroll=b
    }

}