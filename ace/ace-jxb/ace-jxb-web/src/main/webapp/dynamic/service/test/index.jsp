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

        <jsp:include page="../../common/header.jsp" />

        <link rel="stylesheet" href="${portalPath}/content/common/simditor/styles/simditor.css">
        <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css">
        <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/css/uploadImg.css">
        <script src="${pageContext.request.contextPath}/content/service/test/js/act.js?v=${cfg.version}"></script>

    </head>

    <body>
    <jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />



    <div class="portlet light">
                                                                    <div class="portlet-body">
                                                                        <div class="row custom-toolbar">
                                                                            <div class="col-md-2">
                                                                                <a onclick="javascript:createTest()" class="btn green">
                                                                                    创建评测
                                                                                </a>
                                                                            </div>
                                                                            <div class="col-md-6">

                                                                            </div>

                                                                            <div class="col-md-4">
                                                                                <div class="input-group">
                                                                                    <input name="testName" type="text" class="form-control " placeholder="请输入评测名称">
                                                                                    <span class="input-group-btn">
                                                                                    <button onclick="javascript:searchByName()" class="btn  btn-default search_btn" type="submit">
                                                                                        搜索
                                                                                    </button>
                                                                                </span>
                                                                                </div>
                                                                            </div>

                                                                        </div>
                                                                        <div class="table-scrollable">
                                                                            <table class="table table-hover">
                                                                                <thead>
                                                                                    <tr>
                                                                                        <th width="30%">标题</th>
                                                                                        <th width="30%">摘要</th>
                                                                                        <th width="10%">类型</th>
																																												<th width="10%">已测人数</th>
                                                                                        <th width="20%">操作
                                                                                        </th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody id="evaluatTplList">
                                                                                </tbody>
                                                                            </table>
                                                                        </div>
                                                                        <div class="paginationbar">
                                                                            <ul class="pagination" id="pagination1"></ul>
                                                                        </div>
                                                                    </div>
    </div>


    <jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />


</body>

    <jsp:include page="/dynamic/common/footer.jsp" />
    <script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/simditor/scripts/module.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/simditor/scripts/hotkeys.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/simditor/scripts/uploader.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/simditor/scripts/simditor.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/plupload/plupload.full.min.js?v=${cfg.version}"></script>

    <div class="modal bs-example-modal-lg" data-backdrop="static" id="createTest" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">创建评测</h4>
                </div>
                <div class="modal-body">

                    <div class="row step-row step_one" flag="true">

                        <div class="col-lg-12">
                            <div class="mt-element-step">
                                <div class="row step-line">
                                    <div class="col-md-4 mt-step-col first active">
                                        <div class="mt-step-number bg-dark font-grey">1</div>
                                        <div class="mt-step-title uppercase font-grey-cascade">基本信息</div>
                                    </div>
                                    <div class="col-md-4 mt-step-col">
                                        <div class="mt-step-number bg-dark font-grey">2</div>
                                        <div class="mt-step-title uppercase font-grey-cascade">编辑信息</div>
                                    </div>
                                    <div class="col-md-4 mt-step-col last">
                                        <div class="mt-step-number bg-dark font-grey">3</div>
                                        <div class="mt-step-title uppercase font-grey-cascade">设计量表</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="profile-content">
                                <div class="form-horizontal" novalidate="novalidate">
                                    <div class="form-body" style="padding-right: 50px;">

                                        <div class="form-group ">
                                            <label class="col-md-2 control-label">
                                                <span class="required" aria-required="true">*</span>评测名称

                                            </label>
                                            <div class="col-md-10">
                                                <input type="text" id="notNull_name" class="form-control form_name" placeholder="" name="form_name">
                                                <span class="error_message"></span>
                                                <div class="form-control-focus"></div>
                                            </div>
                                        </div>

                                        <div class="form-group ">
                                            <label class="control-label col-md-2">
                                                <span class="required" aria-required="true">*</span>评测封面

                                            </label>
                                            <div class="col-md-10">
                                                <div>
                                                    <div>
                                                        <img class="select_img form_cover" id="cover" data-cover="cover" data-toggle="modal" data-againadd="false" data-xsize="375"
                                                             data-ysize="195" data-target="#img-uploader" src="addImg1.png">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="form-group ">
                                            <label class="control-label col-md-2">
                                                <span class="required" aria-required="true">*</span>评测类型

                                            </label>
                                            <div class="col-md-10">
                                                <select class="col-md-12 form_category" id="TestTypeList" name="">
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group ">
                                            <label class="col-md-2 control-label">
                                                <span class="required" aria-required="true">*</span>评测摘要

                                            </label>
                                            <div class="col-md-10">
                                                <input type="text" id="notNull_summary" class="form-control form_summary" placeholder="" name="form_summary" maxlength="16">
                                                <span class="error_message"></span>
                                                <div class="form-control-focus"></div>
                                            </div>
                                        </div>


                                        <div class="form-group ">
                                            <label class="col-md-2 control-label">
                                                <span class="required" aria-required="true">*</span>原价

                                            </label>
                                            <div class="col-md-4">
                                                <input type="text" id="money_originalCost" class="form-control form_originalCost" placeholder="" name="form_originalCost">
                                                <span class="error_message"></span>
                                                <div class="form-control-focus"></div>
                                            </div>

                                            <label class="col-md-2 control-label">
                                                <span class="required" aria-required="true">*</span>折扣价

                                            </label>
                                            <div class="col-md-4">
                                                <input type="text" id="money_discountCost" class="form-control form_discountCost" placeholder="" name="form_discountCost">
                                                <span class="error_message"></span>
                                                <div class="form-control-focus"></div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>


                    <div class="row step-row step_two" flag="false" style="display: none">

                        <div class="col-lg-12">
                            <div class="mt-element-step">
                                <div class="row step-line">
                                    <div class="col-md-4 mt-step-col first done">
                                        <div class="mt-step-number bg-dark font-grey">1</div>
                                        <div class="mt-step-title uppercase font-grey-cascade">基本信息</div>
                                    </div>
                                    <div class="col-md-4 mt-step-col active">
                                        <div class="mt-step-number bg-dark font-grey">2</div>
                                        <div class="mt-step-title uppercase font-grey-cascade">编辑信息</div>
                                    </div>
                                    <div class="col-md-4 mt-step-col last">
                                        <div class="mt-step-number bg-dark font-grey">3</div>
                                        <div class="mt-step-title uppercase font-grey-cascade">设计量表</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="profile-content">
                                <div class="form-horizontal" novalidate="novalidate">
                                    <div class="form-body" style="padding-right: 50px;">

                                        <div class="form-group ">
                                            <label class="col-md-2 control-label">
                                                <span class="required" aria-required="true">*</span>评测介绍

                                            </label>
                                            <div class="col-md-10">
                                                <textarea class="form-control form_introduce" id="notNull_introduction" name="form_introduce" rows="5"></textarea>
                                                <span class="error_message"></span>
                                                <div class="form-control-focus"></div>
                                            </div>
                                        </div>

                                        <div class="form-group ">
                                            <label class="col-md-2 control-label">
                                                <span class="required" aria-required="true">*</span>评测须知

                                            </label>
                                            <div class="col-md-10">
                                                <textarea class="form-control form_notice" id="notNull_Notice" name="form_notice" rows="3"></textarea>
                                                <span class="error_message"></span>
                                                <div class="form-control-focus"></div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="row step-row step_three" flag="false" style="display: none">

                        <div class="col-lg-12">
                            <div class="mt-element-step">
                                <div class="row step-line">
                                    <div class="col-md-4 mt-step-col first done">
                                        <div class="mt-step-number bg-dark font-grey">1</div>
                                        <div class="mt-step-title uppercase font-grey-cascade">基本信息</div>
                                    </div>
                                    <div class="col-md-4 mt-step-col done">
                                        <div class="mt-step-number bg-dark font-grey">2</div>
                                        <div class="mt-step-title uppercase font-grey-cascade">编辑信息</div>
                                    </div>
                                    <div class="col-md-4 mt-step-col active last">
                                        <div class="mt-step-number bg-dark font-grey">3</div>
                                        <div class="mt-step-title uppercase font-grey-cascade">设计量表</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="portlet-body">
                                <h4>量表分数从高到底排列</h4>
                                <div class="table-scrollable">
                                    <table class="table table-striped table-hover">
                                        <thead>
                                        <tr>

                                            <th width="30%"> 分数</th>
                                            <th width=50%"> 评价</th>
                                            <th width="20% "> 操作</th>
                                        </tr>
                                        </thead>
                                        <tbody class="gauge-list">
                                        <tr>
                                            <th width="30% "> 大于 <input class="form_score " type="text ">分</th>
                                            <th width="50% "><textarea rows="1 "
                                                                       class="textareaHeight form_content "></textarea></th>
                                            <th class=" primary-link " width="20% "></th>
                                        </tr>
                                        <tr>
                                            <th width="30% "> 大于 <input class="form_score " type="text ">分</th>
                                            <th width="50% "><textarea rows="1 "
                                                                       class="textareaHeight form_content "></textarea></th>
                                            <th class=" primary-link " width="20% "></th>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="row ">
                                <div class="col-md-2 col-md-offset-5 ">
                                    <button type="button" id="add-gauge" class=" col-md-12 btn btn-circle btn-success ">添 加
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>



                </div>
                <div class="modal-footer ">

                    <button type="button " class="btn btn-default subtPage " style="display: none ">上一步</button>

                    <button type="button " class="btn btn-default addPage ">下一步</button>

                    <button type="button " class="btn btn-info submit_btn " flag="true " style="display: none ">确定</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <script id="temp_TestTypeList" type="text/template">
    {@each data as item}
    <option value="\${item.id} ">\${item.name}</option>
    {@/each}
</script>


<script id="temp_evaluatTplList" type="text/template">
    {@each data as item,index}
    <tr>

        <td>
            <div class="row ">
                <div class="col-md-3 "><img src="\${item.cover} " class="cover"/></div>
                <div class="col-md-9 ">
                    <div class="describtion ">\${item.name}</div>
                    <div class="cost ">￥\${item.discountCost}</div>
                </div>
            </div>
        </td>
        <td> \${item.summary}</td>
				<td><span class="label label-lg label-info ">\${item.categoryName}</span></td>
				<td>\${item.testedTotal}</td>
        <td>
            <a href="javascript:void(0);" onclick="javascript:modify('\${item.id}')" >修改</a>
            <a href="javascript:void(0);" onclick="javascript:del('\${item.id}')" >删除</a>
            <a href="javascript:void(0);" onclick="javascript:evaluatCaseList('\${item.id}')">查看</a>
        </td>
    </tr>
    {@/each}
</script>

<style>

    .active {
        background: transparent;
    }

    .select_img {
        width: 187px;
        height: 97px;
        object-fit: cover;
        background-color: #32C5D2;
    }

    .simditor .simditor-body {
        height: 300px;
        overflow-y: auto;
    }

    select {
        border: 1px #ccc solid;
        height: 40px;
    }

    .textareaHeight {
        overflow-y: hidden;
    }

    tbody th {
        padding: 0px !important;
    }

    tbody input, tbody textarea {
        border: none;
        height: 40px;
        padding: 8px;
    }

    tbody textarea {
        width: 100%;
    }

    .delectBtn {
        text-align: center;
        line-height: 40px !important;
        cursor: pointer;
    }

    .error_message {
        color: red;
    }

    input[type="checkbox "] {
        width: 18px;
        height: 18px;
        cursor: pointer;
        margin: 0;
    }
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

</html>
