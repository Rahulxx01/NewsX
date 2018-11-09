package in.connectitude.newsx.network;


import java.util.List;

import in.connectitude.newsx.model.News;
import in.connectitude.newsx.utils.Constants;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("sources?country=in&apiKey=" + Constants.API_KEY)
    Call<List<News>> getNewsHighlights();

    @GET("top-headlines?country=in&category=business&apiKey=" + Constants.API_KEY)
    Call<List<News>> getNewsBusiness();

    @GET("top-headlines?country=in&category=entertainment&apiKey=" + Constants.API_KEY)
    Call<List<News>> getNewsEntertainment();

    @GET("top-headlines?country=in&category=general&apiKey=" + Constants.API_KEY)
    Call<List<News>> getNewsGeneral();

    @GET("top-headlines?country=in&category=health&apiKey=" + Constants.API_KEY)
    Call<List<News>> getNewsHealth();

    @GET("top-headlines?country=in&category=science&apiKey=" + Constants.API_KEY)
    Call<List<News>> getNewsScience();

    @GET("top-headlines?country=in&category=technology&apiKey=" + Constants.API_KEY)
    Call<List<News>> getNewsTechnology();

    @GET("top-headlines?country=in&category=sports&apiKey=" + Constants.API_KEY)
    Call<List<News>> getNewsSports();


}