<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../jsp-header.jsp" %>
<form:form action="${contextPath}/register/user/save" method="post" modelAttribute="user">

    <table>
        <tr>
            <td><spring:message code="reg.input.login"/>:</td>
            <td>
                <form:input path="username"/>
            </td>
        </tr>
        <tr>
            <td><spring:message code="reg.input.pass"/>:</td>
            <td>
                    <%--<input type="password" name="password"/>--%>
                <form:input path="password"/>
            </td>
        </tr>
        <tr>
            <td><spring:message code="reg.input.conf.pass"/>:</td>
            <td><form:input path="confirmPassword"/></td>
        </tr>
        <tr>
            <td><spring:message code="reg.input.first"/>:</td>
            <td><form:input path="firstName"/></td>
        </tr>
        <tr>
            <td><spring:message code="reg.input.last"/>:</td>
            <td>
            <td><form:input path="lastName"/></td>
            </td>
        </tr>
        <tr>
            <td><spring:message code="reg.input.comp"/>:</td>
            <td>
            <td><form:input path="companyName"/></td>
        </tr>
        <tr>
            <td><spring:message code="reg.input.phone"/>:</td>
            <td><form:input path="phoneNumber"/></td>
        </tr>
        <tr>
            <td>
                <spring:message code="reg.option.shop"/>:
            </td>
            <td>
                <select>
                    <c:forEach var="shop" items="${shops}">
                        <form:option value="${shop}">${shop}</form:option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value='<spring:message code="reg.btn.register"/>'/>
            </td>
            <td>
                <input type="reset" value='<spring:message code="reg.btn.res"/>'/>
            </td>
        </tr>
    </table>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

</form:form>