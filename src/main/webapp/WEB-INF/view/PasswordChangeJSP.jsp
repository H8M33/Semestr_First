<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form method="post">
    <div class="field">
        <label>
            Password<br>
            <input type="password" name="userPassword" />
        </label>
    </div>
    <button type="submit">Change password</button>
</form>
<c:set var="Error" value="${sessionScope.UpdatePasswordException}"/>
<c:choose>
    <c:when test="${Error!=null}">
        <h4 class="wrong">${Error}</h4>
    </c:when>
</c:choose>
</body>
</html>
