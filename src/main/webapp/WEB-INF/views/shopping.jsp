<%@page import="com.pharmacy.model.ItemsBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Online Pharmacy</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Tangerine">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Material+Icons+Outlined"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
<style>
body {
	background-color: #e8eaf6 !important;
}

.container {
	margin-top: 5%;
	position: absolute;
	left: 15%;
}
</style>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	String token = (String) session.getAttribute("token");

	if (session.getAttribute("username") == null && (!token.contains("USER"))) {
		response.sendRedirect("login.jsp");
	} else {
		//List<ItemsBean> items=(ArrayList<ItemsBean>)request.getAttribute("items");
	%>
	<%@include file="navbar.jsp"%>
	<div class="container">
		<div class="row justify-content-center align-items-center">
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="d-flex justify-content-center search-bar">
					<h3 class="mt-2 text-center text-dark heading">Shop By
						Category</h3>
				</div>
			</div>
			<div class="col-12 mt-5">
				<div class="row justify-content-around">
					<c:forEach items="${items}" var="item">
						<c:set var="category" value="${item.category}" scope="request" />
						<%
						String category = (String) request.getAttribute("category");
						if (!category.equals("Medications")) {
						%>
						<div class="col-lg-3 col-md-6 col-sm-12">
							<div class="card shadow rounded mb-4 me-2"
								style="width: 18rem; height: 26rem;">
								<div class="card-body">
									<h6 class="card-title fw-bold text-dark text-center">${item.category}</h6>

									<hr>
									<div class="row">
										<div class="col">
											<div class="right-content mt-4">
												<%
												if (category.equals("Basic Supplies")) {
												%>
												<img
													src="${pageContext.request.contextPath}/resources/assets/supplies.jpg"
													class="ms-2" alt="" style="width: 15rem; height: 10rem;">
												<%
												} else if (category.equals("Toiletries")) {
												%>
												<img
													src="${pageContext.request.contextPath}/resources/assets/toiletries.jfif"
													class="ms-2" alt=" " style="width: 15rem; height: 10rem; ">
												<%
												}
												%>
												<div class="data p-4">
													<span class="fw-bold text-secondary">Distributor:</span> <span>${item.distributor}</span><br>
												</div>
											</div>
										</div>
									</div>
									<div class="text-center">
										<a
											href="getdistributoritems?category=${item.category}&distributor=${item.distributor}"
											class="btn btn-dark col-11 ms-3">Go </a>
									</div>
								</div>
							</div>
						</div>
						<%
						}
						%>
					</c:forEach>

				</div>
			</div>
		</div>
	</div>
	<%
	}
	%>
</body>
</html>