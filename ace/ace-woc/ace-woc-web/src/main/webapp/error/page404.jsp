<%--
  Created by IntelliJ IDEA.
  User: HuaCai003
  Date: 2018/3/8
  Time: 9:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>404-没有找到网页</title>
    <style type="text/css">
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        .wrap {
            width: 100%;
            margin: 0 auto;
        }

        .logo {
            margin: 60px auto 50px auto;
        }

        .logo img {
            max-width: 100%;
        }

        p a {
            color: #eee;
            font-size: 13px;
            margin-left: 15px;
            padding: 5px;
            background: #FF3366;
            text-decoration: none;
        }

        p a:hover {
            color: #fff;
        }


    </style>
</head>
<body>
<div class="wrap">
    <div class="logo">
        <img src="./images/404.png" alt=""/>
        <p><a href="#" onClick="javascript:history.back(-1)">返回前页</a></p>
    </div>
</div>

</body>
</html>