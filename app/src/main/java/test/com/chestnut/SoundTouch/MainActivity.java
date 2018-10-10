package test.com.chestnut.SoundTouch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.chestnut.common.helper.MediaPlayerHelper;
import com.chestnut.common.utils.LogUtils;

import io.reactivex.schedulers.Schedulers;

public class MainActivity extends Activity implements View.OnClickListener{

    private Recorder recorder = new Recorder();
    private MediaPlayerHelper mediaPlayerHelper = new MediaPlayerHelper();
    private String wav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_play).setOnClickListener(this);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_end).setOnClickListener(this);
        mediaPlayerHelper.init(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                if (wav!=null) {
                    mediaPlayerHelper.setUrl(wav);
                    mediaPlayerHelper.play();
                }
                break;
            case R.id.btn_start:
                wav = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/_"+ System.currentTimeMillis()+".wav";
                recorder.record(wav,1,-3,1)
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(integer -> {
                            LogUtils.i("MainActivity","record:"+integer);
                        });
                break;
            case R.id.btn_end:
                recorder.stop();
                break;
        }
    }
}
