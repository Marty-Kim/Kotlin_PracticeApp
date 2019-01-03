package marty.ration.com.flitto_kimeunchan.common

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by Charny on 2018-11-11.
 */
class OnScrollLisetener(val layoutManager: LinearLayoutManager, val adapter: FlittoAdapter , val callback : ()->Unit) : RecyclerView.OnScrollListener() {

    var totalItemCount = 0
    var previousTotal = 0
    var loading = true
    var lastVisibleItem = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        totalItemCount = adapter.itemCount
        lastVisibleItem = layoutManager.findLastVisibleItemPosition() + 1
        MDEBUG.debug("lastVisibleItem is = ${lastVisibleItem}")
        MDEBUG.debug("totalItemCount  is = ${totalItemCount}")

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && totalItemCount == lastVisibleItem) {
            loading = true
            callback()
        }
    }

    fun getListFailed(){
        loading = false
    }
    fun setinit(){
        previousTotal = 0
    }
}