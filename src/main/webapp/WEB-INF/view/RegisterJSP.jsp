<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form method="post" enctype="multipart/form-data">
    <div class="field">
        <label>
            Nickname<br>
            <input type="text" name="userName"/>
        </label>
    </div>
    <div class="field">
        <label>
            Password<br>
            <input type="password" name="userPassword" />
        </label>
    </div>
    <div class="field">
        <label>
            Email<br>
            <input type="text" name="userEmail"/>
        </label>
    </div>
    <div class="field">
        <label>
            Avatar<br>
            <input type="file" name="file" accept="image/png, image/gif, image/jpeg" />
        </label>
    </div>
    <button type="submit">Sign Up</button>
</form>
<c:set var="Error" value="${sessionScope.RegisterException}"/>
<c:choose>
    <c:when test="${Error!=null}">
        <h4 class="wrong">${Error}</h4>
    </c:when>
</c:choose>
</body>
</html>
