jQuery(function($) {
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

                jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                    page: 1,
                    postData: params
                }).trigger("reloadGrid");
                return false;
            }
        });
    });

    $('#btn-view-add').on('click', function () {
        jQuery(cfg.grid_selector).jqGrid(
            'editGridRow',
            'new',
            {
                closeAfterAdd: true,
                recreateForm: true,
                viewPagerButtons: false,
                beforeShowForm: function (e) {
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find(
                        '.ui-jqdialog-titlebar').wrapInner(
                        '<div class="widget-header" />')
                    appendButtons();

                     $("#sData").click(function(){
                           checkCard();
                           checkNum();
                           MoneyCheck();
                 });
                }
            })
    });
});


          function checkCard(){
                         var card = $("#lpIdentityCard").val();
                         var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
                           if(card==''){
                               $("#lpIdentityCard").val("");
                                               }
                            else if(reg.test(card) === false){
                                 alert("身份证输入不合法");
                                 return false;
                            }
                  }

          function checkNum(){
                         var num1 = $("#crewSize").val();
                         var num2 = $("#partyPeoples").val();
                         var num3 = $("#laborContractNum").val();
                         var reg=(/^\d*$/);
                            if(reg.test(num1)===false || reg.test(num2)===false || reg.test(num3)===false )
                                 {
                                  alert("人数只能为数字");
                                  return false;
                            }
                      }

           function MoneyCheck(){
                         var num1 = $("#registeredCapital").val();
                         var num2 = $("#fixedAssets").val();
                         var num3 = $("#workingCapital").val();
                         var reg = /^\d*\.{0,1}\d{0,2}$/;
                              if(reg.test(num1)===false || reg.test(num2)===false || reg.test(num3)===false )
                              {
                              alert("金额只能是数字和小数点后两位");
                              return false;
                           }
           }


function preview(id, title) {
    window.open(contextPath + '/dynamic/service/company/view.jsp?companyId=' + id);
}
function appendButtons() {
    appendMapBtn("address");
    appendUploadBtn("companyLogo");
}
/**
 * 1、添加地图位置的指针*/
function appendMapBtn(id) {
    var html = new Array();
    html.push("<a id='btn-map-add-"
        + id
        + "' class='ace-icon fa fa-location-arrow bigger-110' href='javascript:false'>选取</a>");
    $("#" + id).after(html.join(''));
    $('#btn-map-add-' + id).on('click', function () {
        window.open(portalPath + "/dynamic/common/map.jsp");
    });
}
/**
 * 2、自动填写地址
 * @param latitude
 */
function latitude(latitude) {
    $("#latitude").val(latitude);
}
function longitude(longitude) {
    $("#longitude").val(longitude);
}
function addr(addr) {
    $("#address").val(addr);
}


function edit(rowid){
    console.log(rowid);
	jQuery(cfg.grid_selector).jqGrid(
						'editGridRow',
						rowid,
						{
							closeAfterAdd : true,
							recreateForm : true,
							viewPagerButtons : true,
							beforeShowForm : function(e) {
								var form = $(e[0]);
								form.closest('.ui-jqdialog').find(
										'.ui-jqdialog-titlebar').wrapInner(
										'<div class="widget-header" />');
										  appendButtons();

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