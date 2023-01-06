<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<h2>User to update: ${sessionScope.UserToUpdate}</h2>
<form method="post">
    <div class="field">
        <label>
            New nickname<br>
            <input type="text" name="username"/>
            <input type="hidden" name="action" value="UpdateUsername">
        </label>
        <button type="submit">Update username</button>
    </div>
</form>
<form method="post">
    <div class="field">
        <label>
            <input type="file" name="file" accept="image/png, image/gif, image/jpeg" />
            <input type="hidden" name="action" value="UpdateImage">
            <button type="submit">Update avatar</button>
        </label>
    </div>
</form>
</body>
</html>
