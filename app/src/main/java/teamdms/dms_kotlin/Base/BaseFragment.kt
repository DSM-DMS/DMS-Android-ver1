package teamdms.dms_kotlin.Base

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import teamdms.dms_kotlin.Fragment.ObjectiveFragment
import teamdms.dms_kotlin.RecyclerAdapter.ObjectiveRecyclerAdapter

/**
 * Created by 윤정현 on 2018-02-03.
 */


abstract class BaseFragment : Fragment() {
    abstract fun sendAnswer(answer : String?) : Boolean
}