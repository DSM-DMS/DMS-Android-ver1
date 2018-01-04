package team_dms.dms.Connect

import android.content.*
import retrofit2.*
import team_dms.dms.Base.*

/**
 * Created by root1 on 2017. 11. 23..
 */
abstract class Res<T>(context: Context): Callback<T> {

    lateinit var context: Context

    init {
        this.context = context
    }

    abstract fun callBack(code: Int, body: T?)

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        Util.showToast(context, "네트워크 오류")
    }

    override fun onResponse(call: Call<T>?, response: Response<T>) {
        val code = response.code()
        val body = response.body()

        when(code){
            500 -> Util.showToast(context, "서버 오류")
            422 -> Util.showToast(context, "로그인이 필요합니다")
            403 -> Util.showToast(context, "권한이 없습니다")
            401 -> {
                Util.showToast(context, "다시 로그인 하세요")
                Util.removeToken(context)
            }
            else -> callBack(code, body)
        }

    }

}