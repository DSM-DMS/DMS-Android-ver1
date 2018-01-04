package teamdms.dms_kotlin.Activity

import android.graphics.*
import android.os.*
import android.support.design.widget.*
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.main.activity_apply_study.*
import kotlinx.android.synthetic.main.view_apply_study_bottom_sheet.view.*
import team_dms.dms.Base.*
import team_dms.dms.Connect.*
import teamdms.dms_kotlin.*

/**
 * Created by root1 on 2017. 12. 1..
 */
class ApplyStudyActivity: BaseActivity() {

    var classState = 1
    var timeState = 11
    var seatState = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_study)
        load()
        setBottomSheet()

        button_apply_study_change_room.setOnClickListener { bottomSheet.show() }
        button_apply_study_cancle.setOnClickListener{
            if(seatState>0) {
                Connector.api.cancleExtension(timeState.toString(), getToken()).enqueue(object : Res<Void> (this) {

                    override fun callBack(code: Int, body: Void?) {

                        when(code) {

                            200 -> {
                                Util.showToast(applicationContext, seatState.toString()+"시 연장이 취소되었습니다")
                                load()
                            }
                            else ->  {
                                Util.showToast(applicationContext, "연장신청을 하지 않으셨습니다.")
                            }
                        }
                    }
                })
            }
        }
        button_apply_study.setOnClickListener {
            if (seatState > 0){
                Connector.api.applyStudy(getToken(), timeState, classState, seatState)
                        .enqueue(object : Res<Void>(this){
                            override fun callBack(code: Int, body: Void?) {
                                showToast(when(code){
                                    201 -> {
                                        load()
                                        getString(R.string.all_apply_success)
                                    }204 -> getString(R.string.all_apply_time_fail)
                                    else -> "오류 : $code"
                                })
                            }
                        })
            }else{ showToast("자리를 선택하세요") }
        }

        toggle_group_apply_study_change_time.check(R.id.toggle_apply_study_time_11)
        toggle_group_apply_study_change_time.setOnCheckedChangeListener { _, id ->
            timeState = when (id){
                R.id.toggle_apply_study_time_11 -> 11
                else -> 12
            }
            load()
        }

    }

    lateinit var bottomSheet: BottomSheetDialog

    private fun setBottomSheet(){
        bottomSheet = BottomSheetDialog(this)
        val bottomSheetView = LayoutInflater.from(this).inflate(R.layout.view_apply_study_bottom_sheet, null)
        setBottomSheetView(bottomSheetView)
        bottomSheet.setContentView(bottomSheetView)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView.parent as View)
        bottomSheetBehavior.peekHeight = resources.getDimensionPixelSize(R.dimen.size_apply_study_bottom_sheet)
    }

    private fun setBottomSheetView(view: View){
        with(view){
            val tempLinearArr = arrayOf(linear_apply_study_bottom_sheet_seat_1, linear_apply_study_bottom_sheet_seat_2, linear_apply_study_bottom_sheet_seat_3,
                    linear_apply_study_bottom_sheet_seat_4, linear_apply_study_bottom_sheet_seat_5, linear_apply_study_bottom_sheet_seat_6, linear_apply_study_bottom_sheet_seat_7)
            button_apply_study_bottom_sheet.setOnClickListener { bottomSheet.dismiss() }
            for(tempLinearNum in tempLinearArr.indices){
                tempLinearArr[tempLinearNum].setOnClickListener {
                    classState = tempLinearNum + 1
                    load()
                    setRoomText(Util.classNameArr[tempLinearNum])
                    bottomSheet.dismiss()
                }
            }
        }
    }

    private fun setRoomText(name: String){
        button_apply_study_change_room.text = name
    }

    private fun load(){
        Connector.api.loadStudyMap(getToken(), timeState, classState)
                .enqueue(object : Res<Array<Array<Any>>>(this){
                    override fun callBack(code: Int, body: Array<Array<Any>>?) {
                        if(code == 200){ drawMap(body!!) }
                        else{ showToast("로드 실패 : $code") }
                    }
                })
    }

    var beforeButton: Button? = null

    private fun drawMap(mapData: Array<Array<Any>>){

        linear_apply_study_map.removeAllViews()

        val seatMargin = resources.getDimension(R.dimen.margin_apply_study_seat).toInt()
        val seatSize = resources.getDimension(R.dimen.size_apply_study_seat).toInt()

        for (horizonMapData in mapData){

            val horizonLayout = LinearLayout(this)
            horizonLayout.orientation = LinearLayout.HORIZONTAL
            val layoutParam = LinearLayout.LayoutParams(seatSize, seatSize)
            layoutParam.setMargins(seatMargin, seatMargin, seatMargin, seatMargin)

            for (seat in horizonMapData){
                horizonLayout.addView( when (seat) {
                    is Double -> {
                        if (seat > 0){
                            val seatButton = Button(this)
                            setText(seatButton, "${seat.toInt()}", R.drawable.apply_study_seat_no_icon)
                            seatButton.setOnClickListener { button ->
                                seatState = seat.toInt()
                                beforeButton?.setBackgroundResource(R.drawable.apply_study_seat_no_icon)
                                button.setBackgroundResource(R.drawable.apply_study_seat_select_icon)
                                beforeButton = button as Button
                            }
                            seatButton
                        }else{ Space(this) }
                    } is String -> {
                        val noSeatTextView = TextView(this)
                        setText(noSeatTextView, seat, R.drawable.apply_study_seat_yes_icon)
                        noSeatTextView
                    } else -> View(this)
                }, layoutParam)
            }

            linear_apply_study_map.addView(horizonLayout)

        }

    }

    private fun setText(textView: TextView, title: String, imageId: Int){
        textView.gravity = Gravity.CENTER
        textView.textSize = if(title.length > 3) 14f else 16f
        textView.text = title
        textView.setTextColor(Color.WHITE)
        textView.setBackgroundResource(imageId)
    }

}