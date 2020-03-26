package com.sunlands.intl.yingshi.ui.my.player;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.arialyy.aria.core.download.DownloadEntity;
import com.gyf.barlibrary.ImmersionBar;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.MyActivity;
import com.yingshi.edu.IMusicPlayerService;

import java.io.Serializable;


public class SystemAudioPlayer extends MyActivity<Object> {

    TextView tvShowName;
    TextView textView1CurrTime;
    SeekBar seekBar1;
    TextView textView1TotalTime;
    ImageView mediaPlay;
    TextView beisuzhi1;
    TextView beisuzhi2;
    TextView beisuzhi3;

    private DownloadEntity mp3List;

    /**
     * 进度的更新
     */
    private static final int PROGRESS = 0;
    public static IMusicPlayerService service;//服务的代理类
    private MyBroadcastReceiver receiver;
    private boolean notification;

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.fitsSystemWindows(false).transparentStatusBar().autoDarkModeEnable(true).navigationBarEnable(false).init();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(R.anim.bottom_in, R.anim.bottom_silent);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.bottom_silent, R.anim.bottom_out);
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        tvShowName = FBIA(R.id.tv_showName);
        textView1CurrTime = FBIA(R.id.textView1_curr_time);
        seekBar1 = FBIA(R.id.seekBar1);
        textView1TotalTime = FBIA(R.id.textView1_total_time);
        mediaPlay = FBIA(R.id.media_play);
        beisuzhi1 = FBIA(R.id.beisuzhi1);
        beisuzhi2 = FBIA(R.id.beisuzhi2);
        beisuzhi3 = FBIA(R.id.beisuzhi3);
        FBIA(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initData();
    }

    @Override
    public void initListener() {
        super.initListener();
        mediaPlay.setOnClickListener(this::onViewClicked);
        beisuzhi1.setOnClickListener(this::onViewClicked);
        beisuzhi2.setOnClickListener(this::onViewClicked);
        beisuzhi3.setOnClickListener(this::onViewClicked);
    }

    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.media_play) {
            try {
                if (service.isPlaying()) {
                    //暂停
                    service.pause();
                } else {
                    //播放
                    service.start();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (id == R.id.beisuzhi1) {
            setBeiSu(1.0f);
        } else if (id == R.id.beisuzhi2) {
            setBeiSu(2.0f);
        } else if (id == R.id.beisuzhi3) {
            setBeiSu(3.0f);
        }
    }


    private void setBeiSu(float v) {
        try {
            service.setBei(v);
            if (v == 1.0f) {
                beisuzhi1.setTextColor(Color.BLACK);
                beisuzhi2.setTextColor(Color.WHITE);
                beisuzhi3.setTextColor(Color.WHITE);
            } else if (v == 2.0f) {
                beisuzhi1.setTextColor(Color.WHITE);
                beisuzhi2.setTextColor(Color.BLACK);
                beisuzhi3.setTextColor(Color.WHITE);
            } else if (v == 3.0f) {
                beisuzhi1.setTextColor(Color.WHITE);
                beisuzhi2.setTextColor(Color.WHITE);
                beisuzhi3.setTextColor(Color.BLACK);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //播放开始了
            if (MusicPlayerService.ACTION_OPENAUDIO.equals(intent.getAction())) {
                String stringExtra = intent.getStringExtra("title");
                tvShowName.setText(stringExtra);
                mediaPlay.setImageResource(R.drawable.player_pause);
            } else if (MusicPlayerService.ACTION_PAUSE.equals(intent.getAction())) {
                mediaPlay.setImageResource(R.drawable.player_play);
            } else if (MusicPlayerService.ACTION_FINISH.equals(intent.getAction())) {
                mediaPlay.setImageResource(R.drawable.player_play);
            } else if (MusicPlayerService.ACTION_BEISU.equals(intent.getAction())) {
                String stringExtra = intent.getStringExtra("beisu");
                if ("X1.0".equals(stringExtra)) {
                    setBeiSu(1.0f);
                } else if ("X2.0".equals(stringExtra)) {
                    setBeiSu(2.0f);
                } else if ("X3.0".equals(stringExtra)) {
                    setBeiSu(3.0f);
                }
            }
        }
    }

    private void bindAndStartService() {
        Intent intent = new Intent(this, MusicPlayerService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) mp3List);//序列化,要注意转化(Serializable)
        intent.putExtras(bundle);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        startService(intent);//屏蔽服务被创建多次
    }

    private ServiceConnection conn = new ServiceConnection() {
        /**
         * 当和服务建立连接成功后的回调
         * @param name
         * @param iBinder
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            service = IMusicPlayerService.Stub.asInterface(iBinder);
            if (service != null) {
                if (notification == true) {
                    try {
                        tvShowName.setText(service.getAudioName());
                        if (service.isPlaying()) {
                            mediaPlay.setImageResource(R.drawable.player_pause);
                        } else {
                            mediaPlay.setImageResource(R.drawable.player_play);
                        }
                        setBeiSu(service.getBei());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(PROGRESS);
                    return;
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            service.openAudio(0);
                            handler.sendEmptyMessage(PROGRESS);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }, 100);
            }
        }

        /**
         * 当和服务断开连接的回调
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {


        }
    };

    protected void initData() {
        //获取list方式
        //true:状态栏进入当前页面，false:从列表进入
        Bundle bundle = getIntent().getExtras();
        notification = getIntent().getBooleanExtra("notification", false);
        mp3List = (DownloadEntity) bundle.getSerializable("list");
        //注册监听广播
        receiver = new MyBroadcastReceiver();
        IntentFilter interFilter = new IntentFilter();
        interFilter.addAction(MusicPlayerService.ACTION_OPENAUDIO);
        interFilter.addAction(MusicPlayerService.ACTION_PAUSE);
        interFilter.addAction(MusicPlayerService.ACTION_FINISH);
        interFilter.addAction(MusicPlayerService.ACTION_PLAY);
        interFilter.addAction(MusicPlayerService.ACTION_BEISU);
        registerReceiver(receiver, interFilter);
        bindAndStartService();
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    this.progress = (int) (progress * service.getDuration() / seekBar.getMax());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                try {
                    service.seekTo(this.progress);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public String formatPlayTime(int time) {
        String times = "";
        //将毫秒换算成秒
        int totalSeconds = time / 1000;
        int minute = totalSeconds % 3600 / 60;
        int second = totalSeconds % 60;
        times = String.format("%02d:%02d", minute, second);
        return times;

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case PROGRESS:
                    try {
                        int currentPosition = service.getCurrentPosition();
                        int duration = service.getDuration();
                        String currentTime = formatPlayTime(currentPosition);
                        String totalTime = formatPlayTime(duration);
                        if (duration > 0) {
                            long pos = seekBar1.getMax() * currentPosition / duration;
                            seekBar1.setProgress((int) pos);
                        }
                        textView1CurrTime.setText(currentTime);
                        textView1TotalTime.setText(totalTime);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    removeMessages(PROGRESS);
                    sendEmptyMessageDelayed(PROGRESS, 1000);
                    break;
            }
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.activity_player;
    }

    @Override
    public String getTitleText() {
        return "";
    }

    @Override
    protected void onDestroy() {
        //取消绑定服务
        if (conn != null) {
            unbindService(conn);
            conn = null;
        }
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
        //把所有消息和任务从栈中移除
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

}
