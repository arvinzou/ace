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
    <jsp:include page="/dynamic/common/header.jsp"/>


    <link rel="stylesheet" href="css/style.css">


</head>
<body>

<!--隐藏存放ID-->
<input type="text" hidden value="" id="auditId"/>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />


                                        <div class="portlet light ">

                                            <div class="portlet-body">



                                                <div class="row custom-toolbar">
                                                    <div class="col-md-2">

                                                    </div>

                                                    <div class="col-md-10">

                                                        <form onsubmit="return t_query()">
                                                            <div class="btn-group" role="group"  style="float:left;padding-right:5px">
                                                                <button type="button" class="btn btn-default"  onclick="changeConsultState('');">全部</button>
                                                                <button type="button" class="btn btn-default"  onclick="changeConsultState('1');">上架</button>
                                                                <button type="button" class="btn btn-default" onclick="changeConsultState('0');">下架</button>
                                                            </div>
                                                            <div class="btn-group" role="group"  style="float:left;padding-right:5px">
                                                                <button type="button" class="btn btn-default"  onclick="changeType('');">全部</button>
                                                                <button type="button" class="btn btn-default"  onclick="changeType('0');">待审</button>
                                                                <button type="button" class="btn btn-default" onclick="changeType('1');">通过</button>
                                                                <button type="button" class="btn btn-default" onclick="changeType('2');">驳回</button>
                                                            </div>

                                                            <div class="btn-group" role="group"  style="float:left;padding-right:5px">
                                                                <button type="button" class="btn btn-default"  onclick="setParams('fine','');">全部</button>
                                                                <button type="button" class="btn btn-default"  onclick="setParams('fine','1');">精品</button>
                                                                <button type="button" class="btn btn-default" onclick="setParams('fine','0');">普通</button>
                                                            </div>

                                                            <div class="btn-group" role="group"  style="float:left;padding-right:5px">
                                                                <button type="button" class="btn btn-default"  onclick="changeCourseType('');">全部</button>
                                                                <button type="button" class="btn btn-default"  onclick="changeCourseType('1');">单节</button>
                                                                <button type="button" class="btn btn-default" onclick="changeCourseType('2');">系列</button>
                                                            </div>
                                                            <div class="input-group">
                                                                <input type="text"
                                                                       name="keyword"
                                                                       class="form-control"
                                                                       placeholder="请输入课程名称">
                                                                <span class="input-group-btn">
                                                                <button class="btn  btn-default search_btn"
                                                                        type="submit">
                                                                        搜索
                                                                </button>
                                                            </span>
                                                            </div>
                                                        </form>
                                                    </div>

                                                </div>



                                                <div class="table-scrollable">
                                                    <table class="table table-hover">
                                                        <thead>
                                                        <tr>

                                                            <th width="30%"> 课程名称 </th>
                                                            <th width="10%">讲师</th>
                                                            <th width="15%">上/下架时间&状态</th>
                                                            <th width="10%">购买数 </th>
                                                            <th width="15%">审核状态</th>
                                                            <th width="20%">操作</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody id="courseList">

                                                        </tbody>
                                                    </table>
                                                </div>


                                                <div class="paginationbar">
                                                    <ul class="pagination" id="pagination1"></ul>
                                                </div>
                                            </div>
                                        </div>



<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

<script id="list" type="text/template">
    {@each data as item, index}
    <tr>

        <td >
            <div class="row">
                <div class="col-md-2"><img src="\${item.cover}" class="cover"/></div>
                <div class="col-md-10">
                    <div class="describtion">
                        \${item.name}

                    </div>
                    <div class="cost">\${item.srcCount}节
                        {@if item.costType != '0'}
                        ￥\${item.cost}
                        {@else}
                        免费
                        {@/if}
                        {@if item.fine==1}<span class="label label-lg label-danger">精品</span>{@/if}
                    </div>
                </div>
            </div>
        </td>
        <td > \${item.counselorName} </td>
        <td  >
            {@if item.auditRst==1}
            <div>
                {@if item.lineState == 1}
                \${item.onlineDate}
                {@else}
                \${item.offlineDate}
                {@/if}
            </div>
            <div style="padding:10px">
                {@if item.lineState!=1}
                <span style="color:red">已下架</span>
            {@else}
                <a href="#">已上架</a>
            {@/if}
            </div>
            {@/if}
        </td>
        <td  >\${item.duration}</td>
        <td >
            {@if item.auditRst==0}
            <span class="label label-lg label-info">待审核</span>
            {@else if item.auditRst==1}
            <span class="label label-lg label-success">审核通过</span>
            <div style="padding-top:10px">\${item.auditRemark}</div>
            {@else}
            <span class="label label-lg label-danger">审核不通过</span>
            <div style="padding-top:10px">\${item.auditRemark}</div>
            {@/if}
        </td>
        <td >
            <a class="operation" href="javascript:void(0);"  onclick="edit('\${item.id}');">编辑</a>
            <a class="operation" href="list/index.jsp?id=${param.id}&did=\${item.id}">详细</a>
            {@if item.auditRst==1}
                {@if item.lineState==0}
                <a class="operation" href="javascript:void(0);"  onclick="online('\${item.id}');">上架</a>
                {@else}
                <a class="operation" href="javascript:void(0);"  onclick="outline('\${item.id}');">下架</a>
                {@/if}
            {@/if}
            {@if item.auditRst==0}
            <a class="operation" href="javascript:void(0);" id="auditOpt\${index}"  onclick="openAudit('\${item.id}','\${index}');">审核</a>
            {@/if}

            {@if item.auditRst==1}
                <a class="operation" href="#" data-toggle="modal" data-id="\${item.id}" data-title="\${item.name}" data-target="#modal-status">设置精品</a>
            {@/if}


        </td>
    </tr>
    {@/each}
</script>
</body>

<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="editCourse"
     aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div id="courseBasic"></div>
        </div>
    </div>
</div>


<!--审核弹框-->
<div class="modal fade"  id="audit">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="gridSystemModalLabel3">课程审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-audit" role="form">
                      <div class="form-body">
                        <div class="form-group " id="operation">
                            <label class="col-md-2 control-label">审核结果</label>
                            <div class="col-md-10">
                                <div class="radio-group-container">
                                    <label>
                                        <input type="radio" name="auditState" value="1"><span style="padding:10px">通过</span>
                                    </label>
                                    <label>
                                        <input type="radio" name="auditState" value="2"><span style="padding:10px">退回</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">审核说明</label>
                            <div class="col-md-10">
                                <textarea name="message" style="width: 100%;height: 100px;"></textarea>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="audit();">确定</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade bs-example-modal-lg" id="modal-status">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">设置状态</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="fm-status" role="form">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-2 view-label">对象</label>
                            <div class="col-md-10 status-title">

                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">状态</label>
                            <div class="col-md-10">
                                <div class="radio-group-container">
                                    <input type="hidden" name="id">
                                    <label>
                                        <input type="radio" name="fine" value="0"><span style="padding:10px">普通</span>
                                    </label>
                                    <label>
                                        <input type="radio" name="fine" value="1"><span style="padding:10px">精品</span>
                                    </label>

                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn green status">确定</button>
            </div>
        </div>
    </div>
</div>

<style>
    .cover{
        width: 70px;
        height: 70px;
        object-fit: cover;
    }
    .describtion{
        padding-left:15px;
        height:50px;
    }
    .cost{
          padding-top: 5px;
          padding-left:15px;
          color:#FE6500;
    }
</style>
<jsp:include page="/dynamic/common/footer.jsp" />
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>