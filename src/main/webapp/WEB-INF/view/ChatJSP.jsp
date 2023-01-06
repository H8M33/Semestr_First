<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.js"></script>
<html>
<head>
    <title>Chat</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
</head>
<body onload="startChat()">
<h2>Chat with ${sessionScope.name}</h2>
<div id="block">Autofilled with Ajax</div>
<div class="field">
    <label>
        <input type="text" name="message" id="message"/>
    </label>
</div>
<table>
    <button type="submit" class="sendMessage">Send</button>
    <button type="submit" class="redirect" data-target="/main">Return</button>
</table>
<script>
    function startChat() {
        $.ajax({
            url: '/technical/chat',  // сервлет
            success: function (responseText) {
                // обработка ответа
                $('#block').html(responseText);
                setTimeout(startChat, 500);
            }
        });
    }
</script>
<script>
    $('.sendMessage').on('click', function () {
        $.ajax({
            url: '/technical/message/send',
            data: {
                message: $('#message').val()
            },
            success: function () {
                $('#message').val('');
            }
        })
    });
</script>
<script>
    $('.redirect').on('click', function (event) {
        event.preventDefault();
        const url = $(this).data('target');
        location.replace(url)
    });
</script>
</body>
</html>
