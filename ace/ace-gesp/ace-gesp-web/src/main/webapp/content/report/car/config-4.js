var option4 = {
	    title : {
	        text: '',
	        subtext: '',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c}辆({d}%)" 
	    },
	    legend: {
	        orient : 'vertical',
	        x : 'left',
	        data:[]
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {
	                show: true, 
	                type: ['pie', 'funnel'],
	                option: {
	                    funnel: {
	                        x: '25%',
	                        width: '50%',
	                        funnelAlign: 'left',
	                        max: 1548
	                    }
	                }
	            },
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    series : [
	        {
	            name:'车辆吨位',
	            type:'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:[
	               
	            ]
	            /*,itemStyle : {
	                normal : {
	                    label : {
	                        //position : 'inner',
	                        formatter : function (params) { 
	                          return  params.name+'('+params.value+")"
	                        }
	                    }
	                }
	            }*/
	        }
	    ]
	};
	                    
