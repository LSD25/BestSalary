<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../jsp-header.jsp" %>
<form action="${contextPath}/register/user/save" method="post">

    <table>
        <tr>
            <td>Username:</td>
            <td>
                <input name="username"/>
            </td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="register"/>
            </td>
            <td>
                <input type="reset" value="clear"/>
            </td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>