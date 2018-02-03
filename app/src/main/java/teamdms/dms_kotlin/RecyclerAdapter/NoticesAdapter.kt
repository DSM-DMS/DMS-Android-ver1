package teamdms.dms_kotlin.RecyclerAdapter

import android.app.*
import android.content.*
import android.os.*
import android.support.v4.app.*
import android.support.v4.util.*
import android.support.v4.view.*
import android.support.v7.widget.*
import android.view.*
import android.view.animation.*
import android.widget.*
import kotlinx.android.synthetic.main.view_notice_item.view.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Activity.*
import teamdms.dms_kotlin.Model.*

/**
 * Created by dsm2016 on 2017-12-18.
 */


class NoticesAdapter(confirm: Int, activity: Activity) : RecyclerView.Adapter<NoticeViewHolder>() {

    private var lastPosition = -1
    lateinit var inflater: LayoutInflater
    lateinit var context: Context
    lateinit var activity: Activity
    var noticeArr = arrayOf<NoticeModel>()
    var confirm: Int = 0

    init {
        this.confirm = confirm
        this.activity = activity
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NoticeViewHolder {
        inflater = LayoutInflater.from(parent!!.context)
        context = parent.context

        val view = inflater.inflate(R.layout.view_notice_item, null)
        return NoticeViewHolder(view)

    }

    override fun onBindViewHolder(holder : NoticeViewHolder?, position: Int) {

        val intent = Intent(context, NoticeDetailActivity::class.java)
        val data = noticeArr[position!!]

        if(noticeArr != null) {
            holder!!.bind(data.title, data.write_date, {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    intent.putExtra("confirm", confirm)
                    intent.putExtra("noticeID", data.id)
                    intent.putExtra("noticeTitle", data.title)
                    //startActivity(holder,intent) //액티비티 애니메이션 적용할때
                    activity.startActivity(intent)
                } else {
                    intent.putExtra("confirm", confirm)
                    intent.putExtra("noticeID", data.id)
                    context.startActivity(intent)
                }
            })

            setAnimation(holder.rootView, position)
        }
    }

    override fun getItemCount(): Int = noticeArr.size

    fun setData(data: Array<NoticeModel>) {
        noticeArr = data!!.reversedArray()
        notifyDataSetChanged()
    }

    private fun setAnimation(view: View, position: Int) {
        val animation: Animation = if (position % 2 == 0) AnimationUtils.loadAnimation(context, R.anim.slide_left) else AnimationUtils.loadAnimation(context, R.anim.slide_right)
        view.startAnimation(animation)
    }

    private fun startActivity(viewHolder: NoticeViewHolder, intent: Intent) {
        //activity animations
        var pairs = arrayOf(Pair<View, String>(viewHolder.titleTextView, ViewCompat.getTransitionName(viewHolder.titleTextView))) //다음액티비티 이동시킬 객체 정의
        var options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, *pairs)
        activity.startActivity(intent, options.toBundle())
    }
}

class NoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var rootView: View

    init {
        rootView = itemView
    }

    var titleTextView: TextView = rootView.findViewById(R.id.text_notice_item_title)

    fun bind(title: String, date: String?, onClick: (Any) -> Unit) {
        with(rootView) {
            titleTextView.text = title
            text_notice_item_date.text = date
            card_notice_item.setOnClickListener(onClick)
        }
    }
}
