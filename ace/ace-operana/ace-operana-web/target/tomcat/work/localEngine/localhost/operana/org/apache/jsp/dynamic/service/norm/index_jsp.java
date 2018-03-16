package org.apache.jsp.dynamic.service.norm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"cn\">\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"/>\n");
      out.write("    <meta charset=\"utf-8\"/>\n");
      out.write("    <meta name=\"viewport\"\n");
      out.write("          content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\"/>\n");
      out.write("    <title>指标</title>\n");
      out.write("</head>\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../common/common.jsp", out, false);
      out.write("\n");
      out.write("<body>\n");
      out.write("<div class=\"page-content\">\n");
      out.write("    <div class=\"widget-box\" id=\"widget-box\">\n");
      out.write("        <div class=\"widget-header\">\n");
      out.write("            <h5 class=\"widget-title smaller\">设置查询条件</h5>\n");
      out.write("\n");
      out.write("            <div class=\"widget-toolbar\"></div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"widget-body\">\n");
      out.write("            <div class=\"widget-main padding-6\">\n");
      out.write("                <form action=\"#\" id=\"fm-search\">\n");
      out.write("                    部门： <input id=\"cc1\" name=\"deptId\"\n");
      out.write("                               class=\"easyui-combotree\"\n");
      out.write("                               data-options=\"url:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${portalPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/system/selectDepartmentTreeList.do',method:'get',animate: true,\n");
      out.write("                lines:false\"\n");
      out.write("                               style='width: 200px; line-height: 25px; height: 25px;'/>\n");
      out.write("\n");
      out.write("                    <a\n");
      out.write("                            href=\"javascript:clearQparams()\">清除</a>\n");
      out.write("                    类别：<input\n");
      out.write("                        class=\"easyui-combobox\" style=\"width: 200px\" name=\"category\"\n");
      out.write("                        data-options=\"\n");
      out.write("                    url:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${portalPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/dict/findListByCategoryId.do?categoryId=86&selected=false',\n");
      out.write("                    method:'get',\n");
      out.write("                    valueField:'code',\n");
      out.write("                    textField:'name',\n");
      out.write("                    panelHeight:'auto'\"/>\n");
      out.write("\n");
      out.write("                    名称： <input name=\"name\" type=\"text\" style=\"width: 200px;\"/>\n");
      out.write("                    <button class=\"btn btn-info\" id=\"btn-search\"\n");
      out.write("                            authority=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/norm/findNormList.do\">\n");
      out.write("                        <i\n");
      out.write("                                class=\"ace-icon fa fa-search  align-middle bigger-125 icon-on-right\"></i>\n");
      out.write("                    </button>\n");
      out.write("\n");
      out.write("\n");
      out.write("                </form>\n");
      out.write("                <div class=\"space10\"></div>\n");
      out.write("                <div id=\"toolbar\" class=\"toolbar\">\n");
      out.write("\n");
      out.write("\n");
      out.write("                    <button class=\"btn btn-info\" id=\"btn-view-add\"\n");
      out.write("                            authority=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/norm/insertNorm.do\">\n");
      out.write("                        <i\n");
      out.write("                                class=\"ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right\"></i>\n");
      out.write("                    </button>\n");
      out.write("                    <button class=\"btn btn-info\" id=\"btn-view-edit\"\n");
      out.write("                            authority=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/norm/updateNorm.do\">\n");
      out.write("                        <i\n");
      out.write("                                class=\"ace-icon fa fa-edit  align-middle bigger-125 icon-on-right\"></i>\n");
      out.write("                    </button>\n");
      out.write("                    <button class=\"btn btn-warning\" id=\"btn-view-del\"\n");
      out.write("                            authority=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/norm/deleteNormByNormId.do\">\n");
      out.write("                        <i\n");
      out.write("                                class=\"ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right\"></i>\n");
      out.write("                    </button>\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <table id=\"grid-table\"></table>\n");
      out.write("\n");
      out.write("    <div id=\"grid-pager\"></div>\n");
      out.write("\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div id=\"dialog-message\" class=\"hide\">\n");
      out.write("    <div id=\"uploader\">\n");
      out.write("        <p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div id=\"dialog-message-file\" class=\"hide\">\n");
      out.write("    <div id=\"load\" class=\"loading\"></div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("<div id=\"dialog-message-view\" class=\"hide\">\n");
      out.write("    <h5 class=\"header-title\">基本信息</h5>\n");
      out.write("    <div class=\"row\" style=\"padding:10px\">\n");
      out.write("\n");
      out.write("        <div class=\"labelItem\">\n");
      out.write("\t\t\t\t<span class=\"labelItemHeader\">\n");
      out.write("\t\t\t\t\t指标编码</span><br>\n");
      out.write("            <span id=\"id\">\n");
      out.write("\t\t\t\t</span>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"labelItem\">\n");
      out.write("\t\t\t\t<span class=\"labelItemHeader\">\n");
      out.write("\t\t\t\t\t指标名称</span><br>\n");
      out.write("            <span id=\"name\">\n");
      out.write("\t\t\t\t\t</span>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"labelItem\">\n");
      out.write("\t\t\t\t<span class=\"labelItemHeader\">\n");
      out.write("\t\t\t\t\t说明</span><br>\n");
      out.write("\n");
      out.write("            <span id=\"remark\">\n");
      out.write("\t\t\t\t\t</span>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"labelItem\">\n");
      out.write("\t\t\t\t<span class=\"labelItemHeader\">\n");
      out.write("\t\t\t\t\t指标分类</span><br>\n");
      out.write("\n");
      out.write("            <span id=\"category\">\n");
      out.write("\t\t\t\t\t</span>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"labelItem\">\n");
      out.write("\t\t\t\t<span class=\"labelItemHeader\">\n");
      out.write("\t\t\t\t\t归属部门</span>\n");
      out.write("            <br>\n");
      out.write("            <span id=\"deptName\">\n");
      out.write("\t\t\t\t</span>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"labelItem\">\n");
      out.write("\t\t\t\t<span class=\"labelItemHeader\">\n");
      out.write("\t\t\t\t\t指标计算类型</span>\n");
      out.write("            <br>\n");
      out.write("            <span id=\"calType\">\n");
      out.write("\t\t\t\t\t</span>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <h5 class=\"header-title\">操作信息</h5>\n");
      out.write("\n");
      out.write("\n");
      out.write("    <div class=\"row\" style=\"padding:10px\">\n");
      out.write("        <div class=\"labelItem\">\n");
      out.write("\t\t\t\t\t<span class=\"labelItemHeader\">\n");
      out.write("\t\t\t\t\t\t创建人姓名</span>\n");
      out.write("            <br>\n");
      out.write("            <span id=\"createUserName\">\n");
      out.write("\t\t\t\t\t</span>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"labelItem\">\n");
      out.write("\t\t\t\t\t\t<span class=\"labelItemHeader\">\n");
      out.write("\t\t\t\t\t\t\t入库日期</span>\n");
      out.write("            <br>\n");
      out.write("            <span id=\"createDate\">\n");
      out.write("\t\t\t\t\t\t</span>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"labelItem\">\n");
      out.write("\n");
      out.write("\t\t\t\t<span class=\"labelItemHeader\">\n");
      out.write("\t\t\t\t\t最后更新时间\n");
      out.write("\t\t\t\t</span>\n");
      out.write("            <br>\n");
      out.write("            <span id=\"lastModifyDate\">\n");
      out.write("\t\t\t\t\t</span>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../common/footer-1.jsp", out, false);
      out.write("\n");
      out.write("<script\n");
      out.write("        src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/service/norm/config.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("<script\n");
      out.write("        src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/service/norm/model.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("<script\n");
      out.write("        src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/service/norm/controller.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("<script\n");
      out.write("        src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/service/norm/view.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../common/footer-2.jsp", out, false);
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("window.onresize = function () {\n");
      out.write("\tconsole.log('autoWidthJqgrid');\n");
      out.write("\t$(cfg.grid_selector).jqGrid('setGridWidth', $(\".page-content\").width());\n");
      out.write("\t$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight);\n");
      out.write("\tparent.autoWidth();\n");
      out.write("}\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${portalPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/common/tableExport/js-xlsx/xlsx.core.min.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${portalPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/common/tableExport/FileSaver/FileSaver.min.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${portalPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/common/tableExport/html2canvas/html2canvas.min.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${portalPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/common/tableExport/tableExport.min.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${portalPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/common/tableExport/export.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
