package test.com.chestnut.SoundTouch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Chestnut on 2017/3/26.
 */

public class BtnsAdapter extends RecyclerView.Adapter<BtnsViewHolder>{

    private String[] titles;
    private Context context = null;

    public BtnsAdapter(String[] titles, Context context) {
        this.titles = titles;
        this.context = context;
    }

    @Override
    public BtnsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.adapter_btns, parent, false);
        return new BtnsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BtnsViewHolder holder, final int position) {
        holder.txtTitles.setText(titles[position]);
        holder.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemListener!=null) {
                    onItemListener.onItemOnClick(BtnsAdapter.this,view,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles == null ? 0 : titles.length;
    }

    private OnItemListener onItemListener = null;

    public void setOnItemClickListener(OnItemListener listener) {
        this.onItemListener = listener;
    }

    //define interface
    public interface OnItemListener {
        void onItemOnClick(BtnsAdapter btnsAdapter,View view,int position);
    }
}
