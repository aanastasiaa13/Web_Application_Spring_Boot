<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@tag pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="content" fragment="true" %>

<html>
<head>
    <title>${title}</title>
    <link href="https://fonts.googleapis.com/css2?family=Play:wght@400;700&display=swap" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<t:header/>

<jsp:invoke fragment="content"/>

<t:footer/>
</body>
</html>
