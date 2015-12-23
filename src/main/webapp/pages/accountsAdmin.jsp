<%--
  Created by IntelliJ IDEA.
  User: itkachuk
  Date: 12/22/2015
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
    <spring:url value="/admin/account/add" var="accountAddUrl" />
    <spring:url value="/admin/account/update" var="accountUpdateUrl" />
    <spring:url value="/admin/account/delete" var="accountDeleteUrl" />
    <c:set var="accountId" scope="request" value='${accountId}'/>

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
            <div class="col-xs-12 col-sm-6">
                <div class="row">
                    <div class="columnHeader">
                        <h2>Accounts</h2>
                    </div>
                    <c:if test="${not empty accountsList}">
                        <div class="list-group">
                            <c:forEach var="account" items="${accountsList}">
                                <a class="list-group-item ${account.id eq accountId ? 'active' : ''}" id="accountItem" href="${accountBaseUrl}/${account.id}">
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
            </div>

            <div class="col-xs-12 col-sm-6">
                <div class="columnHeader">
                    <h2>Account editor</h2>
                </div>
                <div id="newAccountButtonPanel" ${accountId eq -1 ? '' : 'style="display: none;"'}>
                    <div class="row">
                        <button class="btn btn-success newAccountButton" onclick="newAccountButtonClick()">
                            New account
                        </button>
                    </div>
                </div>
                <div id="accountEditorForm" align="left" ${accountId eq -1 ? 'style="display: none;"' : ''}>
                    <c:set var="accountUpdateUrlWithId" value="${accountUpdateUrl}/${accountId}" />
                    <form:form method="post" role="form" modelAttribute="accountForm" action="${accountId eq -1 ? accountAddUrl : accountUpdateUrlWithId}">
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <form:input path="name" type="text" id="name" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="currency">Currency:</label>
                            <form:select path="currency" id="currency" class="form-control">
                                <form:option value="-1" label="" />
                                <form:options items="${currenciesList}" />
                            </form:select>
                        </div>
                        <div class="form-group">
                            <label for="description">Description:</label>
                            <form:input path="description" type="text" id="description" class="form-control" />
                        </div>

                        <div align="right">
                            <button class="btn btn-info" type="submit" onclick="saveAccountButtonClick()">
                                Save
                            </button>
                            <button class="btn btn-primary" type="button" onclick="cancelAccountButtonClick('${accountBaseUrl}/-1')"> Cancel </button>
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
