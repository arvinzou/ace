package org.apache.jsp.dynamic.portal.role;

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

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"cn\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />\r\n");
      out.write("<meta charset=\"utf-8\" />\r\n");
      out.write("<meta name=\"viewport\"\r\n");
      out.write("\tcontent=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\" />\r\n");
      out.write("<title>role</title>\r\n");
      out.write("</head>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../common/common.jsp", out, false);
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"page-content\">\r\n");
      out.write("\t\t<div class=\"widget-box\" id=\"widget-box\">\r\n");
      out.write("\t\t\t<div class=\"widget-header\">\r\n");
      out.write("\t\t\t\t<h5 class=\"widget-title smaller\">设置查询条件</h5>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<div class=\"widget-toolbar\"></div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\"widget-body\">\r\n");
      out.write("\t\t\t\t<div class=\"widget-main padding-6\">\r\n");
      out.write("\t\t\t\t\t<form action=\"#\" id=\"fm-search\">\r\n");
      out.write("\t\t\t\t\t\t角色名称： <input name=\"roleName\" type=\"text\"\r\n");
      out.write("\t\t\t\t\t\t\tstyle=\"width: 200px;height:25px\" />\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<button class=\"btn btn-info\" id=\"btn-search\"\r\n");
      out.write("\t\t\t\t\t\t\tauthority=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/role/findRoleList.do\">\r\n");
      out.write("\t\t\t\t\t\t\t <i\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"ace-icon fa fa-search  align-middle bigger-125 icon-on-right\"></i>\r\n");
      out.write("\t\t\t\t\t\t</button>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\t<div class=\"space10\"></div>\r\n");
      out.write("\t\t\t\t\t<div id=\"toolbar\" class=\"toolbar\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<button class=\"btn btn-info\" id=\"btn-view-add\"\r\n");
      out.write("\t\t\t\t\t\t\tauthority=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/role/insertRole.do\">\r\n");
      out.write("\t\t\t\t\t\t\t <i\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right\"></i>\r\n");
      out.write("\t\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t\t\t<button class=\"btn btn-info\" id=\"btn-view-edit\"\r\n");
      out.write("\t\t\t\t\t\t\tauthority=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/role/updateRole.do\">\r\n");
      out.write("\t\t\t\t\t\t\t <i\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"ace-icon fa fa-edit  align-middle bigger-125 icon-on-right\"></i>\r\n");
      out.write("\t\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t\t\t<button class=\"btn btn-purple\" id=\"btn-view-da\"\r\n");
      out.write("\t\t\t\t\t\t\tauthority=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/role/insertRoleResources.do\">\r\n");
      out.write("\t\t\t\t\t\t\t <i\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"ace-icon glyphicon  glyphicon-cog  align-middle bigger-125 icon-on-right\"></i>\r\n");
      out.write("\t\t\t\t\t\t</button>\r\n");
      out.write("\t\t\t\t\t\t<button class=\"btn btn-warning\" id=\"btn-view-del\"\r\n");
      out.write("\t\t\t\t\t\t\tauthority=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/role/deleteRoleByRoleId.do\">\r\n");
      out.write("\t\t\t\t\t\t\t <i\r\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right\"></i>\r\n");
      out.write("\t\t\t\t\t\t</button>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t<table id=\"grid-table\"></table>\r\n");
      out.write("\r\n");
      out.write("\t\t<div id=\"grid-pager\"></div>\r\n");
      out.write("\t\t<div id=\"dialog-message\" class=\"hide\">\r\n");
      out.write("\t\t\t<div class=\"easyui-panel\" style=\"padding:5px;width:350px;height:400px\">        \r\n");
      out.write("\t\t\t\t<ul id=\"tt\" class=\"easyui-tree\" data-options=\"url:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/role/selectRoleResourceTreeList.do?roleId=1',method:'get',animate:true,checkbox:true,lines:false\">\r\n");
      out.write("\t\t\t\t</ul>    \r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div id=\"spinner-preview\"></div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div id=\"dialog-confirm\" class=\"hide\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"alert alert-info bigger-110\">\r\n");
      out.write("\t\t\t\t\t\t\t重新分配权限后，分配此角色的用户将获取新的权限.\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"space-6\"></div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<p class=\"bigger-110 bolder center grey\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-hand-o-right blue bigger-120\"></i> \r\n");
      out.write("\t\t\t\t\t\t\t\t您确定吗?\r\n");
      out.write("\t\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../common/footer-1.jsp", out, false);
      out.write("\r\n");
      out.write("\t<script\r\n");
      out.write("\t\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/portal/js/role/config.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\r\n");
      out.write("\t<script\r\n");
      out.write("\t\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/portal/js/role/model.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\r\n");
      out.write("\t<script\r\n");
      out.write("\t\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/portal/js/role/controller.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\r\n");
      out.write("\t<script\r\n");
      out.write("\t\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/portal/js/role/view.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../common/footer-2.jsp", out, false);
      out.write("\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("window.onresize = function () {\r\n");
      out.write("\tconsole.log('autoWidthJqgrid');\r\n");
      out.write("\t$(cfg.grid_selector).jqGrid('setGridWidth', $(\".page-content\").width());\r\n");
      out.write("\t$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight);\r\n");
      out.write("\tparent.autoWidth();\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write(" \r\n");
      out.write("</body>\r\n");
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
