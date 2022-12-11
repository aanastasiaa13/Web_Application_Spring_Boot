<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/admin-style.css">
    <link type="text/css" rel="stylesheet" href="css/admin-users-style.css">
</head>
<body>
<t:myhtml title="Users">
        <jsp:attribute name="content">
            <div class="intro">
                <div class="container">
                    <div class="intro__inner">
                        <div class="list__header">
                            <img src="images/plus.png" class="icon__plus"
                                 onclick="location.href='<c:url value="/Add-user?er=f"/>'">
                            List of users
                        </div>
                        <div class="list__users">
                            <table class="table__users">
                                <tr>
                                    <th>Id</th>
                                    <th>Login</th>
                                    <th>Password</th>
                                    <th>Name</th>
                                    <th>Birthday</th>
                                    <th>Age</th>
                                    <th>Salary</th>
                                    <th>Roles</th>
                                    <th>Action</th>
                                </tr>
                                <c:forEach var="user" items="${users}">
                                    <tr>
                                        <td>${user.id}</td>
                                        <td>${user.login}</td>
                                        <td>${user.password}</td>
                                        <td>${user.name}</td>
                                        <td>
                                            <fmt:parseDate var="parsedBirthday" pattern="yyyy-MM-dd"
                                                           value="${user.birthday}"/>
                                            <fmt:formatDate pattern="dd.MM.yyyy" value="${parsedBirthday}"/>
                                        </td>
                                        <td>${user.age}</td>
                                        <td>${user.salary}</td>
                                        <td>
                                            <c:forEach var="role" items="${user.role}">
                                                <c:out value="${role.role_name}"/>
                                            </c:forEach>
                                        </td>
                                        <td><img src="images/edit.png" class="table__icon"
                                                 onclick='location.href="<c:url
                                                         value="/Edit-user?person=${user.login}&er=f"/>"'><img
                                                src="images/delete.png" class="table__icon"
                                                onclick='location.href="<c:url
                                                        value="/delete-user?person=${user.login}"/>"'></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </jsp:attribute>
</t:myhtml>
</body>
</html>
