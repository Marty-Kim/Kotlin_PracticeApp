package marty.ration.com.flitto_kimeunchan.common

import android.view.View
import marty.ration.com.flitto_kimeunchan.http.response.BookRepo

/**
 * Created by Charny on 2018-11-10.
 */

class FlittoAdapter(activity : BaseActivity, mLayout : Int) : MartyAdapter<BookRepo,FlittoVH>(activity,mLayout){

    var listener : View.OnClickListener = activity
    init {
        arrayList = ArrayList<BookRepo>()

    }

    override fun getViewHolder(view: View): FlittoVH {
        return FlittoVH(view)
    }

    override fun onBindViewHolder(p0: FlittoVH, p1: Int) {
        p0.bind(arrayList.get(p1),getmCon(),listener)
    }
}