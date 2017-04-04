jQuery(function($) {
	$.fn.spin = function(opts) {
		this.each(function() {
		  var $this = $(this),
			  data = $this.data();

		  if (data.spinner) {
			data.spinner.stop();
			delete data.spinner;
		  }
		  if (opts !== false) {
			data.spinner = new Spinner($.extend({color: $this.css('color')}, opts)).spin(this);
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

	//$(cfg.grid_selector).closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });  
	//$(cfg.grid_selector).closest(".ui-jqgrid-bdiv").css({ "overflow-y" : "hidden" });  
	//$(cfg.grid_selector).closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "scroll" });  
	//$(cfg.grid_selector).closest(".ui-jqgrid-bdiv").css({ "overflow-y" : "scroll" }); 	

function style_ajax_button(btnId,status){
	console.log(status);
	var btn=$('#'+btnId);
	if(status){
		btn.find('i').removeClass('fa-check');
		btn.find('i').addClass('fa-spinner fa-spin');
		btn.attr('disabled',"true");
		
	}else{
		btn.find('i').removeClass('fa-spinner');
		btn.find('i').removeClass('fa-spin');
		btn.find('i').addClass('fa-check');
		btn.removeAttr("disabled");
	}
}
});