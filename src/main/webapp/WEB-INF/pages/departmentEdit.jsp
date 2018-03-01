<%--
  Created by IntelliJ IDEA.
  User: anikonova
  Date: 22.02.18
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>All departments</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!--Bootstrap js -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <title>Add | Edit Department </title>
</head>
<body>
<div class="container">
    Enter department info:
    <form method="post" action="departments">
        <fieldset class="form-group">
            <label>Name</label>
            <input name="name" type="text" class="form-control" value="${dto.name}"/>
            <br/>
        </fieldset>
        <fieldset class="form-group">
            <label>Info</label>
            <input name="info" type="text" class="form-control" value="${dto.info}"/> <br/>
        </fieldset>


        <input type="hidden" name="id" value="${param.id}"/>

        <input name="action" type="submit" class="btn btn-success" value="submit"/>
        <a class="btn btn-primary" href="/task2/departments?action=list">Back</a>

    </form>

    <form:form modelAttribute="dto"
               method="post" action="/users-system/users/check">
        <form:input path="name"/>
        <form:input path="password"/>
        <input type="submit">Check user</input>
    </form:form>


    <p style="color: brown">${warning}</p>

    <c:forEach items="${violations}" var="entry">
        <p style="color:red;"><strong>${entry.errorCode}</strong> : ${entry.messageTemplate} </p>
    </c:forEach>


</div>

</body>
</html>
