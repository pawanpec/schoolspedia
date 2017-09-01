<%@ include file="../include.jsp"%>
<ol vocab="http://schema.org/" typeof="BreadcrumbList" class="breadcrumb">
		
		<li property="itemListElement" typeof="ListItem"><a href="/" property="item" typeof="WebPage" ><img
				src="<%=WebConstants.IMAGE_URL%>images/spacer.gif"
				class="cus-icon cus-home-small" alt="home"></a>
		</li>
		<c:if test="${not empty param.province}">
		<li property="itemListElement" typeof="ListItem"><a property="item" typeof="WebPage" 
				href="/india/schools-in/${param.province}">Schools in
					${param.province}</a></li>
		</c:if>
		<c:if test="${not empty param.city}">
			<li property="itemListElement" typeof="ListItem"><a 
			property="item" typeof="WebPage" href="/india/${param.province}/schools-in/${param.city}">
      Top Schools In  ${param.city}</a></li>
		</c:if>
</ol>
<c:if test="${fn:containsIgnoreCase(orgURL, 'searchSchool.html')}">
    <input type="button" class="btn btn-xs btn-primary" id="compareSchool" onclick="compareSchool();" value="compare">
	<c:if test="${not empty param.province}">
		<title>Top Schools in ${param.city}  ${param.province} | Best Schools in ${param.city}
			${param.province}</title>
	</c:if>
	<c:if test="${empty param.province}">
		<c:if test="${not empty locationSearch.a}">
			<title>Schools Near or with in ${locationSearch.d} kilometer
				of ${locationSearch.a}</title>
		</c:if>
		<c:if test="${empty locationSearch.a}">
			<title>School Search By location City School Name </title>
		</c:if>
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
	<title>Nursery Admission 2016-2017 News Updates in Delhi NCR</title>
</c:if>
<c:set var="currentURL"
	value="${sessionScope.orgURL}?${sessionScope.orgParam}" />
<div class="container">
	<c:if test="${fn:containsIgnoreCase(orgParam, 'summer_camp')}">
		<h1>Summer camps in Delhi NCR</h1>
	</c:if>
	<c:if test="${fn:containsIgnoreCase(orgParam, 'nursery_admission')}">
		<h1>Nursery Admission 2016-2017 News Updates in Delhi NCR</h1>
	</c:if>
	<c:if test="${fn:containsIgnoreCase(orgURL, 'tags.html')}">
		<h1>${param.url}</h1>
	</c:if>
	<div class="left_coloum">
		<c:if test="${isAdmin}">
			<a
				href="${contextPath}/edit_content.html?url=nursery-admission-news/nursery-admission-delhi-2016-17-update">Edit</a>
		</c:if>
		<p class="ellipsetext newscontentdesc">${nurseryContent.body.value}</p>
		<div class="list_grid">
			<div>
				<c:if test="${fn:containsIgnoreCase(orgURL, 'searchSchool.html')}">
					<c:if test="${not empty param.province}">
						<h1>Schools in ${param.city}
							${param.province}</h1>
					</c:if>
					<c:if test="${empty param.province}">
						<c:if test="${not empty locationSearch.a}">
							<h1>Schools Near or with in ${locationSearch.d} kilometer of
								${locationSearch.a}</h1>
						</c:if>
					</c:if>
					<%@include file="../searchtabs.jsp"%>
				</c:if>
				<%@include file="../adsense_728_90.jsp"%>
				<c:forEach items="${contents}" var="newsContent" begin="0"
					varStatus="theCount">
					<c:set var="index" value="${theCount.index}"/>
					<c:if test="${newsContent.type eq 'group'}">
						<%@include file="schoolListing.jsp"%>
					</c:if>
					<c:if test="${newsContent.type ne 'group'}">
						<%@include file="resultCard.jsp"%>
					</c:if>
					<c:if test="${theCount.index eq 5}">
						<%@include file="../adsense_728_90.jsp"%>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="pagignation">
			<%@include file="pagination.jsp"%>
		</div>
		<%@include file="../adsense_728_90.jsp"%>
	</div>
	<div class="right_coloum">
		<div class="news_subscribe">
			<h4>Recent News</h4>
		</div>
		<%@include file="../recentNews.jsp"%>
		<%--  <%@include file="../amazon_336_280.jsp"%> --%>
		<%@include file="recentSearch.jsp"%>
		<%@include file="../adsense_336_280.jsp"%>
	</div>
</div>






<!-- ==================== -->