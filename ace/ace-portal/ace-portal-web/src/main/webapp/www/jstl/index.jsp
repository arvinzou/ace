<%@ page language="java" contentType="text/html;  charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
 List list=new ArrayList();
 for(int i=0;i<10;i++){
    Map o=new HashMap();
    o.put("id",i);
    o.put("date",new Date().getTime());
    list.add(o);
 }
 request.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<title>jstl</title>

</head>
<body>
<h3>日期格式化:</h3>
<c:set var="now" value="<%=new java.util.Date()%>" />

<p>日期格式化 (7): <fmt:formatDate pattern="yyyy-MM-dd"
                              value="${now}" /></p>
<table border="1">

    <c:forEach var="o" items="${list}" varStatus="status">
        <tr>
            <td>
                ${status.index}
            </td>
            <td>
                ${o.date}
            </td>
            <td>
                ${status.count}
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>