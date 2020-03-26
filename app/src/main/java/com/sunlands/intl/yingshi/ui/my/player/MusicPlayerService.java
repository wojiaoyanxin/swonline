package com.sunlands.intl.yingshi.ui.my.player;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.arialyy.aria.core.download.DownloadEntity;
import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.intl.yingshi.R;
import com.yingshi.edu.IMusicPlayerService;

import java.io.IOException;
import java.io.Serializable;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MusicPlayerService extends Service {

    public static final String ACTION_OPENAUDIO = "mobilepalyer_openAudo";
    public static final String ACTION_FINISH = "mobilepalyer_finish";
    public static final String ACTION_PLAY = "mobilepalyer_play";
    public static final String ACTION_PAUSE = "mobilepalyer_pause";
    public static final String ACTION_BEISU = "mobilepalyer_beisu";
    private DownloadEntity mediaItem;
    IjkMediaPlayer mediaPlayer;

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;

    /**
     * 顺序播放
     */
    public static final int REPEAT_NORMAL = 0;
    /**
     * 单曲循环
     */
    public static final int REPEAT_SINGLE = 1;


    /**
     * 全部循环
     */
    public static final int REPEAT_ALL = 2;
    /**
     * 播放模式
     */
    private int playmode = REPEAT_NORMAL;

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }


    IMusicPlayerService.Stub stub = new IMusicPlayerService.Stub() {
        /**
         * 代表服务的实例
         */
        MusicPlayerService service = MusicPlayerService.this;

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void start() throws RemoteException {
            service.start();
        }

        @Override
        public void pause() throws RemoteException {
            service.pause();
        }

        @Override
        public String getArtist() throws RemoteException {
            return null;
        }

        @Override
        public String getAudioName() throws RemoteException {
            return service.getAudioName();
        }

        @Override
        public String getAudioPath() throws RemoteException {
            return service.getAudioPath();
        }

        @Override
        public int getDuration() throws RemoteException {
            return service.getDuration();
        }

        @Override
        public int getCurrentPosition() throws RemoteException {
            return service.getCurrentPosition();
        }

        @Override
        public void seekTo(int position) throws RemoteException {
            service.seekTo(position);
        }

        @Override
        public void setPlaymode(int playmode) throws RemoteException {
            service.setPlaymode(playmode);
        }

        @Override
        public int getPlaymode() throws RemoteException {
            return service.getPlaymode();
        }

        @Override
        public void pre() throws RemoteException {

        }

        @Override
        public void next() throws RemoteException {

        }


        @Override
        public void openAudio(int position) throws RemoteException {
            service.openAudio();
        }

        @Override
        public boolean isPlaying() throws RemoteException {
            return mediaPlayer.isPlaying();
        }

        @Override
        public void notifyChang() throws RemoteException {
            service.notifyChange(ACTION_OPENAUDIO);
        }

        @Override
        public int getAudioSessionId() throws RemoteException {
            return mediaPlayer.getAudioSessionId();
        }

        @Override
        public void setBei(float l) throws RemoteException {
            currentBei = l;
            mediaPlayer.setSpeed(l);
        }

        @Override
        public float getBei() throws RemoteException {
            return service.getBei();
        }
    };

    float currentBei = 1.0f;

    private float getBei() {
        return currentBei;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //千万不要忘记写
        return stub;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            mediaItem = (DownloadEntity) intent.getExtras().getSerializable("list");
          //  showFloatingWindow();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @SuppressLint("InflateParams")
    private void showFloatingWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {
            // 获取WindowManager服务
            windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
            // 设置LayoutParam
            layoutParams = new WindowManager.LayoutParams();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            } else {
                layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            }
            layoutParams.format = PixelFormat.RGBA_8888;
            layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            //宽高自适应
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            //显示的位置
            layoutParams.x = 300;
            layoutParams.y = 300;

            // 新建悬浮窗控件
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_exam, null);
            view.setOnTouchListener(new FloatingOnTouchListener());
            // 将悬浮窗控件添加到WindowManager
            windowManager.addView(view, layoutParams);
        }
    }

    private class FloatingOnTouchListener implements View.OnTouchListener {
        private int x;
        private int y;

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x = (int) event.getRawX();
                    y = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int nowX = (int) event.getRawX();
                    int nowY = (int) event.getRawY();
                    int movedX = nowX - x;
                    int movedY = nowY - y;
                    x = nowX;
                    y = nowY;
                    layoutParams.x = layoutParams.x + movedX;
                    layoutParams.y = layoutParams.y + movedY;
                    // 更新悬浮窗控件布局
                    windowManager.updateViewLayout(view, layoutParams);
                    break;
                default:
                    break;
            }
            return false;
        }
    }


    /**
     * 根据对应的位置播放音乐
     */
    private void openAudio() {
        if (mediaItem != null) {
            String SongPath = mediaItem.getDownloadPath();
            //MediaPlayer
            //先释放-停止
            if (mediaPlayer != null) {
                mediaPlayer.reset();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            try {
                //重新创建和设置
                mediaPlayer = new IjkMediaPlayer();
                //设置监听
                //设置准备监听
                mediaPlayer.setOnPreparedListener(new MyOnPreparedListener());
                //设置播放完成监听
                mediaPlayer.setOnCompletionListener(new MyOnCompletionListener());
                //设置出错监听
                mediaPlayer.setOnErrorListener(new MyOnErrorListener());
                //设置播放地址
                mediaPlayer.setDataSource(SongPath);
                //准备
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
                ToastUtils.showShort("播放出错");
            }
        } else {
            ToastUtils.showShort("播放出错");
        }

    }

    class MyOnErrorListener implements IMediaPlayer.OnErrorListener {
        @Override
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
            ToastUtils.showShort("播放出错");
            return true;
        }
    }

    class MyOnCompletionListener implements IMediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            notifyChange(ACTION_FINISH);
            openAudio();
        }
    }

    class MyOnPreparedListener implements IMediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            start();
            notifyBei();
        }
    }

    private void notifyBei() {
        if (getBei() == 1.0f) {
            mediaPlayer.setSpeed(1.0f);
            notifyChange2("X1.0");
        }else if (getBei() == 2.0f) {
            mediaPlayer.setSpeed(2.0f);
            notifyChange2("X2.0");
        }else if (getBei() == 3.0f) {
            mediaPlayer.setSpeed(3.0f);
            notifyChange2("X3.0");
        }
    }

    private void notifyChange(String action) {
        if (mediaItem == null) {
            return;
        }
        Intent intent = new Intent(action);
        intent.putExtra("title", mediaItem.getFileName());
        sendBroadcast(intent);
    }

    private void notifyChange2(String action) {
        Intent intent = new Intent(MusicPlayerService.ACTION_BEISU);
        intent.putExtra("beisu", action);
        sendBroadcast(intent);
    }

    public NotificationManager manager;

    /**
     * 播放音乐
     */
    private void start() {
        notifyChange(ACTION_OPENAUDIO);
        mediaPlayer.start();
        //状态显示播放状态
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, SystemAudioPlayer.class);
        intent.putExtra("notification", true);
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) mediaItem);//序列化,要注意转化(Serializable)
        intent.putExtras(bundle);//发送数据
        PendingIntent peningIntent = PendingIntent.
                getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification notification = new Notification.Builder(this).setSmallIcon(R.drawable.logo)
                .setContentTitle(mediaItem.getFileName())
                .setContentText("正在播放:" + getAudioName())
                .setContentIntent(peningIntent)
                .build();
        //设置点击后还存在
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        manager.notify(1, notification);
    }


    /**
     * 暂停音乐
     */
    public void pause() {
        notifyChange(ACTION_PAUSE);
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
        if (manager != null) {
            manager.cancel(1);
        }
    }

    /**
     * 得到歌曲名称
     *
     * @return
     */
    private String getAudioName() {
        if (mediaItem != null) {
            return mediaItem.getFileName();
        }
        return "";
    }

    /**
     * 得到歌曲路径
     *
     * @return
     */
    private String getAudioPath() {
        if (mediaItem != null) {
            return mediaItem.getDownloadPath();
        }
        return "";
    }

    /**
     * 得到总时长
     *
     * @return
     */
    private int getDuration() {
        return (int) mediaPlayer.getDuration();
    }

    /**
     * 得到当前进度
     *
     * @return
     */
    private int getCurrentPosition() {
        return (int) mediaPlayer.getCurrentPosition();
    }

    /**
     * 拖动视频
     *
     * @param position
     */
    private void seekTo(int position) {
        mediaPlayer.seekTo(position);
    }

    /**
     * 设置播放模式
     *
     * @param playmode
     */
    private void setPlaymode(int playmode) {
        this.playmode = playmode;
    }

    /**
     * 得到播放模式
     *
     * @return
     */
    private int getPlaymode() {
        return playmode;
    }


}
