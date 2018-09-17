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

    <jsp:include page="../../common/header.jsp"/>






</head>

<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

<div class="portlet light">

    <div class="portlet-body">
        <div class="form-panel">
            <!--具体界面元素开始-->
            <form class="form-horizontal" id="fm-edit" role="form">

            </form>
        </div>

    </div>
</div>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />



</body>


<style>


</style>


<script id="tpl-fm" type="text/template">

    <div class="form-group">
        <label class="col-md-2 control-label">
            <span
                class="required"
                aria-required="true">*</span>咨询对象

        </label>
        <div class="col-md-10">
            <div class="row">

                {@each data.dict149 as item,index}
                    {@if item.CODE!=''}
                    <div style="float:left;padding:2px;width:100px" >
                        <input type="checkbox" name="objects" id="d149\${index}"
                                value="\${item.NAME}"
                                \${data.o.objects.split(',').contains(item.NAME)?'checked':''}>
                        <label for="d149\${index}">
                           \${item.NAME}
                        </label>
                    </div>
                    {@/if}
                {@/each}
            </div>
            <div class="error-objects"></div>
        </div>

    </div>


    <div class="form-group">
        <label class="col-md-2 control-label"><span
                class="required"
                aria-required="true">*</span>擅长领域

        </label>
        <div class="col-md-10">
            <div class="row">
                {@each data.dict152 as item,index}
                {@if item.CODE!=''}
                <div style="float:left;padding:2px;width:150px">
                    <input type="checkbox" id="d152\${index}" name="field"
                            value="\${item.NAME}"
                          \${data.o.field.split(',').contains(item.NAME)?'checked':''}>
                    <label for="d152\${index}">
                       \${item.NAME}
                    </label>
                </div>
                {@/if}
                {@/each}

            </div>
            <div class="error-field"></div>
        </div>
    </div>

    <div class="form-group ">
        <label class="col-md-2 control-label"><span
                class="required"
                aria-required="true">*</span>电话咨询

        </label>
        <div class="col-md-4">
            <input type="text" class="form-control" name="telephoneCon" value="\${data.o.productList[0].price}">
            <div class="error-telephoneCon"></div>
        </div>
        <div class="col-md-6">
            元/次(每次30分钟)
        </div>
    </div>

    <div class="form-group ">
        <label class="col-md-2 control-label"><span
                class="required"
                aria-required="true">*</span>视频咨询

        </label>
        <div class="col-md-4">
            <input type="text" class="form-control" name="wecharCon" value="\${data.o.productList[1].price}">
            <div class="error-wecharCon"></div>
        </div>
        <div class="col-md-6">
            元/次(每次30分钟)
        </div>
    </div>

    <div class="form-group ">
        <label class="col-md-2 control-label">
            <span
                    class="required"
                    aria-required="true">*</span>面对面咨询
        </label>
        <div class="col-md-4">
            <input type="text" class="form-control" name="facetofaceCon" value="\${data.o.productList[2].price}">
            <div class="error-facetofaceCon"></div>
        </div>
        <div class="col-md-6">
            元/次(每次30分钟)
        </div>
    </div>

    <div class="form-group ">
        <label class="col-md-2 control-label">
            <span class="required" aria-required="true">*</span>
            面对面咨询地址
        </label>
        <div class="col-md-10">
            <textarea class="form-control"  name="city" rows="3">\${data.o.city}</textarea>
            <div class="error-city"></div>
        </div>
    </div>

    <div class="form-group ">
        <label class="col-md-2 control-label"><span
                class="required"
                aria-required="true">*</span>是否上架

        </label>
        <div class="col-md-10">

            <input type="checkbox" value="1" name="status" \${data.o.status==1?'checked':''}>
        </div>
    </div>
    <div class="form-actions">
        <div class="row">
            <div class="col-md-offset-3 col-md-7">
                <button class="btn  green" type="submit" style="width:30%">提交</button>
            </div>
        </div>
    </div>

</script>
<script src="${portalPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>
<jsp:include page="/dynamic/common/footer.jsp" />
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/localization/messages_zh.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>

