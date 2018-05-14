
$(function(){
var data_rate = [{
			"id": 1,
			"text": "10%-20%"
		},
		{
			"id": 2,
			"text": "21%-30%"
		},
		{
			"id": 3,
			"text": "31%-40%"
		}
	];
	$('#product_rate1').comboboxfilter({
		url: '',
		scope: 'FilterQuery1',
		data: data_rate,
		onChange: function(newValue) {
			$('#demo_value').val(newValue);
		}
	});
	
	var data_year = [{
			"id": 1,
			"text": "半年"
		},
		{
			"id": 2,
			"text": "1-2年"
		},
		{
			"id": 3,
			"text": "3-5年"
		},
		{
			"id": 4,
			"text": "五年以上"
		}
	];
	$('#product_year').comboboxfilter({
		url: '',
		scope: 'FilterQuery1',
		data: data_year,
		onChange: function(newValue) {
			$('#demo_value').val(newValue);
		}
	});
	var data_limit = [{
			"id": 1,
			"text": "小于50万"
		},
		{
			"id": 2,
			"text": "50-100万"
		},
		{
			"id": 3,
			"text": "100-200万"
		},
		{
			"id": 4,
			"text": "200万以上"
		}
	];
	$('#product_limit').comboboxfilter({
		url: '',
		scope: 'FilterQuery1',
		data: data_limit,
		onChange: function(newValue) {
			$('#demo_value').val(newValue);
		}
	});
	var data_method = [{
			"id": 1,
			"text": "信用贷"
		},
		{
			"id": 2,
			"text": "抵用贷"
		}
	];
	$('#guarantee_method').comboboxfilter({
		url: '',
		scope: 'FilterQuery1',
		data: data_method,
		onChange: function(newValue) {
			$('#demo_value').val(newValue);
		}
	});
});
	