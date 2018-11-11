package in.connectitude.newsx.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.connectitude.newsx.model.Article;
import in.connectitude.newsx.model.News;
import in.connectitude.newsx.model.NewsSources;
import in.connectitude.newsx.model.Source;
import in.connectitude.newsx.network.ApiService;
import in.connectitude.newsx.network.NewsSourcesUtils;
import in.connectitude.newsx.network.RetrofitClient;
import in.connectitude.newsx.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestNewsViewModel extends ViewModel {

    News newsData;
    List<Article> articles;


    MutableLiveData<List<NewsSources>> newsDataList ;
    MutableLiveData<List<NewsSources>> newsDataBusinessList;
    MutableLiveData<List<NewsSources>> newsDataEntertainmentList;
    MutableLiveData<List<NewsSources>> newsDataScienceList;
    MutableLiveData<List<NewsSources>> newsDataHealthList;
    MutableLiveData<List<NewsSources>> newsDataGeneralList;
    MutableLiveData<List<NewsSources>> newsDataSportsList;
    MutableLiveData<List<NewsSources>> newsDataTechnologyList;


    //we will call this method to get the data
    public LiveData<List<NewsSources>> getNewsData() {
        //if the list is null
        if (newsDataList == null) {
            newsDataList = new MutableLiveData<List<NewsSources>>();
            //we will load it asynchronously from server in this method
            loadNews();

        }

        //finally we will return the list
        return newsDataList;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadNews() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<News> call = apiService.getNewsHighlights();


        call.enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                newsData = response.body();
                List<NewsSources> newsSourcesData = new ArrayList<NewsSources>();
                articles = newsData.getArticles();
                for (int i = 0; i < articles.size(); i++) {
                    String author = articles.get(i).getAuthor();
                    String description = articles.get(i).getDescription();
                    String title = articles.get(i).getTitle();
                    String url = articles.get(i).getUrl();
                    String urlToImage = articles.get(i).getUrlToImage();
                    String content = articles.get(i).getContent();
                    String publishedAt = articles.get(i).getPublishedAt();
                    Source source = articles.get(i).getSource();
                    String name = source.getName();
                    newsSourcesData.add(new NewsSources(name, author, title, description, url, urlToImage, publishedAt, content));
                }
                newsDataList.setValue(newsSourcesData);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                //Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                Log.v("NewsHighlightsFragment:", t.toString());
            }
        });


    }

    private class NewsAsynTask extends AsyncTask<String, Void, List<NewsSources>> {

        @Override
        protected List<NewsSources> doInBackground(String... strings) {
            List<NewsSources> result = NewsSourcesUtils.extractNewsHeadlines(Constants.INDIA_LATEST_NEWS);
            return result;
            //  return null;
        }


    }







////////////////////
///For General Category
    //we will call this method to get the data
    public LiveData<List<NewsSources>> getNewsGeneral() {
        //if the list is null
        if (newsDataGeneralList == null) {
            newsDataGeneralList = new MutableLiveData<List<NewsSources>>();
            //we will load it asynchronously from server in this method
            loadNewsGeneral();
        }

        //finally we will return the list
        return newsDataGeneralList;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadNewsGeneral() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<News> call = apiService.getNewsGeneral();


        call.enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                newsData = response.body();
                List<NewsSources> newsSourcesData = new ArrayList<NewsSources>();
                articles = newsData.getArticles();
                for (int i = 0; i < articles.size(); i++) {
                    String author = articles.get(i).getAuthor();
                    String description = articles.get(i).getDescription();
                    String title = articles.get(i).getTitle();
                    String url = articles.get(i).getUrl();
                    String urlToImage = articles.get(i).getUrlToImage();
                    String content = articles.get(i).getContent();
                    String publishedAt = articles.get(i).getPublishedAt();
                    Source source = articles.get(i).getSource();
                    String name = source.getName();
                    newsSourcesData.add(new NewsSources(name, author, title, description, url, urlToImage, publishedAt, content));
                }
                newsDataGeneralList.setValue(newsSourcesData);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                //Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                Log.v("NewsHighlightsFragment:", t.toString());
            }
        });


    }

/////////////////////////
    ///For Science Category
    //we will call this method to get the data
    public LiveData<List<NewsSources>> getNewsScience() {
        //if the list is null
        if (newsDataScienceList == null) {
            newsDataScienceList = new MutableLiveData<List<NewsSources>>();
            //we will load it asynchronously from server in this method
            loadNewsScience();
        }

        //finally we will return the list
        return newsDataScienceList;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadNewsScience() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<News> call = apiService.getNewsScience();


        call.enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                newsData = response.body();
                List<NewsSources> newsSourcesData = new ArrayList<NewsSources>();
                articles = newsData.getArticles();
                for (int i = 0; i < articles.size(); i++) {
                    String author = articles.get(i).getAuthor();
                    String description = articles.get(i).getDescription();
                    String title = articles.get(i).getTitle();
                    String url = articles.get(i).getUrl();
                    String urlToImage = articles.get(i).getUrlToImage();
                    String content = articles.get(i).getContent();
                    String publishedAt = articles.get(i).getPublishedAt();
                    Source source = articles.get(i).getSource();
                    String name = source.getName();
                    newsSourcesData.add(new NewsSources(name, author, title, description, url, urlToImage, publishedAt, content));
                }
                newsDataScienceList.setValue(newsSourcesData);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                //Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                Log.v("NewsHighlightsFragment:", t.toString());
            }
        });


    }
///

    ///For Entertainment Category
    //we will call this method to get the data
    public LiveData<List<NewsSources>> getNewsEntertainment() {
        //if the list is null
        if (newsDataEntertainmentList == null) {
            newsDataEntertainmentList = new MutableLiveData<List<NewsSources>>();
            //we will load it asynchronously from server in this method
            loadNewsEntertainment();
        }

        //finally we will return the list
        return newsDataEntertainmentList;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadNewsEntertainment() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<News> call = apiService.getNewsEntertainment();


        call.enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                newsData = response.body();
                List<NewsSources> newsSourcesData = new ArrayList<NewsSources>();
                articles = newsData.getArticles();
                for (int i = 0; i < articles.size(); i++) {
                    String author = articles.get(i).getAuthor();
                    String description = articles.get(i).getDescription();
                    String title = articles.get(i).getTitle();
                    String url = articles.get(i).getUrl();
                    String urlToImage = articles.get(i).getUrlToImage();
                    String content = articles.get(i).getContent();
                    String publishedAt = articles.get(i).getPublishedAt();
                    Source source = articles.get(i).getSource();
                    String name = source.getName();
                    newsSourcesData.add(new NewsSources(name, author, title, description, url, urlToImage, publishedAt, content));
                }
                newsDataEntertainmentList.setValue(newsSourcesData);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                //Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                Log.v("NewsHighlightsFragment:", t.toString());
            }
        });


    }
////////////Health

    ///For Health Category
    //we will call this method to get the data
    public LiveData<List<NewsSources>> getNewsHealth() {
        //if the list is null
        if ( newsDataHealthList == null) {
            newsDataHealthList = new MutableLiveData<List<NewsSources>>();
            //we will load it asynchronously from server in this method
            loadNewsHealth();
        }

        //finally we will return the list
        return newsDataHealthList;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadNewsHealth() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<News> call = apiService.getNewsHealth();


        call.enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                newsData = response.body();
                List<NewsSources> newsSourcesData = new ArrayList<NewsSources>();
                articles = newsData.getArticles();
                for (int i = 0; i < articles.size(); i++) {
                    String author = articles.get(i).getAuthor();
                    String description = articles.get(i).getDescription();
                    String title = articles.get(i).getTitle();
                    String url = articles.get(i).getUrl();
                    String urlToImage = articles.get(i).getUrlToImage();
                    String content = articles.get(i).getContent();
                    String publishedAt = articles.get(i).getPublishedAt();
                    Source source = articles.get(i).getSource();
                    String name = source.getName();
                    newsSourcesData.add(new NewsSources(name, author, title, description, url, urlToImage, publishedAt, content));
                }
                newsDataHealthList.setValue(newsSourcesData);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                //Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                Log.v("NewsHighlightsFragment:", t.toString());
            }
        });


    }


    //////////

    ///For Sports Category
    //we will call this method to get the data
    public LiveData<List<NewsSources>> getNewsSports() {
        //if the list is null
        if ( newsDataSportsList == null) {
            newsDataSportsList = new MutableLiveData<List<NewsSources>>();
            //we will load it asynchronously from server in this method
            loadNewsSports();
        }

        //finally we will return the list
        return newsDataSportsList;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadNewsSports() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<News> call = apiService.getNewsEntertainment();


        call.enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                newsData = response.body();
                List<NewsSources> newsSourcesData = new ArrayList<NewsSources>();
                articles = newsData.getArticles();
                for (int i = 0; i < articles.size(); i++) {
                    String author = articles.get(i).getAuthor();
                    String description = articles.get(i).getDescription();
                    String title = articles.get(i).getTitle();
                    String url = articles.get(i).getUrl();
                    String urlToImage = articles.get(i).getUrlToImage();
                    String content = articles.get(i).getContent();
                    String publishedAt = articles.get(i).getPublishedAt();
                    Source source = articles.get(i).getSource();
                    String name = source.getName();
                    newsSourcesData.add(new NewsSources(name, author, title, description, url, urlToImage, publishedAt, content));
                }
                newsDataSportsList.setValue(newsSourcesData);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                //Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                Log.v("NewsHighlightsFragment:", t.toString());
            }
        });

    }

    //////////


    ///For Sports Category
    //we will call this method to get the data
    public LiveData<List<NewsSources>> getNewsTechnology() {
        //if the list is null
        if ( newsDataTechnologyList == null) {
            newsDataTechnologyList = new MutableLiveData<List<NewsSources>>();
            //we will load it asynchronously from server in this method
            loadNewsTechnology();
        }

        //finally we will return the list
        return newsDataTechnologyList;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadNewsTechnology() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<News> call = apiService.getNewsTechnology();


        call.enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                newsData = response.body();
                List<NewsSources> newsSourcesData = new ArrayList<NewsSources>();
                articles = newsData.getArticles();
                for (int i = 0; i < articles.size(); i++) {
                    String author = articles.get(i).getAuthor();
                    String description = articles.get(i).getDescription();
                    String title = articles.get(i).getTitle();
                    String url = articles.get(i).getUrl();
                    String urlToImage = articles.get(i).getUrlToImage();
                    String content = articles.get(i).getContent();
                    String publishedAt = articles.get(i).getPublishedAt();
                    Source source = articles.get(i).getSource();
                    String name = source.getName();
                    newsSourcesData.add(new NewsSources(name, author, title, description, url, urlToImage, publishedAt, content));
                }
                newsDataTechnologyList.setValue(newsSourcesData);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                //Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                Log.v("NewsHighlightsFragment:", t.toString());
            }
        });

    }
////Business
///For Sports Category
//we will call this method to get the data
public LiveData<List<NewsSources>> getNewsBusiness() {
    //if the list is null
    if ( newsDataBusinessList == null) {
        newsDataBusinessList = new MutableLiveData<List<NewsSources>>();
        //we will load it asynchronously from server in this method
        loadNewsBusiness();
    }

    //finally we will return the list
    return newsDataBusinessList;
}

    //This method is using Retrofit to get the JSON data from URL
    private void loadNewsBusiness() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<News> call = apiService.getNewsBusiness();


        call.enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                newsData = response.body();
                List<NewsSources> newsSourcesData = new ArrayList<NewsSources>();
                articles = newsData.getArticles();
                for (int i = 0; i < articles.size(); i++) {
                    String author = articles.get(i).getAuthor();
                    String description = articles.get(i).getDescription();
                    String title = articles.get(i).getTitle();
                    String url = articles.get(i).getUrl();
                    String urlToImage = articles.get(i).getUrlToImage();
                    String content = articles.get(i).getContent();
                    String publishedAt = articles.get(i).getPublishedAt();
                    Source source = articles.get(i).getSource();
                    String name = source.getName();
                    newsSourcesData.add(new NewsSources(name, author, title, description, url, urlToImage, publishedAt, content));
                }
                newsDataBusinessList.setValue(newsSourcesData);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                //Toast.makeText(getContext(), "Error has occured", Toast.LENGTH_SHORT).show();
                Log.v("NewsHighlightsFragment:", t.toString());
            }
        });

    }






}



















