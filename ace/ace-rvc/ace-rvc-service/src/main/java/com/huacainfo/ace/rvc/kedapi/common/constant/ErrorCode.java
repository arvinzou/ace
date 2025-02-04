package com.huacainfo.ace.rvc.kedapi.common.constant;

/**
 * @description: API接口错误码信息
 * @author: ArvinZou
 * @create: 2017-11-16 10:14
 */
public interface ErrorCode {
    int ERROR_10001 = 10001;//软件认证失败
    int ERROR_10002 = 10002;//accountToken认证失败
    int ERROR_10101 = 10101;//password 验证错误
    int ERROR_10102 = 10102;//用户登录超时或者没有登录
    int ERROR_12001 = 12001;//MCU读取配置文件出错
    int ERROR_12002 = 12002;//用户鉴权失败
    int ERROR_12003 = 12003;//未通过用户鉴权
    int ERROR_12004 = 12004;//未注册
    int ERROR_12005 = 12005;//与MCU建链失败
    int ERROR_12006 = 12006;//链路故障, 并未发出该消息
    int ERROR_12007 = 12007;//只有当会议控制台未成功与MCU建链才能执行该操作
    int ERROR_12008 = 12008;//只有当会议控制台已成功与MCU建链才能执行该操作
    int ERROR_12012 = 12012;//MCU忙, 正在处理前一个请求
    int ERROR_12103 = 12103;//会议不存在
    int ERROR_12104 = 12104;//终端不存在
    int ERROR_12105 = 12105;//外设不存在
    int ERROR_19001 = 19001;//用户不存在
    int ERROR_19002 = 19002;//用户名或密码错误
    int ERROR_19003 = 19003;//用户权限错误
    int ERROR_19004 = 19004;//用户名重复
    int ERROR_19005 = 19005;//指定用户不存在
    int ERROR_19006 = 19006;//用户个数已达最大
    int ERROR_19007 = 19007;//当前用户不能删除自己
    int ERROR_19008 = 19008;//没有权限进行该操作，请联系管理员
    int ERROR_19901 = 19901;//用户组数量已达最大
    int ERROR_19902 = 19902;//用户组已存在
    int ERROR_19903 = 19903;//用户组不存在
    int ERROR_19904 = 19904;//用户组ID已存在
    int ERROR_19905 = 19905;//用户组ID非法
    int ERROR_20001 = 20001;//MCU等待应答超时
    int ERROR_20002 = 20002;//MCU正在初始化
    int ERROR_20003 = 20003;//MCU正忙
    int ERROR_20004 = 20004;//MCU正在等待前一条命令的处理
    int ERROR_20005 = 20005;//权限不够, 不能执行此操作
    int ERROR_20006 = 20006;//地址簿正在初始化
    int ERROR_20008 = 20008;//用户名或密码错误
    int ERROR_20017 = 20017;//与MCU版本不匹配，连接被拒绝。请更换正确的版本
    int ERROR_20051 = 20051;//制定外设不在线
    int ERROR_20052 = 20052;//指定的外设不合法
    int ERROR_20053 = 20053;//尝试连接的外设非法
    int ERROR_20054 = 20054;//得到的终端逻辑通道失败
    int ERROR_20055 = 20055;//高清解码卡hdu选看的MCU外设不存在
    int ERROR_20056 = 20056;//电视墙轮询中不能选看VMP或终端
    int ERROR_20057 = 20057;//该通道已被其他会议设置音量控制
    int ERROR_20058 = 20058;//有通道被占用, 请释放
    int ERROR_20059 = 20059;//当前会议没有受邀终端
    int ERROR_20060 = 20060;//当前已经开始网络抓包
    int ERROR_20061 = 20061;//当前并没开始网络抓包
    int ERROR_20101 = 20101;//无法为此会议分配资源，请稍候再创建新会议
    int ERROR_20102 = 20102;//创建会议失败,已达最大会议数
    int ERROR_20103 = 20103;//没有可用的模版空间
    int ERROR_20104 = 20104;//终端接入或转发板未连接，创建会议失败
    int ERROR_20105 = 20105;//该会议名已存在
    int ERROR_20106 = 20106;//会议号重复
    int ERROR_20107 = 20107;//该E164号码已存在
    int ERROR_20108 = 20108;//指定模版未找到
    int ERROR_20109 = 20109;//指定终端是您的上级MCU, 呼叫暂时被拒绝(您可能会被重新呼入)
    int ERROR_20111 = 20111;//没有空闲的适配器，创建双格式媒体会议或者预留适配器会议失败
    int ERROR_20112 = 20112;//没有空闲的混音器,混音会议创建失败
    int ERROR_20113 = 20113;//没有空闲的丢包重传器,丢包重传会议创建失败
    int ERROR_20114 = 20114;//会议加密属性与是否设置加密不一致, 会议创建失败
    int ERROR_20115 = 20115;//没有空闲的电视墙,含电视墙的会议创建失败
    int ERROR_20116 = 20116;//指定录像机不在线, 自动录像会议创建失败
    int ERROR_20117 = 20117;//没有空闲的DCS, 创建数据会议失败
    int ERROR_20118 = 20118;//无终端自动结束会议中终端不能为空
    int ERROR_20119 = 20119;//会议结束时间应该在当前时间之后
    int ERROR_20120 = 20120;//会议码率不支持
    int ERROR_20121 = 20121;//指定的会议音频或视频格式不支持
    int ERROR_20122 = 20122;//会议组播地址非法
    int ERROR_20123 = 20123;//会议组播地址已经被占用
    int ERROR_20124 = 20124;//分散会议组播地址非法
    int ERROR_20125 = 20125;//双速双格式不能支持分散会议
    int ERROR_20126 = 20126;//分散会议组播地址已经被占用
    int ERROR_20127 = 20127;//超过最大回传终端数
    int ERROR_20128 = 20128;//该类型会议不能进行低速组播
    int ERROR_20129 = 20129;//该类型会议不能进行低速录像
    int ERROR_20151 = 20151;//没有足够权限保存缺省会议, 会议已正常保存
    int ERROR_20161 = 20161;//会议号为空, 同时给出终端可选择的会议列表(错误号不可修改)
    int ERROR_20162 = 20162;//呼叫下级MCU失败, 该MCU正在召开其它会议
    int ERROR_20163 = 20163;//上级MCU呼叫被拒绝, 若要接受上级邀请, 请先结束当前会议
    int ERROR_20164 = 20164;//已达到会议数上限, 无法创建其他会议
    int ERROR_20165 = 20165;//当前会议不支持保存缺省会议
    int ERROR_20166 = 20166;//呼叫下级MCU失败, 该MCU已经被其它高级别MCU呼叫
    int ERROR_20167 = 20167;//当前时间已过周期性预约会议最后开启时间
    int ERROR_20168 = 20168;//周期性预约会议最早开启时间晚于最后开启时间
    int ERROR_20169 = 20169;//周期性预约会议设置的开启时间和有效时间冲突
    int ERROR_20170 = 20170;//周期时间到, 当前预约会议自动结束
    int ERROR_20301 = 20301;//超过MCU最大接入终端能力
    int ERROR_20302 = 20302;//转发板负载能力已满
    int ERROR_20303 = 20303;//语音激励时不能进行画面合成
    int ERROR_20304 = 20304;//画面合成时不能进行语音激励
    int ERROR_20305 = 20305;//当 前会议格式不支持画面合成
    int ERROR_20306 = 20306;//会议同时进行混音时无法进行指定风格的画面合成
    int ERROR_20307 = 20307;//会议正在启用适配时无法进行指定风格的混音
    int ERROR_20308 = 20308;//会议正在画面合成时无法进行指定风格的混音
    int ERROR_20309 = 20309;//会议终端数超过最大混音能力
    int ERROR_20310 = 20310;//会议终端数超过最大语音激励能力
    int ERROR_20311 = 20311;//会议正在启用适配时无法对该数量终端进行语音激励
    int ERROR_20312 = 20312;//会议正在对该数量终端进行语音激励时无法开启适配
    int ERROR_20313 = 20313;//会议正在对该数量终端进行混音时无法开启适配
    int ERROR_20314 = 20314;//无法支持更多的终端数量同时进行会议讨论, 讨论已经自动停止或切换为定制混音
    int ERROR_20315 = 20315;//混音终端数已经达到混音器最大通道数
    int ERROR_20316 = 20316;//没有空闲的混音器
    int ERROR_20317 = 20317;//语音激励与定制混音不能同时进行
    int ERROR_20318 = 20318;//混音开启异常
    int ERROR_20319 = 20319;//强制发言人时不允许放像
    int ERROR_20320 = 20320;//本级语音激励被上级级联定制混音冲掉
    int ERROR_20321 = 20321;//强制广播时不允许开启混音
    int ERROR_20331 = 20331;//MCU当前有空闲画面合成器, 但其合成能力均不支持所需风格. 无法进行画面合成或召开会议
    int ERROR_20332 = 20332;//会议当前使用的画面合成器不支持所需风格, 但本MCU还有其他画面合成器支持所需风格. 如果确的画面合成, 重新直接开启该风格的画面合成即可
    int ERROR_20333 = 20333;//指定的画面合成风格不被支持
    int ERROR_20334 = 20334;//没有空闲的画面合成器, 不能进行画面合成
    int ERROR_20335 = 20335;//当前的空闲的高清适配资源不足, 可能会导致稍后的召开会议失败
    int ERROR_20336 = 20336;//当前的空闲的高清适配资源不足, 召开会议失败
    int ERROR_20337 = 20337;//尝试占用空闲高清适配资源失败
    int ERROR_20338 = 20338;//本画面合成不支持主席跟随
    int ERROR_20341 = 20341;//会议已处于轮询广播状态, 不能开始语音激励
    int ERROR_20342 = 20342;//会议已处于已处于讨论状态, 不能进行语音激励控制
    int ERROR_20343 = 20343;//会议已处于已处于语音激励控制, 不能进行讨论
    int ERROR_20344 = 20344;//会议处于语音激励方式, 不能取消发言人
    int ERROR_20345 = 20345;//会议处于讨论方式, 不能指定发言人
    int ERROR_20346 = 20346;//会议处于语音激励方式, 不能指定发言人
    int ERROR_20347 = 20347;//会议处于强制广播模式, 不能选看vmp或终端
    int ERROR_20348 = 20348;//两次发言人切换间隔小于最小时间间隔, 不能指定发言人
    int ERROR_20349 = 20349;//当前的定制混音状态被中止
    int ERROR_20350 = 20350;//当前的会议讨论状态被中止
    int ERROR_20352 = 20352;//适配录像请求失败——适配器资源不够. 请释放或添加适配器资源进行重试, 或者选择普通会议录像功能进行重试
    int ERROR_20353 = 20353;//录像请求失败, 会议是AACLC单双声道会议, 请选择音频适配录像后进行重试
    int ERROR_20354 = 20354;//当前的空闲的音频适配资源不足, 召开会议失败
    int ERROR_20355 = 20355;//会议已开启语音激励, 不能开启会议轮询
    int ERROR_20356 = 20356;//会议正在开启混音, 请稍后
    int ERROR_20371 = 20371;//分配的适配通道不可用
    int ERROR_20381 = 20381;//分散会议不能执行本操作
    int ERROR_20382 = 20382;//接入能力满,终端呼入失败
    int ERROR_20383 = 20383;//高清终端接入能力满, 呼叫终端失败
    int ERROR_20384 = 20384;//为高清适配器或高清画面合成器作出的转发带宽预留超出了转发能力, 可能导致转发失败, 建议为其重新分配转发资源
    int ERROR_20385 = 20385;//PCMT 接入能力满, 终端入会失败
    int ERROR_20386 = 20386;//终端授权数满, 终端入会失败
    int ERROR_20387 = 20387;//USBKEY中的授权数据验证失败
    int ERROR_20388 = 20388;//语音终端接入能力满, 呼叫终端失败
    int ERROR_20389 = 20389;//AES加密会议, 秘钥不匹配, 拒绝入会
    int ERROR_20401 = 20401;//指定终端不可及, 可能不在线
    int ERROR_20402 = 20402;//指定终端拒绝加入会议
    int ERROR_20403 = 20403;//指定终端已在会议中
    int ERROR_20404 = 20404;//非主席终端不能执行此项操作
    int ERROR_20405 = 20405;//呼叫的终端已经与会
    int ERROR_20406 = 20406;//呼叫的终端没有可用的呼叫地址信息
    int ERROR_20407 = 20407;//不能级联下级MCU会议, 密码错误
    int ERROR_20408 = 20408;//上级MCU没有应答
    int ERROR_20409 = 20409;//不能呼叫会议本身
    int ERROR_20410 = 20410;//指定终端忙, 可能在另一会议中
    int ERROR_20411 = 20411;//终端保持链路失败
    int ERROR_20412 = 20412;//此会议为非开放会议, 需主席批准
    int ERROR_20413 = 20413;//已将申请提交主席
    int ERROR_20414 = 20414;//会议中已有上级MCU
    int ERROR_20415 = 20415;//指定终端已经在回传通道中
    int ERROR_20421 = 20421;//呼叫的终端类型与MCU类型不匹配
    int ERROR_20422 = 20422;//终端不在可调度资源的范围内
    int ERROR_20431 = 20431;//指定终端是只接收终端
    int ERROR_20432 = 20432;//被指定终端已经是会议主席
    int ERROR_20433 = 20433;//会议处于无主席模式,不能指定主席
    int ERROR_20434 = 20434;//会议中无主席
    int ERROR_20435 = 20435;//指定终端未与会
    int ERROR_20436 = 20436;//指定MCU未与会
    int ERROR_20437 = 20437;//指定终端已经是发言终端
    int ERROR_20438 = 20438;//会议中已有发言人
    int ERROR_20439 = 20439;//会议中无发言人
    int ERROR_20440 = 20440;//会议未级联
    int ERROR_20441 = 20441;//此操作只能针对本地终端
    int ERROR_20442 = 20442;//指定的下级MCU不存在, 锁定操作失败
    int ERROR_20443 = 20443;//终端版本不支持版本号获取
    int ERROR_20444 = 20444;//终端的媒体能力不支持指定操作
    int ERROR_20501 = 20501;//源与目的的媒体类型不同, 不能选看
    int ERROR_20502 = 20502;//需码率适配终端不得选看
    int ERROR_20503 = 20503;//混音时不得选择音频模式
    int ERROR_20504 = 20504;//混音时选看音视频, 音频选看将失败
    int ERROR_20505 = 20505;//监控终端时, 终端音视频通道有一个没打开
    int ERROR_20506 = 20506;//会控不能选看高清终端
    int ERROR_20507 = 20507;//音频选看被拒绝, 选看模式改变
    int ERROR_20508 = 20508;//视频选看被拒绝, 选看模式改变
    int ERROR_20509 = 20509;//视频适配资源不足, 视频选看失败
    int ERROR_20510 = 20510;//音频适配资源不足, 音频选看失败
    int ERROR_20511 = 20511;//由于系统资源有限, 您不能进行选看操作
    int ERROR_20512 = 20512;//音频终端不能做监控源
    int ERROR_20513 = 20513;//终端没有视频源
    int ERROR_20521 = 20521;//会议控制权被其它会控独享
    int ERROR_20522 = 20522;//无效的会议保护方式
    int ERROR_20523 = 20523;//无效的终端呼叫策略
    int ERROR_20524 = 20524;//轮询停止, 可能是因为所有待轮询的终端不在线或没有视频源或次数已完成(主席轮询, 会议轮询)
    int ERROR_20525 = 20525;//指定轮询位置失败, 指定终端不在轮询列表中
    int ERROR_20526 = 20526;//指定轮询位置失败, 指定终端不在会议中
    int ERROR_20527 = 20527;//指定轮询位置失败, 会议不在轮询
    int ERROR_20528 = 20528;//修改轮询列表失败, 当前轮询终端不能被删除
    int ERROR_20529 = 20529;//点名类型错误
    int ERROR_20530 = 20530;//点名操作消息长度错误
    int ERROR_20531 = 20531;//下级终端不能作点名人
    int ERROR_20532 = 20532;//点名人不能为空
    int ERROR_20533 = 20533;//被点名人不能为空
    int ERROR_20534 = 20534;//强制广播下不能点名
    int ERROR_20535 = 20535;//本点名操作将导致自画面合成风格及成员变更
    int ERROR_20536 = 20536;//本点名操作将导致语音激励停止
    int ERROR_20537 = 20537;//本点名操作将导致定制混音成员变更
    int ERROR_20538 = 20538;//本点名操作将导致智能混音停止
    int ERROR_20539 = 20539;//本点名操作将导致画面合成停止
    int ERROR_20540 = 20540;//本点名操作将导致当前点名变更
    int ERROR_20541 = 20541;//会议轮询的时候不允许开启点名
    int ERROR_20542 = 20542;//点名人掉线, 会议点名停止
    int ERROR_20543 = 20543;//被点名人掉线, 会议点名停止
    int ERROR_20544 = 20544;//混音器掉线, 会议点名停止
    int ERROR_20545 = 20545;//画面合成器掉线, 会议点名停止
    int ERROR_20546 = 20546;//部分终端不能加入当前画面合成
    int ERROR_20547 = 20547;//没有空闲的混音器, 点名无法进行
    int ERROR_20548 = 20548;//没有空闲的画面合成器, 点名无法进行
    int ERROR_20549 = 20549;//画面合成能力不足, 点名无法进行
    int ERROR_20550 = 20550;//画面合成能力不足, 点名合成风格切换为左右两画面
    int ERROR_20551 = 20551;//获取卫星频率失败, 卫星会议结束
    int ERROR_20561 = 20561;//上级抢断本地的轮训回传
    int ERROR_20562 = 20562;//主席轮询时不允许主席选看画面合成、主席选看以及切换主席操作
    int ERROR_20563 = 20563;//会议中主席不在线或者没有主席, 不允许开启主席轮训
    int ERROR_20564 = 20564;//上次点名操作还没完成
    int ERROR_20565 = 20565;//呼叫终端数超出License授权点数
    int ERROR_20566 = 20566;//呼叫终端数超过会议终端数
    int ERROR_20801 = 20801;//当前正在进行会议录像
    int ERROR_20802 = 20802;//当前不在进行会议录像
    int ERROR_20803 = 20803;//当前未被暂停会议录像
    int ERROR_20804 = 20804;//当前正在进行会议放像或在放像状态下, 不能开启会议轮训及指定发言人
    int ERROR_20805 = 20805;//当前不在进行会议放像
    int ERROR_20806 = 20806;//当前未被暂停会议放像
    int ERROR_20807 = 20807;//指定的终端处于录像状态
    int ERROR_20808 = 20808;//指定的终端未处于录像状态
    int ERROR_20809 = 20809;//指定的终端未处于录像暂停状态
    int ERROR_20811 = 20811;//当前正在进行会议混音
    int ERROR_20812 = 20812;//当前不在进行会议混音
    int ERROR_20813 = 20813;//当前未被暂停会议混音
    int ERROR_20814 = 20814;//指定终端不是混音组成员
    int ERROR_20816 = 20816;//会议已处于语音激励控制发言状态, 不能再次开始
    int ERROR_20817 = 20817;//会议未处于语音激励控制发言状态, 不能停止
    int ERROR_20818 = 20818;//会议已处于讨论状态, 不能再次开始
    int ERROR_20819 = 20819;//会议未处于讨论状态, 不能停止
    int ERROR_20821 = 20821;//有画面合成模板时不能设为动态分屏
    int ERROR_20822 = 20822;//画面合成已开始
    int ERROR_20823 = 20823;//画面合成尚未开始, 不能进行此项操作
    int ERROR_20824 = 20824;//画面合成未广播码流
    int ERROR_20825 = 20825;//动态分屏时必需是自动合成方式
    int ERROR_20826 = 20826;//会议不允许同一个终端占多个合成成员通道
    int ERROR_20827 = 20827;//会议不允许多通道设置成为发言人跟随模式
    int ERROR_20828 = 20828;//会议不允许多通道设置成为轮询跟随模式
    int ERROR_20829 = 20829;//会议带音频轮询时, 画面合成器不支持轮询跟随和发言人跟随同时存在
    int ERROR_20830 = 20830;//画面合成器正在处理前一请求, 请稍后再尝试
    int ERROR_20831 = 20831;//老版VMP不支持该功能
    int ERROR_20832 = 20832;//下级mcu不支持多回传, 其终端只能占用一个通道
    int ERROR_20833 = 20833;//当前通道解码能力不匹配, 请更换通道尝试
    int ERROR_20834 = 20834;//前适配通道不足, 终端无法进画面合成
    int ERROR_20835 = 20835;//VMP vicp资源不足, VMP开启失败
    int ERROR_20836 = 20836;//该终端不允许进VMP多个通道
    int ERROR_20837 = 20837;//会议不允许多通道设置成为双流跟随模式
    int ERROR_20838 = 20838;//会议不允许上级MCU占多个合成成员通道
    int ERROR_20839 = 20839;//为保证发言人图像质量, 其它终端可能受影响, 敬请谅解
    int ERROR_20840 = 20840;//VIP位置发生变化, 请重新操作
    int ERROR_20851 = 20851;//连接录播服务器失败
    int ERROR_20852 = 20852;//试图播放的文件与会议能力不匹配, 请选择其他文件
    int ERROR_20853 = 20853;//vrs主动挂断mcu, 录播服务器发生故障, 无法录放像
    int ERROR_20854 = 20854;//单回传会议中, 下级会议上传源发生改变, 停止录像
    int ERROR_20855 = 20855;//当前版本开启直播功能需要勾选录像功能
    int ERROR_20856 = 20856;//当前版本暂不支持单独开启录像或者直播功能
    int ERROR_20857 = 20857;//当前版本暂不支持单独停止录像或者直播功能
    int ERROR_20901 = 20901;//无可用合成资源
    int ERROR_20902 = 20902;//合成器异常停止
    int ERROR_20903 = 20903;//无可用混音器资源
    int ERROR_20904 = 20904;//混音器异常停止
    int ERROR_21401 = 21401;//N+1备份模式下不能创建会议或模板
    int ERROR_21402 = 21402;//N+1备份模式下不能操作用户/用户组信息
    int ERROR_21403 = 21403;//用户没有配置手动回滚
    int ERROR_21404 = 21404;//需要回滚的MCU未连接
    int ERROR_21405 = 21405;//主板没有画面合成器, 会议在备板进行的画面合成将无法在主板恢复
    int ERROR_21406 = 21406;//主板当前有空闲画面合成器, 但其合成能力均不支持所需风格, 画面合成将无法恢复。可能是由于备份端的画面合成能力大于本主端导致
    int ERROR_21501 = 21501;//网守资源耗尽
    int ERROR_21504 = 21504;//带宽资源不够
    int ERROR_21505 = 21505;//呼叫被GK拒绝
    int ERROR_21506 = 21506;//无效呼叫信令地址
    int ERROR_21508 = 21508;//MCU别名或E164号或会议E164号与GK上其他网络实体重复
    int ERROR_21509 = 21509;//对方忙
    int ERROR_21510 = 21510;//GK不明原因
    int ERROR_21511 = 21511;//正常挂断
    int ERROR_21513 = 21513;//GK操作超时
    int ERROR_21521 = 21521;//MCU未成功注册GK
    int ERROR_21522 = 21522;//呼叫时发生内部错误
    int ERROR_21531 = 21531;//GK计费账号不存在
    int ERROR_21532 = 21532;//GK计费账号密码不正确
    int ERROR_21533 = 21533;//GK计费数据库错误
    int ERROR_21534 = 21534;//GK停止计费的会议不存在
    int ERROR_21535 = 21535;//请求GK开始计费超时
    int ERROR_21536 = 21536;//请求GK结束计费超时
    int ERROR_21537 = 21537;//未知的GK计费错误
    int ERROR_21538 = 21538;//MCU未配置GK, 可能会导致召开计费会议失败
    int ERROR_21539 = 21539;//MCU未配置GK, 无法召开计费会议
    int ERROR_21540 = 21540;//GK计费服务器注册失败, 会议结束
    int ERROR_21541 = 21541;//会议计费异常, 将被结束
    int ERROR_21542 = 21542;//GK计费链路断, 会议结束
    int ERROR_21543 = 21543;//GK未配置Radius服务器, 无法召开计费会议
    int ERROR_21544 = 21544;//GK操作Radius服务器失败, 会议计费异常
    int ERROR_21545 = 21545;//会议的计费帐号已经被使用, 无法召开计费会议
    int ERROR_21546 = 21546;//MCU配置了GK, 但没有启用计费功能, 可能会导致召开计费会议失败
    int ERROR_21547 = 21547;//MCU配置了GK, 但没有启用计费功能, 无法召开计费会议
    int ERROR_21548 = 21548;//由于计费服务器故障, 停止计费失败, 您的话单可能略有出入
    int ERROR_21549 = 21549;//GK已经达到计费容量上限, MCU向GK计费注册失败
    int ERROR_21601 = 21601;//单板配置参数非法
    int ERROR_21602 = 21602;//混音器配置参数非法
    int ERROR_21603 = 21603;//录像机配置参数非法
    int ERROR_21604 = 21604;//画面合成器配置参数非法
    int ERROR_21605 = 21605;//电视墙配置参数非法
    int ERROR_21606 = 21606;//码流适配器配置参数非法
    int ERROR_21607 = 21607;//丢包重传器配置参数非法
    int ERROR_21608 = 21608;//多画面电视墙配置参数非法
    int ERROR_21609 = 21609;//MCU外设参数配置非法
    int ERROR_21610 = 21610;//MCU基本信息(Qos等)配置非法
    int ERROR_21611 = 21611;//不存在该IP的MCU主协议接入板
    int ERROR_21612 = 21612;//高清码流适配器配置参数非法
    int ERROR_21613 = 21613;//高清解码卡配置参数非法
    int ERROR_21614 = 21614;//单画面合成器配置参数非法
    int ERROR_21615 = 21615;//双画面合成器配置参数非法
    int ERROR_21616 = 21616;//Mpu码流适配器配置参数非法
    int ERROR_21617 = 21617;//增强型码流适配器配置参数非法
    int ERROR_21618 = 21618;//增强型画面合成器配置参数非法
    int ERROR_21621 = 21621;//写单板配置失败
    int ERROR_21622 = 21622;//写混音器配置失败
    int ERROR_21623 = 21623;//写录像机配置失败
    int ERROR_21624 = 21624;//写画面合成器配置失败
    int ERROR_21625 = 21625;//写电视墙配置失败
    int ERROR_21626 = 21626;//写码流适配器配置失败
    int ERROR_21627 = 21627;//写丢包重传器配置失败
    int ERROR_21628 = 21628;//写多画面电视墙配置失败
    int ERROR_21629 = 21629;//写MCU地址信息失败
    int ERROR_21630 = 21630;//写画面合成风格配置失败
    int ERROR_21631 = 21631;//写MCU本端配置失败
    int ERROR_21632 = 21632;//写MCU网络配置失败
    int ERROR_21633 = 21633;//写Qos配置失败
    int ERROR_21634 = 21634;//写网同步配置失败
    int ERROR_21635 = 21635;//写DCS配置失败
    int ERROR_21636 = 21636;//写Mcu配置标识失败
    int ERROR_21637 = 21637;//DSC网络IP配置与网络实际情况不符, 请修改配置
    int ERROR_21638 = 21638;//写登陆信息失败
    int ERROR_21639 = 21639;//写高清码流适配失败
    int ERROR_21640 = 21640;//写高清解码卡hdu失败
    int ERROR_21641 = 21641;//写单画面合成器失败
    int ERROR_21642 = 21642;//写双画面合成器失败
    int ERROR_21643 = 21643;//写MPU码流适配器失败
    int ERROR_21644 = 21644;//写增强型码流适配器失败
    int ERROR_21645 = 21645;//写增强型画面合成器失败
    int ERROR_21650 = 21650;//其它会控正在进行配置修改, 请稍后再试
    int ERROR_21651 = 21651;//读写配置失败
    int ERROR_21652 = 21652;//高清解码卡hdu预案数过大
    int ERROR_21653 = 21653;//读高清解码卡hdu失败
    int ERROR_21654 = 21654;//预案正在被使用, 不能被修改
    int ERROR_21655 = 21655;//不能添加空预案
    int ERROR_21659 = 21659;//前次短消息正在发送中, 请稍后再试
    int ERROR_21660 = 21660;//多运营网络配置错误
    int ERROR_21661 = 21661;//当前HDU或HDU2通道不支持多画面
    int ERROR_21662 = 21662;//当前HDU2不支持2个通道同时开启多画面
    int ERROR_21663 = 21663;//切换多画面输入参数错误
    int ERROR_21664 = 21664;//由于资源不足, 画面合成失败
    int ERROR_21701 = 21701;//调度席未配置电视墙或者配置了电视墙但均不在线
    int ERROR_21702 = 21702;//调度席未配置画面合成器或者配置了但不可用
    int ERROR_21703 = 21703;//当前会议模式正是要求改变的会议模式
    int ERROR_21704 = 21704;//请求调度的资源为非可调度资源
    int ERROR_21705 = 21705;//模式已更改, 操作被取消
    int ERROR_21706 = 21706;//该调度席正在被调度, 不可修改或删除
    int ERROR_21707 = 21707;//选看音频失败, 选看者不接收音频或者被选看者不发送音频码流
    int ERROR_21708 = 21708;//选看视频失败, 选看者不接收视频或者被选看者不发送视频码流
    int ERROR_21709 = 21709;//选看音视频失败, 选看者不接收音视频或者被选看者不发送音视频码流
    int ERROR_21710 = 21710;//未检测到可用的混音器资源, 请检查外设资源情况, 进入多方调度远端点无声音
    int ERROR_21711 = 21711;//本地终端掉线, 不允许进行任何调度操作, 且调度席恢复到最初状态
    int ERROR_21712 = 21712;//本地终端能力不足, 无法接收图像
    int ERROR_21713 = 21713;//当前调度终端能力不足, 无法接收图像
    int ERROR_21714 = 21714;//本地终端能力不足, 无法接收声音
    int ERROR_21715 = 21715;//当前调度终端能力不足, 无法接收声音
    int ERROR_21716 = 21716;//正在调度终端
    int ERROR_21717 = 21717;//调度终端超时
    int ERROR_21718 = 21718;//超过了可同时进行的组呼数
    int ERROR_21719 = 21719;//主席轮询已开启
    int ERROR_21720 = 21720;//当前调度席允许的临时呼叫数已满, 请结束调度清除
    int ERROR_21721 = 21721;//当前调度员不具备操作该调度席的权限
    int ERROR_21722 = 21722;//MCS和VCS不能同时登陆
    int ERROR_21723 = 21723;//超过MCU允许接入的VCS数量
    int ERROR_21724 = 21724;//VCS软件名修改失败
    int ERROR_21725 = 21725;//不可同时召开VCS和MCS会议
    int ERROR_21726 = 21726;//当前调度席已进入级联模式, 正在被上级调度, 本级无法调度
    int ERROR_21727 = 21727;//调度席正在调度中, 无法进入级联模式, 本级无法调度
    int ERROR_21728 = 21728;//未检测到可用的混音器资源, 请检查外设资源情况, 进入该模式均听发言人
    int ERROR_21729 = 21729;//指定终端忙, 在会议中,是否进行强拆
    int ERROR_21730 = 21730;//回传通道已被发言人占用,没有多余回传通道
    int ERROR_21731 = 21731;//要修改的预案名或者新的预案名已经存在
    int ERROR_21732 = 21732;//预案名长度不正确
    int ERROR_21733 = 21733;//预案名不存在
    int ERROR_21734 = 21734;//电视墙正在调度中, 请稍候
    int ERROR_21735 = 21735;//预案正在修改, 请稍候
    int ERROR_21736 = 21736;//预案数量已满, 无法添加
    int ERROR_21737 = 21737;//预案已被其它VCS锁定
    int ERROR_21738 = 21738;//该调度席正在被他人修改, 请稍后
    int ERROR_21739 = 21739;//该会议不支持多回传
    int ERROR_21740 = 21740;//会议回传带宽已满, 无法多回传
    int ERROR_21741 = 21741;//级联适配器不足, 多回传失败或监控失败
    int ERROR_21742 = 21742;//下级mcu不支持多级联版本, 本操作无效
    int ERROR_21743 = 21743;//回传带宽资源不足, 无法完全满足所有调度应用
    int ERROR_21744 = 21744;//下级会议回传带宽满,无法回传终端
    int ERROR_21745 = 21745;//有终端正在发双流, 不能设置第二广播源
    int ERROR_21746 = 21746;//有第二广播源, 不能发双流
    int ERROR_21748 = 21748;//升级程序错误, 您可以尝试以下操作:先升级到V4.7.1版本, 然后再升级到您需要的版本
    int ERROR_21749 = 21749;//升级程序错误, 文件校验失败
    int ERROR_21750 = 21750;//多回传编解码不足
    int ERROR_21751 = 21751;//端口会议资源不足, 可能造成终端无法正常进行视频交流
    int ERROR_22002 = 22002;//终端原会议不同意其退出原会议加入新会议
    int ERROR_22003 = 22003;//终端状态出错, 无法执行命令
    int ERROR_22004 = 22004;//指定终端不在此会议中
    int ERROR_22005 = 22005;//终端为收视终端, 不能发送媒体数据
    int ERROR_22006 = 22006;//终端为收视终端, 不能进行此项操作
    int ERROR_22007 = 22007;//硬件检测失败, 无法进行远端静音操作
    int ERROR_22008 = 22008;//设备不存在, 无法进行此项操作
    int ERROR_22009 = 22009;//终端不能离开原会议加入新会议
    int ERROR_22010 = 22010;//终端忙, 无法处理此命令
    int ERROR_22011 = 22011;//等待MCU应答超时
    int ERROR_22012 = 22012;//终端正在查询拓扑
    int ERROR_22013 = 22013;//终端所连接MCU不支持此项操作
    int ERROR_22014 = 22014;//MCU操作拒绝
    int ERROR_24002 = 24002;//没有记录
    int ERROR_24003 = 24003;//打开记录失败
    int ERROR_24004 = 24004;//不匹配的命令, 例如让一个播放设备开始录像
    int ERROR_24005 = 24005;//无效的通道
    int ERROR_24006 = 24006;//正在录像
    int ERROR_24007 = 24007;//正在播放
    int ERROR_24008 = 24008;//播放通道没在播放
    int ERROR_24009 = 24009;//录像通道没在录像
    int ERROR_24010 = 24010;//执行指定的请求动作失败
    int ERROR_24011 = 24011;//请求的动作当前正在执行
    int ERROR_24012 = 24012;//一条播放通道命令发到录像通道
    int ERROR_24013 = 24013;//一条录像通道命令发到播放通道
    int ERROR_24014 = 24014;//磁盘满
    int ERROR_24015 = 24015;//文件损坏
    int ERROR_24016 = 24016;//空文件
    int ERROR_24017 = 24017;//指定文件正在被操作
    int ERROR_24018 = 24018;//指定文件不存在
    int ERROR_24019 = 24019;//不支持发布或发布失败
    int ERROR_24020 = 24020;//文件名冲突, 更改文件名失败
    int ERROR_24021 = 24021;//文件已经存在, 录像失败
    int ERROR_24022 = 24022;//放像文件媒体类型与会议不匹配, 可能是其他会议的录像文件
    int ERROR_24023 = 24023;//调用录像库失败, 建议重新安装
    int ERROR_24024 = 24024;//录像方式设置失败
    int ERROR_24025 = 24025;//放像文件分辨率与会议不匹配, 可能是其他会议录像文件
    int ERROR_24026 = 24026;//文件名长度太长
    int ERROR_24027 = 24027;//录像名称不能使用下划线符号
    int ERROR_24028 = 24028;//录像机录像时写文件出错, 请检查文件设备是否工作正常
    int ERROR_24029 = 24029;//录像机放像, 发送模块创建socket失败
    int ERROR_24030 = 24030;//会议不支持放像文件的high profile属性
    int ERROR_24102 = 24102;//录像资源不足, 录像开启失败
    int ERROR_24103 = 24103;//录像文件名中存在非法字符：% & * ^ ~ ‘“ ? / \ < > ` ，请重新输入
    int ERROR_24104 = 24104;//录播服务器发生故障, 无法录放像
    int ERROR_24105 = 24105;//文件名已经存在, 请重新输入
    int ERROR_24106 = 24106;//您点播的文件不存在, 请重新选择
    int ERROR_24107 = 24107;//您点播的文件与会议不匹配, 请重新选择
    int ERROR_24122 = 24122;//当前录播服务器存储空间已满, 请联系管理员
    int ERROR_24205 = 24205;//直播资源不足(录像开启过程中, 开启直播时资源不足)
    int ERROR_24206 = 24206;//状态错误(录像过程中开关直播或者录像vrs状态错误)
    int ERROR_24502 = 24502;//指定的混音组不存在
    int ERROR_24503 = 24503;//指定的混音成员不存在
    int ERROR_24504 = 24504;//没有空闲的混音组
    int ERROR_24505 = 24505;//没有空闲的混音通道
    int ERROR_24506 = 24506;//消息中的消息体非法
    int ERROR_24507 = 24507;//调用底层函数失败
    int ERROR_24508 = 24508;//正在混音
    int ERROR_24509 = 24509;//未混音
    int ERROR_24510 = 24510;//无效的会议ID
    int ERROR_25001 = 25001;//内存访问出错
    int ERROR_25002 = 25002;//无效的指针
    int ERROR_25003 = 25003;//地址簿中不存在此条目
    int ERROR_25004 = 25004;//超出地址簿的最大容量
    int ERROR_25005 = 25005;//地址簿容量为0
    int ERROR_25006 = 25006;//当前地址簿中已经没有空闲位置了
    int ERROR_25007 = 25007;//文件访问出错
    int ERROR_25008 = 25008;//地址簿中已经存在了此条目
    int ERROR_25009 = 25009;//地址簿注册失败
    int ERROR_27501 = 27501;//域信息验证错误
    int ERROR_27502 = 27502;//E164号构造错误
    int ERROR_27503 = 27503;//数据库操作失败
    int ERROR_27504 = 27504;//数据解析失败
    int ERROR_27505 = 27505;//已达最大会议数
    int ERROR_27506 = 27506;//无创会所需资源
    int ERROR_27507 = 27507;//会议已开启
    int ERROR_27508 = 27508;//会议不存在
    int ERROR_28001 = 28001;//电视墙能力不足
    int ERROR_28002 = 28002;//电视墙已经被其他会议占用
    int ERROR_28003 = 28003;//电视墙应答超时
    int ERROR_28004 = 28004;//电视墙通道风格不支持
    int ERROR_28005 = 28005;//停止电视墙参数不正确
    int ERROR_28008 = 28008;//HduPool 异常掉线
    int ERROR_28009 = 28009;//非法的Hdu子通道ID
    int ERROR_28010 = 28010;//HduPool 初始化异常
    int ERROR_28011 = 28011;//非法的Hdu通道ID
    int ERROR_28501 = 28501;//媒体初始化错误
    int ERROR_28502 = 28502;//视频Bas编码通道数错误
    int ERROR_28503 = 28503;//添加句柄映射表错误
    int ERROR_28504 = 28504;//获取句柄映射表错误
    int ERROR_28505 = 28505;//保存句柄映射表错误
    int ERROR_28506 = 28506;//设置画面合成通用配置错误
    int ERROR_28507 = 28507;//设置画面合成解码通道别名错误
    int ERROR_28508 = 28508;//设置画面合成解码通道颜色错误
    int ERROR_28509 = 28509;//设置画面合成编码成员错误
    int ERROR_28510 = 28510;//设置画面合成解码成员错误
    int ERROR_28511 = 28511;//端口Vmp未开启
    int ERROR_28512 = 28512;//画面合成切换风格失败
    int ERROR_28513 = 28513;//请求关键帧错误
    int ERROR_28514 = 28514;//画面合成未申请资源
    int ERROR_28515 = 28515;//解绑资源失败
    int ERROR_28516 = 28516;//设置PayLoad错误
    int ERROR_28517 = 28517;//设置加密参数错误
    int ERROR_28518 = 28518;//通道不支持当前能力
    int ERROR_28519 = 28519;//申请端口失败
    int ERROR_28520 = 28520;//创建资源失败
    int ERROR_28521 = 28521;//设置接收参数失败
    int ERROR_28522 = 28522;//设置发送参数失败
    int ERROR_28523 = 28523;//创建接收对象失败
    int ERROR_28524 = 28524;//创建发送对象失败
    int ERROR_28525 = 28525;//编码Id出错
    int ERROR_28526 = 28526;//解码Id出错
    int ERROR_28527 = 28527;//编码通道Vicp能力不足
    int ERROR_28528 = 28528;//媒体模式错误
    int ERROR_28529 = 28529;//开启设备失败
    int ERROR_28530 = 28530;//绑定资源失败
    int ERROR_28531 = 28531;//媒体类型错误
    int ERROR_28532 = 28532;//获取Bas对象错误
    int ERROR_28533 = 28533;//开启端口Vmp失败
    int ERROR_28534 = 28534;//设置编码参数失败
    int ERROR_28535 = 28535;//消息处理超时
    int ERROR_28536 = 28536;//客户端Get数据出错
    int ERROR_28538 = 28538;//License数出错
    int ERROR_28539 = 28539;//开启MediaNet接收出错
    int ERROR_28540 = 28540;//外设板卡异常掉线
    int ERROR_28541 = 28541;//非法的HMediaRes
    int ERROR_28542 = 28542;//获取板卡失败
    int ERROR_28544 = 28544;//消息长度有误
    int ERROR_28545 = 28545;//资源的状态有误
    int ERROR_28546 = 28546;//混音通道数不合适
    int ERROR_28547 = 28547;//停止失败
    int ERROR_28548 = 28548;//获取编解码通道失败
    int ERROR_28549 = 28549;//添加通道失败
    int ERROR_28550 = 28550;//移除通道失败
    int ERROR_28551 = 28551;//销毁资源失败
    int ERROR_28552 = 28552;//资源不足
    int ERROR_28553 = 28553;//设备已经开启
    int ERROR_28554 = 28554;//设语音激励时间失败
    int ERROR_28555 = 28555;//资源申请参数错误
    int ERROR_28556 = 28556;//资源申请失败
    int ERROR_28557 = 28557;//客户端不存在
    int ERROR_28558 = 28558;//CEU要热拔, 不允许注册
    int ERROR_28621 = 28621;//MIX初始化业务对象失败
    int ERROR_28622 = 28622;//开启混音失败
    int ERROR_28624 = 28624;//MIX初始化业务对象失败
    int ERROR_28625 = 28625;//设置N模式发送信息失败
    int ERROR_28626 = 28626;//删除组的混音通道失败
    int ERROR_28627 = 28627;//停止混音失败
    int ERROR_28628 = 28628;//设置动态载荷失败
    int ERROR_28629 = 28629;//MIX设置解码参数失败
    int ERROR_28630 = 28630;//MIX设置编码参数失败
    int ERROR_28631 = 28631;//MIX设置接收解码失败
    int ERROR_28632 = 28632;//MIX设置强制混音失败
    int ERROR_28633 = 28633;//MIX取消所有强制混音失败
    int ERROR_28634 = 28634;//MIX设置语音激励延时保护失败
    int ERROR_28635 = 28635;//MIX获取指定通道解码参数失败
    int ERROR_28636 = 28636;//MIX获取启动/停止状态失败
    int ERROR_28637 = 28637;//MIX获取通道接收统计信息失败
    int ERROR_28638 = 28638;//MIX获取通道发送统计信息失败
    int ERROR_28639 = 28639;//MIX获取指定通道编码网络参数失败
    int ERROR_28640 = 28640;//MIX获取混音组状态信息参数失败
    int ERROR_28641 = 28641;//MIX获取混音组状态信息参数失败
    int ERROR_28642 = 28642;//Vmp句柄类型错误
    int ERROR_28643 = 28643;//Vmp媒体类型错误
    int ERROR_28644 = 28644;//创建Vmp资源失败
    int ERROR_28645 = 28645;//线程Id错误
    int ERROR_28646 = 28646;//释放Vmp资源失败
    int ERROR_28647 = 28647;//外设不支持超过64方混音成员
    int ERROR_28653 = 28653;//设置指定通道网络参数失败
    int ERROR_28654 = 28654;//设置指定通道编码参数失败
    int ERROR_28655 = 28655;//画面合成初始化失败
    int ERROR_28659 = 28659;//通过默认参数开启画面合成失败
    int ERROR_28661 = 28661;//清除通道参数失败
    int ERROR_28662 = 28662;//清除对应状态的通道静态图片资源失败
    int ERROR_28667 = 28667;//停止画面合成失败
    int ERROR_28668 = 28668;//设置编码发送的码率失败
    int ERROR_28670 = 28670;//设置所有图像的网络接收重传参数失败
    int ERROR_28671 = 28671;//设置所有图像的网络发送重传参数失败
    int ERROR_28675 = 28675;//设置对应通道别名失败
    int ERROR_28692 = 28692;//设置视频适配编码通道参数失败
    int ERROR_28693 = 28693;//设置视频适配编码通道网络地址失败
    int ERROR_28694 = 28694;//激活视频适配编码通道失败
    int ERROR_28695 = 28695;//设置视频适配编码加密参数失败
    int ERROR_28696 = 28696;//设置视频适配解码通道网络地址失败
    int ERROR_28697 = 28697;//设置视频适配接收解密KEY失败
    int ERROR_28698 = 28698;//激活视频适配解码通道失败
    int ERROR_28699 = 28699;//设置视频动态载荷失败
    int ERROR_28700 = 28700;//设置所有图像的网络接收重传参数失败
    int ERROR_28701 = 28701;//设置所有图像的网络发送重传参数失败
    int ERROR_28702 = 28702;//开始VBAS失败
    int ERROR_28703 = 28703;//停止VBAS失败
    int ERROR_28704 = 28704;//设置编码器编关键帧失败
    int ERROR_28705 = 28705;//获取通道接收统计信息失败
    int ERROR_28706 = 28706;//获取视频适配解码通道网络地址失败
    int ERROR_28707 = 28707;//获取编码通道激活状态失败或Vmp未开启
    int ERROR_28708 = 28708;//获取视频适配编码通道网络地址失败或Vmp切换风格失败
    int ERROR_28709 = 28709;//获取视频编码发送信息失败或Vmp未申请资源
    int ERROR_28710 = 28710;//画面合成退出失败
    int ERROR_28711 = 28711;//设置前景填充模式失败
    int ERROR_28731 = 28731;//音频适配初始化失败
    int ERROR_28732 = 28732;//开启音频适配失败
    int ERROR_28733 = 28733;//设置音频适配解码参数失败
    int ERROR_28734 = 28734;//通道加入适配器失败
    int ERROR_28735 = 28735;//设置音频适配编码通道参数失败
    int ERROR_28736 = 28736;//设置音频适配帧回调失败
    int ERROR_28737 = 28737;//传递接收的包给媒控失败
    int ERROR_28738 = 28738;//设置接收本地地址失败
    int ERROR_28739 = 28739;//设置音频码流解密失败
    int ERROR_28740 = 28740;//设置设置码流的动态载荷失败
    int ERROR_28741 = 28741;//重置帧ID失败
    int ERROR_28742 = 28742;//对音频设置多倍发送失败
    int ERROR_28743 = 28743;//设置音频码流加密失败
    int ERROR_28744 = 28744;//重置接收端重传处理的开关失败
    int ERROR_28745 = 28745;//创建接收模块失败
    int ERROR_28746 = 28746;//音频适配停止接收失败
    int ERROR_28747 = 28747;//音频适配开启接收失败
    int ERROR_28748 = 28748;//音频适配创建发送模块失败
    int ERROR_28749 = 28749;//设置网络发送参数失败
    int ERROR_28750 = 28750;//设置码流载荷值失败
    int ERROR_28751 = 28751;//移除接收地址参数失败
    int ERROR_28752 = 28752;//移除发送地址参数失败
    int ERROR_28753 = 28753;//设置编码通道工作状态失败
    int ERROR_28754 = 28754;//设置编码通道工作状态失败
    int ERROR_28902 = 28902;//添加视频Bas句柄映射表错误
    int ERROR_28903 = 28903;//获取视频Bas句柄映射表错误
    int ERROR_28904 = 28904;//保存视频Bas句柄映射表错误
    int ERROR_28905 = 28905;//视频Bas编码通道数错误
    int ERROR_29102 = 29102;//添加音频Bas句柄映射表错误
    int ERROR_29103 = 29103;//获取音频Bas句柄映射表错误
    int ERROR_29104 = 29104;//保存音频Bas句柄映射表错误
    int ERROR_29302 = 29302;//添加Mix句柄映射表错误
    int ERROR_29303 = 29303;//获取Mix句柄映射表错误
    int ERROR_29304 = 29304;//保存Mix句柄映射表错误
    int ERROR_52000 = 52000;//账号API返回结果为空
    int ERROR_52001 = 52001;//账号没有可分配的号码
    int ERROR_52002 = 52002;//账号不能是纯数字
    int ERROR_52003 = 52003;//用户账号已被使用
    int ERROR_52004 = 52004;//请求参数错误(enable为空)
    int ERROR_52005 = 52005;//账号姓名已被使用
    int ERROR_52006 = 52006;//账号邮箱已被使用
    int ERROR_52007 = 52007;//账号手机号已被使用
    int ERROR_52008 = 52008;//账号手机号与E164重复
    int ERROR_52009 = 52009;//该用户域不在服务域下
    int ERROR_52010 = 52010;//该服务域不存在
    int ERROR_52011 = 52011;//部门信息为空
}
