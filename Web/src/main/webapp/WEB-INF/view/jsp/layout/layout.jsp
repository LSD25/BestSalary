<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../jsp-header.jsp" %>
<!DOCTYPE html>
<html>

<head>

    <title>
        <tiles:insertAttribute name="title"/>
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="${contextPath}/styles/main.css" type="text/css" rel="stylesheet"/>

</head>

<header>
    <tiles:insertAttribute name="header"/>
</header>

<menu>
    <tiles:insertAttribute name="menu"/>
</menu>

<body>
<tiles:insertAttribute name="body"/>
</body>

<footer>
    <tiles:insertAttribute name="footer"/>
</footer>

</html>
