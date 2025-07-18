<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="include/header.jsp" %>

<div class="container mt-5">
    <h2 class="mb-4 text-center">Your Order History</h2>

    <c:if test="${empty orders}">
        <div class="alert alert-info text-center">
            You have not placed any orders yet.
        </div>
    </c:if>
    <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-danger">Logout</a>

    <c:forEach var="order" items="${orders}">
        <div class="card mb-3">
            <div class="card-header">
                <strong>Order #${order.id}</strong> | Date: ${order.orderTime} | Total: ₹${order.totalAmount}
            </div>
            <ul class="list-group list-group-flush">
                <c:forEach var="item" items="${order.items}">
                    <li class="list-group-item">
                            ${item.productName} - ₹${item.price} × ${item.quantity} = ₹${item.subtotal}
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:forEach>
</div>

<%@ include file="include/footer.jsp" %>
