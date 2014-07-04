<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../jsp-header.jsp" %>

<html>

<head>

    <title>
        <tiles:insertAttribute name="title"/>
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="${contextPath}/styles/main.css" type="text/css" rel="stylesheet"/>

</head>

<body>

<tiles:insertAttribute name="header"/>

<tiles:insertAttribute name="menu"/>

<tiles:insertAttribute name="body"/>

<tiles:insertAttribute name="footer"/>

</body>

</html>
