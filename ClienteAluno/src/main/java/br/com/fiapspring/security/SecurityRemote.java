package br.com.fiapspring.security;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SecurityRemote {
        @POST("password")
        Call<ResponseBody> criarSenha(@Body() SecurityPasswordRequest securityPasswordRequest);
    }

