<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<script>
  (adsbygoogle = window.adsbygoogle || []).push({
    google_ad_client: "ca-pub-9195507629739815",
    enable_page_level_ads: true
  });
</script>
<c:if test="${empty content.robots and not empty content.rid}">
	<meta name="robots" content="noindex,nofollow">
</c:if>
<c:if test="${not empty param.pageNumber}">
	<meta name="robots" content="noindex,nofollow">
</c:if>
<c:if test="${empty content.robots and not empty param.nid}">
	<meta name="robots" content="noindex,nofollow">
</c:if>
<c:if test="${fn:contains(pageContext.request.localName, '.schoolspedia.com')}">
	<meta name="robots" content="noindex,nofollow">
</c:if>
<%-- <c:if test="${empty content.robots and content.type eq 'group' and empty content.body.summary}">
	<meta name="robots" content="noindex,nofollow">
</c:if> --%>
<c:if test="${not empty content.meta_description}">
	<meta name="description" content="${content.meta_description}" />
</c:if>
<c:if test="${not empty content.meta_keywords}">
	<meta name="keywords" content="${content.meta_keywords}" />
</c:if>
<c:if test="${not empty content.robots}">
	<meta name="robots" content="${content.robots}" />
</c:if>
<c:if test="${fn:contains(content.type, 'nursery_admission')}">
	<c:if
		test="${empty content.meta_description}">
		<meta name="description"
			content="${fn:toLowerCase(content.title)} Rating,Reviews,Forms, Forum, Admission, Fees, Draw, Point, Criteria, Results, 2015-2016, KG, Documents, 
			Schools, Age, Delhi, Nursery, Online, Admissions, Dates" />
	</c:if>
	<c:if test="${empty content.meta_keywords}">
		<meta name="keywords"
			content="Rating,Reviews,Forms, Forum, Admission, Fees, Draw, Point, Criteria, Results, 2015-2016, KG, Documents, Schools, Age, Delhi, Nursery, Online, Admissions, Dates" />
	</c:if>
</c:if>
<c:if test="${fn:contains(content.type, 'group')}">
	<c:if
		test="${empty content.meta_description}">
		<meta name="description"
			content="${fn:toLowerCase(content.title)} School Address Contact Detail |
Reviews Rating" />
	</c:if>
	<c:if test="${empty content.meta_keywords}">
		<meta name="keywords"
			content="${fn:toLowerCase(content.title)} School Address, Contact Detail ,
Contact Number, Email ,Mobile Number,Reviews,Rating,Nursery Admission Information " />
	</c:if>
</c:if>