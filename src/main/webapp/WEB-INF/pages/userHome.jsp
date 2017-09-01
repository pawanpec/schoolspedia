<%@ include file="include.jsp"%>
	  <div class="left_coloum">
	    <input type="button" class="btn btn-xs btn-primary" id="compareSchool" onclick="compareSchool();" value="compare">
		<c:forEach items="${contents}" var="newsContent" begin="0"
					varStatus="theCount">
					<c:set var="index" value="${theCount.index}"/>
					<%@include file="../pages/search/schoolListing.jsp"%>
	  </c:forEach>
	  </div>
	  <div class="right_coloum">
		<%@include file="../pages/school/schoolNews.jsp"%>
		<%@include file="adsense_336_280.jsp"%>
	</div>
