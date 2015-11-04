package api;

import java.util.List;

import model.Guide;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by adrien on 03/11/2015.
 */
public interface ApiService {
    public static final String ENDPOINT = "http://46.101.218.111/api/v1";

    @FormUrlEncoded @POST("/auth/")
    void authentication(@Field("mail") String mail, @Field("pass") String pass, Callback<String> callback);

    @POST("/register")
    String registration(@Field("firstName") String firstName,
                        @Field("lastName") String lastName,
                        @Field("mail") String mail,
                        @Field("address") String address,
                        @Field("birthdate") String birthdate,
                        @Field("sex") String sex,
                        @Field("telNumber") String telNumber,
                        @Field("password") String password,
                        Callback<String> callback);

    @GET("/guides/{city}")
    List<Guide> listGuidesCity(@Path("city") String city, @Header("Authorization") String token, Callback<String> callback);

    @GET("guide/{id}")
    Guide guiDetails(@Path("id") int id, @Header("Authorization") String token, Callback<String> callback);

    // v1/guides?id=98
    /*@GET("/guides")
    List<Repo> searchRepos(@Query("id") String id);*/
}
