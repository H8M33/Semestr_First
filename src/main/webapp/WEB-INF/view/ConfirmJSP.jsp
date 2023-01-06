<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<h3>Please enter the code, that we sent to your email</h3>
<form method="post">
    <input type="text" name="code"/>
    <button type="submit">Confirm</button>
</form>
</body>
</html>
