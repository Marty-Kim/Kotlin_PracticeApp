package marty.ration.com.flitto_kimeunchan.http;

import marty.ration.com.flitto_kimeunchan.http.response.BookDetailRepo;
import marty.ration.com.flitto_kimeunchan.http.response.BookRepo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiService {

    String URL = "https://api.itbook.store/1.0/";

    @GET("search/{query}/{page}")
    Call<ResponseBody<BookRepo>> SearchBooks(@Path("query")String query, @Path("page")int page);

    @GET("books/{isbn13}")
    Call<BookDetailRepo> SearchBook(@Path("isbn13")String query);

}
