function addToCompare(nid,item) {
    var divId = $(item).attr("id");
	var val = $(item).attr("value");
    var index=$.inArray(nid, compareSchoolData);
    if (index == -1) {
				// change the value of follow button to following.
		document.getElementById(divId).value = "added";
		compareSchoolData.push(nid); 
	}else{
		// change the value of follow button to following.
		compareSchoolData.pop(nid); 
		document.getElementById(divId).value = "Add To Compare"
	}
	if(compareSchoolData.length==3){
		     compareSchool();
	}
}
function compareSchool() {
if(compareSchoolData.length==1){
	alert("Add 1 more school to compare.");
	return;
}
var compareUrl="/spedia/compare-school.html?ids="+compareSchoolData;
window.location=compareUrl;
}
function registerUser(data) {
	var queryString = "?data="+JSON.stringify(data) + "&socialType=fb";
	var aurl = "/spedia/registerUser.html" + queryString;
	$.ajax({
		url : aurl,
		success : function(result) {
			console.log("after login " + result);
				window.location=result;

		}
	});
}
function follow(nid, item,uid) {
	var divId = $(item).attr("id");
	var val = $(item).attr("value");
	var status = 0;
	if(uid==null||uid==''||uid === undefined){
	   alert("please login to follow school");
	   window.location="/spedia/login.html";
	}
	if (val == 'FOLLOW') {
		status = 1;
	}
	if (val == 'FOLLOWING') {
		status = 0;
	}
	var queryString = "?nid=" + nid + "&status=" + status;
	var followUrl = "/spedia/followSchool.html" + queryString;
	$.ajax({
		url : followUrl,
		success : function(result) {
			if (result == "1") {
				// change the value of follow button to following.
				document.getElementById(divId).value = "FOLLOWING"
			}
			if (result == "0") {
				// change the value of follow button to following.
				document.getElementById(divId).value = "FOLLOW"
			}
			if (result == null) {
				// change the value of follow button to following.
				document.getElementById(divId).value = "Error"
			}

		}
	});
}

function subscibeUser() {
	var email = document.subscription.userEmail;
	if (ValidateEmail(email)) {
		var queryString = "email=" + email.value + "&op=s"
		var aurl = "/spedia/subscibeUser.html?" + queryString;
		$
				.ajax({
					url : aurl,
					success : function(result) {
						console.log("after login " + result);
						if (result == "1") {
							document.getElementById('resultMessage').innerHTML = 'You have successfully subscribed';
							$("#subscriptionForm").hide();
						} else {
							document.getElementById('resultMessage').innerHTML = 'There is some techincal Error';
						}

					}
				});
	}
}
function ValidateEmail(inputText) {
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	email=inputText.value;
	if (email.match(mailformat)) {
		inputText.focus();
		return true;
	} else {
		document.getElementById('resultMessage').innerHTML = "You have entered an invalid email address!";
		inputText.focus();
		return false;
	}
}
function saveRequest() {
	var email = document.submitApplication.email;
	$("#resultMessageForApplication").html("<img src='/images/bx_loading.gif'/>");
	var postData = $("#submitApplication").serializeArray();
	var formURL = "/spedia/"+$('#submitApplication').attr("action");
	if (ValidateEmail(email)) {
		$.ajax(
		{
			url : formURL,
			type: "POST",
			data : postData,
			success:function(data, textStatus, jqXHR) 
			{
			    $("#submitApplication").hide();
				$("#resultMessageForApplication").html('success');
	
			},
			error: function(jqXHR, textStatus, errorThrown) 
			{
				$("#resultMessageForApplication").html('failed');
			}
		});
	}
}
function getPageTitle() {
    return document.title;
    
}
