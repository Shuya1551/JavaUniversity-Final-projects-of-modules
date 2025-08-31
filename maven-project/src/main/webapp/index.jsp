<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Quest Game</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<section class="margin-top-large">
    <div class="d-flex justify-content-center align-content-center flex-wrap ">

        <div class="flex-header ">
            <div class="col-12">
                <h1 class="text-center text-primary-emphasis display-2 fw-bold">КВЕСТ ПРО НЛО И ПАМЯТЬ</h1>
                <div class="bg-info p-1 "></div>
            </div>

        </div>
    </div>
</section>

<section>
    <div class="container ">
        <div>
            <br>
        </div>
        <form action="gameServlet" method="post" class="row">
            <div class="col-4"></div>
            <div class="col-4">
                <input type="text" name="firstName" class="form-control" placeholder="Your name">
            </div>
            <div class="col-4"></div>
            <div>
                <br>
            </div>

            <div class="col-4"></div>
            <div class="col-4 d-grid gap-2 mx-auto">
                <button class="btn btn-info btn-lg  " type="submit">Начать игру</button>
            </div>
            <div class="col-4"></div>

        </form>
    </div>
</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
