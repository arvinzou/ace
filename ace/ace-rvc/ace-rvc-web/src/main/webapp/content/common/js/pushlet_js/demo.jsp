<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<script type="text/javascript" src="ajax-pushlet-client.js?version=${cfg.version}"></script>

<script type="text/javascript">
    PL.webRoot = '/portal/';
    PL._init();

    PL.joinListen('/message/plushlet');

    function onData(event) {

        alert(event.get("status"));

    }

</script>
</body>
</html>