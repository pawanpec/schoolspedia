<div class="school_review" id="reviews">
	<p>	Reviews of ${fn:toLowerCase(content.title)} <fmt:formatNumber
				value="${content.review.oar}" pattern="0.0" />/5
			(${fn:length(reviews)} Review)</p>
			
	<ul style="margin-top: 10px">
		<c:forEach items="${reviews}" var="review">
			<li>${review.review }  Posted by 
				<c:if test="${not empty review.name}">
				<b>${review.name}</b>
				</c:if>
				<c:if test="${empty review.name}">
				  <b>Anonymous</b>
				</c:if></li>
		</c:forEach>
	</ul>
</div>