<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<footer class="bg-info fixed-bottom">
    <div class="container ">
        <div class="row align-middle">
            <div class="col-12 align-middle">
                <p class="fs-6">
                    КОЛИЧЕСТВО ИГР= ${sessionScope.userId}
                </p>
                <p class="fs-6">
                    NAME= ${sessionScope.userName}
                </p>
                <p class="fs-6">
                    IP= ${sessionScope.ipaddress}
                </p>
                <p class="fs-6">
                    ПОБЕД= ${sessionScope.winCounter}
                </p>
                <p class="fs-6">
                    ПОРАЖЕНИЙ= ${sessionScope.loseCounter}
                </p>
            </div>
        </div>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>