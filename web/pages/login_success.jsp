<%--
  Created by IntelliJ IDEA.
  User: hc
  Date: 2022/12/24
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="./css/login_success.css">
    <title>首页</title>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<h1 align="center" style="font-size: 60px">热门商品</h1>
<form action="/myWeb/loginServlet" class="login">
    <input type="hidden" name="method" value="myCommodity">
    <input type="submit" value="我的购物车">
</form>
<form action="/myWeb/loginServlet">
    <input type="hidden" name="method" value="exit">
    <input type="submit" value="退出登录">
</form>
<br>

<%
    int i = 1;
    int j=1;
    request.setAttribute("i",i);
    request.setAttribute("j",j);
%>

<c:forEach items="${requestScope.commodities}" varStatus="status" var="item">
    <c:if test="${requestScope.i <=5}">
        <%
            String path = "./static/" + i + ".png";
            request.setAttribute("path", path);
        %>
        <img src="${requestScope.path}" width="19.7%" height="20%">
        <%
            i++;
            request.setAttribute("i",i);
        %>
    </c:if>
</c:forEach>


<br>
<c:forEach items="${requestScope.commodities}" varStatus="status" var="item">
    <c:if test="${requestScope.j <=5}">
       <div style="float: left; width: 19.7%;height: 20%" align="center">

                   <form action="/myWeb/loginServlet">
                       <input type="hidden" name="method" value="addCommodity">
                       <input type="hidden" name="addCommodityId" value=${item.commodityId}>
                       <input type="submit" value="${item.commodityName}" onclick="alert('添加成功')">
                   </form>
       </div>
        <%
            j++;
            request.setAttribute("j",j);
        %>
    </c:if>
</c:forEach>
<br>


<c:forEach items="${requestScope.commodities}" varStatus="status" var="item">
    <c:if test="${requestScope.i >5 && requestScope.i<=10}">
        <%
            String path = "./static/" + i + ".png";
            request.setAttribute("path", path);
        %>
        <img src="${requestScope.path}" width="19.7%" height="20%">
        <%
            i++;
            request.setAttribute("i",i);
        %>
    </c:if>
</c:forEach>
<br>
<%
    j=1;
    request.setAttribute("j",j);
%>
<c:forEach items="${requestScope.commodities}" varStatus="status" var="item">
    <c:if test="${requestScope.j >5  && requestScope.j<=10}">
        <div style="float: left; width: 19.7%;height: 20%;" align="center" >

                    <form action="/myWeb/loginServlet">
                        <input type="hidden" name="method" value="addCommodity">
                        <input type="hidden" name="addCommodityId" value=${item.commodityId}>
                        <input type="submit" value="${item.commodityName}" onclick="alert('添加成功')">
                    </form>
        </div>
    </c:if>
    <%
        j++;
        request.setAttribute("j",j);
    %>
</c:forEach>

</body>
</html>
