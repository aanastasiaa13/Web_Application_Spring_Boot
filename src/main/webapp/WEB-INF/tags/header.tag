<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<header class="header">
    <div class="container">
        <div class="header__inner">
            <div class="header__logo">LOGO</div>
            <nav class="nav">
                <div class="header__menu" onclick="location.href='<c:url value="/admin"/>'" ${hide} ${hideUser}>
                    Main
                </div>
                <div class="header__menu"
                     onclick="location.href='<c:url value="/admin-users"/>'" ${hide} ${hideUser}>Users
                </div>
            </nav>
            <div class="hello" style="visibility: ${hide}">
                <div class="header__hello">Hello, ${user}</div>
                <button class="header__logout" onclick="location.href='<c:url value="/logout"/>'">logout
                </button>
            </div>
        </div>
    </div>
</header>
</body>
</html>


