<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
    <link href="https://webfontworld.github.io/goodchoice/Jalnan.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <title>Insert title here</title>
</head>
<style>
    header{


    }
    section{
        width: 80%;
        text-align: center;
        font-family: 'Jalnan';
        line-height: 72px;
        color: white;
    }
    section.d-inline-flex{
        text-align: center;
    }
    section a{

        text-decoration: none;
    }
</style>
<c:set var="root" value="<%=request.getContextPath() %>"/>
<body>

<header>
    <section class="d-inline-flex">
        <h2 style="line-height: 72px;">TREVIEW</h2>
        <div class="titlebox">
            hoihoi
        </div>
    </section>
</header>
</body>
</html>