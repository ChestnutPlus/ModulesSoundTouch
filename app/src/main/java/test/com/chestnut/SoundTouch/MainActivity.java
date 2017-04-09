package test.com.chestnut.SoundTouch;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private Recorder recorder = new Recorder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BtnsAdapter btnsAdapter = new BtnsAdapter(titles,this);
        recyclerView.setAdapter(btnsAdapter);
        btnsAdapter.setOnItemClickListener(onItemListener);
    }

    private BtnsAdapter.OnItemListener onItemListener = new BtnsAdapter.OnItemListener() {
        @Override
        public void onItemOnClick(BtnsAdapter btnsAdapter, View view, int position) {
            Log.w("onItemListener:",position+"");
            switch (position) {
                case 0:
                    onClick0();
                    break;
                case 1:
                    onClick1();
                    break;
                case 2:
                    onClick2();
                    break;
            }
        }
    };

    private void onClick0() {

    }

    private void onClick1() {
        recorder.record(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/_"+ System.currentTimeMillis()+".wav",1,-3,1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.e("recorder",""+integer);
                    }
                });
    }

    private void onClick2() {
        recorder.stop();
    }

    private String[] titles = {
            "播放",
            "start",
            "stop"
    };
}
