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
        <meta charset="utf-8" />
        <title>${cfg.sys_name}</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="${cfg.sys_name}" name="description" />
        <jsp:include page="../../../common/base.jsp" />
        <script src="js/act.js?v=${cfg.version}"></script>
        <script src="${portalPath}/content/common/js/loader.js?v=${cfg.version}"></script>
		     <script type="text/javascript">
            var tplId = '${param.tplId}';
            var pageId = '${param.pageId}';
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
                                                                        <li data-toggle="modal" data-action="add" data-target="#model1">
                                                                            <a href="#">添加</a>
                                                                        </li>
                                                                        <li data-toggle="modal" data-action="edit" data-target="#model1">
                                                                            <a href="#">修改</a>
                                                                        </li>
                                                                    </ul>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>

                                                </div>


                                            </nav>

                                            <div>
                                                <nav class="navbar navbar-default navbar-static">
                                                    <div class="row">
                                                        <div class="col-md-8">

                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="collapse navbar-collapse" style="float:right">
                                                                <ul class="nav navbar-nav">
                                                                    <li class="dropdown">
                                                                        <a id="drop1" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                                                            文章管理
                                                                            <span class="caret"></span>
                                                                        </a>
                                                                        <ul class="dropdown-menu" aria-labelledby="drop1">
                                                                            <li data-toggle="modal" data-action="add" data-target="#model2">
                                                                                <a href="#">添加</a>
                                                                            </li>
                                                                            <li data-toggle="modal" data-action="edit" data-target="#model2">
                                                                                <a href="#">修改</a>
                                                                            </li>
                                                                        </ul>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div id="navitem">

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
                        {@each data.data as item,index} {@if index==0}
                        <li draggable="false" data-id="\${item.id}" data-name="\${item.name}" class="actives">\${item.name}</li>
                        {@else}
                        <li draggable="false" data-id="\${item.id}" data-name="\${item.name}">\${item.name}</li>
                        {@/if} {@/each}
                    </ul>
                </div>

            </script>
            <script id="tpl-navitem" type="text/template">
                {@each data.data.categorys as item,index} {@if index==0}
                <div class="navitem" data-id="\${item.id}" style="display: block;" id="\${item.id}">
                    {@else}
                    <div class="navitem" data-id="\${item.id}"  style="display: none;" id="\${item.id}">
                        {@/if} {@each item.articles as o}
                        <div class="list_item" data-id="\${o.id}" data-tags="\${o.tags}" data-category="\${item.id}" data-hrefurl="\${o.hrefUrl}">
                            <h2 class="title">\${o.title}</h2>
                            <div class="cover ">
                                <img class="img js_img" src="\${o.cover}">
                            </div>
                            <div class="cont">

                                <p class="desc">\${o.remark}</p>
                            </div>
                            <p>
                                {@each o.tags.split(',') as tag,num}
                                <span style="background-color:#21b080;color:#FFFFFF">\${tag}</span>
                                {@/each}
                            </p>
                        </div>
                        {@/each}
                    </div>
                    {@/each}

            </script>
            <div class="modal fade" id="model1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
                <div class="modal-dialog " role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="exampleModalLabel">栏目管理</h4>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="form-group">
                                    <label for="recipient-name" class="control-label">栏目名称:</label>
                                    <input type="text" class="form-control" id="recipient-name" name="name">
                                    <input type="hidden" name="id" />
                                    <input type="hidden" name="action" />
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
            <div class="modal fade" id="model2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="exampleModalLabel">文章管理</h4>
                        </div>
                        <div class="modal-body">
                            <form class="add_item" onsubmit="return false">
                                <div class="form-group">
                                    <span>链接:</span>
                                    <input type="text" name="hrefUrl" placeholder="请输入文章网址">
                                    <input type="hidden" name="id" />
                                    <input type="hidden" name="action" />
                                </div>
                                <div class="form-group">

                                    <span>封面:</span>
																		
																		
                                    <img class="select_img" data-toggle="modal" data-target="#img-uploader" src="img/no-img2.jpg">
                                    <em>推荐尺寸: 640*640</em>
                                </div>
																<div class="form-group">
                                   <img src="" style="display: none;padding-left:60px;" id="cover-img"/>
                                </div>
                                <div class="form-group">
                                    <span>标题:</span>
                                    <input type="text" name="title" placeholder="请输入文章标题">
                                </div>

                                <div class="form-group">
                                    <span>摘要:</span>
                                    <textarea name="remark"></textarea>
                                </div>


                                <div class="form-group">
                                    <span>标签:</span>
                                    <input type="text" name="tags" placeholder="请输入文章标签最多3个,请以英文的逗号隔开">

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

            <div class="modal fade" id="img-uploader" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog modal-lg1100" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title">图片上传</h4>
                        </div>
                        <div class="modal-body row jcrop center">
                            <!-- 原图 -->
                            <div class="col-md-6 original-pane">
                                <img src="img/left_pic_two.jpg" id="target" alt="原图" />
                            </div>
                            <!-- 预览盒子 -->
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="preview-pane">
                                        <img src="img/left_pic_two.jpg" class="preview" alt="Preview" id="Preview" />
                                    </div>
                                </div>
                                <div class="row" id="proc">

                                </div>
                                <div class="row" style="padding-top: 50px;">
                                    <button type="button" class="btn btn-success btn-lg" id="browse">本地上传</button>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-success">确认</button>
                        </div>
                    </div>
                </div>

            </div>

            <div class="popover  pos_center" style="top: 170px; left: 130px; display: none;" id="modify_title_box">
                <div class="popover_inner">
                    <div class="popover_content jsPopOverContent">
                        <div class="popover_edit">
                            <label for="" class="frm_label">模板名称</label>
                            <div class="frm_controls">
                            <span class="frm_input_box">
                                <input type="text" class="frm_input" id="js_hname_input">
                            </span>
                            </div>
                        </div>
                    </div>
                    <div class="popover_bar"><a href="javascript:do_modify_title();" class="btn btn_primary jsPopoverBt greenBtn">确定</a>&nbsp;<a href="javascript:cancel_modify_title();" style="border: 1px solid #eee;" class="btn btn_default jsPopoverBt">取消</a></div>
                </div>
                <i class="popover_arrow popover_arrow_out"></i>
                <i class="popover_arrow popover_arrow_in"></i>
            </div>
    </body>

    </html>
