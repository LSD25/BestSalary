<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../jsp-header.jsp" %>

<script src="${contextPath}/scripts/user.js"></script>

<form:form action="${contextPath}/register/user/save" method="post" modelAttribute="user">

    <table>
        <tr id="username">
            <td><spring:message code="reg.input.login"/>:</td>
            <td><input id="login" type="text"/></td>
        </tr>
        <tr id="password">
            <td><spring:message code="reg.input.pass"/>:</td>
            <td><input id="pass" type="password"/></td>
        </tr>
        <tr id="confirmPassword">
            <td><spring:message code="reg.input.conf.pass"/>:</td>
            <td><input id="confPass" type="password"/></td>
        </tr>
        <tr id="firstName">
            <td><spring:message code="reg.input.first"/>:</td>
            <td><input id="fname" type="text"/></td>
        </tr>
        <tr id="lastName">
            <td><spring:message code="reg.input.last"/>:</td>
            <td><input id="lname" type="text"/></td>
        </tr>
        <tr id="companyName">
            <td><spring:message code="reg.input.comp"/>:</td>
            <td><input id="comp" type="text"/></td>
        </tr>
        <tr id="phoneNumber">
            <td><spring:message code="reg.input.phone"/>:</td>
            <td><input id="phone" type="text"/></td>
        </tr>
        <tr>
            <td>
                <spring:message code="reg.option.shop"/>:
            </td>
            <td>
                <select>
                    <c:forEach var="shop" items="${shops}">
                        <option value="${shop}">${shop}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <input id="save-user" type="button" value='<spring:message code="reg.btn.register"/>'/>
            </td>
            <td>
                <input type="reset" value='<spring:message code="reg.btn.res"/>'/>
            </td>
        </tr>
    </table>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

</form:form>