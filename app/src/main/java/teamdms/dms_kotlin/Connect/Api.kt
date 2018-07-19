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
    fun loadSurvey(@Header("Authorization") token: String): Call<Array<SurveyModel>>

    //급식 데이터 파싱
    @GET("meal/{date}")
    fun loadMeal(@Path("date") data: String): Call<MealModel>

    //마이페이지 데이터, 신청화면 메인 로드
    @GET("student/info/mypage")
    fun loadMyInfo(@Header("Authorization") token: String): Call<MypageModel>

    @GET("student/info/apply")
    fun loadApplyInfo(@Header("Authorization") token: String): Call<MypageModel>

    //연장 map
    @GET("student/apply/extension/{time}/map")
    fun loadStudyMap(@Header("Authorization") token: String, @Path("time") time: Int, @Query("classNum") classNum: Int): Call<Array<Array<Any>>>

    //연장 신청
    @POST("student/apply/extension/{time}")
    @Headers("Content-Type: application/json")
    fun applyStudy(@Header("Authorization") token: String, @Path("time") time: Int, @Body body: Any?): Call<Void>

    //잔류 조회
    @GET("student/apply/stay")
    fun loadStayState(@Header("Authorization") token: String): Call<JsonObject>

    //잔류 신청
    @POST("student/apply/stay")
    @Headers("Content-Type: application/json")
    fun applyStay(@Header("Authorization") token: String, @Body body: Any?): Call<Void>

    //외출 신청
    @POST("student/apply/goingout")
    @Headers("Content-Type: application/json")
    fun applyOut(@Header("Authorization") token: String, @Body body: Any?): Call<Void>

    //로그인
    @POST("student/auth")
    @Headers("Content-Type: application/json")
    fun signIn(@Body body: Any?): Call<AuthModel>

    //회원가입
    @POST("student/signup")
    @Headers("Content-Type: application/json")
    fun signUp(@Body body: Any?): Call<Void>

    //버그 전송
    @POST("student/report/bug")
    @Headers("Content-Type: application/json")
    fun sendBugReport(@Header("Authorization") token: String, @Body body: Any?): Call<Void>

    // 비밀번호 변경
    @POST("student/account/change-pw")
    @Headers("Content-Type: application/json")
    fun changePW(@Header("Authorization") token: String, @Body body: Any?): Call<Void>

    // 비밀번호 확인
    @POST("student/verify/id")
    @Headers("Content-Type: application/json")
    fun checkOverlap(@Body body: Any?): Call<Void>

    // 고장 신고
    @POST("student/report/facility")
    @Headers("Content-Type: application/json")
    fun reportProblem(@Header("Authorization") token: String, @Body body: Any?): Call<Void>

    //UUID 가입 가능 여부 검사
    @POST("student/verify/uuid")
    @Headers("Content-Type: application/json")
    fun checkUUID(@Field("uuid") uuid: String): Call<Void>

    @POST("student/survey/question")
    @Headers("Content-Type: application/json")
    fun sendSurvey(@Header("Authorization") token: String, @Field("question_id") id: String, @Field("answer") answer: String): Call<Void>

    //공지 리스트 불러오기
    @GET("post/{confirm}")
    fun loadNotice(@Header("Authorization") token: String, @Path("confirm") confirm: String): Call<Array<NoticeModel>>

    //공지 디테일 불러오기
    @GET("post/{confirm}/{id}")
    fun loadNotice_detail(@Header("Authorization") token: String, @Path("confirm") confirm: String, @Path("id") id: String): Call<NoticeModel>

    //설문조사 리스트
    @GET("survey")
    fun loadSurvey(): Call<Array<SurveyModel>>

    //설문지의 리스트
    @GET("survey/question")
    fun loadSurvey_detail(@Header("Authorization") token: String, @Query("survey_id") id: String): Call<Array<SurveyQuestionModel>>

    //연장 취소
    @DELETE("student/apply/extension/{time}")
    fun cancelExtension(@Path("time") time: String, @Header("Authorization") token: String): Call<Void>

    //버전 확인
    @GET("metadata/version{platform}")
    fun versionCheck(@Path("platform") platform: String): Call<VersionModel>

    @GET("student/info/point-history")
    fun loadPointHistory(@Header("Authorization") token: String): Call<Array<PointModel>>

    @POST("jwt/refresh")
    fun refreshToken(@Header("Authorization") token: String): Call<AuthModel>
}