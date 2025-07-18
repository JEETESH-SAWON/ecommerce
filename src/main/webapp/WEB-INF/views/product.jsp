<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<%@ include file="include/header.jsp" %>
<body>

<div class="container mt-5">
    <h2 class="mb-4 text-center">Product List</h2>

    <!-- Show "Create Product" only for admin -->
    <c:if test="${sessionScope.userRole == 'admin'}">
        <div class="mb-3 text-end">
            <a href="${pageContext.request.contextPath}/product/createProduct" class="btn btn-success">Create New
                Product</a>
        </div>
    </c:if>

    <c:if test="${not empty success}">
        <div class="alert alert-success">
            <ul>
                <li>${success}</li>
            </ul>
        </div>
    </c:if>

    <div class="d-flex justify-content-between align-item-center mb-3">
        <div>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-danger">Logout</a>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/cart" class="btn btn-success  w-100">Cart
                <c:if test="${not empty cartItemNo}"><sub>${cartItemNo}</sub></c:if></a>
        </div>
    </div>
    <table class="table table-bordered table-hover">

        <thead class="table-dark">
        <tr>
            <th>#</th> <!-- Serial Number -->
            <th>Name</th>
            <th>Price (₹)</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${data}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td> <!-- Serial Number -->
                <td>${product.productName}</td>
                <td>${product.productPrice}</td>
                <td>${product.productDescription}</td>
                <td>
                    <!-- Add to Cart -->
                    <c:if test="${sessionScope.userRole == 'user'}">
                        <form action="cart/add" method="post" style="display:inline;">
                            <input type="hidden" name="productId" value="${product.productId}"/>
                            <button type="submit" class="btn btn-primary btn-sm">Add to Cart</button>
                        </form>
                    </c:if>

                    <!-- Admin-only actions -->
                    <c:if test="${sessionScope.userRole == 'admin'}">
                        <a href="${pageContext.request.contextPath}/products/editProduct"
                           class="btn btn-warning btn-sm">Edit</a>
                        <form action="${pageContext.request.contextPath}/delete/product" method="post"
                              style="display:inline;"
                              onsubmit="return confirm('Are you sure you want to delete this product?');">
                            <input type="hidden" name="productId" value="${product.productId}"/>
                            <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>

<%@ include file="include/footer.jsp" %>

</body>
</html>
