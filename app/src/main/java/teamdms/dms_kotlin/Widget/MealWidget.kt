package teamdms.dms_kotlin.Widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.graphics.ColorSpace
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import team_dms.dms.Base.Util
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import team_dms.dms.Model.MealModel

import teamdms.dms_kotlin.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Implementation of App Widget functionality.
 */
// 하루 간격으로 업데이트 따라서 onRecieve 코드가 필요없음

class MealWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {

        for (appWidgetId in appWidgetIds) {
            Connector.api.loadMeal(getDate("yyyy-MM-dd")).enqueue(object : Res<MealModel>(context) { //급식 통신
                override fun callBack(code: Int, body: MealModel?) {

                    when (code) {
                        200 -> {
                            updateAppWidget(context, appWidgetManager, appWidgetId,
                                    arrayOf(
                                            body!!.getData()[0].replace(", ", "\n"), // 개행문자를 넣어줌
                                            body!!.getData()[1].replace(", ", "\n"),
                                            body!!.getData()[2].replace(", ", "\n")))
                        }
                        204 -> updateAppWidget(context, appWidgetManager, appWidgetId, arrayOf("급식이 없습니다","급식이 없습니다","급식이 없습니다"))
                    }
                }
            })
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object { // 이 블록 안에서 바인딩 코드를 다 짜야 함 이 블록 밖에 작업할시에 접근 불가능

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int, data : Array<String>) {
            val view = RemoteViews(context.packageName, R.layout.widget_meal)

            view.setTextViewText(R.id.text_widget_date, getDate("yyyy-MM-dd")) // 날짜 바인딩
            view.setTextViewText(R.id.text_widget_week, getDate("EEEE"))

            view.setTextViewText(R.id.text_widget_breakfast, data[0]) // 급식 바인딩
            view.setTextViewText(R.id.text_widget_lunch, data[1])
            view.setTextViewText(R.id.text_widget_dinner, data[2])

            appWidgetManager.updateAppWidget(appWidgetId, view) // 위젯 업데이트
        }

        private fun getDate (format : String) : String {
            val date = Date()
            val sdf = SimpleDateFormat(format, Locale.KOREA)
            val dateString : String = sdf.format(date)

            return dateString
        }
    }
}