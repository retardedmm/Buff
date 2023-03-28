<%--
  Created by IntelliJ IDEA.
  User: hc
  Date: 2022/12/24
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="./css/table.css">
    <title>购物车</title>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<h1 align="center">我的购物车</h1>
<form action="/myWeb/loginServlet">
    <input type="hidden" name="method" value="showCommodity">
    <input type="submit" value="返回主页面">
</form>
<form action="/myWeb/loginServlet">
    <input type="hidden" name="method" value="exit">
    <input type="submit" value="退出登录">
</form>
<table border="1px" align="center">
    <tr>
        <th>商品编号</th>
        <th>商品图片</th>
        <th>商品名称</th>
        <th>商品价格</th>
        <th>踢出购物车</th>
    </tr>
    <%
        int sum=0;
        int j=1;
        request.setAttribute("j",j);
    %>
    <c:forEach items="${requestScope.myCommodities}" var="item" varStatus="status">
        <tr>
            <td>${item.commodityId}</td>
            <c:forEach begin="1" end="11" var="i">
                <c:if test="${item.commodityId == i }">

                    <%
                        String path = "./static/" + j + ".png";
                        request.setAttribute("path", path);
                    %>
                    <td><img src="${requestScope.path}" width="250px" height="150px"></td>
                </c:if>
                <%
                     j++;
                    request.setAttribute("j",j);
                %>
            </c:forEach>
            <%
                j=1;
                request.setAttribute("j",j);
            %>

            
            <td>${item.commodityName}</td>
            <td>${item.commodityPrice}</td>

            <td>
                <form action="/myWeb/loginServlet">
                    <input type="hidden" name="method" value="deleteCommodity">
                    <input type="hidden" name="deleteCommodityId" value=${item.id}>
                    <input type="submit" value="踢除" onclick="alert('删除成功')">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
