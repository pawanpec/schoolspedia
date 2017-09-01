<div class="news_subscribe">
			<h4>Recent Search</h4>
</div>

<c:forEach items="${locationSearchData}" var="newsContent" begin="0" end="20"
	varStatus="theCount">
	<div class="news_tick">
		<div class="image_data">
			<div class="news_title">
				<li><a href="/${newsContent.alias }" target="_blank"
					title="${fn:toLowerCase(newsContent.a)}">Schools With in ${newsContent.d} kilometer of ${fn:toLowerCase(newsContent.a)}</a></li>
			</div>
		</div>
	</div>
</c:forEach>
