<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/add-style.css">
</head>
<body>
<t:myhtml title="${title} user">
        <jsp:attribute name="content">
            <div class="intro">
                <div class="container">
                    <div class="intro__inner" style="height: 100vh;">
                        <div class="intro__add">
                            <h3 class="add__header">${title} user</h3>
                            <form action="<c:url value="/${title}-user"/>" method="post" class="add__form">
                                <input type="hidden" name="person" value="${loginUser}">
                                <div class="form__fields">
                                    <div class="form__login">
                                        <input type="text" class="add__input inp__login" name="login"
                                               placeholder="Login: А-я A-z 0-9" value="${loginUser}"
                                               <c:if test="${param.er == 'login'}">
                                                    style="border: 1px solid red"
                                                </c:if>>
                                        <label class="red__error">
                                            <c:if test="${param.er == 'login'}">Invalid login</c:if>
                                        </label>
                                    </div>
                                    <div class="form__birthday">
                                        <input type="text" class="add__input" name="birthday"
                                               placeholder="Birthday: dd.MM.yyyy" value="${birthdayUser}"
                                                <c:if test="${param.er == 'birthday'}">
                                                       style="border: 1px solid red"
                                                </c:if>>
                                        <label class="red__error">
                                            <c:if test="${param.er == 'birthday'}">Invalid birthday</c:if>
                                        </label>
                                    </div>
                                    <div class="form__password">
                                        <input type="${typePassword}" class="add__input" name="password"
                                               placeholder="Password:" value="${passwordUser}"
                                            <c:if test="${param.er == 'password'}">
                                                   style="border: 1px solid red"
                                            </c:if>>
                                        <label class="red__error">
                                            <c:if test="${param.er == 'password'}">Invalid password</c:if>
                                        </label>
                                    </div>
                                    <div class="form__salary">
                                        <input type="number" class="add__input" name="salary"
                                               placeholder="Salary:" value="${salaryUser}"
                                                <c:if test="${param.er == 'salary'}">
                                                       style="border: 1px solid red"
                                                </c:if>>
                                        <label class="red__error">
                                            <c:if test="${param.er == 'salary'}">Invalid salary</c:if>
                                        </label>
                                    </div>
                                    <div class="form__name">
                                        <input type="text" class="add__input" name="name" placeholder="Name:"
                                               value="${nameUser}"
                                                <c:if test="${param.er == 'name'}">
                                                       style="border: 1px solid red"
                                                </c:if>>
                                        <label class="red__error">
                                            <c:if test="${param.er == 'name'}">Invalid name</c:if>
                                        </label>
                                    </div>
                                    <div class="form__role">
                                        <div class="select__role" onmouseup="selectRoles();">Select roles</div>
                                        <ul class="list__role">
                                            <li class="list__role__item">
                                                <label>
                                                    <input type="checkbox" name="role" value="USER" ${checkedUser}>USER
                                                </label>
                                            </li>
                                            <li class="list__role__item">
                                                <label>
                                                    <input type="checkbox" name="role" value="ADMIN" ${checkedAdmin}>ADMIN
                                                </label>
                                            </li>
                                            <li class="list__role__item">
                                                <label>
                                                    <input type="checkbox" name="role" value="VIEWER" ${checkedViewer}>VIEWER
                                                </label>
                                            </li>
                                            <li class="list__role__item">
                                                <label>
                                                    <input type="checkbox" name="role" value="EDITOR" ${checkedEditor}>EDITOR
                                                </label>
                                            </li>
                                            <li class="list__role__item">
                                                <label>
                                                    <input type="checkbox" name="role" value="CONTRIBUTOR" ${checkedContributor}>CONTRIBUTOR
                                                </label>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div>
                                    <label class="error">
                                        <c:if test="${param.er == 't'}">
                                            You've entered an incorrect data
                                        </c:if>
                                    </label>
                                </div>
                                <div class="form__submit">
                                    <input type="submit" class="add__submit" value="${title}">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </jsp:attribute>
</t:myhtml>
</body>
<script src="resources/js/script.js"></script>
</html>

