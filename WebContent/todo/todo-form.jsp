<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>WT Todo Application</title>

        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>

</head>
<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark"
             style="background-color: #47B5FF;">
            <div>
                <a href="" class="navbar-brand"> WT Todo
                    App</a>
            </div>

            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/list"
                       class="nav-link" style="color: #ffffff;font-size: 18px;">Todos</a></li>
            </ul>

            <ul class="navbar-nav navbar-collapse justify-content-end">
                <li><a href="<%=request.getContextPath()%>/logout"
                       class="nav-link" style="color: #ffffff;font-size: 18px;">Logout</a></li>
            </ul>
        </nav>
    </header>
    <div class="container col-md-5 mt-5" style="max-width: 500px">
        <div class="card">
            <div class="card-body">
                <c:if test="${todo != null}">
                    <form action="update" method="post">
                    </c:if>
                    <c:if test="${todo == null}">
                        <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${todo != null}">
                                    Edit Todo
                                </c:if>
                                <c:if test="${todo == null}">
                                    Add New Todo
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${todo != null}">
                            <input type="hidden" name="id" value="<c:out value='${todo.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Todo Title</label> <input type="text"
                                                             value="<c:out value='${todo.title}' />" class="form-control"
                                                             name="title" required="required" minlength="5">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Todo Decription</label> <input type="text"
                                                                  value="<c:out value='${todo.description}' />" class="form-control"
                                                                  name="description" minlength="5">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Todo Status</label> <select class="form-control"
                                                               name="isDone">
                                <option value="false">In Progress</option>
                                <option value="true">Complete</option>
                            </select>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Todo Target Date</label> <input type="date"
                                                                   value="<c:out value='${todo.targetDate}' />" class="form-control"
                                                                   name="targetDate" required="required">
                        </fieldset>

                        <button type="submit" class="btn" style="background-color: #47B5FF; color: #ffffff">Save</button>
                    </form>
            </div>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
