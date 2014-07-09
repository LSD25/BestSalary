<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../jsp-header.jsp" %>

header

<c:choose>
    <c:when test="${pageContext.request.userPrincipal.authenticated}">
        <p><spring:message code="p.hello"/>: ${pageContext.request.userPrincipal.name}
        <p><a href="${contextPath}/logout"><spring:message code="link.logout"/></a>
    </c:when>
    <c:otherwise>
        <p><spring:message code="reg.btn.register"/>: <a href="${contextPath}/register/user"><spring:message code="reg.btn.register"/></a> </p>
    </c:otherwise>
</c:choose>

<br/>
<a href="${contextPath}/?lang=ru">Русский</a>
<br/>
<a href="${contextPath}/?lang=en">English</a>

