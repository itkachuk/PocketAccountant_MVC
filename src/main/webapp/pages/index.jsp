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
    <%-- Common URLs for all JSPs --%>
    <spring:url value="/summary/account" var="summaryBaseUrl" />
    <spring:url value="/reports/consolidated" var="consolidatedReportBaseUrl" />
    <spring:url value="/reports/charts" var="chartsReportBaseUrl" />
    <spring:url value="/admin/account" var="accountBaseUrl" />
    <spring:url value="/admin/category" var="categoryBaseUrl" />
    <spring:url value="/logout" var="logoutUrl" />

    <%-- URLs, specific for given JSP --%>
    <spring:url value="/summary/record/add" var="recordAddUrl" />
    <spring:url value="/summary/record/update" var="recordUpdateUrl" />
    <c:set var="accountId" scope="request" value='${accountId}'/>
    <c:set var="recordId" scope="request" value='${recordId}'/>

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
                <a class="navbar-brand" href="${summaryBaseUrl}/-1">Pocket Accountant</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="${summaryBaseUrl}/-1">Summary page</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Reports <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="${consolidatedReportBaseUrl}">Consolidated</a></li>
                            <li><a href="${chartsReportBaseUrl}">Charts (not ready!)</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="${accountBaseUrl}/-1">Accounts</a></li>
                            <li><a href="${categoryBaseUrl}">Categories (not ready!)</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a class="inactiveLink"><span>Currently logged user: <sec:authentication property="name"/></span></a></li>
                    <li><a class="inactiveLink separator">|</a></li>
                    <li><a id="logout" href="${logoutUrl}">Logout</a></li>

                </ul>
            </div>
        </div>
    </nav>

    <div align="center" class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-4" id="side-bar-left">
                <div class="row">
                    <div class="columnHeader">
                        <h2>Accounts</h2>
                    </div>
                    <c:if test="${not empty accountsList}">
                        <div class="list-group">
                            <c:forEach var="account" items="${accountsList}">
                                <a class="list-group-item ${account.id eq accountId ? 'active' : ''}" id="accountItem" href="${summaryBaseUrl}/${account.id}">
                                    <div class="row firstRow mainMessageText">
                                        <div id="accountNameLabel">${account.name}</div>
                                        <div id="accountCurrencyLabel">${account.currency}</div>
                                    </div>
                                    <div class="row secondRow descriptionText">
                                        <div id="accountDescriptionLabel">${account.description}</div>
                                    </div>
                                </a>
                            </c:forEach>
                        </div>
                    </c:if>
                    <c:if test="${empty accountsList}">
                        <div class="no-data">No data available</div>
                    </c:if>
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
                            <td>${summaryAmounts[3]}</td>
                            <td>${summaryAmounts[1]}</td>
                        </tr>
                        <tr>
                            <td>Expense</td>
                            <td>${summaryAmounts[2]}</td>
                            <td>${summaryAmounts[0]}</td>
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
                            <td>${summaryAmounts[5]}</td>
                            <td>${summaryAmounts[4]}</td>
                        </tr>
                        <tr>
                            <td>Quarter</td>
                            <td>${summaryAmounts[7]}</td>
                            <td>${summaryAmounts[6]}</td>
                        </tr>
                        <tr>
                            <td>Year</td>
                            <td>${summaryAmounts[9]}</td>
                            <td>${summaryAmounts[8]}</td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="col-xs-12 col-sm-4" id="recordsList">
                <div class="columnHeader">
                    <h2>Records history</h2>
                </div>

                <c:if test="${not empty recordsList}">
                    <div class="list-group">
                        <c:forEach var="record" items="${recordsList}">
                            <a class="list-group-item list-group-item-${record.isExpense eq 0 ? 'success' : 'danger'}" href="${recordUpdateUrl}/${record.id}">
                                <div id="recordItem">
                                    <div class="row firstRow dateText">
                                        <div id="dateRecordLabel">${record.date}</div>
                                        <div id="accountRecordLabel">${record.account.name} [${record.account.currency}]</div>
                                    </div>
                                    <div class="row secondRow mainMessageText">
                                        <div id="categoryRecordLabel" class="mainMessageColor">${record.category.name}</div>
                                        <div id="amountRecordLabel">${record.isExpense eq 0 ? '' : '-'}${record.amount}</div>
                                    </div>
                                    <div class="row thirdRow descriptionText descriptionColor">
                                        <div id="descriptionRecordLabel">${record.description}</div>
                                    </div>
                                </div>
                            </a>
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${empty recordsList}">
                    <div class="no-data">No data available</div>
                </c:if>
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
                <div id="addNewRecordForm" align="left" style="display: none;">
                    <c:set var="recordUpdateUrlWithId" value="${recordUpdateUrl}/${recordId}" />
                    <form:form method="post" role="form" modelAttribute="recordForm" action="${empty recordId ? recordAddUrl : recordUpdateUrlWithId}">
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
                                <form:option value="-1" label="" />
                                <form:options items="${categoriesList}" itemValue="id" itemLabel="name" />
                            </form:select>
                        </div>
                        <div class="form-group">
                            <label for="description">Description:</label>
                            <form:input path="description" type="text" id="description" class="form-control" />
                        </div>
                        <div align="right">
                        <button class="btn btn-info" type="submit"
                                <%--onclick="location.href='${empty recordId ? recordAddUrl : recordUpdateUrl}'">--%>
                                onclick="saveRecordButtonClick()">
                            Save
                        </button>
                        <button class="btn btn-primary" type="button" onclick="cancelButtonClick()"> Cancel </button>
                        </div>
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
