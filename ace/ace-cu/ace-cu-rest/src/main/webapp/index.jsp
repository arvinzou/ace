<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>demo</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>

<script>
    jQuery(function ($) {
        $.ajax({
            type: 'post',
            url: '${pageContext.request.contextPath }/v1/token/requestJson',
            contentType: 'application/json;charset=utf-8',
            data: '[{"name":"王五","createTime":"2018-03-08 12:01:05"},{"name":"王五","createTime":"2018-03-08"}]',
            success: function (data) {
                console.log(data);
            }
        });
    });
</script>
</body>
</html>