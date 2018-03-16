package org.apache.jsp.dynamic.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class error_002d404_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<title>404</title>\r\n");
      out.write("</head>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "common.jsp", out, false);
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("\t\r\n");
      out.write("\t\t\t\t<div class=\"page-content\">\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t<!-- /section:settings.box -->\r\n");
      out.write("\t\t\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"col-xs-12\">\r\n");
      out.write("\t\t\t\t\t\t\t<!-- PAGE CONTENT BEGINS -->\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<!-- #section:pages/error -->\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"error-container\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"well\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<h1 class=\"grey lighter smaller\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<span class=\"blue bigger-125\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-sitemap\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t404\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tPage Not Found\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</h1>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<hr />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<h3 class=\"lighter smaller\">We looked everywhere but we couldn't find it!</h3>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<form class=\"form-search\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<span class=\"input-icon align-middle\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-search\"></i>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" class=\"search-query\" placeholder=\"Give it a search...\" />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</span>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<button class=\"btn btn-sm\" type=\"button\">Go!</button>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</form>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"space\"></div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<h4 class=\"smaller\">Try one of the following:</h4>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<ul class=\"list-unstyled spaced inline bigger-110 margin-15\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-hand-o-right blue\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tRe-check the url for typos\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</li>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-hand-o-right blue\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tRead the faq\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</li>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-hand-o-right blue\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tTell us about it\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<hr />\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"space\"></div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"center\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<a href=\"javascript:history.back()\" class=\"btn btn-grey\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-arrow-left\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tGo Back\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</a>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<a href=\"#\" class=\"btn btn-primary\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<i class=\"ace-icon fa fa-tachometer\"></i>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tDashboard\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<!-- /section:pages/error -->\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<!-- PAGE CONTENT ENDS -->\r\n");
      out.write("\t\t\t\t\t\t</div><!-- /.col -->\r\n");
      out.write("\t\t\t\t\t</div><!-- /.row -->\r\n");
      out.write("\t\t\t\t</div><!-- /.page-content -->\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer-1.jsp", out, false);
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer-2.jsp", out, false);
      out.write("\r\n");
      out.write("\t\r\n");
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
