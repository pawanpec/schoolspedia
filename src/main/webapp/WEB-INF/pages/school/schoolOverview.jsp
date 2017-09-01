<c:if test="${not empty content.sd}">
<div class="tab_main">
	<ul id="basic-Details">
			<p>
				Basic Details of <b>${fn:toLowerCase(content.title)}</b>
			</p>
			<li><div>Name of School</div>
				<div>${fn:toLowerCase(content.sd.SN)}</div></li>
			<li><div>Year of Foundation</div>
				<div>${content.sd.YOF}</div></li>
			<li><div>Affiliation Number</div>
				<div>${content.sd.AFF_NO}</div></li>
			<li><div>School Code</div>
				<div>${content.schoolCode}</div></li>
			<li><div>Status of The School</div>
				<div>${content.sd.SOS}</div></li>
			<li><div>Name of Trust/ Society/ Managing Committee</div>
				<div>${content.sd.TN}</div></li>
			
	</ul>
	<ul id="contact-Details">
			<p>
				Contact Details of <b>${fn:toLowerCase(content.title)}</b>
			</p>
		<li><div>Email</div>
			<div><c:if test="${not empty content.sd.E}">
			<img src="/spedia/textToImage?text=${content.sd.E}">
			</c:if></div></li>
		<li><div>Website</div>
			<a href="${content.sd.W}" rel="nofollow" target="_blank">${content.sd.W}</a></li>
		<li><div>Postal Address</div>
			<div>${content.sd.PA}</div></li>
		<li><div>Pin Code</div>
			<div>${content.sd.PIN}</div></li>
		<li><div>Phone No.</div>
			<div>
			<c:if test="${not empty content.sd.PHONE_NO}">
				${content.sd.PHONE_NO}
			</c:if>
			</div>
		</li>
	</ul>
	<c:if test="${not empty content.result}">
		<ul id="result-Details">
				<p>
					CBSE-2015:Result Analysis <b>${fn:toLowerCase(content.title)}</b>
				</p>
			<li><div>Number of Candidates</div>
				<div>${content.result.noc}</div></li>
			<li><div>Average Aggregate (Computed using English
			 + Best 4 Subjects wherever the candidate appears
			  for more than 4 subjects)</div>
				<div>${content.result.aa}</div></li>
		</ul>
	</c:if>
</div>
</c:if>
<c:if test="${not empty content.schoolBean.LogoPath}">
	<img src="<%=WebConstants.LOGO_URL %>${content.schoolBean.LogoPath}"
		alt="${fn:toLowerCase(content.title)}" data-errsrc="round" data-font="30"
		data-width="60" style="width:63px;" />
</c:if>
<c:if test="${ empty content.schoolBean.LogoPath}">
	<span data-firstletter="${fn:toLowerCase(content.title)}" class="width60 position"
		data-type="round" data-font="30"></span>
</c:if>
<c:if test="${content.type eq 'group' and empty content.sd}">
					${content.body.value}
				</c:if>
<c:if test="${content.type ne 'group'}">
					${content.body.value}
				</c:if>


