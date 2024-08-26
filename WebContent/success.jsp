<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<style>
body{
background-color:#16a085;
}
.custom-navbar {
  background-color: #566573;
  border: 2px solid #566573;
}

.custom-navbar .navbar-brand, 
.custom-navbar .nav-link {
  color: lightgreen;
}

.custom-navbar .nav-link.active {
  color: lightgreen;
}

.custom-navbar .navbar-brand:hover,
.custom-navbar .navbar-brand:focus{
  text-decoration: none; /* Remove underline if any */
  text-shadow: 4px 4px 6px rgba(0, 255, 0, 0.5); 
}
.custom-navbar .nav-link:hover, 
.custom-navbar .nav-link:focus{
  
  animation:anime 0.5s;
 outline-offset: 1px; 
background-image: linear-gradient(#566573 90%,rgba(0, 255, 0, 0.3) 10%); 
  color: lightgreen;  
 
}
 
.custom-navbar .nav-link:active{
	outline-offset: 1px; 
background-image: linear-gradient(#566573 0%,rgba(0, 255, 0, 0.3) 100%); 
  color: lightgreen; 
}
@keyframes anime{
0%{
outline-offset: 1px; 
background-image: linear-gradient(#566573 100%,rgba(0, 255, 0, 0.3) 0%); 
  color: lightgreen; 
}50%{
outline-offset: 1px; 
background-image: linear-gradient(#566573 0%,rgba(0, 255, 0, 0.3) 100%); 
  color: lightgreen; 

}100%{

outline-offset: 1px; 
background-image: linear-gradient(#566573 90%,rgba(0, 255, 0, 0.3) 10%); 
  color: lightgreen; 
}

}
.custom-navbar .nav-item {
  margin-right: 20px; /* Adjust as needed */
}

.custom-navbar .nav-item:last-child {
  margin-right: 0; /* Remove margin from the last item */
}
h1{
text-align:center;
}
.btn{
	
	color:lightgreen;
}
.form-label{
color:lightgreen;
}

.form-control:focus {
  box-shadow: 0 0 8px rgba(0, 255, 0, 0.5); /* Light green shadow on focus */
  border-color: lightgreen; /* Change border color to match the shadow */
 /* Remove default outline */
}

</style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light custom-navbar">
  <div class="container-fluid">
  
    <a class="navbar-brand" href="index.html">SecuringData</a>
     <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav ms-auto my-2 my-lg-0 navbar-nav-scroll" id="rep" style="--bs-scroll-height: 100px;">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="index.html">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="register.html">Sign up</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="login.html">Sign in</a>
        </li>
        <li class="nav-item">
          <a href="Logout" class="nav-link">Log Out</a>
        </li>
        
      </ul>
      
    </div>
  </div>
</nav>
<h1>Welcome ${user} !!!</h1>

<h1>You have logged in successfully!!</h1>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>