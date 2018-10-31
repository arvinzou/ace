package com.huacainfo.ace.society.constant;

/**
 * @Auther: Arvin
 * @Date: 2018/9/11 17:34
 * @Description:
 */
public interface BisType {

    /**
     * 个人信息审核
     */
    String PERSON_INFO = "personInfo";
    /**
     * 社会组织审核
     */
    String SOCIETY_ORG_INFO = "societyOrgInfo";

    /**
     * 线下活动审核
     */
    String ACTIVITY = "activity";

    /**
     * 线下活动-报道审核
     */
    String ACTIVITY_REPORT = "activityReport";

    /**
     * 文明随手拍审核
     */
    String BEHAVIOR = "behavior";

    /**
     * 方案提议点子审核
     */
    String SUBJECT_IDEA = "subjectIdea";

    /**
     * 直播审核
     */
    String LIVE_SHOW = "liveShow";

    /**
     * 课程审核
     */
    String COURSE = "course";


    /**
     * ================
     * 爱心币流水变动业务类型
     */
    /**
     * 订单消费
     */
    String POINTS_ORDER_CONSUME = "ORDER-CONSUME";
    /**
     * 公益活动 - 发起者
     */
    String POINTS_WELFARE_SPONSOR = "WELFARE-SPONSOR";
    /**
     * 公益活动 - 参与者 - 加
     */
    String POINTS_WELFARE_PARTER_ADD = "WELFARE-PARTER-ADD";
    /**
     * 公益活动 - 参与者 - 减
     */
    String POINTS_WELFARE_PARTER_SUB = "WELFARE-PARTER-SUB";
    /**
     * 普及活动 - 发起者
     */
    String POINTS_ORDINARY_SPONSOR = "ORDINARY-SPONSOR";
    /**
     * 普及活动 - 参与者 - 加
     */
    String POINTS_ORDINARY_PARTER_ADD = "ORDINARY-PARTER-ADD";
    /**
     * 普及活动 - 参与者 - 减
     */
    String POINTS_ORDINARY_PARTER_SUB = "ORDINARY-PARTER-SUB";
    /**
     * 创意活动 - 发起者
     */
    String POINTS_CREATIVE_SPONSOR = "CREATIVE-SPONSOR";
    /**
     * 创意活动 - 参与者 - 加
     */
    String POINTS_CREATIVE_PARTER_ADD = "CREATIVE-PARTER-ADD";
    /**
     * 创意活动 - 参与者 - 减
     */
    String POINTS_CREATIVE_PARTER_SUB = "CREATIVE-PARTER-SUB";
    /**
     * 党建活动 - 发起者
     */
    String POINTS_PARTY_SPONSOR = "PARTY-SPONSOR";
    /**
     * 党建活动 - 参与者 - 加
     */
    String POINTS_PARTY_PARTER_ADD = "PARTY-PARTER-ADD";
    /**
     * 党建活动 - 参与者 - 减
     */
    String POINTS_PARTY_PARTER_SUB = "PARTY-PARTER-SUB";
    /**
     * 随手拍 - 发行
     */
    String POINTS_BEHAVIOR = "BEHAVIOR-ISSUE";
    /**
     * 我有点子 - 发行
     */
    String POINTS_IDEA = "IDEA-ISSUE";
    /**
     * 直播 - 发行
     */
    String POINTS_LIVE = "LIVE-ISSUE";
    /**
     * 邻里圈子 - 发行
     */
    String POINTS_GROUP = "GROUP-ISSUE";

    /**
     * ================
     * 评论模块业务类型（点赞共用此业务类型）
     */
    /**
     * 点子评论-业务类型
     */
    String COMMENT_IDEA = "idea";
    /**
     * 活动评论-业务类型
     */
    String COMMENT_ACTIVITY = "activity";
}

