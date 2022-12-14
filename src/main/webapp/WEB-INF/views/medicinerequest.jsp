<%@page import="com.pharmacy.model.OrdersBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Online Pharmacy</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/orders.component.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Tangerine">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Material+Icons+Outlined"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js"
	integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/"
	crossorigin="anonymous"></script>
<style>
#alert {
	margin-left: 20%;
}

@media screen and (max-width:670px) {
	#alert {
		margin-left: 0%;
	}
}
</style>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	String role = (String) session.getAttribute("role");
	String username = (String) session.getAttribute("username");
	if (username == null || role.equals("USER")) {
		response.sendRedirect("login.jsp");
	} else {
		List<OrdersBean> orders = (ArrayList<OrdersBean>)request.getAttribute("orders");
	%>
	<%@include file="navbar.jsp"%>
	<div class="d-flex justify-content-center container">
		<div class="display-content">
			<p class="fs-2 text-center text-dark fw-bold mt-2 mb-4 heading">
				<u>Medicine Requests</u>
			</p>
			<br>
			<%
				boolean empty=true;
				if (orders != null && !orders.isEmpty()) {
				for (OrdersBean order : orders) {
					String status = order.getStatus();
					if (order.isMedicine() && 
							(order.getDistributorName() == null || order.getDistributorName().equals("PENDING"))) {
						empty=false;
			%>
			<div class="row">
				<div class="col-lg-5 col-md-5 d-none d-lg-block">
					<div
						class="card text-dark mt-3 mb-3 shadow bg-white rounded left-card"
						style="padding: 10px; margin-left: 35%;">
						<div class="card-body">
							<h5 class="card-title text-dark">
								<strong>Order ID:</strong>&nbsp;<%=order.getOrderId()%></h5>
							<p class="card-text">
								<strong class="text-dark">Order Date:</strong>&nbsp;
								<%=order.getOrderDate()%></p>
							<p class="card-text">
								<strong class="text-dark">Status:</strong>&nbsp;
								<%
									if (status.equals("PENDING")) {
								%>
								<span>
									<button class="btn btn-outline-secondary btn-sm ms-2">
										<span class="material-icons-outlined me-2"
											style="float: left;"> pending_actions </span>Pending
									</button>
								</span>
								<%
									}
								%>
							</p>
						</div>
					</div>
				</div>
				<div class="col-sm-12 offset-sm-2 col-lg-7 col-md-7 offset-md-0">
					<div
						class="card text-dark bg-white mb-3 mt-3 shadow rounded right-card"
						style="margin-left: 25%; padding: 15px;">
						<div class="card-body">
							<ul class="nav position-absolute top-0 end-0 mt-5 me-2">
								<li><span><a
										href="${pageContext.request.contextPath}/particularOrder/<%=order.getOrderId()%>"
										class="btn btn-success btn-sm">View Prescription</a></span></li>
							</ul>
							<h5 class="card-title text-primary">
								<strong>Customer Name: </strong><%=order.getUsername()%>
							</h5>
							<p class="card-text">
								<strong class="text-dark">Address:</strong>&nbsp;
								<%=order.getAddress()%></p>
							<p class="card-text">
								<strong class="text-dark">Phone Number</strong>&nbsp;
								<%=order.getPhoneNumber()%></p>
						</div>
					</div>
				</div>
			</div>
			<%
				}
			}}
				if(empty){%>
			<div
				class="alert alert-danger text-center position-absolute top-50 start-50 translate-middle w-50 mt-5"
				role="alert" id="alert">No Requests!</div>

			<%}
			%>
		</div>
	</div>
	<%
		}
	%>
</body>
</html>