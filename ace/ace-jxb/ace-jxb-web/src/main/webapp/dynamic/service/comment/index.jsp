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
    <jsp:include page="/dynamic/common/base.jsp"/>
    <link rel="stylesheet" href="${portalPath}/content/common/assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/css/components.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/layouts/layout3/css/layout.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dynamic/service/studioAudit/css/style.css">
    <script src="${pageContext.request.contextPath}/dynamic/service/comment/js/act.js?v=${cfg.version}"></script>
    <script src="${pageContext.request.contextPath}/content/common/js/loader.js?v=${cfg.version}"></script>
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
                            				<span>评价管理</span>
                            		</li>
                            </ul>
                            <div class="page-content-inner">

                                <!---==============================================-->
                                <div class="row">
                                        <div class="col-md-12">
                                            <!-- BEGIN SAMPLE TABLE PORTLET-->
                                            <div class="portlet light ">
                                                <div class="portlet-title">
                                                    <div class="caption">
                                                       评价管理
                                                    </div>
																										<div class="actions">
																											<form onsubmit="return t_query()">


                                                        <div class="input-group">
                                                            <input type="text" name="keyword"
                                                                   class="form-control input-circle-left"
                                                                   placeholder="请输入昵称">
																																	 
                                                            <span class="input-group-btn">
                                                               <button class="btn btn-circle-right btn-default search_btn" type="submit">

                                                                        搜索
                                                                </button>
                                                            </span>
                                                        </div>
                                                    </form>
																										</div>
                                                </div>
                                                <div class="portlet-body">
                                                    <div class="table-scrollable">
                                                        <table class="table table-hover">
                                                            <thead>
                                                            <tr>
                                                               
                                                                <th width="10%"> 昵称 </th>
                                                                <th width="20%"> 课程名称 </th>
                                                                <th width="15%"> 好评度 </th>
                                                                <th width="30%"> 评价内容 </th>
                                                                <th width="15%"> 评价时间 </th>
                                                                <th width="10%"> 操作 </th>
                                                            </tr>
                                                            </thead>
                                                            <tbody id="cmtList">

                                                            </tbody>
                                                        </table>
																												
																												 <div class="paginationbar">
                                                                <ul class="pagination" id="pagination1" style="padding-left: 15px;"></ul>
                                                         </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- END SAMPLE TABLE PORTLET-->
                                        </div>
                                    
                                </div>
                                <script id="list" type="text/template">
                                    {@each data as item,index}
                                    <tr>
          
                                        <td style="text-align: center;"> 
																				<div class="header-box">
																				 <div class="header-img"><img src="\${item.headimgurl}" class="headimg"></div>
																				 <div class="header--nickname">\${item.nickname} </div>
																				 </div>
																				</td>
                                        <td> \${item.courseName} </td>
                                        <td>
                                            {@if item.grade == '1'}
                                            <img src="img/icon_comment.png" style="width:18px;height: 17px;"/>
                                            {@else if item.grade == '2'}
                                            <img src="img/icon_comment.png" style="width:18px;height: 17px;"/>
                                            <img src="img/icon_comment.png" style="width:18px;height: 17px;"/>
                                            {@else if item.grade == '3'}
                                            <img src="img/icon_comment.png" style="width:18px;height: 17px;"/>
                                            <img src="img/icon_comment.png" style="width:18px;height: 17px;"/>
                                            <img src="img/icon_comment.png" style="width:18px;height: 17px;"/>
                                            {@else if item.grade == '4'}
                                            <img src="img/icon_comment.png" style="width:18px;height: 17px;"/>
                                            <img src="img/icon_comment.png" style="width:18px;height: 17px;"/>
                                            <img src="img/icon_comment.png" style="width:18px;height: 17px;"/>
                                            <img src="img/icon_comment.png" style="width:18px;height: 17px;"/>
                                            {@else if item.grade == '5'}
                                            <img src="img/icon_comment.png" style="width:18px;height: 17px;"/>
                                            <img src="img/icon_comment.png" style="width:18px;height: 17px;"/>
                                            <img src="img/icon_comment.png" style="width:18px;height: 17px;"/>
                                            <img src="img/icon_comment.png" style="width:18px;height: 17px;"/>
                                            <img src="img/icon_comment.png" style="width:18px;height: 17px;"/>
                                            {@/if}
                                        </td>
                                        <td> \${item.content} </td>
                                        <td> \${item.createDate} </td>
                                        <td>
                                            <a href="javascript:void(0);" onclick="deleteCmt(this,'\${item.id}');">删除</a>
                                        </td>
                                    </tr>
                                    {@/each}
                                </script>
                                <!--=======================================-->

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="bottom"></div>

</div>

</body>

<style>
		.headimg{
			  width: 50px;
                height: 50px;
                object-fit: cover;
				border-radius: 50%;
		}
		.header-box{
			padding:2px;
			width: 100%;
			text-align: center
		}
		.header-img{
			padding:2px;
		}
		.header-nickname{
			padding:2px;
		}
</style>
</html>