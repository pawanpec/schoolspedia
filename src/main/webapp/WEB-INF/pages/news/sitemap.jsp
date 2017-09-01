<%@ include file="../include.jsp"%>
<title>sitemap Page</title>

<br></br><h1>SiteMap</h1>
<br></br>
<c:if
	test="${not fn:containsIgnoreCase(requestScope['javax.servlet.forward.query_string'], 'pageNumber')}">
	<c:set var="orgParam"
		value="${requestScope['javax.servlet.forward.query_string']}"
		scope="session" />
</c:if>
<c:set var="currentURL"
	value="${sessionScope.orgURL}?${sessionScope.orgParam}" />
<div class="pagignation">
			<%@include file="../search/pagination.jsp"%>
</div>
<c:forEach items="${contents}" var="newsContent"
					varStatus="theCount">
					<a href="/${newsContent.alias }" target="_blank"
				title="${fn:toLowerCase(newsContent.title)}"><strong>${fn:toLowerCase(newsContent.title)}</strong></a>
				<br>
				</br>
</c:forEach>
	
