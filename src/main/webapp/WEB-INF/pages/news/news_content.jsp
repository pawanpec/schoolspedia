<%@ include file="/WEB-INF/pages/include.jsp"%>
<title>${fn:toLowerCase(content.title)}</title>
<!--######## Main Container Start Here ###########-->
<div class="container">
	<ol vocab="http://schema.org/" typeof="BreadcrumbList" class="breadcrumb">
		<li property="itemListElement" typeof="ListItem"><a href="/" property="item" typeof="WebPage"><img
				src="<%=WebConstants.IMAGE_URL%>images/spacer.gif"
				class="cus-icon cus-home-small" alt="home" /></a></li>
		<c:if test="${fn:contains(content.type, 'nursery_admission')}">
			<li property="itemListElement" typeof="ListItem"  class="active"><a href="/nursery-admission" property="item" typeof="WebPage">Nursery Admission</a></li>
		</c:if>
			<c:if test="${content.type eq 'summer_camp'}">
				<li property="itemListElement" typeof="ListItem"  class="active"><a href="/summer-camp" property="item" typeof="WebPage">Summer Camp</a></li>
			</c:if>
		<li property="itemListElement" typeof="ListItem"  class="active"><a href="/${content.alias}" property="item" typeof="WebPage">${fn:toLowerCase(content.title)}</a>
			<c:if test="${isAdmin }">
			 <a href="/spedia/edit_content.html?url=${content.alias}" property="item" typeof="WebPage">|Edit</a>
			 </c:if>
		</li>
	</ol>
</div>
<div class="container">
	<h1>${fn:toLowerCase(content.title)}</h1>
	<span class="listing-add-this">
				<div class="addthis_native_toolbox"></div>
	    </span>
	<div class="left_coloum">
		<div class="details_grid">
		<c:if test="${content.type ne 'discussion'}">
			<div class="details_image">
			<c:if test="${not empty content.imagePath}">
					<img id="contentImage" alt="${content.imagePath}"
						src="${content.imagePath}">
			</c:if>
				<c:if test="${empty content.imagePath}">
					<img id="contentImage" alt="Schoolspedia"
						src="/spedia/images/contentimages/nr.jpg">
					<%@include file="../adsense_728_90.jsp"%>
					<c:if test="${content.type eq 'summer_camp'}">
						<script type="text/javascript">
							document.getElementById("contentImage").src = '/spedia/images/summer_camp/'
									+ random[0] + '.jpg';
						</script>
					</c:if>
					<c:if test="${fn:contains(content.type, 'nursery_admission')}">
						<script type="text/javascript">
							document.getElementById("contentImage").src = '/spedia/images/nursery_admission/'
									+ random[0] + '.jpg';
						</script>
					</c:if>
					 <c:if test="${not empty group.schoolsImages.BackGroundImagePath}">
					 		<script type="text/javascript">
								document.getElementById("contentImage").src = '/spedia/'+'${group.schoolsImages.BackGroundImagePath}';
						   </script>
					</c:if>
				</c:if>

			</div>
			</c:if>
			<div class="content_details">${content.body.value}</div>
			<jsp:useBean id="myDate" class="java.util.Date" />
			<c:set target="${myDate}" property="time"
				value="${content.changed*1000}" />
			<div class="date_tag">
				<fmt:formatDate value="${myDate}" pattern="dd-MMM-yyyy  HH:mm:ss z" />
			</div>
			<c:if test="${not empty postedByUser.id}">
				<div>
				Posted By <a href="${postedByUser.link}" target="_blank"><img src="https://graph.facebook.com/${postedByUser.id}/picture"></a>
				<a href="${postedByUser.link}" target="_blank">${postedByUser.name}</a>
				 </div>
			 </c:if>
			 <br>
			 <c:if test="${not empty group}">
			    check more detail at <a href="/${group.alias}">${fn:toLowerCase(group.title)}</a>
			 </c:if>
			 <c:forEach items="${content.tags}" var="tag">	
			  <a href="/tags/tags/${tag}">${tag}</a> |
			</c:forEach>
		</div>
		<%@include file="../adsense_728_90.jsp"%>
		<div class="fb-comments" data-numposts="5" data-colorscheme="light"></div>
	</div>
	<div class="right_coloum">
		 <c:if test="${content.type eq 'play_school'}">
	    	<%@include file="../school/admissionApplication.jsp"%>
	    </c:if>
		<%@include file="../adsense_336_280.jsp"%>
		<div class="news_subscribe">
			<h4>Recent News</h4>
		</div>
		<%@include file="../recentNews.jsp"%>
		<%-- <%@include file="../amazon_336_280.jsp"%> --%>
		<%@include file="../adsense_336_280.jsp"%>
	</div>
</div>


