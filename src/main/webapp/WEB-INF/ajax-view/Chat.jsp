<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<c:set var="list" value="${sessionScope.allMessages}"/>
<c:choose>
  <c:when test="${list!=null}">
    <table style="margin-right: 100px; margin-bottom: 30px; float: left">
      <c:forEach var="Message" items="${list}">
        <tr>
          <td><c:out value="${Message.author}"/></td>
        </tr>
        <tr>
          <td><c:out value="${Message.text}"/></td>
        </tr>
      </c:forEach>
    </table>
  </c:when>
</c:choose>
</body>
</html>
