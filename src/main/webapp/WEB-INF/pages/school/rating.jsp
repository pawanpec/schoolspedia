<c:if test="${not empty content.review.ora}">
			<div class="review_bars_green">
				<div style="display:inline" class="pull-left">
					<span class="stars" style="margin-top:5px"
						title='<fmt:formatNumber value="${content.review.ora}" 
						pattern="0.0" />/5'>
						<fmt:formatNumber value="${content.review.ora}" pattern="0.0" />
					</span>
				</div>
				<span>Academic Result</span>
			</div>
		</c:if>
		<c:if test="${empty content.review.ora}">
			<div class="review_bars_green">
				<div style="display:inline" class="pull-left">
					<span class="stars" style="margin-top:5px"
						title='<fmt:formatNumber value="2.5" 
						pattern="0.0" />/5'>
						<fmt:formatNumber value="2.5" pattern="0.0" />
					</span>
				</div>
				<span>Academic Result</span>
			</div>
		</c:if>
		<c:if test="${not empty content.review.orb}">
			<div class="review_bars_orange">
				<div style="display:inline" class="pull-left">
					<span class="stars" style="margin-top:5px"
						title='<fmt:formatNumber value="${content.review.orb}"
						pattern="0.0" />/5'><fmt:formatNumber
							value="${content.review.orb}" pattern="0.0" /></span>
				</div>
				<span>Infrastructure and facilities</span>
			</div>
		</c:if>
		<c:if test="${empty content.review.orb}">
				<div class="review_bars_orange">
				<div style="display:inline" class="pull-left">
					<span class="stars" style="margin-top:5px"
						title='<fmt:formatNumber value="2.5"
						pattern="0.0" />/5'><fmt:formatNumber
							value="2.5" pattern="0.0" /></span>
				</div>
				<span>Infrastructure and facilities</span>
			</div>
		</c:if>
		<c:if test="${not empty content.review.orc}">
			<div class="review_bars_black">
				<div style="display:inline" class="pull-left">
					<span class="stars" style="margin-top:5px"
						title='<fmt:formatNumber value="${content.review.orc}"
						pattern="0.0" />'><fmt:formatNumber
							value="${content.review.orc}" pattern="0.0" /></span>
				</div>
				<span>Attention to students</span>
			</div>
		</c:if>
		<c:if test="${empty content.review.orc}">
			<div class="review_bars_black">
				<div style="display:inline" class="pull-left">
					<span class="stars" style="margin-top:5px"
						title='3"
						pattern="0.0" />'><fmt:formatNumber
							value="2.5" pattern="0.0" /></span>
				</div>
				<span>Attention to students</span>
			</div>
		</c:if>
		<c:if test="${not empty content.review.ord}">
			<div class="review_bars_red">
				<div style="display:inline" class="pull-left">
					<span class="stars" style="margin-top:5px"
						title='<fmt:formatNumber value="${content.review.ord}"
						pattern="0.0" />'><fmt:formatNumber
							value="${content.review.ord}" pattern="0.0" /></span>
				</div>
				<span>Co-curricular activities</span>
			</div>
		</c:if>
		<c:if test="${empty content.review.ord}">
			<div class="review_bars_red">
				<div style="display:inline" class="pull-left">
					<span class="stars" style="margin-top:5px"
						title='<fmt:formatNumber value="2.5"
						pattern="0.0" />'><fmt:formatNumber
							value="2.5" pattern="0.0" /></span>
				</div>
				<span>Co-curricular activities</span>
			</div>
		</c:if>
		<c:if test="${not empty content.review.ore}">
			<div class="review_bars_blue">
				<div style="display:inline" class="pull-left">
					<span class="stars" style="margin-top:5px;"
						title='<fmt:formatNumber value="${content.review.ore}"
						pattern="0.0" />'><fmt:formatNumber
							value="${content.review.ore}" pattern="0.0" /></span>
				</div>
				<span>Quality of faculty</span>
			</div>
		</c:if>
		<c:if test="${empty content.review.ore}">
			<div class="review_bars_blue">
				<div style="display:inline" class="pull-left">
					<span class="stars" style="margin-top:5px;"
						title='<fmt:formatNumber value="2.5"
						pattern="0.0" />'><fmt:formatNumber
							value="2.5" pattern="0.0" /></span>
				</div>
				<span>Quality of faculty</span>
			</div>
		</c:if>