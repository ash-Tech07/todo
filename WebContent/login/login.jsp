<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>WT Todo App</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body>

        <jsp:include page="../common/header.jsp"></jsp:include>
            <div class="container col-md-8 col-md-offset-3 mt-5 shadow p-3 mb-5 bg-body rounded" style="overflow: auto; max-width: 500px; padding: 20px">
                <h1 style="color: #47B5FF" class="text-center mb-4">Login Form</h1>
                <form action="<%=request.getContextPath()%>/login" method="post">

                <div class="form-group">
                    <label for="uname">User Name:</label> <input type="text"
                                                                 class="form-control" id="username" placeholder="User Name"
                                                                 name="username" required>
                </div>

                <div class="form-group">
                    <label for="uname">Password:</label> <input type="password"
                                                                class="form-control" id="password" placeholder="Password"
                                                                name="password" required>
                </div>


                <button type="submit" class="btn btn-primary" style="background-color: #47B5FF; margin-left: 200px;">Submit</button>
            </form>
        </div>
        <jsp:include page="../common/footer.jsp"></jsp:include>
    </body>
</html>