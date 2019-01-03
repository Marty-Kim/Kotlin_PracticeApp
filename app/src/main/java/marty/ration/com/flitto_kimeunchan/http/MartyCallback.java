package marty.ration.com.flitto_kimeunchan.http;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import marty.ration.com.flitto_kimeunchan.R;
import marty.ration.com.flitto_kimeunchan.common.MDEBUG;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MartyCallback<T> implements Callback<T> {

    private final int NO_VALUE = -1;
    public interface MartyCall<T> {
        void onResponse(Call<T> call, Response<T> response);
    }

    public interface MartyCallWithFailure<T> {
        void onResponse(Call<T> call, Response<T> response);
        void onFailure(@NonNull Call<T> call, @NonNull Throwable t);
    }

    private MartyCall<T> martyCall;
    private MartyCallWithFailure<T> martyCallWithFailure;
    private AppCompatActivity baseView;
    private int failAlertText;

    public MartyCallback(AppCompatActivity baseView, MartyCall<T> rationCall) {
        this.martyCall = rationCall;
        this.baseView = baseView;
        this.failAlertText = NO_VALUE;
    }

    public MartyCallback(AppCompatActivity baseView, MartyCall<T> rationCall, int failAlertText) {
        this.martyCall = rationCall;
        this.baseView = baseView;
        this.failAlertText = failAlertText;
    }

    public MartyCallback(AppCompatActivity baseView, MartyCallWithFailure<T> rationCall) {
        this.martyCallWithFailure = rationCall;
        this.baseView = baseView;
        this.failAlertText = NO_VALUE;
    }

    public MartyCallback(AppCompatActivity baseView, MartyCallWithFailure<T> rationCall, int failAlertText) {
        this.martyCallWithFailure = rationCall;
        this.baseView = baseView;
        this.failAlertText = failAlertText;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            if (martyCall != null)
                martyCall.onResponse(call, response);
            else
                martyCallWithFailure.onResponse(call, response);
        }else{
            onFailure(call,new Throwable());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (martyCallWithFailure != null)
            martyCallWithFailure.onFailure(call, t);
        MDEBUG.debug(t.toString());
        Toast.makeText(baseView, failAlertText == NO_VALUE ? baseView.getString(R.string.retry) : baseView.getString(failAlertText), Toast.LENGTH_SHORT).show();
    }
}
