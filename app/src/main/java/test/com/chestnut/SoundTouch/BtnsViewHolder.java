package test.com.chestnut.SoundTouch;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Chestnut on 2017/3/26.
 */

public class BtnsViewHolder extends RecyclerView.ViewHolder{

    protected Button btnClick = null;
    protected TextView txtTitles = null;

    public BtnsViewHolder(View itemView) {
        super(itemView);
        btnClick = (Button) itemView.findViewById(R.id.btnClick);
        txtTitles = (TextView) itemView.findViewById(R.id.txtTiles);
    }
}
