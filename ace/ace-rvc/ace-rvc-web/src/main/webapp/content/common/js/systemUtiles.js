function getComboxStore(id, service) {
    var record = Ext.data.Record.create([{
        name: 'label'
    }, {
        name: 'data'
    }]);
    var store = new Ext.data.Store({
        proxy: new Ext.data.DWRProxy(service, true),
        reader: new Ext.data.ListRangeReader({
            totalProperty: 'allRows',
            root: 'list',
            id: 'data'
        }, record)
    });
    store.load({
        params: {
            id: id
        }
    });
    return store;
}

function initCombox(t_id, t_fieldLabel, t_store) {
    return new Ext.form.ComboBox({
        fieldLabel: t_fieldLabel,
        id: t_id,
        store: t_store,
        displayField: 'label',
        valueField: 'data',
        triggerAction: 'all',
        allowBlank: true,// 不允许为空
        typeAhead: true,
        mode: 'local',// mode: 'remote',
        emptyText: '请选择',
        editable: false,// 是否允许输入
        selectOnFocus: true,
        blankText: '请选择'// 该项如果没有选择，则提示错误信息
    });
}