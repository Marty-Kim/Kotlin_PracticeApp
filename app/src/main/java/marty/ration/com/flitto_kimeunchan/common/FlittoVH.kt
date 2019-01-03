package marty.ration.com.flitto_kimeunchan.common

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import marty.ration.com.flitto_kimeunchan.R
import marty.ration.com.flitto_kimeunchan.http.response.BookRepo

/**
 * Created by Charny on 2018-11-10.
 */

class FlittoVH(view : View) : MartyVH<BookRepo>((view)){
    private val imageView : ImageView = view.findViewById<ImageView>(R.id.list_image)
    private val titleView : TextView = view.findViewById<TextView>(R.id.list_title)
    private val priceView : TextView = view.findViewById<TextView>(R.id.list_price)
    private val contentView : RelativeLayout = view.findViewById<RelativeLayout>(R.id.list_rl)


    override fun bind(t: BookRepo?, mCon: Context? , listener : View.OnClickListener?) {
        titleView.text = t?.title
        priceView.text = t?.price
        Glide.with(mCon).load(t?.image).into(imageView)
        contentView.tag = t?.isbn13
        contentView.setOnClickListener(listener)
    }
}