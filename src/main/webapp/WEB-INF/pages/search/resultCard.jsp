<style>
.col-sm-4{padding-right: 1px !Important;    padding-left: 1px !Important;}
</style>
<li class="col-sm-4">
	<div class="tile tile-height">
	  <div class="school_pic">
            <a href="/${newsContent.alias }" target="_blank" title="${fn:toLowerCase(newsContent.title)}"> 
             <c:if test="${not empty newsContent.imagePath}">
							<img src='${newsContent.imagePath}' style="width: 100%"/>
			</c:if>
            <c:if test="${newsContent.type eq 'summer_camp'}">
                <img id="contentImage" alt="${fn:toLowerCase(newsContent.title)}"
                    src="/spedia/images/summer_camp/${theCount.count}.jpg"
                    >
            </c:if>
            <c:if test="${fn:contains(newsContent.type, 'nursery_admission')}">
                <img id="contentImage" alt="${fn:toLowerCase(newsContent.title)}"
                    src="/spedia/images/nursery_admission/${theCount.count}.jpg"
                    >
            </c:if>
            </a>
        </div>
        <div class="school_title">
            <a href="/${newsContent.alias }" target="_blank"
                title="${fn:toLowerCase(newsContent.title)}"><strong>${fn:toLowerCase(newsContent.title)}</strong></a>
        </div>
   <%--       <c:if test="${newsContent.type ne 'summer_camp'}">
	        <div class="school_description">
	            <c:if test="${not empty newsContent.body.summary}">
	                <p class="ellipsetext newscontentdesc">${fn:substring(newsContent.body.summary, 0, 100)}</p>
	            </c:if>
	            <c:if test="${empty newsContent.body.summary}">
	                <p class="ellipsetext newscontentdesc">${fn:substring(newsContent.body.value, 0, 100)}</p>
	            </c:if>
	        </div>
        </c:if> --%>
      
        </div>
</li>




