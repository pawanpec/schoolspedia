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
		$(document).ready(function() {
			$("form").submit(function() {
				var url = appUrl + 'spedia/searchSchool.html?distance='+$("#distance").val()+'&longitude='+$("#longitude").val()+
						'&latitude='+$("#latitude").val()+'&address='+$("#address").val();
				window.open(url);
				return false;
			});
		});
$(document).ready(function() {
	$('.search-school-by-tabs li:nth-child(1)').click(function() {
		$('.search-school-by-tabs li:nth-child(1)').addClass('vsble');
		$('.search-school-by-tabs li:nth-child(2)').removeClass('vsble');
		$('.search-school-by-tabs li:nth-child(3)').removeClass('vsble');
	});

	$('.search-school-by-tabs li:nth-child(2)').click(function() {
		$('.search-school-by-tabs li:nth-child(2)').addClass('vsble');
		$('.search-school-by-tabs li:nth-child(1)').removeClass('vsble');
		$('.search-school-by-tabs li:nth-child(3)').removeClass('vsble');
	});

	$('.search-school-by-tabs li:nth-child(3)').click(function() {
		$('.search-school-by-tabs li:nth-child(3)').addClass('vsble');
		$('.search-school-by-tabs li:nth-child(1)').removeClass('vsble');
		$('.search-school-by-tabs li:nth-child(2)').removeClass('vsble');
	});

	$('.search-school-by-tabs li').click(function() {
		$('.targetDiv').slideUp();
		$('.targetDiv').hide();
		$('#div' + $(this).attr('target')).slideToggle();
	});
});

	$(function() {
		$("#searchSchoolByName").autocomplete(
				{
					minLength : 2,
					source : function(request, response) {
						$.ajax({
							url : appUrl
									+ "spedia/autoSuggestSchools.json?state="
									+ $("#stateListSearchSchoolByName").val(),
							data : {
								term : request.term
							},
							success : function(data) {
								response(data);
							}
						});
					},
					focus : function(event, ui) {
						$("#searchSchoolByName").val(ui.item.title);
						return false;
					},
					click : function(event, ui) {
						$("#searchSchoolByName").val("");
						return false;
					},
					select : function(event, ui) {
						$("#searchSchoolByName").val("Enter School Name e.g dps");
						var url = appUrl + '/spedia/search.html?nid='
								+ ui.item.id;
						window.open(url);
						return false;
					}
				}).autocomplete("instance")._renderItem = function(ul, item) {
			return $("<li>").append(item.title).appendTo(ul);
		};
	});
	$("#searchSchoolByName").val("Enter School Name e.g dps");

	$(function() {
		$("#searchCity").autocomplete(
				{
					minLength : 2,
					source : function(request, response) {
						$.ajax({
							url : appUrl
									+ "spedia/autoSuggestCity.json?state="
									+ $("#stateListSearchSchoolByCity").val(),
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