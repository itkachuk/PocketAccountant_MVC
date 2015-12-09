<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 21.11.2015
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-theme.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

    <title>Pocket Accountant</title>
</head>
<body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <%--<span class="sr-only">Toggle navigation</span>--%>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Pocket Accountant</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Summary page</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Reports <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Consolidated</a></li>
                            <li><a href="#">Charts</a></li>
                            <li><a href="#">Something else here</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <%--<li><a>Currently logged user: <sec:authentication property="name"/></a></li>--%>
                    <%--<li><a><span> | </span></a></li>--%>
                    <%--<li><a id="logout" href="/accountant/logout">Logout</a></li>--%>
                    <li><a href="/accountant/logout"> Currently logged user: <sec:authentication property="name"/> | Logout</a>
                    </li>
                </ul>
                <%--<div class="loggedUserHeader">--%>
                <%--<h4>Currently logged user: <sec:authentication property="name"/>--%>
                <%--<span> | </span>--%>
                <%--<a id="logout" href="/accountant/logout">Logout</a>--%>
                <%--</h4>--%>
                <%--</div>--%>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <div align="center" class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-4" id="side-bar-left">
                <div class="row">
                    <div class="columnHeader">
                        <h2>Accounts</h2>
                    </div>
                    <ul>
                        <c:forEach var="account" items="${accountsList}">
                            <li>
                                <div id="accountItem">
                                    <div class="row firstRow">
                                        <div id="accountNameLabel">${account.name}</div>
                                        <div id="accountCurrencyLabel">${account.currency}</div>
                                    </div>
                                    <div class="row secondRow">
                                        <div id="accountDescriptionLabel">${account.description}</div>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="row">
                    <div class="columnHeader">
                        <h2>Account summary</h2>
                    </div>
                    <div class="columnSubHeader">
                        <h4>Income/Expense per month</h4>
                    </div>
                    <table class="table table-striped" border="1" cellpadding="5">
                        <tr>
                            <th></th>
                            <th>Previous</th>
                            <th>Current</th>
                        </tr>
                        <tr>
                            <td>Income</td>
                            <td>35434</td>
                            <td>25654</td>
                        </tr>
                        <tr>
                            <td>Expense</td>
                            <td>45260</td>
                            <td>54656</td>
                        </tr>
                    </table>

                    <div class="columnSubHeader">
                        <h4>Profit</h4>
                    </div>
                    <table class="table table-striped" border="1" cellpadding="5">
                        <tr>
                            <th></th>
                            <th>Previous</th>
                            <th>Current</th>
                        </tr>
                        <tr>
                            <td>Month</td>
                            <td>35434</td>
                            <td>25654</td>
                        </tr>
                        <tr>
                            <td>Quarter</td>
                            <td>45260</td>
                            <td>54656</td>
                        </tr>
                        <tr>
                            <td>Year</td>
                            <td>145260</td>
                            <td>254656</td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="col-xs-12 col-sm-4" id="recordsList">
                <div class="columnHeader">
                    <h2>Records history</h2>
                </div>
                <ul>
                    <c:forEach var="record" items="${recordsList}">
                        <li>
                            <div id="recordItem">
                                <div class="row firstRow">
                                    <div id="dateRecordLabel">${record.date}</div>
                                </div>
                                <div class="row secondRow">
                                    <div id="categoryRecordLabel">${record.category.name}</div>
                                    <div id="amountRecordLabel">${record.amount}</div>
                                </div>
                                <div class="row thirdRow">
                                    <div id="descriptionRecordLabel">${record.description}</div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div class="col-xs-12 col-sm-4" id="side-bar-right">
                <div class="columnHeader">
                    <h2>Add new record</h2>
                </div>
                <div id="newRecordButtonsPanel">
                    <div class="row">
                        <button class="btn btn-success newRecordButton" onclick="newRecordButtonClick('income')">
                            New income
                        </button>
                    </div>
                    <div class="row">
                        <button class="btn btn-danger newRecordButton" onclick="newRecordButtonClick('expense')">
                            New expense
                        </button>
                    </div>
                </div>
                <div id="addNewRecordForm" align="center" style="display: none;">
                    <spring:url value="/summary/record/add" var="recordAddUrl" />
                    <spring:url value="/summary/record/update/" var="recordUpdateUrl" />
                    <form:form method="post" role="form" modelAttribute="recordForm" action="${empty id ? recordAddUrl : recordUpdateUrl}">
                        <div class="form-group">
                            <label for="isExpense">Record type:</label>
                            <form:select path="isExpense" id="isExpense" class="form-control">
                                <form:option value="0" label="Income" />
                                <form:option value="1" label="Expense" />
                            </form:select>
                        </div>
                        <div class="form-group">
                            <label for="account">Account:</label>
                            <form:select path="account" id="account" class="form-control">
                                <form:options items="${accountsList}" itemValue="id" itemLabel="name" />
                            </form:select>
                        </div>
                        <div class="form-group">
                            <label for="amount">Amount:</label>
                            <form:input path="amount" type="text" id="amount" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="date">Date:</label>
                            <form:input path="date" type="date" id="date" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="category">Category:</label>
                            <form:select path="category" id="category" class="form-control">
                                <form:options items="${categoriesList}" itemValue="id" itemLabel="name" />
                            </form:select>
                        </div>
                        <div class="form-group">
                            <label for="description">Description:</label>
                            <form:input path="description" type="text" id="description" class="form-control" />
                        </div>
                        <button class="btn btn-info"
                                <%--onclick="location.href='${empty recordId ? recordAddUrl : recordUpdateUrl}'">--%>
                                onclick="saveRecordButtonClick()">
                            Save
                        </button>
                        <button class="btn btn-primary" onclick="cancelButtonClick()"> Cancel </button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>

    <script src="<c:url value='/resources/js/jquery-1.11.3.js'/>"></script>
    <script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
    <script src="<c:url value='/resources/js/main.js'/>"></script>
</body>
</html>
