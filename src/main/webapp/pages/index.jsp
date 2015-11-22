<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">

    <title>Pocket Accountant</title>
</head>
<body>
<div id="recordsList" align="center" class="container">
    <div class="loggedUserHeader">
        <h4>Currently logged user: <sec:authentication property="name"/>
            <span> | </span>
            <a id="logout" href="/accountant/logout">Logout</a>
        </h4>
    </div>
    <div class="recordsListHeader">
        <h2>List of records</h2>
        <%--<sec:authorize ifAnyGranted="ROLE_ADMIN">--%>
            <%--<button class="btn btn-info" onclick="location.href='${bookAddUrl}'">Add new book</button>--%>
        <%--</sec:authorize>--%>
    </div>
    <table class="table table-striped" border="1" cellpadding="5">
        <tr>
            <th>Account</th>
            <th>Category</th>
            <th>Date</th>
            <th>Description</th>
            <th>Amount</th>
        </tr>
        <c:forEach var="record" items="${recordsList}">
            <%--<c:choose>--%>
                <%--<c:when test="${book.status eq 0}">--%>
                    <%--<c:set var="bookTakeReturnUrl" value='${bookTakeUrl}${book.id}' ></c:set>--%>
                    <%--<c:set var="buttonText" value='Take' ></c:set>--%>
                    <%--<c:set var="buttonClass" value='btn btn-primary' ></c:set>--%>
                    <%--<c:set var="buttonDisabled" value='false' ></c:set>--%>
                <%--</c:when>--%>
                <%--<c:when test="${book.status eq 1 and book.holder.userName eq loggedUser}">--%>
                    <%--<c:set var="bookTakeReturnUrl" value='${bookReturnUrl}${book.id}' ></c:set>--%>
                    <%--<c:set var="buttonText" value='Return' ></c:set>--%>
                    <%--<c:set var="buttonClass" value='btn btn-success' ></c:set>--%>
                    <%--<c:set var="buttonDisabled" value='false' ></c:set>--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                    <%--<c:set var="bookTakeReturnUrl" value='${bookReturnUrl}${book.id}' ></c:set>--%>
                    <%--<c:set var="buttonText" value='Take' ></c:set>--%>
                    <%--<c:set var="buttonClass" value='btn btn-primary disabled' ></c:set>--%>
                    <%--<c:set var="buttonDisabled" value='true' ></c:set>--%>
                <%--</c:otherwise>--%>
            <%--</c:choose>--%>
            <tr>
                <td><c:out value="${record.account.name}" /></td>
                <td><c:out value="${record.category.name}" /></td>
                <td><c:out value="${record.date}" /></td>
                <td><c:out value="${record.description}"/></td>
                <td><c:out value="${record.amount}"/></td>
                <%--<td>--%>
                    <%--<button class="${buttonClass}" onclick="location.href='${bookTakeReturnUrl}'" ${buttonDisabled ? 'disabled' : '' }>--%>
                        <%--<c:out value="${buttonText}" />--%>
                    <%--</button>--%>
                    <%--<button class="btn btn-info" onclick="location.href='${bookStatsUrl}${book.id}'">--%>
                        <%--<c:out value="Show stats" />--%>
                    <%--</button>--%>
                    <%--<sec:authorize ifAnyGranted="ROLE_ADMIN">--%>
                        <%--<button class="btn btn-primary" onclick="location.href='${bookUpdateUrl}${book.id}'" ${book.status eq 1 ? 'disabled' : ''}>Update</button>--%>
                        <%--<button class="btn btn-danger" onclick="location.href='${bookDeleteUrl}${book.id}'" ${book.status eq 1 ? 'disabled' : ''}>Delete</button>--%>
                    <%--</sec:authorize>--%>
                <%--</td>--%>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
