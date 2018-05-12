$(function() {
	//预期年收益
	var data1 = [{
			"id": 1,
			"text": "10%-20%"
		},
		{
			"id": 2,
			"text": "21%-30%"
		},
		{
			"id": 3,
			"text": "31-40%"
		}
	];
	//单选
	$('#demo1').comboboxfilter({
		url: '',
		scope: 'FilterQuery1',
		data: data1,
		onChange: function(newValue) {
			$('#demo_value').val(newValue);
		}
	});

	//融资年限
	var data2 = [{
			"id": 1,
			"text": "半年"
		},
		{
			"id": 2,
			"text": "1-2年"
		},
		{
			"id": 3,
			"text": "3-4年"
		},
		{
			"id": 4,
			"text": "5年以上"
		}
	];
	//例子1
	//单选
	$('#demo2').comboboxfilter({
		url: '',
		scope: 'FilterQuery1',
		data: data2,
		onChange: function(newValue) {
			$('#demo_value').val(newValue);
		}
	});

});