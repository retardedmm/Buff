<%--
  Created by IntelliJ IDEA.
  User: hc
  Date: 2022/12/24
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./css/register.css">
    <title>注册</title>
</head>
<script type="text/javascript">

</script>
<body>

    <span class="registerMsg">
        ${ empty requestScope.error ? "":requestScope.error }
    </span>
    <form action="/myWeb/loginServlet" class="register">
        <p>Register</p>
        <input type="text" placeholder="用户名" name="userName"><br>
        <input type="password" placeholder="密码" name="password"><br>
        <input type="submit" class="btn" value="注册">
        <input type="hidden" name="method" value="register" />
    </form>
<form action="/myWeb/index.jsp" >
    <input type="submit"  value="返回">
</form>
</body>
</html>
