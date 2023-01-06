<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/image.css">
</head>
<body>
<c:set var="User" value="${sessionScope.viewed_user}"/>
<c:choose>
    <c:when test="${User!=null}">
        <table style="margin-right: 100px; margin-bottom: 30px; float: left">
            <tr>
                <img class="avatar" src="${request.contextPath}/images/${User.id}.png"/>
            </tr>
            <tr>
                <td><c:out value="${User.username}"/></td>
            </tr>
            <tr>
                <td><c:out value="${User.wallet}"/></td>
            </tr>
        </table>
    </c:when>
</c:choose>

</body>
</html>
