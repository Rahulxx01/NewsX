package in.connectitude.newsx.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {



    @SerializedName("status")
    @Expose
    private String status;


    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;


    @SerializedName("articles")
    @Expose
    private List<Article> articles;



    public News() {

    }

    public News(String status, Integer totalResults,List<Article> articles) {
        this.totalResults = totalResults;
        this.status = status;
        this.articles = articles;

    }



    public String getStatus() {
        return status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}
