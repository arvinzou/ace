package com.huacainfo.ace.rvc.kedapi.vrs.model;

public class RoomState {
        /**
         * 直播室id
         */
        public int roomid;
        /**
         * 直播室名称
         */
        public String roomname;
        /**
         * 直播室所属用户域moid
         */
        public String userdomainmoid;
        /**
         * 节目id
         */
        public int prgid;
        /**
         * 节目已播放时间
         */
        public int elapse;
        /**
         * 直播点
         */
        public String lcastpoint;
        /**
         * 直播源描述文件，直播所需IP取‘获取VRS的IP组’API返回的IP，
         * 需要将COOKIE中的SSO_COOKIE_KEY以参数带入，参数名sso_token(URL后'?'分割，如?sso_token=***)
         */
        public String livestreampath;
        /**
         * 直播截帧图片的路径
         */
        public String livesnapshotpath;
        /**
         * 会议E164号/终端E164号(终端呼叫)
         */
        public String mte164;
        /**
         * 直播统计人数
         */
        public int livestatnum;
        /**
         * 直播室创建时间
         */
        public int createtime;

        public int getRoomid() {
            return roomid;
        }

        public void setRoomid(int roomid) {
            this.roomid = roomid;
        }

        public String getRoomname() {
            return roomname;
        }

        public void setRoomname(String roomname) {
            this.roomname = roomname;
        }

        public String getUserdomainmoid() {
            return userdomainmoid;
        }

        public void setUserdomainmoid(String userdomainmoid) {
            this.userdomainmoid = userdomainmoid;
        }

        public int getPrgid() {
            return prgid;
        }

        public void setPrgid(int prgid) {
            this.prgid = prgid;
        }

        public int getElapse() {
            return elapse;
        }

        public void setElapse(int elapse) {
            this.elapse = elapse;
        }

        public String getLcastpoint() {
            return lcastpoint;
        }

        public void setLcastpoint(String lcastpoint) {
            this.lcastpoint = lcastpoint;
        }

        public String getLivestreampath() {
            return livestreampath;
        }

        public void setLivestreampath(String livestreampath) {
            this.livestreampath = livestreampath;
        }

        public String getLivesnapshotpath() {
            return livesnapshotpath;
        }

        public void setLivesnapshotpath(String livesnapshotpath) {
            this.livesnapshotpath = livesnapshotpath;
        }

        public String getMte164() {
            return mte164;
        }

        public void setMte164(String mte164) {
            this.mte164 = mte164;
        }

        public int getLivestatnum() {
            return livestatnum;
        }

        public void setLivestatnum(int livestatnum) {
            this.livestatnum = livestatnum;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }
    }