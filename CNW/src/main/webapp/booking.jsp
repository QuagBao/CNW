<%@page import="Model.BEAN.Room_1"%>
<%@page import="Model.BO.Room_1BO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.BEAN.Room" %>
<%@ page import="Model.BEAN.RoomType" %>
<%@ page import="Model.BO.RoomBO" %>
<%@ page import="Model.BO.RoomTypeBO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<!-- Navbar -->
<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-white">
    <!-- Container wrapper -->
    <div class="container">
        <!-- Toggle button -->
        <button 
            class="navbar-toggler" 
            type="button" 
            data-mdb-toggle="collapse" 
            data-mdb-target="#navbarSupportedContent1" 
            aria-controls="navbarSupportedContent1" 
            aria-expanded="false" 
            aria-label="Toggle navigation">
            <i class="fas fa-bars"></i>
        </button>

        <!-- Collapsible wrapper -->
        <div class="collapse navbar-collapse" id="navbarSupportedContent1">
            <!-- Navbar brand -->
            <a class="navbar-brand mt-2 mt-sm-0" href="https://mdbootstrap.com/">
                <img src="https://mdbcdn.b-cdn.net/img/logo/mdb-transaprent-noshadows.webp" height="20" alt="MDB Logo" loading="lazy" />
            </a>
            <!-- Left links -->
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item active">
                    <a class="nav-link" href="javascript: history.go (-1)">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="https://mdbootstrap.com/docs/standard/">About MDB</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="https://mdbootstrap.com/docs/standard/getting-started/installation/">Free download</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="https://mdbootstrap.com/education/bootstrap/">Free tutorials</a>
                </li>
            </ul>
            <!-- Left links -->
        </div>
        <!-- Collapsible wrapper -->
    </div>
    <!-- Container wrapper -->
</nav>
<!-- Navbar -->


<!--Main layout-->
<main class="mt-5 pt-4">
    <div class="container mt-5">
        <!--Grid row-->
        <div class="row">
            <!--Grid column-->
            
            <%
            	String roomNumberParam = request.getParameter("roomNumber");
            	if (roomNumberParam != null && !roomNumberParam.isEmpty()){
            		int roomNumber = Integer.parseInt(roomNumberParam);
            		
            		//Truy vấn thông tin phòng từ cơ sở dữ liệu
            		Room_1BO room_1BO = new Room_1BO();
            		Room_1 room_1 = room_1BO.getRoomByNumber(roomNumber);
            		
            		if(room_1BO != null){
            %>	
            <div class="col-md-6 mb-4">
                <img src="<%=room_1.getImage() %>" class="img-fluid" alt="" />
            </div>
           	<!--Grid column-->

            <!--Grid column-->
            <div class="col-md-6 mb-4">
                <!--Content-->
                <div class="p-4">
                    
					<p class="lead">
                        
                        <span>Room <%= room_1.getRoomNumber()%></span>
                    </p>
                    <p class="lead">
                        
                        <span><%= room_1.getPrice()%> $</span>
                    </p>

                    <strong><p style="font-size: 20px;">Description</p></strong>

                    <p><%=room_1.getRoom_des() %></p>

                    <form class="d-flex justify-content-left">
                        <!-- Default input -->
                        <div class="form-outline me-1" style="width: 100px;">
                            <label for="checkInDate">Check-in Date:</label>
		                    <input type="text" id="checkInDate" name="checkInDate" placeholder="dd/mm/yyyy" required>
		
		                    <label for="checkOutDate">Check-out Date:</label>
		                    <input type="text" id="checkOutDate" name="checkOutDate" placeholder="dd/mm/yyyy" required>
                        	<br>
                        	<br>
                        	<a class="btn btn-primary ms-1" href="ProcessBookingController" role="button" data-mdb-ripple-color="dark">Booking</i></a>
                        </div>
                    </form>
                    <br>
                </div>
                <!--Content-->
            </div>
            <!--Grid column-->
            <%		} %>
            
            <%
            	}
            %>
</body>
</html>
