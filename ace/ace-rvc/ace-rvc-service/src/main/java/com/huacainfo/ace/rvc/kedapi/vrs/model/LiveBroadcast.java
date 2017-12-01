package com.huacainfo.ace.rvc.kedapi.vrs.model;

import java.util.List;

/**
 * Created by Arvin on 2017/11/30.
 */
public class LiveBroadcast {
    /// <summary>
    /// kedacom
    /// </summary>
    private String author;
    /// <summary>
    /// reserve
    /// </summary>
    private String authority;
    /// <summary>
    /// reserve
    /// </summary>
    private String content;
    /// <summary>
    /// 1788944768
    /// </summary>
    private String modifyTime;
    /// <summary>
    /// API接口创建会议20171130
    /// </summary>
    private String name;
    /// <summary>
    /// ProList
    /// </summary>
    private List<ProList> proList;
    /// <summary>
    /// Published
    /// </summary>
    private boolean published;
    /// <summary>
    /// ResolutionList
    /// </summary>
    private List<String> resolutionList;
    /// <summary>
    /// StreamNum
    /// </summary>
    private int streamNum;
    /// <summary>
    /// Type
    /// </summary>
    private int type;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProList> getProList() {
        return proList;
    }

    public void setProList(List<ProList> proList) {
        this.proList = proList;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public List<String> getResolutionList() {
        return resolutionList;
    }

    public void setResolutionList(List<String> resolutionList) {
        this.resolutionList = resolutionList;
    }

    public int getStreamNum() {
        return streamNum;
    }

    public void setStreamNum(int streamNum) {
        this.streamNum = streamNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static class ProList {
        /// <summary>
        /// SecStream
        /// </summary>
        private boolean SecStream;
        /// <summary>
        /// Id
        /// </summary>
        private int id;
        /// <summary>
        /// IndexList
        /// </summary>
        private List<IndexList> indexList;
        /// <summary>
        /// 视频0
        /// </summary>
        private String name;
        /// <summary>
        /// ResolutionNum
        /// </summary>
        private int resolutionNum;

        public boolean isSecStream() {
            return SecStream;
        }

        public void setSecStream(boolean secStream) {
            SecStream = secStream;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<IndexList> getIndexList() {
            return indexList;
        }

        public void setIndexList(List<IndexList> indexList) {
            this.indexList = indexList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getResolutionNum() {
            return resolutionNum;
        }

        public void setResolutionNum(int resolutionNum) {
            this.resolutionNum = resolutionNum;
        }
    }

    public static class IndexList {
        /// <summary>
        /// /hlsfile/2017/11/30/20171130162544_431/1280_720/playlist.m3u8
        /// </summary>
        private String hlsIndex;
        /// <summary>
        /// 1280_720
        /// </summary>
        private String resolution;

        public String getHlsIndex() {
            return hlsIndex;
        }

        public void setHlsIndex(String hlsIndex) {
            this.hlsIndex = hlsIndex;
        }

        public String getResolution() {
            return resolution;
        }

        public void setResolution(String resolution) {
            this.resolution = resolution;
        }
    }
}
