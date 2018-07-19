package teamdms.dms_kotlin.Widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import team_dms.dms.Base.Util
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import team_dms.dms.Model.MealModel
import teamdms.dms_kotlin.Activity.MainActivity
import teamdms.dms_kotlin.Fragment.MealContentFragment

import teamdms.dms_kotlin.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

/**
 * Implementation of App Widget functionality.
 */
class MealWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {

        val map = hashMapOf<String, String>()
        SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).let {
            map["date"] = it.format(Date(System.currentTimeMillis()))
            Connector.api.loadMeal(it.format(Date(System.currentTimeMillis()))).enqueue(object : Res<MealModel>(context) {
                override fun callBack(code: Int, body: MealModel?) {
                    val meal = body!!.getData()
                    map["breakfast"] = meal[0].replace(", ", "\n")
                    map["lunch"] = meal[1].replace(", ", "\n")
                    map["dinner"] = meal[2].replace(", ", "\n")

                    for (appWidgetId in appWidgetIds) {
                        updateAppWidget(context, appWidgetManager, appWidgetId, map)
                    }
                }
            })
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    fun update(map: HashMap<String, String>) {

    }

    companion object {

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int, map: HashMap<String, String>) {
            val views = RemoteViews(context.packageName, R.layout.widget_meal)
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            with(views) {
                setTextViewText(R.id.text_widget_date, map["date"])
                setTextViewText(R.id.text_widget_breakfast, map["breakfast"])
                setTextViewText(R.id.text_widget_lunch, map["lunch"])
                setTextViewText(R.id.text_widget_dinner, map["dinner"])
                setOnClickPendingIntent(R.id.linear_widget, pendingIntent)
            }
//            views.setTextViewText(R.id.appwidget_text, widgetText)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

    }
}

