 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
 <!DOCTYPE html>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/admin-style.css">
</head>
<body>
<t:myhtml title="Main">
        <jsp:attribute name="content">
            <div class="intro">
                <div class="container">
                    <div class="intro__inner">
                        <div class="intro__content">
                            <div class="content__welcome">Welcome<br> to Web App 2</div>
<%--                            <div style="text-align: center; font-size: 30px; font-weight: bold;">List of users</div>--%>
<%--                            <div class="list__users">--%>
<%--                                <ol>--%>
<%--                            <c:forEach var="user" varStatus="userLoop" items="${users}">--%>
<%--                                <li>--%>
<%--                                    <c:if test="${userLoop.index < 2}">--%>
<%--                                        <p>Login: ${user.login}</p>--%>
<%--                                        <p>Password: ${user.password}</p>--%>
<%--                                        <p>Email: ${user.email}</p>--%>
<%--                                        <p>Surname: ${user.surname}</p>--%>
<%--                                        <p>Name: ${user.name}</p>--%>
<%--                                        <p>Patronymic: ${user.patronymic}</p>--%>
<%--                                        <p>Birthday:--%>
<%--                                            <fmt:parseDate var="parsedBirthday" pattern="yyyy-MM-dd"--%>
<%--                                                           value="${user.birthday}"/>--%>
<%--                                            <fmt:formatDate pattern="dd.MM.yyyy" value="${parsedBirthday}"/>--%>
<%--                                        </p>--%>
<%--                                        <p>Role: ${user.role}</p>--%>
<%--                                    </c:if>--%>
<%--                                    <c:if test="${userLoop.index >= 2}">--%>
<%--                                        <c:choose>--%>
<%--                                            <c:when test="${userLoop.index == 3}">--%>
<%--                                                <p>Name: ${user.name}</p>--%>
<%--                                                <p>Password: ${user.password}</p>--%>
<%--                                                <p>Login: ${user.login}</p>--%>
<%--                                                <c:out value="половина"/>--%>
<%--                                            </c:when>--%>
<%--                                            <c:when test="${userLoop.index == 4}">--%>
<%--                                                <p><b>Login: ${user.login}</b></p>--%>
<%--                                                <p><b>Password: ${user.password}</b></p>--%>
<%--                                                <p><b>Name: ${user.name}</b></p>--%>
<%--                                            </c:when>--%>
<%--                                            <c:otherwise>--%>
<%--                                                <p>Name: ${user.name}</p>--%>
<%--                                                <p>Password: ${user.password}</p>--%>
<%--                                                <p>Login: ${user.login}</p>--%>
<%--                                            </c:otherwise>--%>
<%--                                        </c:choose>--%>
<%--                                    </c:if>--%>
<%--                                </li>--%>
<%--                            </c:forEach>--%>
<%--                                </ol>--%>
<%--                            </div>--%>
                        </div>
                    </div>
                </div>
            </div>
        </jsp:attribute>
</t:myhtml>
</body>
</html>

