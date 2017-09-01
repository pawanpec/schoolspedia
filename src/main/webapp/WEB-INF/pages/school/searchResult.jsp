<%@ include file="../include.jsp"%>
<c:if test="${fn:containsIgnoreCase(orgURL, 'searchSchool.html')}">
	<c:if test="${not empty param.province}">
		<title>Top Schools | Best Schools  in ${param.city} ${param.province}</title>
	</c:if>
	<c:if test="${empty param.province}">
		<title>Search School Find School Near to your Home</title>
	</c:if>
</c:if>
<c:if test="${fn:containsIgnoreCase(orgURL, 'tags.html')}">
	<title>${param.url}</title>
</c:if>
<c:if
	test="${not fn:containsIgnoreCase(requestScope['javax.servlet.forward.query_string'], 'pageNumber')}">
	<c:set var="orgParam"
		value="${requestScope['javax.servlet.forward.query_string']}"
		scope="session" />
</c:if>
<c:if test="${fn:containsIgnoreCase(orgParam, 'summer_camp')}">
	<title>Summer camps in Delhi NCR</title>
</c:if>
<c:if test="${fn:containsIgnoreCase(orgParam, 'nursery_admission')}">
	<title>Nursery Admission News Updates in Delhi NCR</title>
</c:if>
<c:set var="currentURL"
	value="${sessionScope.orgURL}?${sessionScope.orgParam}" />
<div class="container">
<c:if test="${fn:containsIgnoreCase(orgParam, 'summer_camp')}">
	<h1>Summer camps in Delhi NCR</h1>
</c:if>
<c:if test="${fn:containsIgnoreCase(orgParam, 'nursery_admission')}">
	<h1>Nursery Admission News Updates in Delhi NCR</h1>
</c:if>
<c:if test="${fn:containsIgnoreCase(orgURL, 'tags.html')}">
	<h1>${param.url}</h1>
</c:if>
	<div class="left_coloum">
		<div class="list_grid">
			<div>
				<c:if test="${fn:containsIgnoreCase(orgURL, 'searchSchool.html')}">
					<c:if test="${not empty param.province}">
						<h1>Top Schools Best Schools in ${param.city} ${param.province}</h1>
					</c:if>
					<c:if test="${empty param.province}">
						<h1>Search School Find School Near to your Home</h1>
					</c:if>
					<%@include file="searchSchoolCity.jsp"%>
				</c:if>
				<%@include file="../adsense_728_90.jsp"%>
				<c:forEach items="${contents}" var="newsContent"
					varStatus="theCount">
					<c:if test="${newsContent.type eq 'group'}">
						<%@include file="schoolListing.jsp"%>
					</c:if>
					<c:if test="${newsContent.type ne 'group'}">
						<%@include file="resultCard.jsp"%>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="pagignation">
			<%@include file="pagination.jsp"%>
		</div>
	</div>
	<div class="right_coloum">
		<%@include file="../adsense_336_280.jsp"%>
		<div class="news_subscribe">
			<h4>Recent News</h4>
		</div>
		<%@include file="../recentNews.jsp"%>
		<%--  <%@include file="../amazon_336_280.jsp"%> --%>
		<%@include file="../adsense_336_280.jsp"%>
	</div>
</div>






<!-- ==================== -->