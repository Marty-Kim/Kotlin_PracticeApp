package marty.ration.com.flitto_kimeunchan.common;

import android.util.Log;

import marty.ration.com.flitto_kimeunchan.BuildConfig;


public class MDEBUG {

    public static void debug(String msg){
        if (BuildConfig.DEBUG){
            Log.d("<Marty>",msg);
        }
    }
    public static void debug(double msg){
        if (BuildConfig.DEBUG){
            Log.d("<Marty>",msg + "");
        }
    }
    public static void debug(int msg){
        if (BuildConfig.DEBUG){
            Log.d("<Marty>",msg + "");
        }
    }

}
