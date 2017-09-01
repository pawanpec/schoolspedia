<%@ page import="com.spedia.utils.WebConstants"%>
<!doctype html>
<html>
<head>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="<%=WebConstants.CSS_URL%>css/search.css" />
<script src="<%=WebConstants.JS_URL%>js/jquery-1.10.2.min.js"
	type="text/javascript"></script>
<script src="<%=WebConstants.JS_URL%>js/jquery-ui.js"
	type="text/javascript">
	
</script>
<script src="<%=WebConstants.JS_URL%>js/search.js"
	type="text/javascript">
	
</script>
<script type="text/javascript">
	var appUrl = "http://test.schoolspedia.com/";
</script>
</head>
<body>

	<div class="search-school-by">

		<div class="search-school-by-tabs">
			<h2>Search School</h2>
			<ul>
				<li target="1" class="vsble"><a href="#">By City</a><span></span></li>
				<li target="2"><a href="#">By Name</a><span></span></li>
				<li target="3"><a href="#" onclick="getLocation()">By
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
				</label> <label> <input id="searchCity" class=""
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
				</label> <label> <input id="searchSchool" class=""
					onfocus="(this.value == 'Enter School Name e.g dps') && (this.value = '')"
					onblur="(this.value == '') && (this.value = 'Enter School Name e.g dps')">
				</label>
			</div>


			<div id="div3" class="targetDiv" style="display:none;">
				<form action="">
					<label> <select id="distance">
							<option value="1">1km</option>
							<option value="2">2km</option>
					</select> <label style="width:78%">
					 <input type="hidden" id="latitude" 
							value="" onClick="this.value=''" /> 
							<input type="hidden" 
							id="longitude" value="" onClick="this.value=''" />
							<input type="text" 
							id="address" value="" onClick="this.value=''" />
					</label> 
					<label style="width:20%"> <input type="submit"
							value="SUBMIT" class="sbmt" />
					</label>
				</form>
			</div>


		</div>

		<div class="dnar"></div>
		<div class="clr"></div>
	</div>
	<script>
		var x = document.getElementById("demo");
		var geocoder=null;
		function getLocation() {
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(showPosition);
			} else {
				x.innerHTML = "Geolocation is not supported by this browser.";
			}
		}
		function showPosition(position) {
			$("#latitude").val(position.coords.latitude);
			$("#longitude").val(position.coords.longitude);
			var latlng = {lat: parseFloat(position.coords.latitude), lng: parseFloat(position.coords.longitude)};
			geocoder.geocode({'location': latlng}, function(results, status) {
			    if (status === google.maps.GeocoderStatus.OK) {
			      if (results) {
			    	  $("#address").val(results[0].formatted_address);
			      } else {
			    	  console.log('No results found');
			      }
			    } else {
			    	console.log('Geocoder failed due to: ' + status);
			    }
			  });
			
		}
		function initMap() {
			 geocoder = new google.maps.Geocoder;
		}
		$(document).ready(function() {
			$("form").submit(function() {
				var url = appUrl + 'spedia/searchSchool.html?distance='+$("#distance").val()+'&longitude='+$("#longitude").val()+
						'&latitude='+$("#latitude").val();
				window.open(url);
				return false;
			});
		});
	</script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDDDnST4FfwAhVEcG7pGY-G7oQWD_JnxYc&signed_in=true&callback=initMap"
        async defer></script>
</body>
</html>
