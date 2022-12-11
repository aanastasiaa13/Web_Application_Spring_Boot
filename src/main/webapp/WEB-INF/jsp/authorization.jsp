<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/authorization-style.css">
</head>
<body>
<t:myhtml title="Sign Up">
        <jsp:attribute name="content">
            <div class="intro">
                <div class="container">
                    <div class="intro__inner" style="background-color: hsla(213, 0%, 100%, 0); height: 100vh;">
                        <div class="intro__signup">
                            <h3 class="signup__header">Sign Up</h3>
                            <form action="<c:url value="/autho"/>" method="post" class="signup__form">
                                <div class="form__login">
                                    <input type="text" class="signup__input" name="login" placeholder="Name:">
                                </div>
                                <div class="form__password">
                                    <input type="password" class="signup__input" name="password"
                                           placeholder="Password:">
                                </div>
                                <div style="margin-top: 20px;">
                                    <label class="red__error" style="justify-content: center; margin-right: 0">
                                        <c:if test="${param.er == 'login' and param.er != 'password'}">The login mustn't be empty</c:if>
                                        <c:if test="${param.er == 'password' and param.er != 'login'}">The password mustn't be empty</c:if>
                                        <c:if test="${param.er == 'login-and-password'}">You've entered an incorrect login or password</c:if>
                                    </label>
                                </div>
                                <div class="form__submit">
                                    <input type="submit" class="signup__submit" value="submit">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </jsp:attribute>
</t:myhtml>
</body>
</html>

