<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/pages/include.jsp"%>
<script type="text/javascript"
	src="<%=WebConstants.JS_URL%>js/ckeditor/ckeditor.js"></script>

<c:if test="${not empty schoolInformation.schoolName}">
	<h3 align="center">Update School Information of
		${schoolInformation.schoolName}</h3>
</c:if>
<c:if test="${empty schoolInformation.schoolName}">
	<h3 align="center">Add School</h3>
</c:if>
<div class="edit-school-form">
	<form:form method="POST" action="submitSchoolInfo.html"
		acceptCharset="utf-8" modelAttribute="schoolInformation"
		enctype="multipart/form-data">
		<label><span>Title</span> <input type="text" name="title"
			id="robots" value="${schoolInformation.content.title}" /> </label>
		<br>
		<br>
		<label>School Content</label>
		<form:textarea cssClass="ckeditor" path="schoolContent" />

		<br />

		<label>School Summary</label>
		<form:textarea cssClass="ckeditor" path="schoolSummary" />



		<br />

		<label><span>Logo File</span> <form:input type="file"
				path="logoFile" /></label>

		<br />

		<label><span>School Background image </span> <form:input
				type="file" path="backgroundImage" /></label>

		<form:hidden path="sid" value="${param.sid}" />
		<br />
		<label> Image Gallery</label>
		<br />
		<label style="margin-left:320px">
			<div>
				Image1
				<form:input type="file" path="imageGallery" multiple="multiple" />
			</div>
			<div>
				Image2
				<form:input type="file" path="imageGallery" multiple="multiple" />
			</div>
			<div>
				Image3
				<form:input type="file" path="imageGallery" multiple="multiple" />
			</div>
		</label>

		<br />

		<br></br>
		<label><span>First Draw Date</span> <input type="text"
			name="drawDate1" id="drawDate1" /> </label>
		<label><span>Second Draw Date</span> <input type="text"
			name="drawDate2" id="drawDate2" /> </label>
		<label><span>Third Draw Date</span> <input type="text"
			name="drawDate3" id="drawDate3" /> </label>
		<label><span>Admission Form Last Date</span> <input
			type="text" name="afld" id="afld" /> </label>
		<label><span>School Pre School Seats</span> <form:input
				path="schoolData.PS_SEATS" /> </label>
		<br>
		<label><span>School Pre Primary Seats</span> <form:input
				path="schoolData.PP_SEATS" /> </label>
		<br>
		<label><span>School FEES Per Month (For Nursery Class)</span>
			<form:input path="schoolData.FEES" /> </label>
		<label><span>Latitude</span> <input type="text" name="lat"
			id="lat" /> </label>
		<label><span>Longitude</span> <input type="text" name="lng"
			id="lng" /> </label>
		<label><span>School Email</span> <form:input
				path="schoolData.E" /> </label>

		<br>
		<label><span> School Contact Number</span> <form:input
				path="schoolData.PHONE_NO" /> </label>
				
	   <label><span>State</span> <select id="stateList" name="state">
	<option value="delhi">Delhi</option>
	<option value="andaman-nicobar">Andaman and Nicobar Islands</option>
	<option value="andhra pradesh">Andhra Pradesh</option>
	<option value="arunachal pradesh">Arunachal Pradesh</option>
	<option value="assam">Assam</option>
	<option value="bihar">Bihar</option>
	<option value="chandigarh">Chandigarh</option>
	<option value="chhattisgarh">Chhattisgarh</option>
	<option value="dadra-nagar-haveli">Dadra and Nagar Haveli</option>
	<option value="daman-diu">Daman and Diu</option>
	<option value="goa">Goa</option>
	<option value="gujarat">Gujarat</option>
	<option value="haryana">Haryana</option>
	<option value="himachal pradesh">Himachal Pradesh</option>
	<option value="jammu-kashmir">Jammu and Kashmir</option>
	<option value="jharkhand">Jharkhand</option>
	<option value="karnataka">Karnataka</option>
	<option value="kerala">Kerala</option>
	<option value="lakshadweep">Lakshadweep</option>
	<option value="madhya pradesh">Madhya Pradesh</option>
	<option value="maharashtra">Maharashtra</option>
	<option value="manipur">Manipur</option>
	<option value="meghalaya">Meghalaya</option>
	<option value="mizoram">Mizoram</option>
	<option value="nagaland">Nagaland</option>
	<option value="orissa">Orissa</option>
	<option value="pondicherry">Pondicherry</option>
	<option value="punjab">Punjab</option>
	<option value="rajasthan">Rajasthan</option>
	<option value="sikkim">Sikkim</option>
	<option value="tamil nadu">Tamil Nadu</option>
	<option value="tripura">Tripura</option>
	<option value="uttaranchal">Uttaranchal</option>
	<option value="uttar pradesh">Uttar Pradesh</option>
	<option value="west bengal">West Bengal</option>
</select> </label>
		<label><span>City</span> <input type="text"
			name="city" id="city" /> </label>

		<label><span>Meta keywords </span> <input type="text"
			name="meta_keywords" id="meta_keywords"
			value="${schoolInformation.content.meta_keywords}" /> </label>
		<label><span>Meta description</span> <input type="text"
			name="meta_description" id="meta_description"
			value="${schoolInformation.content.meta_description}" /> </label>
		<label><span>Robots</span> <input type="text" name="robots"
			id="robots" value="${schoolInformation.content.robots}" /> </label>
		<label><span>Tags</span> <input type="text"
			name="tags" id="tags" /> </label>
		<label><input type="submit" value="Submit"
			class="add-sbmt-btn" /></label>
	</form:form>
</div>
<script>
	$(function() {
		$("#drawDate1").datepicker({
			dateFormat : 'dd-mm-yy'
		});
	});
	$(function() {
		$("#drawDate2").datepicker({
			dateFormat : 'dd-mm-yy'
		});
	});
	$(function() {
		$("#drawDate3").datepicker({
			dateFormat : 'dd-mm-yy'
		});
	});
	$(function() {
		$("#afld").datepicker({
			dateFormat : 'dd-mm-yy'
		});
	});
</script>
