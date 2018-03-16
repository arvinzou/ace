package org.apache.jsp.dynamic.service.tpaCommon;

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
      out.write("    <title>任务</title>\n");
      out.write("</head>\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../common/common.jsp", out, false);
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("var meetingId='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.meetingId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\n");
      out.write("var topicId='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.topicId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\n");
      out.write("var normId='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.normId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("';\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("<body>\n");
      out.write("<div class=\"page-content\">\n");
      out.write("    <div>\n");
      out.write("        <div class=\"div-left header-title-custom\">任务</div>\n");
      out.write("        <div class=\"div-right header-title-custom\">\n");
      out.write("            <div style=\"text-align:right\"><a class=\"blue\" href=\"javascript:add()\" data-rel=\"tooltip\" data-placement=\"top\"\n");
      out.write("                                             title=\"添加\"><i class=\"ace-icon fa fa-plus-square\"></i></a>\n");
      out.write("\n");
      out.write("                <a class=\"blue\" href=\"javascript:reload()\" data-rel=\"tooltip\" data-placement=\"top\" title=\"刷新\"><i\n");
      out.write("                        class=\"ace-icon glyphicon glyphicon-refresh\"></i></a>\n");
      out.write("\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <div class=\"toolbar\">\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("        <table id=\"grid-table\"></table>\n");
      out.write("\n");
      out.write("    <div id=\"grid-pager\"></div>\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../common/footer-1.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("<script\n");
      out.write("        src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${portalPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/common/js/dict_");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${SESSION_USERPROP_KEY.activeSyId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(".js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("<script\n");
      out.write("        src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/service/tpaCommon/config.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("<script\n");
      out.write("        src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/service/tpaCommon/model.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("<script\n");
      out.write("        src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/service/tpaCommon/controller.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("<script\n");
      out.write("        src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/content/service/tpaCommon/view.js?version=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cfg.version}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></script>\n");
      out.write("\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../common/footer-2.jsp", out, false);
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("window.onresize = function () {\n");
      out.write("\tconsole.log('autoWidthJqgrid');\n");
      out.write("\t$(cfg.grid_selector).jqGrid('setGridWidth', $(\".page-content\").width());\n");
      out.write("\t$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight+100);\n");
      out.write("\n");
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
      out.write("<div id=\"tableExport\"></div>\n");
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
