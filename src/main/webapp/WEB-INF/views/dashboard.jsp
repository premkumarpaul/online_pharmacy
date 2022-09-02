<%@page import="com.pharmacy.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Online Pharmacy</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/dashboard.component.css">
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
</head>
<style>
body{
  background-image: url('https://img.freepik.com/free-photo/shopping-cart-with-pill-foils-copy-space_23-2148533453.jpg?w=740&t=st=1660718832~exp=1660719432~hmac=5de5d01a8cf43771d1f25d5fa28ecc30a22753b1eb184e0990763d55ab2e3745');
  background-size: 1300px 850px;
  background-repeat: no-repeat;
}
</style>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	String token=(String)session.getAttribute("token");
	
	if(token==null){response.sendRedirect("login");}
	else{
	%>
	<%@include file="navbar.jsp" %>
	<div class="container">
	<%
			String result = (String) request.getAttribute("message");
		if (result != null ) {
		%>
		<div
			class="alert alert-success alert-dismissible fade show text-center position-relative top-50 start-50 w-25 translate-middle"
			role="alert">
			<strong><%=result %></strong>
			<button type="button" class="btn-close" data-dismiss="alert"
				aria-label="Close" style="float: right;">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%}%>
        <div class="row">
          <div class=" col-lg-12 col-sm-12">
            <div class="row">
            <%if(token.contains("USER")){ %>
              <div class="col-md-4">
                <div class="col-lg-6 bg-indigo text-dark" style="width: 25rem;">
                  <div class="card-body text-center">
                    <h5 class="card-title text-center fw-bold">Order Medicine</h5>
                    <hr>
                    <p class="text-center lead">
                      <span>Our strong network lets us deliver medicines to almost everywhere</span>
                    </p>
                    <a href="<%=request.getContextPath()%>/producttype" class="btn btn-dark col-11 ms-3"><span class="text-white">Click here</span></a>
                  </div>
                </div>
              </div>

              <%}if(token.contains("DISTRIBUTOR") || token.contains("ADMIN")) {
                   if(token.contains("ADMIN")) {
                    	session.setAttribute("role", "ADMIN");
                    }%>
              	<div class="col-md-4">
                <div class="col-lg-6 bg-indigo text-dark" style="width: 25rem;">
                  <div class="card-body text-center">
                    <h5 class="card-title text-center fw-bold">Add/Update Supplies</h5>
                    <hr>
                    <p class="text-center lead">
                      <span>Add new supplies or modify existing supplies</span>
                    </p>
                    <a href="manageitems" class="btn btn-dark col-11 ms-3"><span class="text-white">Click here</span></a>
                  </div>
                </div>
              </div>
              <%}if(token.contains("USER") || token.contains("DISTRIBUTOR")){ 
                    if(token.contains("USER"))
                    { 
                    	session.setAttribute("role", "USER");
                    }
                    else if(token.contains("DISTRIBUTOR"))
                    {
                    	session.setAttribute("role", "DISTRIBUTOR");
                    }
              
                    %>
              	<div class="col-md-4">
                <div class="col-lg-6 bg-indigo text-dark" style="width: 25rem;">
                  <div class="card-body text-center">
                    <h5 class="card-title text-center fw-bold">Your Orders</h5>
                    <hr>
                    <p class="text-center lead"><%if(token.contains("USER")){%>View your recently placed orders along with their bills<%}else{%>
                    	View orders by placed by customers and do the needful<%} %>
                    </p>
                    <a href="<%=request.getContextPath()%>/showOrders/<%= session.getAttribute("username") %>/<%=session.getAttribute("role") %>" class="btn btn-dark col-11 ms-3"><span class="text-white">Click here</span></a>
                  </div>
                </div>
              </div>
              
              <div class="col-md-4">
                <div class="col-lg-6 bg-indigo text-dark" style="width: 25rem;">
                  <div class="card-body text-center">
                    <h5 class="card-title text-center fw-bold">Edit your Profile</h5>
                    <hr>
                    <p class="text-center lead">Customize your profile, update your profile at an instant</p>
                    <a href="${pageContext.request.contextPath}/updateprofile?username=<%=uname %>" class="btn btn-dark col-11 ms-3"><span class="text-white">Click here</span></a>
                  </div>
                </div>
              </div>
              <%}if(token.contains("ADMIN")){ %>
              	<div class="col-md-4">
                <div class="col-lg-6 bg-indigo text-dark" style="width: 25rem;">
                  <div class="card-body text-center">
                    <h5 class="card-title text-center fw-bold">Modify Users</h5>
                    <hr>
                    <p class="text-center lead">View user details and add,delete or update user data</p>
                    <a href="${pageContext.request.contextPath}/manageusers" class="btn btn-dark col-11 ms-3"><span class="text-white">Click here</span></a>
                  </div>
                </div>
              </div>
              <div class="col-md-4">
                <div class="col-lg-6 bg-indigo text-dark" style="width: 25rem;">
                  <div class="card-body text-center">
                    <h5 class="card-title text-center fw-bold">Modify Orders</h5>
                    <hr>
                    <p class="text-center lead">Edit orders placed by customers. Add, update and delete them.</p>
                    <a href="<%=request.getContextPath() %>/showOrdersAdmin/<%= session.getAttribute("username") %>/<%=session.getAttribute("role") %>" class="btn btn-dark col-11 ms-3"><span class="text-white">Click here</span></a>
                  </div>
                </div> 
              </div>
              <%}%>
            </div>
          </div>
        </div>
      </div>
	<%} %>
</body>
</html>