<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/image.css">
</head>
<body>
<c:set var="List" value="${sessionScope.players_list}"/>
<c:choose>
    <c:when test="${List!=null}">
        <table style="margin-right: 100px; margin-bottom: 30px; float: left">
            <c:forEach var="player" items="${List}">
                <tr>
                    <img class="avatar" src="${request.contextPath}/images/${player.player_id}.png"/>
                </tr>
                <tr>
                    <td><c:out value="${player.name}"/></td>
                </tr>
                <tr>
                    <td><c:out value="${player.cards}"/></td>
                </tr>
                <tr>
                    <td><c:out value="${player.value}"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
</c:choose>
</body>
</html>
