<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

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
    <jsp:include page="../../common/base.jsp" />
    <script src="js/act.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/js/loader.js?v=${cfg.version}"></script>
    <script type="text/javascript">
            var tplId ='${param.tplId}';
            var pageId='${param.pageId}';
     </script>
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
                                    <a href="index4.jsp">首页</a>
                                    <i class="fa fa-circle"></i>
                                </li>
                                <li>
                                    <span>选择模板</span>
                                </li>
                            </ul>
                            <div class="page-content-inner row">

                                <!---==============================================-->
                              <div class="col-md-4">
                                  <div class="box">
                                      <div class="simulator_hd">
                                          <h4 class="title" id="js_preview_title">页面模板</h4>
                                      </div>

                                      <iframe id="ifr" src="${portalPath}/www/page/${param.tplId}/index.jsp?pageId=${param.pageId}" name="ifr" class="list_frame"></iframe>

                                  </div>
                              </div>
                                <div class="col-md-8">
                                    <nav id="navbar-example" class="navbar navbar-default navbar-static">
                                        <div class="row">
                                            <div class="col-md-8" id="categoryItems">

                                            </div>
                                            <div class="col-md-4">
                                                <div class="collapse navbar-collapse" style="float:right">
                                                    <ul class="nav navbar-nav">
                                                        <li class="dropdown">
                                                            <a id="drop2" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                                                栏目管理
                                                                <span class="caret"></span>
                                                            </a>
                                                            <ul class="dropdown-menu" aria-labelledby="drop2">
                                                                <li data-toggle="modal" data-action="add" data-target="#model1"><a href="#">添加</a></li>
                                                                <li data-toggle="modal" data-action="edit" data-target="#model1"><a href="#">修改</a></li>
                                                            </ul>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>

                                        </div>


                                    </nav>

                                    <div class="edit" id="edit_bottom">
                                    </div>

                                </div>

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

<script id="tpl-categoryItems" type="text/template">



    <div class="nav-bar">
        <ul class="news-module clear" id="bar">
            {@each data.data as item,index}
            {@if index==0}
            <li draggable="false"  data-id="\${item.id}" data-name="\${item.name}" class="actives">\${item.name}</li>
            {@else}
            <li draggable="false" data-id="\${item.id}" data-name="\${item.name}">\${item.name}</li>
            {@/if}
            {@/each}
        </ul>
    </div>

</script>
<div class="modal fade" id="model1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog " role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">栏目管理</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">栏目名称:</label>
                        <input type="text" class="form-control" id="recipient-name" name="name">
                        <input type="hidden" name="id"/>
                        <input type="hidden" name="action"/>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>

