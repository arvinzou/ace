var _colNames =['姓名','性别','出生日期','手机','入党日期','操作'];
var _colModel = function () {
    return [
    {
    			name: 'name',
    			editable: true,
    			editoptions: {
    				size: "20",
    				maxlength: "25"
    			},
    			formoptions: {
    				elmprefix: "",
    				elmsuffix: "<span style='color:red;'>*</span>"
    			},
    			editrules: {
    				required: true
    			}
    		},

    		{
            			name: 'sex',
            			//width : 60,
            			editable: true,
            			edittype: "select",
            			renderer: function(value) {
            				return rsd(value, "01");
            			},
            			editoptions: {
            				value: odparse("01")
            			},
            			formoptions: {
            				elmprefix: "",
            				elmsuffix: "<span style='color:red;'>*</span>"
            			},
            			editrules: {
            				required: true
            			}
            		},
            		{
                    			name: 'birthday',
                    			editable: true,
                    			edittype: "datebox",
                    			editoptions: {
                    				style: 'width:175px;height:30px'
                    			},
                    			dataoptions: {
                    				formatter: function(date) {
                    					var y = date.getFullYear();
                    					var m = date.getMonth() + 1;
                    					var d = date.getDate();
                    					return y + '-' + (m < 10 ? ('0' + m) : m) + '-' +
                    						(d < 10 ? ('0' + d) : d);
                    				},
                    				parser: function(s) {
                    					if (!s)
                    						return new Date();
                    					var ss = (s.split('-'));
                    					var y = parseInt(ss[0], 10);
                    					var m = parseInt(ss[1], 10);
                    					var d = parseInt(ss[2], 10);
                    					if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
                    						return new Date(y, m - 1, d);
                    					} else {
                    						return new Date();
                    					}
                    				}
                    			},
                    			renderer: function(value) {
                    				return value == null ? "" : value.substring(0, 10);
                    			},
                    			editrules: {
                    				required: false
                    			}
                    		},
                    		{
                            			name: 'mobile',
                            			width: 150,
                            			editable: true,
                            			editoptions: {
                            				size: "20",
                            				maxlength: "11"
                            			},
                            			formoptions: {
                            				elmprefix: "",
                            				elmsuffix: "<span style='color:red;'></span>"
                            			},
                            			editrules: {
                            				required: false
                            			}
                            		},
                            		{
                                                        			name: 'inPmDate',
                                                        			//width : 120,
                                                        			editable: true,
                                                        			edittype: "datebox",
                                                        			editoptions: {
                                                        				style: 'width:175px;height:30px'
                                                        			},
                                                        			dataoptions: {
                                                        				formatter: function(date) {
                                                        					var y = date.getFullYear();
                                                        					var m = date.getMonth() + 1;
                                                        					var d = date.getDate();
                                                        					return y + '-' + (m < 10 ? ('0' + m) : m) + '-' +
                                                        						(d < 10 ? ('0' + d) : d);
                                                        				},
                                                        				parser: function(s) {
                                                        					if (!s)
                                                        						return new Date();
                                                        					var ss = (s.split('-'));
                                                        					var y = parseInt(ss[0], 10);
                                                        					var m = parseInt(ss[1], 10);
                                                        					var d = parseInt(ss[2], 10);
                                                        					if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
                                                        						return new Date(y, m - 1, d);
                                                        					} else {
                                                        						return new Date();
                                                        					}
                                                        				}
                                                        			},
                                                        			renderer: function(value) {
                                                        				return value == null ? "" : value.substring(0, 10);
                                                        			},
                                                        			editrules: {
                                                        				required: false
                                                        			}
                                                        		}
        ,
        {
            name : 'opt',
            sortable: false,
            width : 100,
            renderer : function(value, cur) {
                var rowid=$.jgrid.getAccessor(cur, cfg.dataId);
                var title=$.jgrid.getAccessor(cur,'fullName');
                var opt=[];
                if(true){
                    opt.push('<a href="javascript:edit(\''+rowid+'\')">编辑</a> ');
                }
                if(true){
                    opt.push('<a href="javascript:del(\''+rowid+'\')">删除</a>  ');
                }

                return opt.join('');
            }
        }
    ];
}