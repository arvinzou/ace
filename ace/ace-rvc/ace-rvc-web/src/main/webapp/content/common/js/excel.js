//导出excel
function exportExcel(DivID) {

    //先声明Excel插件、Excel工作簿等对像
    var jXls, myWorkbook, myWorksheet;

    try {
        //插件初始化失败时作出提示
        jXls = new ActiveXObject('Excel.Application');
    } catch (e) {
        alert("无法启动Excel!\n\n如果您确信您的电脑中已经安装了Excel，" + "那么请调整IE的安全级别。\n\n具体操作：\n\n" + "工具 → Internet选项 → 安全 → 自定义级别 → 对没有标记为安全的ActiveX进行初始化和脚本运行 → 启用");
        return false;
    }

    //不显示警告
    jXls.DisplayAlerts = false;

    //创建AX对象excel
    myWorkbook = jXls.Workbooks.Add();
    //myWorkbook.Worksheets(3).Delete();//删除第3个标签页(可不做)
    //myWorkbook.Worksheets(2).Delete();//删除第2个标签页(可不做)

    //获取DOM对像
    var curTb = document.getElementById(DivID);

    //获取当前活动的工作薄(即第一个)
    myWorksheet = myWorkbook.ActiveSheet;

    //设置工作薄名称
    myWorksheet.name = "NP统计";

    //获取BODY文本范围
    var sel = document.body.createTextRange();

    //将文本范围移动至DIV处
    sel.moveToElementText(curTb);

    //选中Range
    sel.select();

    //清空剪贴板
    window.clipboardData.setData('text', '');

    //将文本范围的内容拷贝至剪贴板
    sel.execCommand("Copy");

    //将内容粘贴至工作簿
    myWorksheet.Paste();

    //打开工作簿
    jXls.Visible = true;

    //清空剪贴板
    window.clipboardData.setData('text', '');
    jXls = null;//释放对像
    myWorkbook = null;//释放对像
    myWorksheet = null;//释放对像
}

