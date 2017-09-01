<%@ page import="com.spedia.utils.WebConstants"%>

<select id="stateListForSearch">
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
</select>
<input id="searchCity"	class=""
	onfocus="(this.value == 'Enter City Name e.g rohini') && (this.value = '')"
	onblur="(this.value == '') && (this.value = 'Enter City Name e.g rohini')">
<script>
	$(function() {
		$("#searchCity").autocomplete(
				{
					minLength : 2,
					source : function(request, response) {
						$.ajax({
							url : appUrl
									+ "spedia/autoSuggestCity.json?state="
									+ $("#stateListForSearch").val(),
							data : {
								term : request.term
							},
							success : function(data) {
								response(data);
							}
						});
					},
					focus : function(event, ui) {
						$("#searchCity").val(ui.item.title);
						return false;
					},
					click : function(event, ui) {
						$("#searchCity").val("");
						return false;
					},
					select : function(event, ui) {
						$("#searchCity").val("Enter City Name e.g rohini");
						var url = appUrl + '/india/'+ui.item.province+'/schools-in/'+ui.item.city;
						window.open(url);
						return false;
					}
				}).autocomplete("instance")._renderItem = function(ul, item) {
			return $("<li>").append(item.city).appendTo(ul);
		};
	});
	$("#searchCity").val("Enter City Name e.g rohini");
</script>
<br></br>