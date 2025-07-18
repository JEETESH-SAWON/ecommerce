<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<%@ include file="include/header.jsp" %>
<body>

<div class="container mt-5">
    <h2 class="text-center mb-4">Your Cart</h2>

    <!-- Show success message -->
    <c:if test="${not empty success}">
        <div class="alert alert-success">
            <ul>
                <li>${success}</li>
            </ul>
        </div>
    </c:if>

    <!-- Show cart only if items exist -->
    <c:if test="${not empty cartItems}">
        <table class="table table-bordered table-hover">
            <thead class="table-dark">
            <tr>
                <th>#</th>
                <th>Product Name</th>
                <th>Price (₹)</th>
                <th>Quantity</th>
                <th>Subtotal (₹)</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="total" value="0"/>
            <c:forEach var="item" items="${cartItems}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${item.productName}</td>
                    <td>${item.productPrice}</td>
                    <td>${item.quantity}</td>
                    <td>
                        <c:set var="subtotal" value="${item.productPrice * item.quantity}"/>
                            ${subtotal}
                        <c:set var="total" value="${total + subtotal}"/>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/cart/remove" method="post"
                              style="display:inline;">
                            <input type="hidden" name="productId" value="${item.productId}"/>
                            <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr class="fw-bold">
                <td colspan="4" class="text-end">Total:</td>
                <td colspan="2">₹${total}</td>
            </tr>
            </tfoot>
        </table>

        <div class="d-flex justify-content-between align-items-center mt-4">
            <!-- Back Button (Left) -->
            <div class="col-1">
                <a href="${pageContext.request.contextPath}/product" class="btn btn-danger w-100">
                    Back
                </a>
            </div>

            <!-- Place Order Button (Right) -->
            <form action="${pageContext.request.contextPath}/order/place" method="post">
                <button type="submit" class="btn btn-primary">
                    Place Order
                </button>
            </form>
        </div>

    </c:if>

    <!-- If cart is empty -->
    <c:if test="${empty cartItems}">
        <div class="alert alert-info text-center">
            Your cart is empty. <a href="${pageContext.request.contextPath}/product">Continue Shopping</a>
        </div>
    </c:if>
</div>

<%@ include file="include/footer.jsp" %>
</body>
</html>
