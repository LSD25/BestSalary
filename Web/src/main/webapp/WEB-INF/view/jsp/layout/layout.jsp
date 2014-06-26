<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../jsp-header.jsp" %>

<html>

<head>

    <title>
        <tiles:insertAttribute name="title"/>
    </title>

</head>

<body>

<tiles:insertAttribute name="header"/>

<tiles:insertAttribute name="menu"/>

<tiles:insertAttribute name="body"/>

<tiles:insertAttribute name="footer"/>

</body>

</html>
