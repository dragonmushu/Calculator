package io.calculatorapi.client;

import io.calculatorapi.dto.ResultDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CalculatorRestApi {
    @POST("v1/calculator/evaluate/add")
    Call<ResultDto> add(@Query("firstOperand") double firstOperand, @Query("secondOperand") double secondOperand);

    @POST("v1/calculator/evaluate/subtract")
    Call<ResultDto> subtract(@Query("firstOperand") double firstOperand, @Query("secondOperand") double secondOperand);

    @POST("v1/calculator/evaluate/multiply")
    Call<ResultDto> multiply(@Query("firstOperand") double firstOperand, @Query("secondOperand") double secondOperand);

    @POST("v1/calculator/evaluate/divide")
    Call<ResultDto> divide(@Query("firstOperand") double firstOperand, @Query("secondOperand") double secondOperand);

    @GET("v1/calculator/audit")
    Call<ResultDto> getAudit();
}
