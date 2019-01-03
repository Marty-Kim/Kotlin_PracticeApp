package marty.ration.com.flitto_kimeunchan.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public abstract class MartyAdapter<T,S extends MartyVH> extends RecyclerView.Adapter<S> {

    public ArrayList<T> arrayList;
    private Context mCon;
    private LayoutInflater mInflater;
    private Integer mLayout;

    public MartyAdapter(Context mCon , Integer mLayout) {
        this.mCon = mCon;
        this.mLayout = mLayout;
        mInflater = (LayoutInflater)mCon.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Context getmCon() {
        return mCon;
    }

    public void setList(ArrayList<T> t){
        arrayList = t;
        notifyDataSetChanged();
    }

    public void addList(ArrayList<T> t){
        if (arrayList == null)
            arrayList = new ArrayList<>();
        int pos = arrayList.size();
        arrayList.addAll(t);
        notifyItemRangeChanged(pos,arrayList.size());

    }

    public void addList(T t){
        if (arrayList == null)
            arrayList = new ArrayList<>();
        arrayList.add(t);
        notifyItemInserted(arrayList.size()-1);

    }


    @NonNull
    @Override
    public S onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return getViewHolder(mInflater.inflate(mLayout,viewGroup,false));
    }

    public abstract S getViewHolder(View view);

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
