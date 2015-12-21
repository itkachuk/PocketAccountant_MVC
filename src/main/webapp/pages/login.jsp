<%--
  Created by IntelliJ IDEA.
  User: itkachuk
  Date: 11/10/2015
  Time: 6:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <c:if test="${not empty error}">
        <div id="error">
            <h2 style="color: red">Authentication was unsuccessful</h2>
        </div>
    </c:if>

    <div id="user-login-form">
        <form id="login" action="j_spring_security_check" method="post">
            <table>
                <tr>
                    <td>Username:</td>
                    <td>
                        <input type="text" name="j_username"/>
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td>
                        <input type="password" name="j_password"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Login"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
