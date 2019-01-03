package marty.ration.com.flitto_kimeunchan

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import marty.ration.com.flitto_kimeunchan.common.*
import marty.ration.com.flitto_kimeunchan.http.ApiService
import marty.ration.com.flitto_kimeunchan.http.MartyCallback
import marty.ration.com.flitto_kimeunchan.http.ResponseBody
import marty.ration.com.flitto_kimeunchan.http.response.BookRepo
import retrofit2.Call

class MainActivity : BaseActivity() ,TextView.OnEditorActionListener {

    private var mApi : ApiService? = null;
    private var Callback : MartyCallback<ResponseBody<BookRepo>>? = null
    private var preCall : Call<ResponseBody<BookRepo>>? = null
    private val FIRST_PAGE : Int = 1
    private var current_page : Int = 0
    private var total_page : Int = 0
    private val MAX_TOTAL : Int = 1000
    private val MAX_PAGE : Int = 100
    private var mainAdapter : FlittoAdapter? = null
    private var scrollLiistener : OnScrollLisetener? = null
    private val PAGE_ITEM_CNT = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Callback = setCallback()

        mainAdapter = FlittoAdapter(this@MainActivity,R.layout.main_listitem)
        main_list.adapter = mainAdapter

        mApi = (application as MAPP).getApiService()
        scrollLiistener = OnScrollLisetener(main_list?.layoutManager as LinearLayoutManager , mainAdapter!!,this@MainActivity::UpdateList)

        main_search_btn.setOnClickListener(this@MainActivity)
        main_search_edt.setOnEditorActionListener(this@MainActivity)
        main_list.addOnScrollListener(scrollLiistener!!)


    }

    override fun onResume() {
        super.onResume()
        if (loadingDlg!!.isShowing) loadingDlg?.dismiss()
    }

    override fun onClick(v: View?) {
        v?.let { var oid : Int = it?.id
            when(oid){
                R.id.main_search_btn->getList(FIRST_PAGE)
                R.id.list_rl->{
                    val inte = Intent(this,DetailActivity::class.java)
                    inte.putExtra(getString(R.string.isbn_key),it.tag as String)
                    startActivity(inte)
                }
            }

        }
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            getList(FIRST_PAGE)
            hideKeyboard(v as View)
            return true
        } else
            return false
    }

    fun getList(page : Int){
        if(page == FIRST_PAGE) scrollLiistener?.setinit()
        mApi?.let {
            preCall?.let { it.cancel() }
            main_search_edt.text.toString()?.let {
                if (it.isEmpty()) Toast.makeText(this@MainActivity,R.string.isempty,Toast.LENGTH_SHORT).show()
                else {
                    loadingDlg?.show()

                    var call: Call<ResponseBody<BookRepo>>? = mApi?.SearchBooks(main_search_edt.text?.toString(), page)
                    current_page = page

                    call?.enqueue(Callback)
                    preCall = call
                }
            }

        }
    }

    fun setList(res : ResponseBody<BookRepo>? ) : Boolean{
        res?.let {
            MDEBUG.debug("list Size =  ${it.result.size}")
            if (it.result?.size != 0){

                val list : ArrayList<BookRepo> = it.result
                total_page = if (it.total >= MAX_TOTAL) MAX_PAGE else{ (it.total / PAGE_ITEM_CNT) + (if (it.total % PAGE_ITEM_CNT != 0) 1 else 0) }
                MDEBUG.debug("total PAge = ${total_page}")
                main_no_list.visibility = View.GONE
                main_list.visibility = View.VISIBLE
                if (current_page == FIRST_PAGE){
                    mainAdapter?.setList(list)
                }else{
                    mainAdapter?.addList(list)
                }
                ++current_page
                return true
            }else{
                setNoListTv(R.string.main_search_nolist)
            }
        }?:run{
            scrollLiistener?.getListFailed()
            setNoListTv(R.string.main_search_error)
        }


        return true
    }
    fun UpdateList(){ if (total_page >= current_page) getList(current_page) }



    fun setNoListTv(msg : Int){
        main_list.visibility = View.GONE
        main_no_list.visibility = View.VISIBLE
        main_no_list.setCompoundDrawablesRelativeWithIntrinsicBounds(null,getDrawable(R.drawable.list_none_imoji),null,null)
        main_no_list.text = getString(msg)
    }


    fun setCallback() : MartyCallback<ResponseBody<BookRepo>>{
        return MartyCallback(this@MainActivity, MartyCallback.MartyCall { _, response -> response.body()?.let {
            setList(response.body())
            if (loadingDlg!!.isShowing) loadingDlg?.dismiss()
        } ?:run { setList(null) }
        })
    }


    fun Context.hideKeyboard(view: View) {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
