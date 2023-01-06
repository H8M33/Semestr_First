<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<c:set var="list" value="${sessionScope.UserList}"/>
<form method="post">
    <c:choose>
        <c:when test="${list!=null}">
        <table style="float: left">
            <c:forEach var="User" items="${list}"/>
            <tr>
                <td>
                    <label>
                        <input type="checkbox" name="user${User.id}" value="${User.id}">
                    </label>
                </td>
                <td><c:out value="${User.username}"/></td>
                <td><c:out value="${User.email}"/></td>
                <td><c:out value="${User.status}"/></td>
                <td><c:out value="${User.wallet}"/></td>
            </tr>
        </table>
        </c:when>
    </c:choose>
</form>

</body>
</html>
