package marty.ration.com.flitto_kimeunchan

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import marty.ration.com.flitto_kimeunchan.common.BaseActivity
import marty.ration.com.flitto_kimeunchan.common.MAPP
import marty.ration.com.flitto_kimeunchan.http.ApiService
import marty.ration.com.flitto_kimeunchan.http.MartyCallback
import marty.ration.com.flitto_kimeunchan.http.response.BookDetailRepo

/**
 * Created by Charny on 2018-11-11.
 */
class DetailActivity : BaseActivity(){
    private var isbn : String? = ""
    private var mApi : ApiService? = null;
    override fun onClick(v: View?) {
        //Not Using
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getData()

        mApi = (application as MAPP).getApiService()

        mApi?.SearchBook(isbn)?.enqueue(setCallback())

    }

    fun initData(res : BookDetailRepo){

        Glide.with(baseContext).load(res.image).into(book_image)
        book_title.text = getString(R.string.book_title,res.title)
        book_subtitle.text = getString(R.string.book_subtitle,res.subtitle)
        book_author.text = getString(R.string.book_author,res.authors)
        book_price.text = getString(R.string.book_price,res.price)
        book_desc.text = getString(R.string.book_desc,res.desc)
    }

    fun getData(){
        var data = intent
        isbn = data.getStringExtra(getString(R.string.isbn_key))
    }
    fun setCallback() : MartyCallback<BookDetailRepo> {
        return MartyCallback(this@DetailActivity, MartyCallback.MartyCall { _, response -> response.body()?.let { initData(it) }})

    }
}