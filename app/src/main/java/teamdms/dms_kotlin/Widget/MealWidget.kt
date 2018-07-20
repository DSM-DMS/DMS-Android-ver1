package teamdms.dms_kotlin.Widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team_dms.dms.Connect.Connector
import team_dms.dms.Connect.Res
import team_dms.dms.Model.MealModel

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
            Connector.api.loadMeal(it.format(Date(System.currentTimeMillis()))).enqueue(object : Callback<MealModel> {
                override fun onResponse(call: Call<MealModel>?, response: Response<MealModel>?) {
                    val meal = response!!.body()!!.getData()
                    map["breakfast"] = meal[0].replace(", ", "\n")
                    map["lunch"] = meal[1].replace(", ", "\n")
                    map["dinner"] = meal[2].replace(", ", "\n")

                    for (appWidgetId in appWidgetIds) {
                        updateAppWidget(context, appWidgetManager, appWidgetId, map)
                    }
                }

                override fun onFailure(call: Call<MealModel>?, t: Throwable?) {
                    for (appWidgetId in appWidgetIds) {
                        updateAppWidget(context, appWidgetManager, appWidgetId, map)
                        Toast.makeText(context, "인터넷이 연결되어있지 않습니다", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        val action = intent!!.action
        Log.d("DMS_MealWidget", action)
        if (action == "RefreshBtnClick") {
            val appWidgetIds: IntArray? = intent.extras.getIntegerArrayList(WIDGET_IDS_KEY).toIntArray()
            Log.d("DMS_MealWidget", appWidgetIds.toString())
            if (appWidgetIds != null && appWidgetIds.isNotEmpty()) {
                Log.d("DMS_MealWidget", "Can Do Update")
                this.onUpdate(context!!, android.appwidget.AppWidgetManager.getInstance(context), appWidgetIds)
            }
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {


        val WIDGET_IDS_KEY = "mealwidgetproviderwidgetids"
        val WIDGET_DATA_KEY = "mealwidgetproviderwidgetdata"

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int, map: HashMap<String, String>) {
            val views = RemoteViews(context.packageName, R.layout.widget_meal)
            val intent = Intent("RefreshBtnClick")
            intent.putIntegerArrayListExtra(WIDGET_IDS_KEY, arrayListOf(appWidgetId))
            val pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            with(views) {
                setTextViewText(R.id.text_widget_date, map["date"])
                setTextViewText(R.id.text_widget_breakfast, map["breakfast"])
                setTextViewText(R.id.text_widget_lunch, map["lunch"])
                setTextViewText(R.id.text_widget_dinner, map["dinner"])
                setOnClickPendingIntent(R.id.button_widget_refresh, pi)
            }
//            views.setTextViewText(R.id.appwidget_text, widgetText)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

    }
}

