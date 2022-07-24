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
        <h2>Subscribe to newsletter</h2>
        <form:form action="subscribe" method="post" modelAttribute="subscription">
            <form:label path="firstName">First name:</form:label>
            <form:input path="firstName"/><br/>

            <form:label path="email">*E-mail:</form:label>
            <form:input path="email"/><br/>

            <form:label path="birthDate">*Birthday (yyyy-mm-dd):</form:label>
            <form:input path="birthDate"/><br/>

            <form:label path="gender">Gender:</form:label>
            <form:select path="gender" items="${genders}" /><br/>

            <form:label path="consent">I give consent to be informed</form:label>
            <form:checkbox path="consent"/><br/>

            <form:label path="newsletterId">*NewsletterId</form:label>
            <form:input path="newsletterId"/><br/>

            <form:button>subscribe</form:button>
        </form:form>
    </div>
</body>
</html>