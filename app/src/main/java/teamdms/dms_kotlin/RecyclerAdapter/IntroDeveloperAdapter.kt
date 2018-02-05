package teamdms.dms_kotlin.RecyclerAdapter

import android.content.*
import android.graphics.drawable.Drawable
import android.support.v7.widget.*
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.view_intro_developer_item_1.view.*
import kotlinx.android.synthetic.main.view_mypage_list_content.view.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*
import teamdms.dms_kotlin.Activity.*
import teamdms.dms_kotlin.Dialog.BugReportDialog
import teamdms.dms_kotlin.Fragment.*

/**
 * Created by root1 on 2017. 11. 30..
 */
class IntroDeveloperAdapter(val mConfirm: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var context: Context
    lateinit var inflater: LayoutInflater
    private var confirm: Int = 0

    init {
        confirm = mConfirm
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        inflater = LayoutInflater.from(parent?.context)
        context = parent!!.context


        return when (viewType) {
            0 -> {
                val view = inflater.inflate(R.layout.view_intro_developer_item_1, parent, false)
                IntroDeveloperFirstViewHolder(view)
            }
            1 -> {
                val view = inflater.inflate(R.layout.view_intro_developer_item_2, parent, false)
                IntroDeveloperSecondViewHolder(view)
            }
            else->{
                val view = inflater.inflate(R.layout.view_intro_developer_item_1, parent, false)
                IntroDeveloperFirstViewHolder(view)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        when(holder!!.itemViewType){
            0->    {
                var contentHolder = holder as IntroDeveloperFirstViewHolder
                contentHolder.bind(Util.introConfirmName[confirm][position], Util.introConfirmContent[confirm][position],Util.introConfirmImage[confirm][position])
            }
            1->{
                var contentHolder = holder as IntroDeveloperSecondViewHolder
                contentHolder.bind(Util.introConfirmName[confirm][position], Util.introConfirmContent[confirm][position],Util.introConfirmImage[confirm][position])
            }

        }

    }

    override fun getItemCount(): Int = Util.introConfirmName[confirm].size

    override fun getItemViewType(position: Int): Int = position % 2


    class IntroDeveloperFirstViewHolder(view: View) : IntroViewHolder(view) {

        lateinit var rootView: View

        init {
            rootView = view
        }

        var nameTextView: TextView = rootView.findViewById(R.id.developer_name)
        var contentTextView = rootView.findViewById<TextView>(R.id.developer_tell)
        var profileImage = rootView.findViewById<ImageView>(R.id.developer_profile)

        @Override
        override fun bind(name: String, content: String,image: Int) {
            with(rootView) {
                nameTextView.text = name
                contentTextView.text = content
                profileImage.setImageResource(image)
            }
        }
    }


    class IntroDeveloperSecondViewHolder(view: View) : IntroViewHolder(view) {

        lateinit var rootView: View

        init {
            rootView = view
        }

        var nameTextView: TextView = rootView.findViewById(R.id.developer_name)
        var contentTextView = rootView.findViewById<TextView>(R.id.developer_tell)
        var profileImage = rootView.findViewById<ImageView>(R.id.developer_profile)
        @Override
        override fun bind(name: String, content: String, image : Int) {
            with(rootView) {
                nameTextView.text = name
                contentTextView.text = content
                profileImage.setImageResource(image)
            }
        }
    }
}

abstract class IntroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(name: String, content: String,image : Int)
}

