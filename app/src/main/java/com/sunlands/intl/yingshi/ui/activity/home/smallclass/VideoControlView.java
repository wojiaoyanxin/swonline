package com.sunlands.intl.yingshi.ui.activity.home.smallclass;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sunlands.intl.yingshi.R;
import com.sunlands.sunlands_live_sdk.ijkplayer.widget.media.IMediaController;
import com.sunlands.sunlands_live_sdk.widget.MediaPlayerControl;
import com.sunlands.sunlands_live_sdk.widget.VerticalSeekBar;


/**
 * author: 李小凡
 * created on: 2018/8/24 18:05
 * description:视频播放控制器
 */
public class VideoControlView extends FrameLayout implements IMediaController, View.OnClickListener {
    ImageView fragmentVideoFloatIvPlay;
    ViewStub fragmentVideoFloatViewstub;
    ImageView fragmentVideoFloatIvDanmuku;
    ImageView activitySlidingImage;
    ProgressBar activitySlidingProgressbar;
    TextView activitySeeDuration;
    TextView activityTotalDuration;
    RelativeLayout activityGenseeSlidingLayout;
    TextView fragmentVideoFloatTvName;
    RelativeLayout floatTop;
    RelativeLayout floatBottom;
    TextView floatSpeed;
    TextView chooseSpeedPlay0;
    TextView chooseSpeedPlay125;
    TextView chooseSpeedPlay15;
    TextView chooseSpeedPlay2;
    LinearLayout chooseSpeedPlayLayout;
    ImageView fragmentVideoFloatIvChange;
    ImageView fragmentVideoFloatIvSwitch;

    VerticalSeekBar seekBarSound;
    RelativeLayout floatLeft;

    private MediaPlayerControl mPlayer;
    private ViewGroup mAnchor;
    private View mRoot;
    private SeekBar mSeekBar;
    private TextView mFullTime, mCurrentTime;
    private boolean mShowing;
    private boolean mDragging;
    private static final int sDefaultTimeout = 5 * 1000;
    private static final int FADE_OUT = 1;
    private static final int SHOW_PROGRESS = 2;
    private boolean isLive;
    private boolean isDanmakuOn = true; //默认显示弹幕
    private String title = "";
    private int speed = 1;

    //视频控制回调
    private ControlListener controlListener;

    public GestureDetector gestureDetector;
    AudioManager audioManager;

    private int soundLevel;
    private int soundMax;

    public VideoControlView(Context context) {
        super(context);
    }

    public VideoControlView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRoot = this;
    }

    public void setControlListener(ControlListener controlListener) {
        this.controlListener = controlListener;
    }

    /**
     * 设置是否为直播
     */
    public void isLive(boolean isLive) {
        this.isLive = isLive;
    }

    public void setTitle(String title) {
        this.title = title;
        if (fragmentVideoFloatTvName != null) {
            fragmentVideoFloatTvName.setText(title);
        }
    }

    @Override
    public void setMediaPlayer(MediaPlayerControl player) {
        mPlayer = player;
        updatePausePlay();
    }


    /**
     * Set the view that acts as the anchor for the control view.
     * This can for example be a VideoView, or your Activity's main view.
     * When VideoView calls this method, it will use the VideoView's parent
     * as the anchor.
     *
     * @param view The view to which to anchor the controller when it is visible.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void setAnchorView(View view) {
        if (mAnchor != null) {
            return;
        }
        mAnchor = (ViewGroup) view;
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
        mAnchor.addView(this);
        removeAllViews();
        initControllerView();
        show();
    }

    private int screenOrientation = Configuration.ORIENTATION_PORTRAIT;

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //横竖屏方向切换后重新加载布局，横竖屏布局有细微差异
        if (newConfig.orientation != screenOrientation) {
            screenOrientation = newConfig.orientation;
            removeAllViews();
            initControllerView();
            setVideoChangeVisibility(videoWindowStatus);
            if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                fragmentVideoFloatIvChange.setVisibility(GONE);
            } else {
//                fragmentVideoFloatIvChange.setVisibility(videoWindowStatus ? VISIBLE : GONE);
                if (videoWindowStatus) {
                    fragmentVideoFloatIvDanmuku.setVisibility(danmakuVisibility ? VISIBLE : GONE);
                }
            }
            show();
        }

        changeSize(newConfig);
    }

    private void changeSize(Configuration newConfig) {
        ViewGroup.LayoutParams params = null;
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            params = getLayoutParams();
            params.height = screenW * 9 / 16;
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            params = getLayoutParams();
            params.height = params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        setLayoutParams(params);
    }

    private void initView(View root) {
        fragmentVideoFloatIvPlay = root.findViewById(R.id.fragment_video_float_iv_play);
        fragmentVideoFloatViewstub = root.findViewById(R.id.fragment_video_float_viewstub);
        fragmentVideoFloatIvDanmuku = root.findViewById(R.id.fragment_video_float_iv_danmuku);
        activitySlidingImage = root.findViewById(R.id.activity_sliding_image);
        activitySlidingProgressbar = root.findViewById(R.id.activity_sliding_progressbar);
        activitySeeDuration = root.findViewById(R.id.activity_see_duration);
        activityTotalDuration = root.findViewById(R.id.activity_total_duration);
        activityGenseeSlidingLayout = root.findViewById(R.id.activity_gensee_sliding_layout);
        fragmentVideoFloatTvName = root.findViewById(R.id.fragment_video_float_tv_name);
        floatTop = root.findViewById(R.id.fragment_onlive_float_rl_top);
        floatBottom = root.findViewById(R.id.fragment_onlive_float_rl_bottom);
        floatSpeed = root.findViewById(R.id.fragment_video_float_speed);
        chooseSpeedPlay0 = root.findViewById(R.id.choose_speed_play_0);
        chooseSpeedPlay125 = root.findViewById(R.id.choose_speed_play_1_25);
        chooseSpeedPlay15 = root.findViewById(R.id.choose_speed_play_1_5);
        chooseSpeedPlay2 = root.findViewById(R.id.choose_speed_play_2);
        chooseSpeedPlayLayout = root.findViewById(R.id.choose_speed_play_layout);
        fragmentVideoFloatIvChange = root.findViewById(R.id.fragment_video_float_iv_change);
        fragmentVideoFloatIvChange.setVisibility(GONE);
        fragmentVideoFloatIvSwitch = root.findViewById(R.id.fragment_video_float_iv_switch);

        setOnClickListener(root);
    }

    private void setOnClickListener(View root) {
        root.findViewById(R.id.fragment_video_float_iv_back).setOnClickListener(this);
        root.findViewById(R.id.fragment_video_float_iv_fullscreen).setOnClickListener(this);
        fragmentVideoFloatIvPlay.setOnClickListener(this);
        fragmentVideoFloatIvChange.setOnClickListener(this);
        fragmentVideoFloatIvSwitch.setOnClickListener(this);
        fragmentVideoFloatIvDanmuku.setOnClickListener(this);
        floatSpeed.setOnClickListener(this);
        chooseSpeedPlay0.setOnClickListener(this);
        chooseSpeedPlay125.setOnClickListener(this);
        chooseSpeedPlay15.setOnClickListener(this);
        chooseSpeedPlay2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.fragment_video_float_iv_back) {
            if (controlListener != null) {
                controlListener.onBackClicked();
            }
        } else if (i == R.id.fragment_video_float_speed) {
            chooseSpeedPlayLayout.setVisibility(chooseSpeedPlayLayout.getVisibility() == VISIBLE ? GONE : VISIBLE);
        } else if (i == R.id.fragment_video_float_iv_fullscreen) {
            if (controlListener != null) {
                controlListener.fullScreen();
            }
        } else if (i == R.id.fragment_video_float_iv_change) {
            if (controlListener != null) {
                controlListener.changeSubFloat();
                setSubOrFloat(!isFloatingShow);
            }
        } else if (i == R.id.fragment_video_float_iv_switch) {
            if (controlListener != null) {
                controlListener.changeMainSub();
            }
        } else if (i == R.id.fragment_video_float_iv_play) {
            doPauseResume();
            show(sDefaultTimeout);
        } else if (i == R.id.fragment_video_float_iv_danmuku) {
            showDanmaku();
        } else if (i == R.id.choose_speed_play_0) {
            setSpeed(1);
        } else if (i == R.id.choose_speed_play_1_25) {
            setSpeed(2);
        } else if (i == R.id.choose_speed_play_1_5) {
            setSpeed(3);
        } else if (i == R.id.choose_speed_play_2) {
            setSpeed(4);
        }
    }

    /**
     * 初始化控制布局
     */
    private void initControllerView() {
        mRoot = LayoutInflater.from(getContext()).inflate(R.layout.view_small_video_control, this);
        initView(mRoot);
        fragmentVideoFloatTvName.setText(title);
        if (!isLive) {
            fragmentVideoFloatIvPlay.setVisibility(VISIBLE);
            floatSpeed.setVisibility(VISIBLE);
            fragmentVideoFloatViewstub.inflate();
            mSeekBar = findViewById(R.id.fragment_video_float_seekbar);
            mSeekBar.setMax(1000);
            mSeekBar.setOnSeekBarChangeListener(mSeekListener);
            mFullTime = findViewById(R.id.fragment_video_float_tv_fulltime);
            mCurrentTime = findViewById(R.id.fragment_video_float_tv_curtime);
        } else {
            fragmentVideoFloatIvPlay.setVisibility(GONE);
            fragmentVideoFloatViewstub.setVisibility(GONE);
            floatSpeed.setVisibility(GONE);
        }

        //状态设置和恢复
        fragmentVideoFloatIvDanmuku.setImageResource(isDanmakuOn ? R.drawable.fragment_video_float_drawable_danmaku_on : R.drawable.fragment_video_float_drawable_danmaku_off);
        //设置弹幕切换按钮是否可见
        fragmentVideoFloatIvDanmuku.setVisibility(danmakuVisibility ? VISIBLE : GONE);
        //设置sub和float窗切换状态
        fragmentVideoFloatIvChange.setImageResource(isFloatingShow ? R.drawable.fragment_video_float_drawable_change_big : R.drawable.fragment_video_float_drawable_change_small);


        gestureDetector = new GestureDetector(getContext(), gestureListener);
        if (audioManager == null) {
            audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        }

        seekBarSound = findViewById(R.id.fragment_video_float_seekbar_sound);
        floatLeft = findViewById(R.id.fragment_video_float_rl_left);
        soundLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        soundMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        if (soundMax > 0 && seekBarSound != null) {
            seekBarSound.setMax(soundMax);
            seekBarSound.setProgress(soundLevel);
            seekBarSound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (audioManager == null)
                        return;
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_SHOW_UI);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        }
    }

    private int screenW = getResources().getDisplayMetrics().widthPixels;

    private GestureDetector.OnGestureListener gestureListener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            chooseSpeedPlayLayout.setVisibility(GONE);
            if (!mShowing) {
                show();
            } else {
                hide();
            }
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (Math.abs(distanceX) > Math.abs(distanceY)) {
                if (e1.getX() - e2.getX() > verticalMinDistance && Math.abs(distanceX) > minVelocity) {
                    isScroll = true;
                    int temp = (int) (e1.getX() - e2.getX());
                    slidingLeft(temp);
                } else if (e2.getX() - e1.getX() > verticalMinDistance && Math.abs(distanceX) > minVelocity) {
                    float temp = e2.getX() - e1.getX();
                    isScroll = true;
                    slidingRight(temp);
                }
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        private int verticalMinDistance = 0;
        private int minVelocity = 0;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    };

    //是否左右拖动标志位
    private boolean isScroll = false;
    //是否拖动位置
    private int seekToPosition;

    public void slidingLeft(float index) {
        if (!isLive) {
            int position = mPlayer.getCurrentPosition();
            int duration = mPlayer.getDuration();
            activitySlidingImage.setImageResource(R.drawable.video_retreat_quickly);
            activityGenseeSlidingLayout.setVisibility(View.VISIBLE);
            final float temp = index / screenW * 360000;
            seekToPosition = (int) (position - temp);
            if (seekToPosition < 0) {
                seekToPosition = 0;
            }
            activitySlidingProgressbar.setProgress((int) ((position - temp) / duration * 100));
            activitySeeDuration.setText(formatMs(seekToPosition));
            activityTotalDuration.setText("/" + formatMs(duration));
        }
    }

    public void slidingRight(float index) {
        if (!isLive) {
            int position = mPlayer.getCurrentPosition();
            int duration = mPlayer.getDuration();
            activitySlidingImage.setImageResource(R.drawable.video_fast_forward);
            activityGenseeSlidingLayout.setVisibility(View.VISIBLE);
            final float temp = index / screenW * 360000;
            seekToPosition = (int) (position + temp);
            if (seekToPosition > duration) {
                seekToPosition = duration;
            }
            activitySlidingProgressbar.setProgress((int) ((position + temp) / duration * 100));
            activitySeeDuration.setText(formatMs(seekToPosition));
            activityTotalDuration.setText("/" + formatMs(duration));
        }
    }


    /**
     * Show the controller on screen. It will go away
     * automatically after 3 seconds of inactivity.
     */
    public void show() {
        show(sDefaultTimeout);
    }

    /**
     * 控制器显示动画
     */
    private void showAnimation() {
        ObjectAnimator topAnimator = ObjectAnimator.ofFloat(floatTop, "translationY", -floatTop.getHeight(), 0).setDuration(300);
        ObjectAnimator bottomAnimator = ObjectAnimator.ofFloat(floatBottom, "translationY", floatBottom.getHeight(), 0).setDuration(300);

        AnimatorSet set = new AnimatorSet();
        if (floatLeft != null) {
            ObjectAnimator leftAnimator = ObjectAnimator.ofFloat(floatLeft, "translationX", -floatLeft.getWidth(), 0).setDuration(300);
            set.play(topAnimator).with(bottomAnimator).with(leftAnimator);
        } else {
            set.play(topAnimator).with(bottomAnimator);
        }
        set.start();
    }

    /**
     * 控制器隐藏动画
     */
    private void hideAnimation() {
        ObjectAnimator topAnimator = ObjectAnimator.ofFloat(floatTop, "translationY", 0, -floatTop.getHeight()).setDuration(300);
        ObjectAnimator bottomAnimator = ObjectAnimator.ofFloat(floatBottom, "translationY", 0, floatBottom.getHeight()).setDuration(300);
        final AnimatorSet set = new AnimatorSet();
        if (floatLeft != null) {
            ObjectAnimator leftAnimator = ObjectAnimator.ofFloat(floatLeft, "translationX", 0, -floatLeft.getWidth()).setDuration(300);
            set.play(topAnimator).with(bottomAnimator).with(leftAnimator);
        } else {
            set.play(topAnimator).with(bottomAnimator);
        }
        set.start();
    }

    /**
     * Show the controller on screen. It will go away
     * automatically after 'timeout' milliseconds of inactivity.
     *
     * @param timeout The timeout in milliseconds. Use 0 to show
     *                the controller until hide() is called.
     */
    public void show(int timeout) {
        if (!mShowing && mAnchor != null) {
            setProgress();
            showAnimation();
            mShowing = true;
        }
        updatePausePlay();

        // cause the progress bar to be updated even if mShowing
        // was already true.  This happens, for example, if we're
        // paused with the progress bar showing the user hits play.
        mHandler.sendEmptyMessage(SHOW_PROGRESS);

        Message msg = mHandler.obtainMessage(FADE_OUT);
        if (timeout != 0) {
            mHandler.removeMessages(FADE_OUT);
            mHandler.sendMessageDelayed(msg, timeout);
        }
    }

    public boolean isShowing() {
        return mShowing;
    }

    public boolean isPlaying() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            return true;
        }
        return false;
    }

    /**
     * Remove the controller from the screen.
     */
    public void hide() {
        if (mAnchor == null)
            return;
        if (mShowing) {
            try {
                mHandler.removeMessages(SHOW_PROGRESS);
            } catch (IllegalArgumentException ex) {
            }
            mShowing = false;
        }

        hideAnimation();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FADE_OUT:
                    hide();
                    break;
                case SHOW_PROGRESS:
                    setProgress();
                    if (!mDragging && mShowing) {
                        msg = obtainMessage(SHOW_PROGRESS);
                        sendMessageDelayed(msg, 500);
                    }
                    break;
            }
        }
    };

    private int setProgress() {
        if (mPlayer == null || mDragging) {
            return 0;
        }

        setSoundBar();

        if (!isLive) {//点播才需要更新播放状态显示
            updatePausePlay();
            int position = mPlayer.getCurrentPosition();
            int duration = mPlayer.getDuration();
            if (controlListener != null) {
                controlListener.currentPosition(position);
            }
            if (mSeekBar != null) {
                if (duration > 0) {
                    // use long to avoid overflow
                    long pos = 1000L * position / duration;
                    mSeekBar.setProgress((int) pos);
                }
                int percent = mPlayer.getBufferPercentage();
                mSeekBar.setSecondaryProgress(percent * 10);
            }

            if (mFullTime != null)
                mFullTime.setText(formatMs(duration));
            if (mCurrentTime != null)
                mCurrentTime.setText(formatMs((position)) + "/");

            return position;
        }
        return 0;
    }

    private void setSoundBar() {
        if (audioManager != null && seekBarSound != null) {
            soundLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            soundMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            seekBarSound.setMax(soundMax);
            seekBarSound.setProgress(soundLevel);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isScroll = false;
                break;
            case MotionEvent.ACTION_UP:
                if (isScroll) { //
                    seekTo();
                    isScroll = false;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                hide();
                break;
            default:
                break;
        }
        gestureDetector.onTouchEvent(event);
        return true;
    }

    private long downTime = 0;  //距离上次拖动时间需要大于两秒钟

    /**
     * 拖动后跳转到新的位置
     */
    private void seekTo() {
        long nowTime = System.currentTimeMillis();
        activityGenseeSlidingLayout.setVisibility(View.GONE);
        if (nowTime - downTime > 2000) {
            downTime = nowTime;
            if (null != mPlayer && mPlayer.isPlaying() && !isLive) {
                mPlayer.seekTo(seekToPosition);
                show(sDefaultTimeout);
            }
        }
    }

    @Override
    public boolean onTrackballEvent(MotionEvent ev) {
        show(sDefaultTimeout);
        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        final boolean uniqueDown = event.getRepeatCount() == 0
                && event.getAction() == KeyEvent.ACTION_DOWN;
        if (keyCode == KeyEvent.KEYCODE_HEADSETHOOK
                || keyCode == KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE
                || keyCode == KeyEvent.KEYCODE_SPACE) {
            if (uniqueDown) {
                doPauseResume();
                show(sDefaultTimeout);
                if (fragmentVideoFloatIvPlay != null) {
                    fragmentVideoFloatIvPlay.requestFocus();
                }
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MEDIA_PLAY) {
            if (uniqueDown && !mPlayer.isPlaying()) {
                mPlayer.start();
                updatePausePlay();
                show(sDefaultTimeout);
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MEDIA_STOP
                || keyCode == KeyEvent.KEYCODE_MEDIA_PAUSE) {
            if (uniqueDown && mPlayer.isPlaying()) {
                mPlayer.pause();
                updatePausePlay();
                show(sDefaultTimeout);
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
                || keyCode == KeyEvent.KEYCODE_VOLUME_UP
                || keyCode == KeyEvent.KEYCODE_VOLUME_MUTE
                || keyCode == KeyEvent.KEYCODE_CAMERA) {
            // don't show the controls for volume adjustment
            return super.dispatchKeyEvent(event);
        } else if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_MENU) {
            if (uniqueDown) {
                hide();
            }
            return true;
        }

        show(sDefaultTimeout);
        return super.dispatchKeyEvent(event);
    }

    private void updatePausePlay() {
        if (mRoot != null && fragmentVideoFloatIvPlay != null)
            if (mPlayer.isPlaying()) {
                fragmentVideoFloatIvPlay.setImageResource(R.drawable.ic_vod_pause_normal);
            } else {
                fragmentVideoFloatIvPlay.setImageResource(R.drawable.ic_vod_play_normal);
            }
    }

    private boolean isFloatingShow = false;

    public void setSubOrFloat(boolean isFloatingShow) {
        this.isFloatingShow = isFloatingShow;
        if (fragmentVideoFloatIvChange != null) {
            fragmentVideoFloatIvChange.setImageResource(isFloatingShow ? R.drawable.fragment_video_float_drawable_change_big : R.drawable.fragment_video_float_drawable_change_small);
        }
    }

    private void doPauseResume() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        } else {
            mPlayer.start();
        }
        updatePausePlay();
    }

    // There are two scenarios that can trigger the seekbar listener to trigger:
    //
    // The first is the user using the touchpad to adjust the posititon of the
    // seekbar's thumb. In this case onStartTrackingTouch is called followed by
    // a number of onProgressChanged notifications, concluded by onStopTrackingTouch.
    // We're setting the field "mDragging" to true for the duration of the dragging
    // session to avoid jumps in the position in case of ongoing playback.
    //
    // The second scenario involves the user operating the scroll ball, in this
    // case there WON'T BE onStartTrackingTouch/onStopTrackingTouch notifications,
    // we will simply apply the updated position without suspending regular updates.
    private SeekBar.OnSeekBarChangeListener mSeekListener = new SeekBar.OnSeekBarChangeListener() {
        long newPosition;

        public void onStartTrackingTouch(SeekBar bar) {
            show(3600000);
            mDragging = true;

            // By removing these pending progress messages we make sure
            // that a) we won't update the progress while the user adjusts
            // the seekbar and b) once the user is done dragging the thumb
            // we will post one of these messages to the queue again and
            // this ensures that there will be exactly one message queued up.
            mHandler.removeMessages(SHOW_PROGRESS);
        }

        public void onProgressChanged(SeekBar bar, int progress, boolean fromuser) {
            if (!fromuser) {
                // We're not interested in programmatically generated changes to
                // the progress bar's position.
                return;
            }
            long duration = mPlayer.getDuration();
            newPosition = (duration * progress) / 1000L;
            // 系统原来的实现是在progress改变的时候时刻都在进行videoplayer的seek
            //这会导致seek m3u8切片文件的时候拖动seek时不准确，所以需要在拖动完成后才进行播放器的seekTo()
            //                mPlayer.seekTo((int) newPosition);
            if (mCurrentTime != null)
                mCurrentTime.setText(formatMs((int) newPosition));
        }

        public void onStopTrackingTouch(SeekBar bar) {
            mDragging = false;
            mPlayer.seekTo((int) newPosition);
            setProgress();
            updatePausePlay();
            show(sDefaultTimeout);
            // Ensure that progress is properly updated in the future,
            // the call to show() does not guarantee this because it is a
            // no-op if we are already showing.
            mHandler.sendEmptyMessage(SHOW_PROGRESS);
        }
    };

    @Override
    public void setEnabled(boolean enabled) {
        if (mSeekBar != null) {
            mSeekBar.setEnabled(enabled);
        }
//        disableUnsupportedButtons();
        super.setEnabled(enabled);
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (seekBarSound != null) {
            seekBarSound.setOnSeekBarChangeListener(null);
        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        controlListener = null;
    }

    /**
     * 设置
     *
     * @param s
     */
    private View curSpeedView;

    private void setSpeed(int s) {
        if (curSpeedView != null) {
            curSpeedView.setEnabled(true);
        }
        speed = s;
        float spd = 1f;
        switch (speed) {
            case 1:
                spd = 1f;
                curSpeedView = chooseSpeedPlay0;
                floatSpeed.setText(R.string.video_speed_1);
                break;
            case 2:
                spd = 1.25f;
                curSpeedView = chooseSpeedPlay125;
                floatSpeed.setText(R.string.video_speed_2);
                break;
            case 3:
                spd = 1.5f;
                curSpeedView = chooseSpeedPlay15;
                floatSpeed.setText(R.string.video_speed_3);
                break;
            case 4:
                spd = 2f;
                curSpeedView = chooseSpeedPlay2;
                floatSpeed.setText(R.string.video_speed_4);
                break;
        }
        if (curSpeedView != null) {
            curSpeedView.setEnabled(false);
        }
        chooseSpeedPlayLayout.setVisibility(GONE);
        if (controlListener != null) {
            controlListener.setSpeed(spd);
        }
    }

    //弹幕开关是否可见
    private boolean danmakuVisibility = false;

    /**
     * 设置弹幕库开关是否可见
     *
     * @param visibility 是否可见
     */
    public void setDanmakuVisibility(boolean visibility) {
        danmakuVisibility = visibility;
        if (fragmentVideoFloatIvDanmuku != null) {
            fragmentVideoFloatIvDanmuku.setVisibility(visibility ? VISIBLE : GONE);
        }
    }

    //sub 或float 小窗是否显示
    private boolean videoWindowStatus = true;

    //关闭sub和float小窗后要隐藏弹幕开关、交换窗口按钮
    public void setVideoChangeVisibility(boolean visibility) {
        videoWindowStatus = visibility;
        if (fragmentVideoFloatIvDanmuku != null && fragmentVideoFloatIvChange != null && fragmentVideoFloatIvSwitch != null) {
            fragmentVideoFloatIvDanmuku.setVisibility(visibility ? VISIBLE : GONE);
            //竖屏下才显示浮动屏、副屏交换按钮
//            fragmentVideoFloatIvChange.setVisibility(visibility && screenOrientation == Configuration.ORIENTATION_PORTRAIT ? VISIBLE : GONE);
            // fragmentVideoFloatIvSwitch.setVisibility(visibility ? VISIBLE : GONE);
        }
    }


    public boolean isDanmakuOn() {
        return isDanmakuOn;
    }

    /**
     * 显示或者关闭弹幕
     */
    private void showDanmaku() {
        isDanmakuOn = !isDanmakuOn;
        fragmentVideoFloatIvDanmuku.setImageResource(isDanmakuOn ? R.drawable.fragment_video_float_drawable_danmaku_on : R.drawable.fragment_video_float_drawable_danmaku_off);
        if (controlListener != null) {
            controlListener.showDanmaku(isDanmakuOn);
        }
    }

    public void showDanmaku(boolean show) {
        isDanmakuOn = show;
        if (fragmentVideoFloatIvDanmuku != null) {
            fragmentVideoFloatIvDanmuku.setImageResource(isDanmakuOn ? R.drawable.fragment_video_float_drawable_danmaku_on : R.drawable.fragment_video_float_drawable_danmaku_off);
        }
    }

    public static String formatMs(int ms) {
        ms = Math.round(ms / 1000f);
        String result = "";
        int hour = 0, min = 0, second = 0;//
        hour = ms / 3600;
        min = ms / 60 - (hour * 60);
        second = ms - (ms / 60) * 60;
        if (hour < 10) {
            result += "0" + hour + ":";
        } else {
            result += hour + ":";
        }
        if (min < 10) {
            result += "0" + min + ":";
        } else {
            result += min + ":";
        }
        if (second < 10) {
            result += "0" + second;
        } else {
            result += second;
        }
        return result;
    }


    /**
     * 视频播放控制回调
     */
    public interface ControlListener {

        void onBackClicked(); //回退按钮点击

        void fullScreen(); //切换全屏

        void changeSubFloat(); //切换副屏和浮动屏

        void changeMainSub(); //切换主屏和副屏

        void showDanmaku(boolean show); //是否显示弹幕

        void setSpeed(float speed); //点击切换速度

        void currentPosition(int position);
    }
}