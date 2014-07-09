<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../jsp-header.jsp" %>
<div id="login-box">

    <h3><spring:message code="log.form.name"/></h3>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>

    <form name='loginForm' action="${contextPath}/j_spring_security_check" method='POST'>

        <table>
            <tr>
                <td><spring:message code="log.login"/>:</td>
                <td><input type='text' name='j_username'></td>
            </tr>
            <tr>
                <td><spring:message code="log.pass"/>:</td>
                <td><input type='password' name='j_password'/></td>
            </tr>
            <tr>
                <td colspan='2'>
                    <input name="submit" type="submit" value="submit"/>
                </td>
                <td>
                    <a href="${contextPath}/register/user"><spring:message code="log.reg.btn"/></a>
                </td>
            </tr>
            <tr>
                <td>
                    <spring:message code="log.check.rem"/>
                </td>
                <td>
                    <input type="checkbox" class="LoginCheckbox" name="_spring_security_remember_me"
                           id="donot_remember_me" name="donot_remember_me">
                </td>
            </tr>
        </table>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

</div>