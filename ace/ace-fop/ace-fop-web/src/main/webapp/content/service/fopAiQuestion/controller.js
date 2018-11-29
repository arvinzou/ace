jQuery(function ($) {
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
                // console.log(params);
                jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                    page: 1,
                    postData: params
                }).trigger("reloadGrid");
                return false;
            }
        });
    });

    $('#btn-view-add').on(
        'click',
        function () {
            jQuery(cfg.grid_selector).jqGrid(
                'editGridRow',
                'new',
                {
                    closeAfterAdd: true,
                    recreateForm: true,
                    viewPagerButtons: false,
                    beforeShowForm: function (e) {

                    }
                })
        });
    });
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



  var show = false;

  function del(rowid) {
      console.log(rowid);
      jQuery(cfg.grid_selector).jqGrid('delGridRow',
          rowid,
          {
              beforeShowForm: function (e) {
                  var form = $(e[0]);
                  if (!show) {
                      form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />');
                  }
                  show = true;

              }
          });
  }

  function preview(rowid) {
      var url = contextPath + "/fopAiQuestion/selectFopAiQuestionByPrimaryKey";
      $.getJSON(url, {id: rowid}, function (result) {
          if (result.status == 0) {
              var navitem = document.getElementById('tpl-detail').innerHTML;
              var html = juicer(navitem, {data: result.value});
              $("#fm-detail").html(html);
              $("#modal-detail").modal("show");
          }
      })
  }


//function preview(id, title) {
//    var dialog = $("#dialog-message-view").removeClass('hide').dialog({
//        modal: false,
//        width: 800,
//        title: "<div class='widget-header widget-header-small'><div class='widget-header-pd'>" + title + "</div></div>",
//        title_html: true,
//        buttons: [
//            {
//                html: "<i class='ace-icon fa fa-check bigger-110'></i>&nbsp; 确定",
//                "class": "btn btn-info btn-xs",
//                click: function () {
//                    $(this).dialog("close");
//                }
//            },
//            {
//                html: "<i class='ace-icon fa fa-times bigger-110'></i>&nbsp; 取消",
//                "class": "btn btn-xs",
//                click: function () {
//                    $(this).dialog("close");
//                }
//            }
//        ]
//    });
//    $(dialog).parent().css("top", "1px");
//    $(dialog).css("max-height", window.innerHeight - layoutTopHeight + 50);
//    loadView(id);
//}
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
            $.each(rst.value, function (key, value) {
                if (key == 'category') {
                    value = rsd(value, '83');
                }
                if (key == 'status') {
                    value == "1" ? "正常" : "关闭";
                }
                if (key.indexOf('Date') != -1 || key.indexOf('time') != -1 || key.indexOf('Time') != -1 || key.indexOf('birthday') != -1) {
                    value = Common.DateFormatter(value);
                }
                $("#dialog-message-view").find('#' + key).html(value);
            });
        },
        error: function () {
            alert("加载错误！");
        }
    });
}