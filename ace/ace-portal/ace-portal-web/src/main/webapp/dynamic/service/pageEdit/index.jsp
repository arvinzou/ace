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
                              <div class="﻿col-md-4">
                                  <div class="box">
                                      <div class="simulator_hd">
                                          <h4 class="title" id="js_preview_title">页面模板</h4>
                                      </div>

                                      <iframe id="ifr" src="${portalPath}/www/page/${param.tplId}/index.jsp?pageId=${param.pageId}" name="ifr" class="list_frame"></iframe>

                                  </div>
                              </div>
                                <div class="col-md-8">
                                    <div class="tools-bar">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-success">Action</button>
                                            <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu">
                                                <li><a href="#">Action</a></li>
                                                <li><a href="#">Another action</a></li>
                                                <li><a href="#">Something else here</a></li>
                                                <li role="separator" class="divider"></li>
                                                <li><a href="#">Separated link</a></li>
                                            </ul>
                                        </div>
                                        <div id="categoryItems" class="navigation">

                                        </div>
                                    </div>
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



    <div class="news-title">
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

</body>
</html>

