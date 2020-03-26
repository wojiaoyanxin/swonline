package com.sunlands.intl.yingshi.ui.home.bean;

/**
 * @author yxin
 * @date 2019-12-20 - 16:22
 * @des
 */
public class SmallClassWatchBean {


    /**
     * liveData : {"partnerId":1,"roomId":44517,"ts":1581036686,"userAvatar":"https://education-1254383113.file.myqcloud.com/115586469379429.png","userId":227,"userName":"严新","userRole":1,"sign":"2488aac0a4c9c018b76e576d5103a866","status":1,"lastProgress":0}
     */

    private LiveDataBean liveData;

    public LiveDataBean getLiveData() {
        return liveData;
    }

    public void setLiveData(LiveDataBean liveData) {
        this.liveData = liveData;
    }

    public static class LiveDataBean {
        /**
         * partnerId : 1
         * roomId : 44517
         * ts : 1581036686
         * userAvatar : https://education-1254383113.file.myqcloud.com/115586469379429.png
         * userId : 227
         * userName : 严新
         * userRole : 1
         * sign : 2488aac0a4c9c018b76e576d5103a866
         * status : 1
         * lastProgress : 0
         */

        private int partnerId;
        private int roomId;
        private int ts;
        private String userAvatar;
        private int userId;
        private String userName;
        private int userRole;
        private String sign;
        private int status;
        private int lastProgress;
        private int isPause;

        public int getPartnerId() {
            return partnerId;
        }

        public void setPartnerId(int partnerId) {
            this.partnerId = partnerId;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public int getTs() {
            return ts;
        }

        public void setTs(int ts) {
            this.ts = ts;
        }

        public String getUserAvatar() {
            return userAvatar;
        }

        public void setUserAvatar(String userAvatar) {
            this.userAvatar = userAvatar;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserRole() {
            return userRole;
        }

        public void setUserRole(int userRole) {
            this.userRole = userRole;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getLastProgress() {
            return lastProgress;
        }

        public void setLastProgress(int lastProgress) {
            this.lastProgress = lastProgress;
        }

        public int getIsPause() {
            return isPause;
        }

        public void setIsPause(int isPause) {
            this.isPause = isPause;
        }
    }
}
