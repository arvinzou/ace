$(function () {
    jQuery("#Ttable").jqGrid({
        prmNames: {
            totalRecord: "totalRecord",
            start: "start",
            page: "page",
            rows: "limit",

        },
        datatype: "json",
        url: "/woc/traffic/findTrafficList",
        jsonReader: {
            root: "rows",
            page: "page",
            total: "totalPages",
            records: "total",
            id: 'deviceId',
        },
        postData: {
            deviceStatus: 1,
        },
        multiselect: true,
        // multikey: "ctrlKey",
        multiboxonly: true,
        rownumbers: true,
        viewrecords: true,
        rowNum: 20,
        //rowList: [10, 20, 30, 50],
        height: 400,
        width: 470,
        colNames: ['时间', '车牌号', '轴数', '总质量', '超限质量', '超限率', '速度'],
        colModel: [
            {
                name: 'inspectTime',
                hidden: true,
                width: 90
            },
            {
                name: 'plateNo',
                width: 100
            },
            {
                name: 'axleCount',
                width: 100
            },
            {
                name: 'totalMass',
                width: 100
            }, {
                name: 'overMass',
                width: 100
            }, {
                name: 'overRate',
                width: 100
            }, {
                name: 'speed',
                width: 100
            }
        ],
        caption: '可分配',
        loadComplete: function () {
            $("#alldevice-grid-table").jqGrid('setGridWidth', $("#alldevice-grid-table").width());
        }
    });
});