var modifyPassWordWindow;
var modifyPassWordForm;
function createModifyPassWordWindow() {
	if (!modifyPassWordWindow) {
		modifyPassWordForm = new Ext.form.FormPanel({
					labelWidth : 65,
					baseCls : 'x-plain',
					snail : false,
					layout : {
						type : 'form',
						align : 'stretch'
					},
					defaultType : 'textfield',
					defaults : {
						width : 150
					},
					width : 150,
					autoHeight : true,
					items : [{
								fieldLabel : "密码",
								inputType : 'password',
								name : "password"
							}, {
								fieldLabel : "确认密码",
								inputType : 'password',
								name : "repassword"
							}]

				});
		modifyPassWordWindow = new Ext.Window({
					title : '修改密码',
					layout : 'fit',
					bodyStyle : 'padding:5px;',
					width : 350,
					height : 150,
					autoHeight : false,
					closeAction : 'hide',
					plain : true,
					collapsible : true,
					maximizable : true,
					buttonAlign : 'center',
					items : modifyPassWordForm,
					buttons : [{
								text : '保存',
								type : 'submit',
								disabled : false,
								iconCls : 'Save',
								id : "t_modifyPassWord_save",
								handler : function() {
									t_save();
								}
							}, {
								text : '清除',
								iconCls : 'Refresh',
								handler : function() {
									var form = modifyPassWordForm.getForm();
									form.reset();
								}
							}, {
								text : '关闭',
								iconCls : 'Erase',
								handler : function() {
									modifyPassWordWindow.hide();
								}
							}]
				});

	}

	modifyPassWordWindow.show();
}

function t_save() {
	t_disableModifyPassWordSave();
	Ext.Ajax.request({
		timeout : 10000000,
		url : contextPath + '/system/updatePassword.do',
		method : 'POST',
		async : false,
		params : getParams(modifyPassWordForm),
		success : function(v) {
			t_enableModifyPassWordSave();
			var rst = Ext.decode(v.responseText);
			Ext.Msg.show({
				title : '系统提示',
				msg : rst.errorMessage,
				icon : Ext.Msg.INFO,
				minWidth : 300,
				buttons : Ext.Msg.OK
			});
			
		},
		failure : function(response, opts) {
			t_enableModifyPassWordSave();
			Ext.Msg.show({
				title : '系统提示',
				msg : response.statusText,
				icon : Ext.Msg.INFO,
				minWidth : 300,
				buttons : Ext.Msg.OK
			});
		}
	})
}


function t_disableModifyPassWordSave() {
	Ext.getCmp('t_modifyPassWord_save').disable();
	Ext.getCmp('t_modifyPassWord_save').setText("请稍候......");
}
function t_enableModifyPassWordSave() {
	Ext.getCmp('t_modifyPassWord_save').enable();
	Ext.getCmp('t_modifyPassWord_save').setText("保存");
}
