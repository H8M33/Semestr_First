<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Main</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<button type="submit" class="redirect" data-target="/welcome/login">Sign in</button>
<button type="submit" class="redirect" data-target="/welcome/register">Sign up</button>


<script>
    $('.redirect').on('click', function (event) {
        event.preventDefault();
        const url = $(this).data('target');
        location.replace(url)
    });
</script>
</body>
</html>
