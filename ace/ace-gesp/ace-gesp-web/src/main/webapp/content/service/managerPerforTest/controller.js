jQuery(function($) {
	$.fn.spin = function(opts) {
		this.each(function() {
			var $this = $(this), data = $this.data();

			if (data.spinner) {
				data.spinner.stop();
				delete data.spinner;
			}
			if (opts !== false) {
				data.spinner = new Spinner($.extend({
					color : $this.css('color')
				}, opts)).spin(this);
			}
		});
		return this;
	};

	$('#btn-search').on('click', function() {
		$('#fm-search').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
				});
				$.extend(params, {
					time : new Date()
				});
				// console.log(params);
				jQuery(cfg.grid_selector).jqGrid('setGridParam', {
					page : 1,
					postData : params
				}).trigger("reloadGrid");
				return false;
			}
		});
	});

	$("#btn-view-pay").on('click', function(e) {
		e.preventDefault();
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam', 'selrow');
		if (!gr) {
			$.jgrid.info_dialog($.jgrid.nav.alertcap, $.jgrid.nav.alerttext);
			return;
		}

		var r = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);
		showPayDia(r.memberCode, r.status, r.departmentName);
	});

	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title : function(title) {
			var $title = this.options.title || '&nbsp;'
			if (("title_html" in this.options)
					&& this.options.title_html == true)
				title.html($title);
			else
				title.text($title);
		}
	}));
	
	$('#btn-view-update').on('click',function() {
		var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam','selrow');
		var grName = jQuery(cfg.grid_selector).jqGrid('getCell',gr, 'plateNumber');
		if (gr) {
			//console.log(gr+"-----------------------------"+grName);
			editDetail(gr,grName);
		}else{
			alert("请选择一行");
		}
	});
});

function editDetail(id,name){
	parent.addPanel(name,contextPath+'/dynamic/service/industryResourceCar/edit.jsp?id='+id,true);
}