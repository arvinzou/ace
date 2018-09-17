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
        <meta charset="utf-8" />
        <title>${cfg.sys_name}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="${cfg.sys_name}" name="description" />
        <jsp:include page="../../../common/header.jsp" />



    </head>

    <body>
    <jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />



                                                <div class="portlet light">

                                                    <div class="portlet-body">
                                                        <!--具体界面元素开始-->

                                                        <div class="row custom-toolbar">
                                                            <div class="col-md-4">
                                                                <a href="javascript:void(0);" style="float:left;margin-right:20px" class="btn green" data-target="#myModal" data-toggle="modal">创建章节</a>
                                                                <a href="#" class="btn green" style="float:left;margin-right:20px" data-target="#chapterBox" data-toggle="modal" onclick="initPartEditList();">章节修改</a>
                                                                <a href="javascript:void(0);" style="float:left;margin-right:20px" class="btn green" onclick="add();">创建课件</a>

                                                            </div>
                                                            <div class="col-md-8" id="chapters">

                                                            </div>

                                                        </div>
                                                        <div class="table-scrollable">

                                                            <table class="table table-hover">
                                                                <thead>
                                                                    <tr>
																																			
                                                                        <th width="5%">
                                                                            ＃
                                                                        </th>
																																				<th width="20%">
																																					课程名称
																																				</th>
                                                                        <th width="15%">
                                                                           课程
                                                                        </th>
                                                                        
                                                                        <th width="15%">
                                                                           创建时间
                                                                        </th>
                                                                        <th width="15%">
                                                                            时长
                                                                        </th>
                                                                        <th width="10%">
                                                                            是否可试听
                                                                        </th>
                                                                        <th width="20%">
                                                                            操作
                                                                        </th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody id="courseList">

                                                                </tbody>
                                                            </table>
                                                            <div class="paginationbar">
                                                                <ul class="pagination" id="pagination1" style="padding-left: 15px;"></ul>
                                                            </div>
                                                        </div>

                                                        <!--具体界面元素结束-->
                                                    </div>
                                                </div>
                                                <jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
    </body>

    <div class="modal fade" tabindex="-1" role="dialog" id="myModal" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="gridSystemModalLabel">章节创建</h4>
                </div>
				<div class="modal-body form">
				
                <form class="form-horizontal form-panel" id="fm-group" role="form">
                      <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">章节名称</label>
                            <div class="col-md-9">
                                <input name="partName" class="form-control" type="text" maxlength="50" placeholder="请输入课程章节名称" />
                            </div>
                        </div>
                    </div>
                </form>
				
            </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn green" onclick="addChapter();">确定</button>
                </div>
            </div>
        </div>
    </div>



    <script id="chapterTemp" type="text/template">
        <div class="btn-group" role="group"  style="float:left;padding-right:5px">
        {@each data as item,index}
            {@if index == 0}
            <button type="button" class="btn btn-default active" onclick="changeChapter('\${item.id}');">第\${parseInt(index)+1}章 \${item.name}</button>
            {@else}
            <button type="button" class="btn btn-default" onclick="changeChapter('\${item.id}');">第\${parseInt(index)+1}章 \${item.name}</button>
        {@/if}
            {@/each}
        </div>
    </script>

    <script id="courseTemp" type="text/template">
        {@each data as item, index}
        <tr>
            <td width="5%" >
               \${parseInt(index)+1}
            </td>
           
            <td width="20%" >
                <span>\${item.name}</span>

            </td>
						<td width="15%">
								<audio src="\${item.mediUrl}" controls ></audio>
						</td>
            <td width="15%" >
                \${item.createDate}
            </td>
            <td width="15%" >
                \${item.duration}秒
            </td>
            <td width="10%" >
                {@if item.free == '1'}
								<span class="label label-lg label-info">是</span>
                {@else if item.free == '0'}
                 <span class="label label-lg label-danger">否</span>
                {@/if}
            </td>
            <td width="20%" >
                <a class="operation" href="javascript:void(0);" data-toggle="modal" data-target="#editCourseSource" onclick="editCourseSource('\${item.id}');">编辑</a>
                <a class="operation" href="javascript:void(0);" onclick="deletePartCourse('\${item.id}','\${item.name}');">删除</a>
            </td>
        </tr>
        {@/each}
    </script>


    <!--章节修改列表-->
    <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="chapterBox" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content" id="chapterList">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="gridSystemModalLabe2">章节管理</h4>
                </div>
                <div class="modal-body">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th width="10%">

                                    ＃ </font>
                                    </font>
                                </th>
                                <th width="30%">

                                    章节名称 </font>
                                    </font>
                                </th>

                                <th width="20%">

                                    操作 </font>
                                    </font>
                                </th>
                            </tr>
                        </thead>
                        <tbody id="chapter">

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <script id="editChapterTemp" type="text/template">
        {@each data as item, index}
        <tr>
            <td>

                第\${parseInt(index)+1}章</font>
                </font>
            </td>
            <td  id="chapter-\${item.id}">
                <input name="chapterName" type="text" value="\${item.name}">
            </td>

            <td>
                <a href="javascript:void(0);" onclick="updateChapter('\${item.id}');">修改</a>|
                <a href="javascript:void(0);" onclick="deleteChapter('\${item.id}');">删除</a>
            </td>
        </tr>
        {@/each}
    </script>
    <jsp:include page="/dynamic/common/footer.jsp" />
    <script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
    <script src="js/act.js?v=${cfg.version}"></script>
    </html>
