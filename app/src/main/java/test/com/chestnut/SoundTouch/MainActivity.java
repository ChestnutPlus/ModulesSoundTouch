package test.com.chestnut.SoundTouch;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chestnut.SoundTouch.SoundTouch;


public class MainActivity extends AppCompatActivity {

    private SoundTouch soundTouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BtnsAdapter btnsAdapter = new BtnsAdapter(titles,this);
        recyclerView.setAdapter(btnsAdapter);
        btnsAdapter.setOnItemClickListener(onItemListener);

        soundTouch = new SoundTouch();
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
        soundTouch.setPitchSemiTones(1)
                .setPitchSemiTones(2)
                .setSpeed(10)
                .processFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/hehe.wav",
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/hehexxx.wav",
                        new SoundTouch.CallBack() {
                            @Override
                            public void onStart(String inputFile, String outputFile) {
                                Log.e("SoundTouch-onStart","input:"+inputFile+",output:"+outputFile);
                            }

                            @Override
                            public void onSuccess(String inputFile, String outputFile) {
                                Log.e("SoundTouch-onSuccess","input:"+inputFile+",output:"+outputFile);
                            }

                            @Override
                            public void onFail(String inputFile, String outputFile, int errorCode) {
                                Log.e("SoundTouch-onFail","input:"+inputFile+",output:"+outputFile+",errCode:"+errorCode);
                            }
                        });
    }

    private void onClick1() {

    }

    private void onClick2() {

    }

    private String[] titles = {
            "0",
            "1",
            "2"
    };
}
