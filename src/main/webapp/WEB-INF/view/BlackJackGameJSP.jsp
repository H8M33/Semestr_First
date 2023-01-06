<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
</head>
<body onload="startGame()">
<p/>
    <div id="block">Autofilled with Ajax</div>
<div class="field">
    <label>
        <input type="hidden" name="message" id="message" value="${sessionScope.game_id}"/>
    </label>
</div>
<button type="submit" class="take_card">Take card</button>
<button type="submit" class="stop">Stop</button>
<script>
    function startGame() {
        $.ajax({
            url: '/technical/blackjack',  // сервлет
            data: {
                message: $('#message').val()
            },
            success: function (responseText) {
                // обработка ответа
                $('#block').html(responseText);
                setTimeout(startGame, 500);
            }
        });
    }
</script>
<script>
    $('.take_card').on('click', function () {
        $.ajax({
            url: '/technical/blackjack/get',
            data: {
                game_id: $('#message').val()
            }
        })
    });
</script>
<script>
    $('.stop').on('click', function () {
        $.ajax({
            url: '/technical/blackjack/stop',
            data: {
                game_id: $('#message').val()
            }
        })
    });
</script>
</body>
</html>
