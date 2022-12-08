<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<!DOCTYPE html>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/user-style.css">
</head>
<body>
<t:myhtml title="Main">
        <jsp:attribute name="content">
            <div class="intro">
                <div class="container">
                    <div class="intro__inner">
                        <div class="intro__content">
                            <div class="content__welcome">Welcome<br> to Web App 2</div>
                        </div>
                    </div>
                </div>
            </div>
        </jsp:attribute>
</t:myhtml>
</body>
</html>

