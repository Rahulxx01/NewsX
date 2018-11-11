package in.connectitude.newsx.network;


import java.util.List;

import in.connectitude.newsx.model.News;
import in.connectitude.newsx.utils.Constants;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("top-headlines?country=in&apiKey=c35fbbe4f0f24045bba98b491faeca54")
    Call<News> getNewsHighlights();

    @GET("top-headlines?country=in&category=business&apiKey=" + Constants.API_KEY)
    Call<News> getNewsBusiness();

    @GET("top-headlines?country=in&category=entertainment&apiKey=" + Constants.API_KEY)
    Call<News> getNewsEntertainment();

    @GET("top-headlines?country=in&category=general&apiKey=" + Constants.API_KEY)
    Call<News> getNewsGeneral();

    @GET("top-headlines?country=in&category=health&apiKey=" + Constants.API_KEY)
    Call<News> getNewsHealth();

    @GET("top-headlines?country=in&category=science&apiKey=" + Constants.API_KEY)
    Call<News> getNewsScience();

    @GET("top-headlines?country=in&category=technology&apiKey=" + Constants.API_KEY)
    Call<News> getNewsTechnology();

    @GET("top-headlines?country=in&category=sports&apiKey=" + Constants.API_KEY)
    Call<News> getNewsSports();


}