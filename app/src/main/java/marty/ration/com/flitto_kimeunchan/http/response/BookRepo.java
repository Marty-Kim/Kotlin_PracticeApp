package marty.ration.com.flitto_kimeunchan.http.response;

import com.google.gson.annotations.SerializedName;



public class BookRepo {

    @SerializedName("title")
    private String title;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("isbn13")
    private String isbn13;
    @SerializedName("price")
    private String price;
    @SerializedName("image")
    private String image;
    @SerializedName("url")
    private String url;


    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }
}
