package marty.ration.com.flitto_kimeunchan.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public class martyBaseActivity extends AppCompatActivity {

   protected Context mCon;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       mCon = this;
   }
}
