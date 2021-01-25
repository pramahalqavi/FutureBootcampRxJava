package com.example.testapp.network

import com.example.testapp.model.Berry
import com.example.testapp.model.PokeResponse
import com.example.testapp.model.Pokemon
import com.example.testapp.model.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DummyApi {
    @GET("users")
    fun getUsers(): Observable<List<User>>

    @GET("users/{number}")
    fun getUser(@Path("number") number: Int): Observable<List<User>>

    @GET("pokemon")
    fun getPokemons(): Observable<PokeResponse<List<Pokemon>>>

    @GET("berry")
    fun getBerries(): Observable<PokeResponse<List<Berry>>>


}