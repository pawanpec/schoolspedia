<%@ page import="com.spedia.utils.WebConstants"%>
<link rel="stylesheet" type="text/css"
	href="<%=WebConstants.CSS_URL%>css/search.css" />
<script src="<%=WebConstants.JS_URL%>js/search.js"
	type="text/javascript">
	
</script>
<script>
var geocoder=null;
function initMap() {
	 geocoder = new google.maps.Geocoder;
}
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDDDnST4FfwAhVEcG7pGY-G7oQWD_JnxYc&signed_in=true&callback=initMap"
        async defer></script>
<div class="search-school-by">

	<div class="search-school-by-tabs">
		<h2>Search School</h2>
		<ul>
			<li target="1" class="vsble"><a href="#"><img src="<%=WebConstants.IMAGE_URL%>images/bycity.png" align="absmiddle"/> By City</a><span></span></li>
			<li target="2"><a href="#"><img src="<%=WebConstants.IMAGE_URL%>images/byname.png" align="absmiddle"/> By Name</a><span></span></li>
			<li target="3"><a href="#" onclick="getLocation()"> <img src="<%=WebConstants.IMAGE_URL%>images/bydist.png" align="absmiddle"/>By
					Distance</a><span></span></li>
		</ul>
	</div>

	<div class="search-school-area">

		<div id="div1" class="targetDiv">
			<label> <select id="stateListSearchSchoolByCity">
					<option value="delhi">Delhi</option>
					<option value="andaman-nicobar">Andaman and Nicobar
						Islands</option>
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
			</select>
			</label> <label> <input id="searchCity" class="" value='Enter City Name e.g rohini' 
				onfocus="(this.value == 'Enter City Name e.g rohini') && (this.value = '')"
				onblur="(this.value == '') && (this.value = 'Enter City Name e.g rohini')">
			</label>
		</div>

		<div id="div2" class="targetDiv" style="display:none;">
			<label> <select id="stateListSearchSchoolByName">
					<option value="delhi">Delhi</option>
					<option value="andaman-nicobar">Andaman and Nicobar
						Islands</option>
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
			</select>
			</label> <label> <input id="searchSchoolByName" class="" value='Enter School Name e.g dps' 
				onfocus="(this.value == 'Enter School Name e.g dps') && (this.value = '')"
				onblur="(this.value == '') && (this.value = 'Enter School Name e.g dps')">
			</label>
		</div>



	</div>
    
    	<div class="search-school-kmwise">
    
    	<div id="div3" class="targetDiv" style="display:none;">
			<form action="/spedia/searchSchool.html" id="searchSchoolTabs" name="searchSchoolTabs">
				<label> <select id="distance">
						<option value="1">1 km</option>
						<option value="2">2 km</option>
						<option value="3">3 km</option>
						<option value="4">4 km</option>
						<option value="5">5 km</option>
						<option value="6">6 km</option>
						<option value="7">7 km</option>
						<option value="8">8 km</option>
						<option value="9">9 km</option>
						<option value="10">10 km</option>
				</select></label> 
				<label> 
                <input type="text" id="address" value="Please enter your address here" class=""	onClick="this.value=''" />
				</label> 
                <label> 
				<input type="hidden" id="latitude" value="" onClick="this.value=''" /> 
				</label> 
                 <label> 
                 <input type="hidden" id="longitude" value=""	onClick="this.value=''" />
				</label> 
                <label> 
                <input type="submit"	value="SUBMIT" class="sbmt" />
				</label>
			</form>
		</div>
    </div>

	<div class="dnar"></div>
	<div class="clr"></div>
</div>
