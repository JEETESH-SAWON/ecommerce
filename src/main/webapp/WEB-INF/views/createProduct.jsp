<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<%@ include file="include/header.jsp" %>
<body>

<div class="container mt-5">
    <h2 class="mb-4 text-center">Create New Product</h2>

    <!-- Show form only for admin -->
    <c:if test="${sessionScope.userRole == 'admin'}">
        <form action="${pageContext.request.contextPath}/products/save" method="post">

            <!-- Product Name -->
            <div class="mb-3">
                <label for="productName" class="form-label">Product Name</label>
                <input type="text" class="form-control" id="productName" name="productName" required>
            </div>

            <!-- Product Price -->
            <div class="mb-3">
                <label for="productPrice" class="form-label">Product Price (₹)</label>
                <input type="number" step="0.01" class="form-control" id="productPrice" name="productPrice" required>
            </div>

            <!-- Product Description -->
            <div class="mb-3">
                <label for="productDescription" class="form-label">Product Description</label>
                <textarea class="form-control" id="productDescription" name="productDescription" rows="3"
                          required></textarea>
            </div>

            <!-- Submit Button -->
            <button type="submit" class="btn btn-success">Save Product</button>
            <a href="${pageContext.request.contextPath}/product" class="btn btn-secondary">Cancel</a>

        </form>
    </c:if>

    <!-- Fallback if not admin -->
    <c:if test="${sessionScope.userRole != 'admin'}">
        <div class="alert alert-danger text-center">You are not authorized to access this page.</div>
    </c:if>
</div>

<%@ include file="include/footer.jsp" %>
</body>
</html>
