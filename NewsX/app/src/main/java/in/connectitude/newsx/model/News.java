package in.connectitude.newsx.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {

    @SerializedName("articles")
    @Expose
    private List<Articles> articles;

    @SerializedName("totalResults")
    @Expose
    private String totalResults;

    @SerializedName("status")
    @Expose
    private String status;


    public News() {

    }

    public News(List<Articles> articles, String totalResults, String status) {
        this.articles = articles;
        this.totalResults = totalResults;
        this.status = status;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public String getStatus() {
        return status;
    }

    public String getTotalResults() {
        return totalResults;
    }
}
