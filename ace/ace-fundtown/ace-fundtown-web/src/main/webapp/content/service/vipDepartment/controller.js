jQuery(function ($) {
    //查询

   $('body').on('change','select[name=type]',function(){
        var type=$('select[name=type]').val();
        if(type==1){
             $('input[name=parentDepartmentId]').val('');
             $('input[name=parentDepartmentId]').siblings().first().attr('readonly',false);
             $('input[name=parentDepartmentId]').siblings().first().val('');
        }else{
            $('input[name=parentDepartmentId]').val('0');
            $('input[name=parentDepartmentId]').siblings().first().attr('readonly',true);
            $('input[name=parentDepartmentId]').siblings().first().val('无所属机构');
        }
   });
    $('#btn-search').on('click', function () {
        $('#fm-search').ajaxForm({
            beforeSubmit: function (formData, jqForm, options) {
                var params = {};
                $.each(formData, function (n, obj) {
                    params[obj.name] = obj.value;
                });
                $.extend(params, {
                    time: new Date()
                });
                // console.log("====================");
                // console.log(params);
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                    page: 1,
                    postData: params
                }).trigger("reloadGrid");
                return false;
            }
        });
    });
// 添加
    $('#btn-view-add').on('click', function () {
        var regAddr = "常德市柳叶湖旅游度假区七里桥街道戴家岗社区清科基金小镇";
        jQuery(cfg.grid_selector).jqGrid(
            'editGridRow',
            'new',
            {
                closeAfterAdd: true,
                recreateForm: true,
                viewPagerButtons: false,
                beforeSubmit: function (postdata) {
                    if (postdata.type == '1'
                        && postdata.parentDepartmentId != null
                        && postdata.parentDepartmentId != '') {
                        postdata.parent = {departmentName: postdata.parentDepartmentId};
                    }
                    return [true, "", ""];
                },
                beforeShowForm: function (e) {
                    $('#regAddr').val(regAddr);
                }
            })
    });
    initEvents();
});

    function initEvents() {
        //审核模态框
        $('#modal-audit').on('shown.bs.modal', function (event) {
            var relatedTarget = $(event.relatedTarget);
            var id = relatedTarget.data('id');
            console.log(id);
            var modal = $(this);
            modal.find('.modal-body input[name=id]').val(id);
        });
        //审核框 确定按钮
        $('#modal-audit .btn-primary').on('click', function () {
            $('#modal-audit form').submit();
        });
        //审核框 提交事件
        $('#modal-audit form').ajaxForm({
            beforeSubmit: function (formData, jqForm, options) {
                var rstVal = $('input[name="rst"]:checked').val();
                var nodeIdVal = $('#fm-audit').find('input[name="nodeId"]').val();
                if (nodeIdVal == undefined || nodeIdVal == '' || nodeIdVal == null) {
                    alert("请选择入驻节点!");
                    return;
                }
                if (rstVal == undefined) {
                    alert("请选择审核结果!");
                    return;
                }
                var params = {};
                $.each(formData, function (n, obj) {
                    params[obj.name] = obj.value;
                });
                $.extend(params, {
                    time: new Date()
                });
                console.log(params);
                audit(params);
                return false;
            }
        });
         $(".btn-group .btn").bind('click', function (event) {
                $(event.target).siblings().removeClass("active");
                console.log(event);
                $(event.target).addClass("active");
            });
    }

 function audit(params) {
        console.log(params);
        startLoad();
        $.ajax({
            type: "post",
            url: contextPath + "/vipDepartment/audit",
            data: {
                deptId: params.id,
                nodeId: params.nodeId,
                auditResult: params.rst,
                auditOpinion: params.message
            },
            beforeSend: function (XMLHttpRequest) {
            },
            success: function (rst, textStatus) {
                stopLoad();
                $("#modal-audit").modal('hide');
                alert(rst.errorMessage);
                if (rst.status == 0) {
                    jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: params}).trigger("reloadGrid");
                }
            },
            complete: function (XMLHttpRequest, textStatus) {
            },
            error: function () {
            }
        });
    }

function edit(rowid) {
      console.log(rowid);
     jQuery(cfg.grid_selector).jqGrid(
               'editGridRow',
               rowid,
               {
                   closeAfterAdd: true,
                   recreateForm: true,
                   viewPagerButtons: true,
                   beforeSubmit: function (postdata) {
                       postdata.content = editor.getValue();
                       return [true, "", ""];
                   },
                   beforeShowForm: function (e) {

                   }
               });
  }


var show=false;
function del(rowid){
    console.log(rowid);
	jQuery(cfg.grid_selector).jqGrid('delGridRow',
    rowid,
    {
        beforeShowForm : function(e) {
            var form = $(e[0]);
            if(!show){
                form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
            }

            show=true;

        }
    });
}

function setParams(key, value) {
    params[key] = value;
    jQuery(cfg.grid_selector).jqGrid('setGridParam',{postData : params}).trigger("reloadGrid");
}
//字符串判空
function strIsEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
    } else {
        return false;
    }
}

function loadView(id) {
    $.ajax({
        type: "post",
        url: cfg.view_load_data_url,
        data: {
            id: id
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            console.log(JSON.stringify(rst.value));
            $.each(rst.value, function (key, value) {
                if (key == 'status') {
                    var rst = "";
                    switch (value) {
                        case '0' :
                            rst = "已删除";
                            break;
                        case '1' :
                            rst = "入驻中";
                            break;
                        case '2' :
                            rst = "注册成功";
                            break;
                        default :
                            rst = "入驻中";
                    }
                    value = rst;
                }
                if (key.indexOf('Date') != -1 ||
                    key.indexOf('time') != -1 ||
                    key.indexOf('Time') != -1 ||
                    key.indexOf('birthday') != -1) {
                    value = Common.DateFormatter(value);
                }
                if (key == 'type') {
                    value = rsd(value, '147');
                }

                $("#dialog-message-view").find('#' + key).html(value);
            });
        },
        error: function () {
            alert("加载错误！");
        }
    });
}
//加载附件信息
function loadAttach(id) {
    $.ajax({
        type: "post",
        url: contextPath + "/vipDepartment/findVipResList",
        data: {deptId: id},
        success: function (rst, textStatus) {
            console.log(rst);
            //
            if (rst && rst.status == 0) {
                initAttachDom(rst.data, id);
            } else {
                bootbox.dialog({
                    title: '系统提示',
                    message: rst.info,
                    detail: rst.info,
                    buttons: {
                        "success": {
                            "label": "<i class='ace-icon fa fa-check'></i>确定",
                            "className": "btn-sm btn-success",
                            "callback": function () {
                                //$( this ).dialog( "close" );
                            }
                        }
                    }
                });
            }
        }
    });
}
//初始化附件节点
function initAttachDom(data, deptId) {
    console.log("========initAttachDom:" + data)
    var html = [];
    var fileName;
    $.each(data, function (n, file) {
        fileName = strIsEmpty(file.resName) ? '未知文件名' : file.resName;
        html.push('<div id="' + file.id + '">' +
            '<a href="' + file.resUrl + '" target="_blank">' + fileName + '</a>   ' +
            ' <a class=\'ace-icon glyphicon glyphicon-remove bigger-110\' ' +
            'href="javascript:deleteAttach(\'' + file.id + '\',\'' + deptId + '\')"></a>' +
            '<b></b></div>');
    });
    $('#filelist-history').html(html.join(''));
}
//删除文件
function deleteAttach(id, deptId) {
    $.ajax({
        type: "post",
        url: contextPath + "/vipDepartment/deleteAttach",
        data: {resId: id},
        success: function (rst, textStatus) {
            if (rst && rst.status == 0) {
                loadAttach(deptId);
            } else {
                bootbox.dialog({
                    title: '系统提示',
                    message: rst.info,
                    detail: rst.info,
                    buttons: {
                        "success": {
                            "label": "<i class='ace-icon fa fa-check'></i>确定",
                            "className": "btn-sm btn-success",
                            "callback": function () {
                            }
                        }
                    }
                });
            }
        }
    });
}


function loadText(id) {
    console.log("id = " + id);
    $.ajax({
        type: "post",
        url: cfg.view_load_data_url,
        data: {
            id: rowid
        },
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
//            $("#TblGrid_grid-table").find("input[name=parentDepartmentId]").val(rst.value.parentDepartmentName);
        },
        error: function () {
            alert("加载错误！");
        }
    });
}

function repealPublicity(deptId) {

    $.ajax({
        type: "post",
        url: contextPath + "/vipDepartment/repealPublicity",
        data: {deptId: deptId},
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
              stopLoad();
              alert(rst.errorMessage);
              if (rst.status == 0) {
                  jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: deptId}).trigger("reloadGrid");
               }
            },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function () {
        }
    });
}

    //信息公示
    function publicity(deptId) {
        startLoad();
        console.log("--sss--"+deptId)
        $.ajax({
            type: "post",
            url: contextPath + "/vipDepartment/publicity",
            data: {deptId:deptId },
            beforeSend: function (XMLHttpRequest) {
            },
            success: function (rst, textStatus) {
              stopLoad();
              alert(rst.errorMessage);
              if (rst.status == 0) {
                  jQuery(cfg.grid_selector).jqGrid('setGridParam', {postData: deptId}).trigger("reloadGrid");
               }
            },
            complete: function (XMLHttpRequest, textStatus) {
            },
            error: function () {
            }
        });

    }




 function preview(rowid) {
      var url = contextPath + "/vipDepartment/selectVipDepartmentByPrimaryKey";
      $.getJSON(url, {id: rowid}, function (result) {
          if (result.status == 0) {
              var navitem = document.getElementById('tpl-detail').innerHTML;
              var html = juicer(navitem, {data: result.value});
              $("#fm-detail").html(html);
              $("#modal-detail").modal("show");
          }
      })
  }


