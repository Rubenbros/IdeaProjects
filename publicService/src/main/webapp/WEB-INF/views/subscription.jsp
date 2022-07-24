<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Subscribe to newsletter</title>
</head>
<body>
    <div align="center">
        <h2>Hi ${firstName}!</h2>
        <h3>You are suscribed to newsletter: ${newsletterId}</h3>
        <h3>And we will send you information to your email : ${email}</h3>
        <h4>If you want to cancel your subscription click here:</h4>
        <form:form action="/cancelSubscription?email=${email}" method="post" modelAttribute="email">
            <form:button>Cancel Subscription</form:button>
        </form:form>
    </div>
</body>
</html>