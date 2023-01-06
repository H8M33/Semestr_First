<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<table>
    <label>
        <input type="number" id="idToEdit">
        <button type="submit" class="redirectToEdit" data-target="/main/games/blackjack/game?id=">Enter game</button>
    </label>
    <form method="post">
        <label>
            <button type="submit">Create new game</button>
        </label>
    </form>
</table>
<script>
    $('.redirectToEdit').on('click', function (event) {
        event.preventDefault();
        const url = $(this).data('target') + $("#idToEdit").val();
        location.replace(url)
    });
</script>
</body>
</html>
