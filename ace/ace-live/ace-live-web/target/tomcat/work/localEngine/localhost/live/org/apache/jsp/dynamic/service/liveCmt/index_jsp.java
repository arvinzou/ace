package org.apache.jsp.dynamic.service.liveCmt;

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
      out.write("    <title>图文直播</title>\n");
      out.write("</head>\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../common/common.jsp", out, false);
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("\n");
      out.write("</script>\n");
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
      out.write("\n");
      out.write("                    类别：<input\n");
      out.write("                        class=\"easyui-combobox\" style=\"width: 200px\" name=\"status\"\n");
      out.write("                        data-options=\"\n");
      out.write("                    url:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${portalPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/dict/findListByCategoryId.do?categoryId=113&selected=false',\n");
      out.write("                    method:'get',\n");
      out.write("                    valueField:'code',\n");
      out.write("                    textField:'name',\n");
      out.write("                    panelHeight:'auto'\">\n");
      out.write("                    RID：<input name=\"rptId\" type=\"text\" style=\"width: 200px;\"/>\n");
      out.write("\n");
      out.write("                    UID： <input name=\"uid\" type=\"text\" style=\"width: 200px;\"/>\n");
      out.write("                    <button class=\"btn btn-info\" id=\"btn-search\"\n");
      out.write("                            authority=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/liveCmt/findLiveCmtList.do\">\n");
      out.write("                        <i class=\"ace-icon fa fa-search  align-middle bigger-125 icon-on-right\"></i>\n");
      out.write("                    </button>\n");
      out.write("                </form>\n");
      out.write("                <div class=\"space10\"></div>\n");
      out.write("                <div id=\"toolbar\" class=\"toolbar\">\n");
      out.write("\n");
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
      out.write("<div id=\"dialog-message\" class=\"hide\">\n");
      out.write("    <div id=\"uploader\">\n");
      out.write("        <p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("<div id=\"dialog-message-file\" class=\"hide\">\n");
      out.write("    <div id=\"load\" class=\"loading\"></div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div id=\"dialog-message-view\" class=\"hide\">\n");
      out.write("    <div class=\"row\" style=\"padding:10px\" id=\"content\">\n");
      out.write("\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("</div>\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../common/footer-1.jsp", out, false);
      out.write("\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/common/js/reconnecting-websocket.js\"></script>\n");
      out.write("<script\n");
      out.write("        src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/service/liveCmt/config.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("<script\n");
      out.write("        src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/service/liveCmt/model.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("<script\n");
      out.write("        src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/service/liveCmt/controller.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("<script\n");
      out.write("        src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/service/liveCmt/view.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../common/footer-2.jsp", out, false);
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("    window.onresize = function () {\n");
      out.write("        console.log('autoWidthJqgrid');\n");
      out.write("        $(cfg.grid_selector).jqGrid('setGridWidth', $(\".page-content\").width());\n");
      out.write("        $(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight);\n");
      out.write("        parent.autoWidth();\n");
      out.write("    }\n");
      out.write("\n");
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
