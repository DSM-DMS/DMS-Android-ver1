package teamdms.dms_kotlin.Support

import android.support.v4.view.ViewPager
import android.view.View

/**
 * Created by root1 on 2017. 11. 26..
 */
class ZoomOutPageTransformer: ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        val pageWidth = page.width
        val parentViewPager = page.parent as ViewPager
        var varPosition = position

        varPosition -= parentViewPager.paddingRight / (pageWidth as Float)


    }
}