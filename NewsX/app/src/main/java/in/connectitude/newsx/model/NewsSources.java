package in.connectitude.newsx.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "news_favourites")
public class NewsSources {

    @PrimaryKey(autoGenerate = true)
    private int newsId;

    private String title;
    private String name;
    private String author;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    String sourceCategory;
    int imageCategory;

    String newsCategoryName;


    @Ignore
    public NewsSources() {


    }

    @Ignore
    public NewsSources(String sourceCategory, int imageCategory, String newsCategoryName) {
        this.sourceCategory = sourceCategory;
        this.imageCategory = imageCategory;
        this.newsCategoryName = newsCategoryName;

    }

    @Ignore
    public NewsSources(String name, String author, String title, String description,
                       String url, String urlToImage, String publishedAt, String content) {
        this.name = name;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;

    }


    public NewsSources(int newsId, String name, String author, String title, String description,
                       String url, String urlToImage, String publishedAt, String content) {

        this.newsId = newsId;
        this.name = name;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;

    }

    public String getNewsCategoryName() {
        return newsCategoryName;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getName() {
        return name;
    }


    public int getImageCategory() {
        return imageCategory;
    }

    public String getSourceCategory() {
        return sourceCategory;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;

    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImageCategory(int imageCategory) {
        this.imageCategory = imageCategory;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setSourceCategory(String sourceCategory) {
        this.sourceCategory = sourceCategory;
    }

    public void setNewsCategoryName(String newsCategoryName) {
        this.newsCategoryName = newsCategoryName;
    }

}

