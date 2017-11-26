package teamdms.dms_kotlin.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_meal.view.*
import teamdms.dms_kotlin.R

/**
 * Created by root1 on 2017. 11. 25..
 */
class MealFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_meal, null)
        return view
    }

    private fun init(view: View){
        with(view){
            viewPager.pageMargin = 16
            viewPager.offscreenPageLimit = 3

        }
    }

}