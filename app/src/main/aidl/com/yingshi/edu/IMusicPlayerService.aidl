// IMusicPlayerService.aidl
package com.yingshi.edu;

// Declare any non-default types here with import statements

interface IMusicPlayerService {
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
              double aDouble, String aString);

              /**
                   * 播放音乐
                   */
                   void start();
                  /**
                   * 暂停音乐
                   */
                   void pause();
                  /**
                   * 得到艺术家
                   * @return
                   */
                   String getArtist();
                  /**
                   * 得到歌曲名称
                   * @return
                   */
                   String getAudioName();

                  /**
                   * 得到歌曲的路径
                   * @return
                   */
                   String getAudioPath();
                  /**
                   * 得到总时长
                   */
                   int getDuration();
                  /**
                   * 得到当前进度
                   * @return
                   */
                   int getCurrentPosition();
                  /**
                   * 拖动视频
                   * @param position
                   */
                   void seekTo(int position);
                  /**
                   * 设置播放模式
                   * @param playmode
                   */
                   void setPlaymode(int playmode);
                  /**
                   * 得到播放模式
                   * @return
                   */
                   int getPlaymode();
                  /**
                   * 播放上一个视频
                   */
                   void pre();
                  /**
                   * 播放下一个视频
                   */
                   void next();
                   /**
                    根据位置播放对应的音频
                   */
                   void openAudio(int position);
                   /**
                   * 是否正在播放
                   */
                   boolean isPlaying();

                   void notifyChang();

                   int getAudioSessionId();

                   void setBei(float l);

                   float getBei();
}
