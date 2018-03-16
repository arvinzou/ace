package org.apache.jsp.dynamic.service.normCfg;

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
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />\n");
      out.write("<meta charset=\"utf-8\" />\n");
      out.write("<meta name=\"viewport\"\n");
      out.write("\tcontent=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\" />\n");
      out.write("<title>指标</title>\n");
      out.write("</head>\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../common/common.jsp", out, false);
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("<body>\n");
      out.write("\t<div class=\"page-content\">\n");
      out.write("\t\t<div class=\"widget-box\" id=\"widget-box\">\n");
      out.write("\t\t\t<div class=\"widget-header\">\n");
      out.write("\t\t\t\t<h5 class=\"widget-title smaller\">设置查询条件</h5>\n");
      out.write("\n");
      out.write("\t\t\t\t<div class=\"widget-toolbar\"></div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t<div class=\"widget-body\">\n");
      out.write("\t\t\t\t<div class=\"widget-main padding-6\">\n");
      out.write("\t\t\t\t\t<form action=\"#\" id=\"fm-search\">\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t部门： <input id=\"cc1\" name=\"deptId\"\n");
      out.write("\t\t\t\t\t\t\t\t   class=\"easyui-combotree\"\n");
      out.write("\t\t\t\t\t\t\t\t   data-options=\"url:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${portalPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/system/selectDepartmentTreeList.do',method:'get',animate: true,\n");
      out.write("                lines:false\"\n");
      out.write("\t\t\t\t\t\t\t\t   style='width: 200px; line-height: 25px; height: 25px;'/>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t<a\n");
      out.write("\t\t\t\t\t\t\t\thref=\"javascript:clearQparams()\">清除</a>\n");
      out.write("\t\t\t\t\t\t类别：<input\n");
      out.write("\t\t\t\t\t\t\tclass=\"easyui-combobox\" style=\"width: 100px\" name=\"category\"\n");
      out.write("\t\t\t\t\t\t\tdata-options=\"\n");
      out.write("                    url:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${portalPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/dict/findListByCategoryId.do?categoryId=86&selected=false',\n");
      out.write("                    method:'get',\n");
      out.write("                    valueField:'code',\n");
      out.write("                    textField:'name',\n");
      out.write("                    panelHeight:'auto'\"/>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t年度：<input\n");
      out.write("\t\t\t\t\t\t\tclass=\"easyui-combobox\" style=\"width: 100px\" name=\"year\"\n");
      out.write("\t\t\t\t\t\t\tdata-options=\"\n");
      out.write("                    url:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${portalPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/dict/findListByCategoryId.do?categoryId=year&selected=false',\n");
      out.write("                    method:'get',\n");
      out.write("                    valueField:'code',\n");
      out.write("                    textField:'name',\n");
      out.write("                    panelHeight:'auto'\"/>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t名称： <input name=\"name\" type=\"text\"  style=\"width: 100px;\" />\n");
      out.write("\t\t\t\t\t\t<button class=\"btn btn-info\" id=\"btn-search\"\n");
      out.write("\t\t\t\t\t\t\tauthority=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/normCfg/findNormCfgList.do\">\n");
      out.write("\t\t\t\t\t\t\t <i\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"ace-icon fa fa-search  align-middle bigger-125 icon-on-right\"></i>\n");
      out.write("\t\t\t\t\t\t</button>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t<div class=\"space10\"></div>\n");
      out.write("\t\t\t\t\t<div id=\"toolbar\" class=\"toolbar\">\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t<button class=\"btn btn-info\" id=\"btn-view-edit\"\n");
      out.write("\t\t\t\t\t\t\tauthority=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/normCfg/updateNormCfg.do\">\n");
      out.write("\t\t\t\t\t\t\t <i\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"ace-icon fa fa-cog  align-middle bigger-125 icon-on-right\"></i>\n");
      out.write("\t\t\t\t\t\t</button>\n");
      out.write("\t\t\t\t\t\t<button class=\"btn btn-warning\" id=\"btn-view-del\"\n");
      out.write("\t\t\t\t\t\t\tauthority=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/normCfg/deleteNormCfgByNormCfgId.do\">\n");
      out.write("\t\t\t\t\t\t\t <i\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right\"></i>\n");
      out.write("\t\t\t\t\t\t</button>\n");
      out.write("\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\n");
      out.write("\t\t<table id=\"grid-table\"></table>\n");
      out.write("\n");
      out.write("\t\t<div id=\"grid-pager\"></div>\n");
      out.write("\t\t\n");
      out.write("\t\t\n");
      out.write("\t</div>\n");
      out.write("\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../common/footer-1.jsp", out, false);
      out.write("\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/service/normCfg/config.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/service/normCfg/model.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/service/normCfg/controller.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("\t<script\n");
      out.write("\t\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/service/normCfg/view.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../common/footer-2.jsp", out, false);
      out.write("\n");
      out.write("\t<script type=\"text/javascript\">\n");
      out.write("window.onresize = function () {\n");
      out.write("\tconsole.log('autoWidthJqgrid');\n");
      out.write("\t$(cfg.grid_selector).jqGrid('setGridWidth', $(\".page-content\").width());\n");
      out.write("\t$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight);\n");
      out.write("\tparent.autoWidth();\n");
      out.write("}\n");
      out.write("</script>\n");
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
