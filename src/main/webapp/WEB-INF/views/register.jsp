<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="include/header.jsp" %>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow-sm p-4" style="min-width: 300px; max-width: 400px; width: 100%;">
        <h3 class="text-center mb-3">Register</h3>

        <!-- Optional error display -->
        <c:if test="${not empty errorList}">
            <div class="alert alert-danger">
                <ul class="mb-0">
                    <c:forEach var="error" items="${errorList}">
                        <li>${error.defaultMessage}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="userName" required/>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="userPassword" required/>
            </div>

            <button type="submit" class="btn btn-success w-100 mb-2">Register</button>
            <a href="${pageContext.request.contextPath}/login" class="btn btn-primary  w-100">Login</a>
        </form>


    </div>
</div>
<%@ include file="include/footer.jsp" %>
</body>
</html>
