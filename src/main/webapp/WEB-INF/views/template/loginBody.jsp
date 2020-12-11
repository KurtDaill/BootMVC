<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<html>
<div>
<h3>Please Log In</h3>
<br>
<form:form modelAttribute="login" action="mvcProj/auth/login.html" method="post">

<form:errors path="*" cssClass="error"/>
User
<form:input type="text" path="userName" name="userName" /><div>${userName }</div>
Pass
<form:input type="password" path="password" name="password" />
<input type="submit"/>
</form:form>
</div>
</html>