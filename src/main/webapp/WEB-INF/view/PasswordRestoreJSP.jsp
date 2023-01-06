<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
<html>
<head>
    <title>Password restore</title>
</head>
<body>
<form method="post">
    <div class="field">
        <label>
            Email<br>
            <input type="text" name="userEmail"/>
        </label>
    </div>
    <button type="submit">Restore</button>
</form>
<c:set var="Error" value="${sessionScope.PasswordRestoreException}"/>
<c:choose>
    <c:when test="${Error!=null}">
        <h4 class="wrong">${Error}</h4>
    </c:when>
</c:choose>
</body>
</html>
