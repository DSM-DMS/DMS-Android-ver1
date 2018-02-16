package team_dms.dms.Connect

import com.google.gson.*
import retrofit2.*
import retrofit2.http.*
import team_dms.dms.Model.*
import teamdms.dms_kotlin.Model.*

/**
 * Created by root1 on 2017. 11. 23..
 */
interface Api {

    //방과후 신청
    @GET("survey")
    fun loadSurvey(@Header("Authorization") token : String) : Call<Array<SurveyModel>>

    //급식 데이터 파싱
    @GET("meal/{date}")
    fun loadMeal(@Path("date") data: String): Call<MealModel>

    //마이페이지 데이터, 신청화면 메인 로드
    @GET("mypage")
    fun loadMyInfo(@Header("Authorization") token: String): Call<MypageModel>

    //연장 map
    @GET("extension/map/{time}")
    fun loadStudyMap(@Header("Authorization")token: String, @Path("time")time: Int, @Query("class_num")classNum: Int): Call<Array<Array<Any>>>

    //연장 신청
    @POST("extension/{time}")
    @FormUrlEncoded
    fun applyStudy(@Header("Authorization") token: String, @Path("time") time: Int, @Field("class_num") classNum: Int, @Field("seat_num") seatNum: Int): Call<Void>

    //잔류 조회
    @GET("stay")
    fun loadStayState(@Header("Authorization") token: String): Call<JsonObject>

    //잔류 신청
    @POST("stay")
    @FormUrlEncoded
    fun applyStay(@Header("Authorization") token: String, @Field("value") state: Int): Call<Void>

    //외출 신청
    @POST("goingout")
    @FormUrlEncoded
    fun applyOut(@Header("Authorization") token: String, @Field("sat") satState: Boolean, @Field("sun") sunState: Boolean): Call<Void>

    //로그인
    @POST("auth")
    @FormUrlEncoded
    fun signIn(@Field("id") id: String, @Field("pw") pw: String): Call<JsonObject>

    //회원가입
    @POST("signup")
    @FormUrlEncoded
    fun signUp(@Field("uuid") code: String, @Field("id") id: String, @Field("pw") pw: String): Call<Void>

    //버그 전송
    @POST("report/bug")
    @FormUrlEncoded
    fun sendBugReport(@Header("Authorization") token: String, @Field("title") title: String, @Field("content") content: String): Call<Void>

    // 비밀번호 변경
    @POST("change/pw")
    @FormUrlEncoded
    fun changePW(@Header("Authorization") token: String, @Field("current_pw") exist_pw: String, @Field("new_pw") new_pw: String): Call<Void>

    // 비밀번호 확인
    @POST("verify/id")
    @FormUrlEncoded
    fun checkOverlap(@Field("id") id: String): Call<Void>

    // 고장 신고
    @POST("report/facility")
    @FormUrlEncoded
    fun reportProblem(@Header("Authorization") token: String, @Field("title") title: String, @Field("room") room: Int, @Field("content") content: String): Call<Void>

    //UUID 가입 가능 여부 검사
    @POST("verify/uuid")
    @FormUrlEncoded
    fun checkUUID(@Field("uuid") uuid : String) : Call<Void>

    @POST("survey/question")
    @FormUrlEncoded
    fun sendSurvey(@Header ("Authorization") token : String, @Field("question_id") id : String, @Field("answer") answer : String) : Call<Void>

    //공지 리스트 불러오기
    @GET("{confirm}")
    fun loadNotice(@Header("Authorization") token: String, @Path("confirm")confirm: String) : Call<Array<NoticeModel>>

    //공지 디테일 불러오기
    @GET("{confirm}/{id}")
    fun loadNotice_detail(@Header("Authorization") token: String, @Path("confirm") confirm: String, @Path("id") id: String): Call<NoticeModel>

    //설문조사 리스트
    @GET("survey")
    fun loadSurvey() : Call<Array<SurveyModel>>

    //설문지의 리스트
    @GET("survey/question")
    fun loadSurvey_detail(@Header("Authorization") token: String, @Query("survey_id")id : String) : Call<Array<SurveyQuestionModel>>

    //연장 취소
    @DELETE("extension/{time}")
    fun cancelExtension (@Path("time") time : String, @Header("Authorization") token: String) : Call<Void>
}