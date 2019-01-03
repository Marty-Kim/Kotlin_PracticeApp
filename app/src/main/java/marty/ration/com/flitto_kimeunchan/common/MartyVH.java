package marty.ration.com.flitto_kimeunchan.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public abstract class MartyVH<T> extends RecyclerView.ViewHolder {

    private T item;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public MartyVH(@NonNull View itemView ) {
        super(itemView);
    }
    public abstract void bind(T t,Context mCon,View.OnClickListener listener);
}
