package marty.ration.com.flitto_kimeunchan.common

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * Created by Charny on 2018-11-11.
 */
open abstract  class BaseActivity : AppCompatActivity() , View.OnClickListener{



    protected var loadingDlg : ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDlg = ProgressDialog(this@BaseActivity).apply {
            setMessage("로딩중....")
            setProgressStyle(ProgressDialog.STYLE_SPINNER)
        }
    }
}