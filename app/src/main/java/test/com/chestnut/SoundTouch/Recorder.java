package test.com.chestnut.SoundTouch;

import android.os.Environment;
import android.util.Log;

import com.chestnut.SoundTouch.SoundTouch;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Chestnut on 2017/4/9.
 */

public class Recorder {

    private SoundTouch soundTouch;
    private AudioRecordHelper audioRecordHelper;

    public Recorder() {
        soundTouch = new SoundTouch();
        audioRecordHelper = new AudioRecordHelper();
    }

    public Observable<Integer> record(final String outPutFile, final float tempo, final float pitch, final float speed) {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(final ObservableEmitter<Integer> emitter) throws Exception {
                audioRecordHelper.init(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/Test.pmc");
                audioRecordHelper.setCallBack(new AudioRecordHelper.CallBack() {
                    @Override
                    public void onRecordStart(String file) {
                        Log.w("onRecordStart",file);
                        emitter.onNext(-2);
                    }

                    @Override
                    public void onRecordDBChange(double dbValue) {
                        Log.w("onRecordDBChange",dbValue+"");
                        emitter.onNext((int)dbValue);
                    }

                    @Override
                    public void onRecordFail(String file, String msg) {
                        Log.w("onRecordFail",file+","+msg);
                        emitter.onNext(-1);
                    }

                    @Override
                    public void onRecordEnd(String file) {
                        Log.w("onRecordEnd",file);
                        soundTouch.setTempo(tempo)
                                .setPitchSemiTones(pitch)
                                .setSpeed(speed)
                                .processFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/Test.pmc",
                                        outPutFile,
                                        new SoundTouch.CallBack() {
                                            @Override
                                            public void onStart(String inputFile, String outputFile) {
                                                Log.e("SoundTouch-onStart","input:"+inputFile+",output:"+outputFile);
                                            }

                                            @Override
                                            public void onSuccess(String inputFile, String outputFile) {
                                                Log.e("SoundTouch-onSuccess","input:"+inputFile+",output:"+outputFile);
                                                emitter.onNext(0);
                                            }

                                            @Override
                                            public void onFail(String inputFile, String outputFile, int errorCode) {
                                                Log.e("SoundTouch-onFail","input:"+inputFile+",output:"+outputFile+",errCode:"+errorCode);
                                                emitter.onNext(-1);
                                            }
                                        });
                    }
                });
                audioRecordHelper.startRecord();
            }
        });
    }

    public void stop() {
        audioRecordHelper.stopRecord();
    }
}
