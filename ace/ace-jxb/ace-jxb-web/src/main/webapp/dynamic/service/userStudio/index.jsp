<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<!--[if IE 8]>
<html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]>
<html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->

<head>
    <meta charset="utf-8"/>
    <title>${cfg.sys_name}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="description"/>

    <jsp:include page="../../common/base.jsp"/>
    <link rel="stylesheet" href="${portalPath}/content/common/assets/pages/css/profile.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/css/components.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/layouts/layout3/css/layout.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/simditor/styles/simditor.css">
    <link rel="stylesheet" href="${portalPath}/content/common/swiper-4.3.5/dist/css/swiper.css">
    <link rel="stylesheet"
          href="${portalPath}/content/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/content/service/information/css/style.css">--%>
    <script src="${pageContext.request.contextPath}/content/service/studio/js/act.js?v=${cfg.version}"></script>
    <script src="${pageContext.request.contextPath}/content/common/js/loader.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/swiper-4.3.5/dist/js/swiper.js?v=${cfg.version}"></script>
</head>

<body>
<div class="page-wrapper">
    <div class="page-wrapper-row full-height">
        <div class="page-wrapper-middle">
            <div class="page-container">
                <div class="page-content-wrapper">
                    <div class="page-content">
                        <div class="container">
                            <ul class="page-breadcrumb breadcrumb">
                                <li>
                                    <a href="${pageContext.request.contextPath}/index.jsp">首页</a>
                                    <i class="fa fa-circle"></i>
                                </li>
                                <li>
                                    <span>预约设置</span>
                                </li>
                            </ul>
                            <div class="page-content-inner">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="portlet light portlet-fit ">
                                            <div class="portlet-title">
                                                <div class="caption">

                                                    工作室
                                                </div>
                                                <div class="actions">
                                                    <a onclick="javascript:createStudio()" class="btn green">

                                                        创建工作室</a>
                                                </div>
                                            </div>
                                            <div class="portlet-body">
                                               


                                                        <div class="table-scrollable table-scrollable-borderless">
                                                            <table class="table table-hover table-light">
                                                                <thead>
                                                                <tr class="uppercase">
                                                                    <th>LOGO</th>
																																		<th> 名称</th>
                                                                    <th> 负责人</th>
                                                                    <th> 状态</th>
                                                                    <th> 操作</th>
                                                                </tr>
                                                                </thead>
                                                                <tbody id="studioList">

                                                                </tbody>
                                                            </table>
                                                        </div>


                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="bottom"></div>
</div>

<div class="modal fade bs-example-modal-lg" id="studioInfoModal" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="gridSystemModalLabel">工作室编辑</h4>
            </div>
            <div class="modal-body">
                <div class="form-panel">

                    <div class="form-horizontal" novalidate="novalidate">
                        <div class="form-body">

                            <div class="form-group ">
                                <label class="col-md-2 control-label">
                                    <span class="required" aria-required="true">*</span>工作室名字
                                </label>
                                <div class="col-md-10">
                                    <input type="text" id="notNull" maxlength="12" class="form-control" placeholder=""
                                           name="form_name">
                                    <span class="error_message"></span>
                                    <div class="form-control-focus"></div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                    <span class="required" aria-required="true">*</span>工作室标志
                                </label>
                                <div class="col-md-10">
                                    <div>
                                        <div class="studiologo" id="studiologo">
                                            <img class="select_img form_logoImgUrl" id="logo" data-cover="logo"
                                                 data-toggle="" data-againadd="false" data-xsize="300"
                                                 data-ysize="300" data-target=""
                                                 src="${pageContext.request.contextPath}/content/service/studio/img/addImg.png?v=${cfg.version}">
                                        </div>
                                        <p class="hide">工作室logo</p>
                                    </div>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-md-2 control-label">
                                    <span class="required" aria-required="true">*</span>封面轮播图
                                </label>
                                <div class="col-md-10 idCardBoxs">


                                    <div id="indexImg">
                                        <div class="idCardBox">
                                            <img class="select_img form_idCardImgUrl" id="uploadImgBtn"
                                                 data-cover="banner" data-xsize="240" data-ysize="150"
                                                 data-againadd="true"
                                                 data-target="#img-uploader" data-toggle="modal"
                                                 src="${pageContext.request.contextPath}/content/service/studio/img/addImg1.png?v=${cfg.version}">
                                        </div>
                                    </div>

                                </div>
                            </div>


                            <div class="form-group ">
                                <label class="col-md-2 control-label">
                                    <span class="required" aria-required="true">*</span>工作室简介
                                </label>
                                <div class="col-md-10">
                                            <textarea class="form-control" id="notNull1" name="form_introduce" rows="5">
                                            </textarea>
                                    <span class="error_message"></span>
                                    <div class="form-control-focus"></div>
                                </div>
                            </div>

                            <div class="form-group ">
                                <div class="col-md-10 col-md-offset-2">
                                    <input class="protocol" type="checkbox"/>
                                    <span></span>
                                    <span>同意
                                        <span class="primary-link"
                                              data-target="#modal-doc"
                                              data-toggle="modal">工作室服务协议</span>
                                    </span>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>

                <button type="button" class="btn btn-info submit_btn">确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<div class="modal" id="studioInfo" tabindex="-1" role="dialog"
     aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="">工作室详情</h4>
            </div>
            <div class="modal-body" id="modalstudioInfo">


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade bs-example-modal-lg" id="modal-doc" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content doc-content">
            <div class="content">

                <p style="line-height:15pt; margin:0pt 0pt 10pt; text-align:center"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:15pt">工作室服务协议</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">一、提示条款</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt; text-indent:22pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">在接受本协议之前，应当认真阅读本协议。</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">请发展</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">工作室的讲师会员、务必审慎阅读、充分理解各条款内容，特别是免除或限制责任的条款、法律适用、和争议解决条款。免除或限制责任的条款将以粗体下划线标识，应重点阅读。如工作室对协议有任何疑问，请不要进行后续操作。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">&#xa0;</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">二、协议内容</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（一）工作室在使用“心阳光联盟”平台提供的各项服务的同时，承诺接受并遵守各项相关规则的规定。“心阳光联盟”平台有权根据需要不时地制定、修改本协议和</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">/</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">或各类规则，如有任何变更，“心阳光联盟”平台将在微网站、</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">pc</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">等以公示的方式进行公告，不再单独通知每</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">个工作室。变更后的协议和规则一经公布即自动生效，成为本协议的一部分。如工作室不同意相关变更，应当立即以书面通知的方式终止本协议；任何修订和新规则一经在网站上公布即自动生效，成为本协议的一部分。如工作室登录或继续使用服务的，则视为对修改后的协议和规则不持异议并同意遵守。除另行明确声明外，任何使服务范围扩大或功能增强的新内容均受本协议约束。</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt"> </span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（二）“心阳光联盟”平台保留在任何时候自行决定对服务及其相关功能、应用软件变更、升级的权利。“心阳光联盟”平台进一步保留在服务中开发新的模块、功能和软件或其它语种服务的权</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">利。上述所有新的模块、功能、软件服务的提供，除非“心阳光联盟”平台另有说明，否则仍适用本协议。服务随时可能因“心阳光联盟”平台的单方判断而被增加或修改，或因定期、不定期的维护而暂缓提供，工作室将会得到相应的变更通知。工作室在此同意“心阳光联盟”平台在任何情况下都无需向其或第三方在使用服务时对其在传输或联络中的迟延、不准确、错误或疏漏及因此而致使的损害负责。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">三、使用规则</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（一）合法性</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">1</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、“心阳光联盟”讲师成员可使用“心阳光联盟”平台提供的专属的二</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">维码发展</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">其他讲师，建立自己的工作室。工作室在使用“心阳光联盟</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">”平台服务及产品时，务必遵守中华人民共和国相关法律法规的规定，不得把“心阳光联盟”平台提供的服务及产品用于任何违法或不正当或不道德的活动，否则“心阳光联盟”平台可采取注销工作室并拒绝提供服务措施包括但不限于以下行为：</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">1</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）违反宪法或法律法规规定的；危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；损害国家荣誉和利益的，损害公共利益的；煽动民族仇恨、民族歧视，破坏民族团结的；破坏国家宗教政策，宣扬邪教和封建迷信的；散布谣言，扰乱社会秩序，破坏社会稳定的；散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">犯罪的；侮辱或者诽谤他人，侵害他人合法权益的；含有法律、行政法规禁止的其他内容的；</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">2</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）以任何非法目的而使用网络服务系统；</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">3</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）故意制作、传播计算机病毒等破坏性程序；</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">4</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）其他危害计算机信息网络安全的行为。</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">2</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、工作室设置的网站会员名不得违反国家法律法规及本网站相关规则，否则“心阳光联盟”平台有权关闭工作室。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">3</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、“心阳光联盟”平台通过合法途径从商业伙伴处取得的工作室数据。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt; text-indent:21.75pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">&#xa0;</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">四、工作室发布的信息</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（一）工作室声明并保证，工作室对自己所发表的信息、评论等拥有相应、合法的权利。否则，“心阳光联盟”平台有</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">权即时对工作室发布的信息进行删除、屏蔽处理。</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt"> </span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（二）工作室应当确保您所发布的信息不包含以下内容：</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">1</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、违反国家法律法规禁止性规定的；</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">2</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、政治宣传、封建迷信、淫秽、色情、赌博、暴力、恐怖或者教唆犯罪的；</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">3</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、欺诈、虚假、不准确或存在误导性的；</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">4</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、侵犯他人知识产权或涉及第三</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">方商业</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">秘密及其他专有权利的；</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">5</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、侮辱、诽谤、恐吓、涉及他人隐私等侵害他人合法权益的；</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">6</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、存在可能破坏、篡改、删除、影响本网站任何系统正常运行或未经授权秘密获取本网站及其他工作室的数据、个人资料的病毒、木马、爬虫等恶意软件、程序代码的；</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">7</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、其他违背社会公共利益或公共道德或依据本网站的规定不适合在网站上发布的。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">&#xa0;</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">五、费用</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（一）收入标准</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">1</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、工作室负责人有权获得在“心阳光联盟”平台产生的利益，讲师工作室的收益依照上一季度发展讲师数量及工作室的总营业额为基础进行分成，规则如下：</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">以一季度为期限，工作室负责人的分成模式：</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">1</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）工作室负责人招募发展讲师数达到</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">50</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">名，工作室共达到</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">10</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">万营业额，即</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">可获得产品收益的</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">60%</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">。</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">2</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）工作室负责人招募发展讲师数达到</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">100</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">名，工作室共达到</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">30</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">万营业额，即可获得产品收益的</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">70%</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">。</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">3</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）工作室负责人招募发展讲师数达到</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">150</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">名，工作室共达到</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">60</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">万营业额，即可获得产品收益的</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">80%</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">。</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">4</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）发展会员数额可累计，营业额每季度清空，不累加。</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">5</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）讲师直播获得的打赏收入，全部归由讲师，平台不收取服务费。</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">2</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、如“心阳光联盟”平台在某个时期有优惠政策，则收费标准按当时的优惠政策执行。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（二）付费方式</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt; text-indent:22pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">依据以上提到的分成情况，以及讲师自己的产出（如视频、音频，直播打赏）情况</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">。工作室讲师的收入按月向平台方进行申请结算。平台方在收到申请后，将在月初</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">1</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">日——</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">10</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">日依照申请顺序依次将收益发放至讲师填写的银行账号。</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt"> </span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">六、隐私保护</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（一）请工作室妥善</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">保管您</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">的账户及密码信息。</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">若工</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">作</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">室发</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">现账户被他人非法使用，应立即通知“心阳光联盟”平台。因不可抗力（包括黑客行为、计算机病毒、系统不稳定等）、遭受他人诈骗或工作室主动泄露、保管疏忽导致账号、密码被他人非法使用的，“心阳光联盟”平台不承担任何责任。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（二）“心阳光联盟”平台将采取合理的安全手段保护工作室已存储的个人信息，除下述信息披露条款外，在</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">未得到工作室许可之前，“心阳光联盟”平台不会将工作室的任何个人信息提供给任何无关的第三方（包括公司或个人）。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">1</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、经工作室事先同意，向第三方披露；</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">2</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、只有通过使用工作室的保密信息，才能向工作室提供所要求的产品服务；</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">3</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、根据法律的有关规定，或者行政或司法机构的要求，向第三方或者行政、司法机构披露；</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">4</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、如工作室出现违反中国有关法律、法规或者本网站法律声明、服务协议或相关规则的情况，需要向第三方披露；</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">5</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、维护本网站的合法权益；</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">6</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、根据本协议相关规定或者本网站认为必要的其他情况。</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（三）“心阳光联盟”平台亦不允许任何第三方以任何手段收集、编辑、出售或无偿传播工作室的个人信息。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">七、权利声明</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（一）“心阳光联盟”平台所属公司拥有本网站内的所有产品、技术、程序、资料、信息内容（包括但不限于文字、图片、图像、照片、音频、视频、图表、色彩、版面设计、电子文档）的所有权利（包括但不限于版权、商标权、专利权、商业秘密及其他所有相关权利）。未经权利人许可，任何人不得擅自使用。</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt"> </span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（二）工作室在接受本协议时即视为主动将其在本网站发表的任何形式的信息的著作权，包括但不限于复制权、发行权、出租权、展览权、表演权、放映权、广播权、信息网络传播权、摄制权、改编权、翻译权、汇编权以及《著作权法》规定的由著作权人享有的其他著作财产权利无偿独家转让给“心阳光联盟”平台所有，若发生第三人侵权，“心阳光联盟”平台有权单独提起诉讼并获得全部赔偿。</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt"> </span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（三）工作室同意“心阳光联盟”平台网站后服务市场的全部运营数据，包括但不限于工作室信息、工作室列表、工作室关系、工作室的使用数据、交易数据、订单数据等，其全</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">部的权利均归属于“心阳光联盟”平台。未经“心阳光联盟”平台事先书面同意，均不得利用工作室列表及工作室关系、向工作室发送信息，不得为任何目的擅自保存、备份、泄露、使用或授权他人使用前述运营数据。本协议自然终止或提前解除后，工作室无权要求备份前述运营数据</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt"> </span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（四）工作室通过注册所得的本网站账户（即本网站工作室</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">ID</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）所有权归属于本网站公司，工作室仅对该账户拥有使用权。工作室不能以任何方式转让其账户的使用权，如工作室违反上述规定，“心阳光联盟”平台有权追究的违约责任，由此产生的一切责任由其自行承担。</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt"> </span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（五）对不遵</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">守本协议或其他违法、未经许可或者恶意使用本网站内容者，本网站所属公司保留追究其法律责任的权利。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">八、第三方主张权利</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">若工</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">作室认为本网站的内容，或通过本网站得到的内容可能涉及侵犯其合法权益，应该及时以书面形式向“心阳光联盟”平台反馈，并提供身份证明、权属证明、具体链接（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">URL</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）及详细侵权情况证明，“心阳光联盟”平台在收到上述法律文件并经审查同意后，将会在合理时间内依法尽快移除、删除相关涉嫌侵权的内容或断开网络连接。</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">如权利</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">通知的陈述失实，权利通知提交者将承担由此造成的全部法律责任。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">九、反贿赂</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（一）“心阳光联</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">盟”平台坚持认真贯彻国家关于反商业贿赂的各项规定，努力建立健全反对不正当交易行为和反商业贿赂的长效机制。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（二）“心阳光联盟”平台坚持严厉打击商业贿赂行为，对违反本声明相关规定的员工，将严肃查处和教育，情节严重的，及时报告相关机构；对违反本声明相关规定的工作室或合作伙伴，一经查实，“心阳光联盟”平台有权终止对工作室提供服务，并保留追究相关法律责任的权利。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（三）“心阳光联盟”平台愿意在公开透明、公正平等、诚实信用的合作机制下，与所有工作室及合作伙伴们“共赢”美好明天。真诚希望工作室及合作伙伴们能理解并支持本</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">“心阳光联盟”平台的反商业贿赂政策，并欢迎对“心阳光联盟”平台及员工遵守本声明政策的情况进行监督。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">十、保密</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（一）工作室保证在使用本网站过程中所获悉的属于本网站及他方的且无法自公开渠道获得的商业秘密、专有信息、双方共有的客户信息及其他保密性质的所有信息均为保密信息（包括但不限于商业秘密、公司计划、运营活动、财务信息、技术信息、经营信息及其他商业秘密）。未经该信息的原享有方同意，不应以任何形式向第三方披露或泄露保密信息，否则，应承担赔偿损失责任。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（二）不论</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">本服务</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">协议是否变更、解除或终止，本条款均由法律效力</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">。</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">十一、免责条款</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（一）“心阳光联盟”平台不保证网络服务的及时性、安全性、准确性。不论在何种情况下，均不对由于</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">Internet</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">连接故障，电脑，通讯或其他系统的故障，电力故障，罢工，劳动争议，暴乱，起义，骚乱，生产力或生产资料不足，火灾，洪水，风暴，爆炸，不可抗力，战争，政府行为，国际、国内法院的命令或第三方的不作为而造成的不能服务或延迟服务承担责任。请工作室务必及时保存自己的需求，否则因网络服务中断，需要维修、检修、维护等导致损失的，“心阳光联盟”平台不承担损失赔偿的责任。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（二）在任何情况下，对于因使用</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">“心阳光联盟”平台网站内容或无法进入本网站而导致的任何直接的、间接的、附带的、给第三人造成的损失（包括但不限于利润损失、信息数据丢失、财产毁坏等损失），“心阳光联盟”平台均无须承担法律责任，不论是采用合同之诉、侵权之诉或其他诉讼理由。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（三）未经授权的存取或修改数或数据的传输，</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">“心阳光联盟”平台“心阳光联盟”平台</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">不承担赔偿因此造成损失的责任。</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（四）第三方通过服务所作的陈述或行为，“心阳光联盟”平台不承担赔偿因此造成损失的责任。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（五）其他与服务相关事件，包括疏忽等所造成的损害，“心阳光联盟”平台不承担赔偿</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">因此造成损失的责任。</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">十二、协议终止</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（一）终止的情形</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">1</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、【工作室发起的终止】工作室有权通过以下任</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">一</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">方式终止本协议：</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">1</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）变更事项生效前工作室停止使用并明示不愿接受变更事项的</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">2</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）工作室明示不愿继续使用“心阳光联盟”平台提供的服务，且符合本网站终止条件的。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">2</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、【“心阳光联盟”平台发起的终止】出现以下情况时，“心阳光联盟”平台有权无须通知工作室即终止本协议：</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">1</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）违反本协议约定，“心阳光联盟”平台依据违约条款终止本协议的；</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">2</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）盗用他人账户、发布违禁信息、骗取他人财物、扰乱网站秩序、采取不正当手段</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">谋利等行为，“心阳光联盟”平台对工作室的账户予以查封的；</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">3</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）除上述情形外，</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">若工</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">作室多次违反“心阳光联盟”平台相关规定且情节严重，“心阳光联盟”平台有权对其的账户予以查封的；</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">4</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）工作室的账户被“心阳光联盟”平台依据本协议取消的；</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">5</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）有欺诈、发布或侵犯他人合法权益或其他严重违法违约行为的；</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">6</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）其它应当终止服务或“心阳光联盟”平台拒绝提供服务的情况。</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（二）协议终止后的处理</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">1</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、【工作室信息披露】本协议终止后，除法律有明确规定外，“心阳光联盟”平台无义务向工作室或工作室指定的第三方披露工作室账户中的任何信息。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">2</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">、【本网站权利】本协议终止后，“心阳光联盟”平台仍享有下列权利：</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">1</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）继续保存工作室留存于本网站的本协议的各类信息；</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">2</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">）对于工作室过往的违约行为，本网站仍可依据本协议追究其违约责任。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">十三、违约责任</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（一）除本协议另有约定之外，如一方发生违约行为，守约方可以书面通知方式要求违约方在指定的时限内停止违约行为，并就违约行为造成的损失（包括但不限于诉讼费、仲裁费、差旅费</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">等）进行索赔，如违约方未能按时停止违约行为，则守约方有权立即终止本协议。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（二）</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">”</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">心阳光联盟”平台因违约或侵权依法对工作室赔偿损失的，赔偿额以实际最终收取工作室款项总额为限。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">十四、有效通知</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt; text-indent:22pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">本协议下所规定的通知有权要求以书面形式通过以下邮址递交收悉，通知的到达以</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">”</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">心阳光联盟”平台收悉为准。</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">“心阳光联盟”平台地址：湖南省常德市武陵区沙港东路荷花社区</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">3</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">楼</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">邮编：</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">415100 </span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">；</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt"> </span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">收件人：”心阳光联盟</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">”</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">平台心理咨询组</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt"> </span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（收）</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">“心阳光联盟”平台可自</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">jinxinbang123@163.com</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">邮址向工作室在</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">”</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">心阳光联盟”平台网注册时提供的电子邮件地址发出通知。通知的送达以邮件发出为准。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">&#xa0;</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">二十一、争议及其他</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（一）</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt"> </span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">本协议之解释与适用，以及与本协议有关的争议，均应依照中华人民共和国法律予以处理，并以常德市人民法院为管辖法院。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">（二）</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt"> </span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">如本协议的任何条款被视作无效或无法执行，则上述条款可被分离，其余部分则仍具有法律效力。</span>
                </p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">本协议于</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">2018</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">年</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">8</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">月</span><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">01</span><span
                        style="color:#0d0d0d; font-family:微软雅黑; font-size:11pt">日发布并执行。</span></p>
                <p style="line-height:11pt; margin:0pt 0pt 10pt"><span
                        style="color:#0d0d0d; font-family:Tahoma; font-size:11pt">&#xa0;</span></p>

            </div>
        </div>
    </div>
</div>


</body>

<style>
    .form-panel .form-body {
        text-align: left;
    }

    .swiper-container img {
        width: 100%;
    }

    .swiper-container {
        width: 500px;
        height: 320px;
    }

    .info_cotent {
        width: 500px;
        height: 700px;
        overflow-y: auto;
        margin: auto;
    }

    .info_cotent .info_text {
        position: relative;
    }

    .info_cotent .info_text .top {
        position: absolute;
        width: 500px;
        height: 106px;
        top: -53px;
    }

    .info_cotent .info_text .bottom1 .doc {
        width: 100%;
        overflow: hidden;
        word-wrap: break-word;
    }

    .info_cotent .info_text .bottom1 h4 {
        text-align: center;
        color: rgba(28, 40, 67, 1);
        font-size: 15px;
        font-weight: bold;
    }

    .info_cotent .info_text .bottom1 {
        padding-top: 78px;
    }

    .info_cotent .info_text .top .box .left_div img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .info_cotent .info_text .top .box .right_div {
        float: right;
        width: 320px;
        display: table;
        height: 100%;
        margin-right: 20px;
    }

    .info_cotent .info_text .top .box .right_div span {
        display: table-cell;
        vertical-align: middle;
        font-size: 16px;
        word-wrap: break-word;
        color: rgba(28, 40, 67, 1);
        font-weight: bold;
        line-height: 24px;
    }

    .info_cotent .info_text .top .box .left_div {
        width: 68px;
        height: 68px;
        border-radius: 50% !important;
        overflow: hidden;
        float: left;
        margin: 20px 0 0 20px;
    }

    .info_cotent .info_text .top .box {
        position: relative;
        width: 460px;
        margin: auto;
        height: 100%;
        background-color: #fff;
        z-index: 99;
        border-radius: 10px !important;
        box-shadow: 0px 6px 20px 0px #0000002e;
    }


    .error_message {
        color: red;
    }

    .studiologo {
        width: 100px;
        height: 100px;
        overflow: hidden;
        background-color: #BDE1FF;
    }

    .studiologo img {
        text-align: center;
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .studiologo + p {
        line-height: 0.05rem;
    }

    .input_style {
        border-bottom: 1px solid #c2cad8 !important;
    }

    .portlet-body {
        background-color: #fff !important;
        padding: 41px 98px 45px 20px;
    }

    .idCardBoxs > div, .certificateBoxs > div {
        width: 192px;
        float: left;
        margin-right: 30px;
        margin-bottom: 15px;
    }

    .idCardBoxs > div > p, .certificateBoxs > div > p {
        text-align: center;
        line-height: 0.05rem;
    }

    .idCardBox {
        position: relative;
        height: 128px;
        overflow: hidden;
        background-color: #BDE1FF;
    }

    .idCardBox .deleteBtn {
        position: absolute;
        right: 0;
        top: 0;
        width: 30px;
        height: 30px;
        background-color: #ff000088;
        border-radius: 50% !important;
        line-height: 30px;
        text-align: center;
        color: #fff;
        cursor: pointer;
    }

    .idCardBox img {
        text-align: center;
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .modal-lg {
        width: 950px !important;
    }

    .cover {
        width: 70px;
        height: 70px;
        object-fit: cover;
    }

    .doc-content {
        height: 700px;
        padding: 50px;
    }

    .doc-content .content {
        height: 600px;
        overflow: auto;
    }

    .doc-content .content span {
        line-height: 28px;
    }

</style>


<script id="temp_studioList1" type="text/template">
    <tr>
        <td>
            <img class="cover" src="\${data.logoImgUrl?data.logoImgUrl:'headImg.png'}">
        </td>
        <td>
            \${data.name}
        </td>
        <td> \${data.dutyName}</td>
        <td> \${parseStatus(data.status)}</td>

        <td>
            <a onclick="javascript:detail('\${data.id}')">查看</a>
        </td>
    </tr>
</script>

<script id="temp_studioList" type="text/template">
    {@each data as item}
    <tr>
        <td>
            <img class="cover" src="\${item.logoImgUrl?item.logoImgUrl:'headImg.png'}">
        </td>
        <td>
            <a onclick="javascript:userStudioStaff('\${item.id}')" class="primary-link">\${item.name}</a>
        </td>
        <td> 我创建的</td>
        <td>\${parseStatus(item.status)}</td>
        <td>
            <a href="#" onclick="javascript:modify('\${item.id}')">修改</a>
            <a href="#" onclick="javascript:detail('\${item.id}')">查看</a>
        </td>
    </tr>
    {@/each}
</script>

<script id="temp_modalstudioInfo" type="text/template">
    <div class="info_cotent">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                {@each data.imgList as item}
                <div class="swiper-slide"><img src="\${item.imgUrl}" alt=""></div>
                {@/each}
            </div>
            <!-- Add Arrows -->
            <div class="swiper-button-next"></div>
            <div class="swiper-button-prev"></div>
        </div>
        <div class="info_text">
            <div class="top">
                <div class="box">
                    <div class="left_div">
                        <img src="\${data.logoImgUrl}" alt="">
                    </div>
                    <div class="right_div">
                        <span>\${data.name}</span>
                    </div>

                </div>
            </div>
            <div class="bottom1">
                <h4>工作室简介</h4>
                <div class="doc">
                    \$\${data.introduce}
                </div>
            </div>
        </div>
    </div>
</script>

</html>
