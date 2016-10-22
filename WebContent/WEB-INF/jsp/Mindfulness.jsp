<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Just Be</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/Mindfulness.css" />"/>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"> </script>
<script src="//cdnjs.cloudflare.com/ajax/libs/gsap/1.18.0/TweenMax.min.js"></script>
<script src="<c:url value="/js/CookieAnimator.js" />"> </script>
</head>
<body>
<div class="fortune-wrapper">
   <div class="fortune">
      <div class="fortune-left"></div>
      <div class="fortune-right"></div>
      <div class="fortune-message"><span>${quote}</span></div>
   </div>
</div>
<div class="add-quotes">
<a href="mindfulness/addquotes">Add quotes</a>
</div>
</body>
</html>