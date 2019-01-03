package marty.ration.com.flitto_kimeunchan.http;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
/**
 * User: Marty
 * Date: 2018-11-10
 * Time: 오전 15:25
 * Description:
 */
public class ResponseBody<T> {

    @SerializedName("error")
    private boolean error;
    @SerializedName("total")
    private int total;
    @SerializedName("page")
    private int page;
    @SerializedName("books")
    private ArrayList<T> result;

    public boolean isError() {
        return error;
    }

    public int getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public ArrayList<T> getResult() {
        return result;
    }
}
