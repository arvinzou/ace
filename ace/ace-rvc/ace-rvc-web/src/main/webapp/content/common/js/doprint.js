var howprint = '打印';

function loadPrinters() {
    if (typeof(jatoolsPrinter.page_div_prefix) == 'undefined') {
        alert("请按页顶上的黄色提示下载ActiveX控件.如果没有提示请按以下步骤设置ie.\n 工具-> internet 选项->安全->自定义级别,设置 ‘下载未签名的 ActiveX ’为'启用'状态")
        return;
    }
    //打印文档对象
    printers = jatoolsPrinter.printers;   // 打印机列表是一个字符串,用换行符分割
    if (printers) {
        printers = printers.split('\n');
        for (i = 0; i < printers.length; i++)

            printer_list.options[i + 1] = new Option(printers[i], printers[i]);

        printer_list.options[0].selected = true;


    }

}


function printerChanged() {
    if (printer_list.selectedIndex > 0) {
        updatepaper_list(printer_list.value);
    }
}

function printScripts() {
    if (howprint == '打印预览...')
        return '<br>jatoolsPrinter.printPreview(myreport);';

    else if (howprint == '打印...')
        return '<br>jatoolsPrinter.print(myreport,true);';

    else
        return '<br>jatoolsPrinter.print(myreport,false);';
}

function actionChanged() {
    howprint = event.srcElement.value;
}

function updatepaper_list(printer) {
    papers = jatoolsPrinter.papers(printer);
    if (papers) {
        firstOption = paper_list.options[0];
        for (i = papers.length - 1; i > 0; i--)
            paper_list.options[i] = null;

        papers = papers.split('\n');
        for (i = 0; i < papers.length; i++) {
            paper = papers[i].split('\t');
            ps = paper[1].split('x');
            paper_list.options[i + 1] = new Option(paper[0] + ' (' + ps[0] + ' mm x ' + ps[1] + ' mm)', paper[1]);
        }
    }
}
function doPrint() {
    report = buildReport();

    if (howprint == '打印预览...')
        jatoolsPrinter.printPreview(report);
    else if (howprint == '打印...')
        jatoolsPrinter.print(report, true);
    else
        jatoolsPrinter.print(report, false);
}


function buildReport() {
    myreport = new Object();

    settingsRequired = printer_list.selectedIndex > 0 || paper_list.selectedIndex > 0
        || orientation_list.selectedIndex > 0;

    if (settingsRequired)  // 如果需要打印配置,则生成一个print_settings对象保存之
    {
        mysettings = new Object();

        if (printer_list.selectedIndex > 0)
            mysettings.printer = printer_list.value;

        if (paper_list.selectedIndex > 0) {
            paper = paper_list.value;  // "2100mm x 2970mm" 类似这种形式,需要析取出数值
            paper = paper.split('x');
            mysettings.pageWidth = parseInt(paper[0]);
            mysettings.pageHeight = parseInt(paper[1]);
        }

        if (orientation_list.selectedIndex > 0)
            mysettings.orientation = parseInt(orientation_list.value);

        myreport.print_settings = mysettings;
    }
    // 打印配置id
    //myreport.print_settings_id = print_settings_id_text.value;
    // 选择报表,也就是div的前缀
    myreport.page_div_prefix = "report1";

    myreport.documents = document;
    myreport.save_print_settings_after_print = 'true';
    return myreport;
}