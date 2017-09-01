<style>
.col-sm-4{padding-right: 1px !Important;    padding-left: 1px !Important;}
</style>
<c:if test="${theCount.count<14}">
<c:set var="imageCount"
	value="${theCount.count}" scope="session" />
</c:if>
<c:if test="${theCount.count>13}">
<c:set var="imageCount"
	value="${theCount.count-11}" scope="session" />
</c:if>
<li class="col-sm-4">
	<div class="tile tile-height">
	<div class="school_pic">
			<a href="/${newsContent.alias }" target="_blank"
				title="${fn:toLowerCase(newsContent.title)}">
				<c:if test="${empty newsContent.schoolsImages.BackGroundImagePath}">
				 <img alt="${fn:toLowerCase(newsContent.title)}" src="/spedia/images/static/${imageCount}.jpg"
				 style="width: 100%">
				</c:if>
				 <c:if test="${not empty newsContent.schoolsImages.BackGroundImagePath}">
							<img src='<%=WebConstants.IMAGE_URL %>${newsContent.schoolsImages.BackGroundImagePath}' style="width: 100%"/>
				 </c:if>
				
				</a>
		</div>
		<div class="school_title">
			<a href="/${newsContent.alias }" target="_blank"
				title="${fn:toLowerCase(newsContent.title)}"><strong>${fn:toLowerCase(newsContent.title)}</strong></a>
			<div>
				<span class="stars"> 
				<c:if test="${not empty content.review.oar}">
				<fmt:formatNumber
						value="${newsContent.review.oar}" pattern="0.0" /></span>
				<fmt:formatNumber value="${newsContent.review.oar}" pattern="0.0" />
				/5
				</c:if>
				<c:if test="${empty content.review.oar}">
							2.5/5
				</c:if>
			</div>
		</div>
		<div class="school_description">
			<c:if test="${not empty newsContent.body.summary}">
				<p class="ellipsetext newscontentdesc">${fn:substring(newsContent.body.summary, 0, 100)}</p>
			</c:if>
			<c:if test="${empty newsContent.body.summary}">
				<p class="ellipsetext newscontentdesc">${fn:substring(newsContent.body.value, 0, 100)}</p>
			</c:if>
		</div>
		 <c:if test="${fn:contains(newsContent.f, uid)}">
								<input type="button" class="btn btn-xs btn-primary" id="followSchool_${index}" onclick="follow(${newsContent.nid},this,'${uid}');" value="FOLLOWING">
							</c:if>
							<c:if test="${not fn:contains(newsContent.f, uid)}">
								<input type="button" class="btn btn-xs btn-primary" id="followSchool_${index}" onclick="follow(${newsContent.nid},this,'${uid}');" value="FOLLOW">
		</c:if>
		<input type="button" class="btn btn-xs btn-primary" id="addToCompare${newsContent.nid}" onclick="addToCompare(${newsContent.nid},this);" value="Add To Compare">
	</div>
</li>
