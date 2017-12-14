package teamdms.dms_kotlin.Fragment

import android.os.*
import android.support.v4.app.*
import android.view.*
import kotlinx.android.synthetic.main.fragment_meal.view.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.ViewPagerAdapter.*


/**
 * Created by root1 on 2017. 11. 25..
 */

class MealFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_meal, null)
        init(view!!)
        return view
    }

    private fun init(view: View){
        with(view){
            view_pager_meal.clipToPadding = false
            view_pager_meal.pageMargin = 64
            view_pager_meal.setPadding(64,64,64,64)
            view_pager_meal.adapter = MealViewPagerAdapter(childFragmentManager)
        }
    }

}
