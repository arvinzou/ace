var panel, pages, pageA;
var me = {
    init: function () {
        console.log("init");
        panel = $("#P040201");
        pages = panel.find(".page");
        pageA = pages.filter("[data-page='noticeMessage']");
        list = {
            LIST: [
                {
                    "NOTICE_ID": "1",
                    "NOTICE_TITLE": "贷款",
                    "NOTICE_CONTENT": "关于移动CRM的开发申请工作贷款流程审批关于移动CRM的开发申请工作贷款流程审批，关于移动CRM的开发申请工作贷款流程审批，关于移动CRM的开发申请工作贷款流程审批，关于移动CRM的开发申请工作贷款流程审批。",
                    "NOTICE_TIME": "2018-1-25 9:00",
                    "NOTICE_READ": "0"
                },
                {
                    "NOTICE_ID": "2",
                    "NOTICE_TITLE": "jquery data",
                    "NOTICE_CONTENT": "在元素上存放或读取数据,返回jQuery对象。当参数只有一个key的时候，为读取该jQuery对象对应DOM中存储的key对应的值，值得注意的是，如果浏览器支持HTML5，同样可以读取该DOM中使用 data-[key] = [value] 所存储的值。参见最后一个示例。当参数为两个时，为像该jQuery对象对应的DOM中存储key-value键值对的数据。\
            如果jQuery集合指向多个元素，那将在所有元素上设置对应数据。 这个函数不用建立一个新的expando，就能在一个元素上存放任何格式的数据，而不仅仅是字符串。\
            V1.4.3 新增用法, data(obj) 可传入key-value形式的数据。",
                    "NOTICE_TIME": "2018-1-25 9:00",
                    "NOTICE_READ": "0"
                }
            ]
        };
        me.initPageA();
    },
    initPageA: function () {

        var tpl = document.getElementById('tpl2').innerHTML;
        var data = {departmentId: "deptId111", creditCode: "creditCode2222"};
        var tplHtml = juicer(tpl, data);
        pageA.html(tplHtml);

        // $.get("tpl_list.html",function(tpl){
        // });
    }
};