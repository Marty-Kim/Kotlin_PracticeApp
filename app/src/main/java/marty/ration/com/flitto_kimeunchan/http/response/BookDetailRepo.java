package marty.ration.com.flitto_kimeunchan.http.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Charny on 2018-11-11.
 */

public class BookDetailRepo {

    @SerializedName("url")
    private String url;
    @SerializedName("image")
    private String image;
    @SerializedName("price")
    private String price;
    @SerializedName("desc")
    private String desc;
    @SerializedName("rating")
    private String rating;
    @SerializedName("year")
    private String year;
    @SerializedName("pages")
    private String pages;
    @SerializedName("isbn13")
    private String isbn13;
    @SerializedName("isbn10")
    private String isbn10;
    @SerializedName("language")
    private String language;
    @SerializedName("publisher")
    private String publisher;
    @SerializedName("authors")
    private String authors;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("title")
    private String title;
    @SerializedName("error")
    private String error;

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    public String getRating() {
        return rating;
    }

    public String getYear() {
        return year;
    }

    public String getPages() {
        return pages;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public String getLanguage() {
        return language;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAuthors() {
        return authors;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String getError() {
        return error;
    }
}
