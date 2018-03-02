package team_dms.dms.Connect

import android.content.*
import retrofit2.*
import team_dms.dms.Base.*

/**
 * Created by root1 on 2017. 11. 23..
 */
abstract class Res<T>(val context: Context, val check401: Boolean = true): Callback<T> {

    abstract fun callBack(code: Int, body: T?)

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        Util.showToast(context, "네트워크 오류")
    }

    override fun onResponse(call: Call<T>?, response: Response<T>) {
        val code = response.code()
        val body = response.body()

        if (code == 401 && check401){
            Util.showToast(context, "다시 로그인 하세요")
            Util.removeToken(context)
        }

        when(code){
            500 -> Util.showToast(context, "서버 오류")
            422 -> { Util.showToast(context, "로그인이 필요합니다")
                Util.removeToken(context) }
            403 -> Util.showToast(context, "권한이 없습니다")
            else -> callBack(code, body)
        }

    }

}