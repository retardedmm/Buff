<%--
  Created by IntelliJ IDEA.
  User: hc
  Date: 2022/12/24
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="./css/login.css">
  <title>登录</title>
</head>
<script type="text/javascript" src="./script/jquery-1.7.2.js"></script>

<script type="text/javascript">
  $(function () {
    $("#code_img").click(function () {
      this.src="http://localhost:8080/myWeb/kaptcha.jpg";
    })
    if(!${ empty requestScope.msg}){
      alert("${requestScope.msg}");
    }


  })
</script>
<body>

<form action="/myWeb/loginServlet"  class="login">

  <p>Login</p>
  <span class="errorMsg">

</span>
  <input type="text"  placeholder="用户名" name="userName"><br>
  <input type="password" placeholder="密码" name="password"><br>
  <input type="text" id="code" placeholder="验证码" name="code">
  <img id="code_img" src="http://localhost:8080/myWeb/kaptcha.jpg" style="width: 100px;height: 25px">
  <br>
  <input type="submit" class="btn" value="登  录">
  <a href="/myWeb/loginServlet?method=register" >注册</a>
  <input type="hidden" name="method" value="login" />
</form>

</body>
</html>
