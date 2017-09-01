<%@ include file="/WEB-INF/pages/include.jsp"%>
<title>Compare ${contents[0].title} |${contents[1].title}|${contents[2].title} </title>
<div class="left_coloum">

	<table style="width:100%">
		<tr>
			<th><c:out value="Keys" /></th>
			<c:forEach items="${contents}" var="newsContent" begin="0"
					varStatus="theCount">
					<th><a href="/${newsContent.alias}">${newsContent.title}</a></th>
	        </c:forEach>
		</tr>
		<tr>
			<td>Overall Rating</td>
			<c:forEach items="${contents}" var="newsContent" begin="0"
					varStatus="theCount">
					<td><span class="stars" style="margin-top:5px"
						title='<fmt:formatNumber value="${newsContent.review.oar}" 
						pattern="0.0" />/5'>
						<fmt:formatNumber value="${newsContent.review.oar}" pattern="0.0" />
					</span>
					(<fmt:formatNumber value="${newsContent.review.oar}" pattern="0.0" />/5)
					</td>
	        </c:forEach>
		</tr>
		<tr>
			<td>Academic Result</td>
			<c:forEach items="${contents}" var="newsContent" begin="0"
					varStatus="theCount">
					<td><span class="stars" style="margin-top:5px"
						title='<fmt:formatNumber value="${newsContent.review.ora}" 
						pattern="0.0" />/5'>
						<fmt:formatNumber value="${newsContent.review.ora}" pattern="0.0" />
					</span>
					(<fmt:formatNumber value="${newsContent.review.ora}" pattern="0.0" />/5)
					</td>
	        </c:forEach>
		</tr>
		<tr>
			<td>Infrastructure and facilities</td>
			<c:forEach items="${contents}" var="newsContent" begin="0"
					varStatus="theCount">
					<td><span class="stars" style="margin-top:5px"
						title='<fmt:formatNumber value="${newsContent.review.orb}" 
						pattern="0.0" />/5'>
						<fmt:formatNumber value="${newsContent.review.orb}" pattern="0.0" />
					</span>
					(<fmt:formatNumber value="${newsContent.review.orb}" pattern="0.0" />/5)
					</td>
	        </c:forEach>
		</tr>
		<tr>
			<td>Attention to students</td>
			<c:forEach items="${contents}" var="newsContent" begin="0"
					varStatus="theCount">
					<td><span class="stars" style="margin-top:5px"
						title='<fmt:formatNumber value="${newsContent.review.orc}" 
						pattern="0.0" />/5'>
						<fmt:formatNumber value="${newsContent.review.orc}" pattern="0.0" />
					</span>
					(<fmt:formatNumber value="${newsContent.review.orc}" pattern="0.0" />/5)
					</td>
	        </c:forEach>
		</tr>
		<tr>
			<td>Co-curricular activities</td>
				<c:forEach items="${contents}" var="newsContent" begin="0"
					varStatus="theCount">
					<td><span class="stars" style="margin-top:5px"
						title='<fmt:formatNumber value="${newsContent.review.ord}" 
						pattern="0.0" />/5'>
						<fmt:formatNumber value="${newsContent.review.ord}" pattern="0.0" />
					</span>
					(<fmt:formatNumber value="${newsContent.review.ord}" pattern="0.0" />/5)
					</td>
	        </c:forEach>
		</tr>
		<tr>
			<td>Quality of faculty</td>
			<c:forEach items="${contents}" var="newsContent" begin="0"
					varStatus="theCount">
					<td><span class="stars" style="margin-top:5px"
						title='<fmt:formatNumber value="${newsContent.review.ore}" 
						pattern="0.0" />/5'>
						<fmt:formatNumber value="${newsContent.review.ore}" pattern="0.0" />
					</span>
					(<fmt:formatNumber value="${newsContent.review.ore}" pattern="0.0" />/5)
					</td>
	        </c:forEach>
		</tr>
	</table>
</div>
<div class="right_coloum">
	<%@include file="../adsense_336_280.jsp"%>
</div>
<style>
table,th,td {
	border: 1px solid black;
	border-collapse: collapse;
}

th,td {
	padding: 5px;
}
</style>
