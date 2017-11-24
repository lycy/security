<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  isELIgnored="false"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
</head>
<body>
<form action="formLogin" method="post">
    <table>
        <tr>
            <td> 用户名：</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td> 密码：</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value=" 登录 "/>
                <input type="reset" value=" 重置 "/>
            </td>
        </tr>
    </table>
</form>
dddddddddddddddd
</body>
</html>