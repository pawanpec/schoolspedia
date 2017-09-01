<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>jQuery UI Autocomplete - Custom data and display</title>
  <link rel="stylesheet" type="text/css"
		href="/spedia/css/jquery-ui.css" />
  <script src="/spedia/js/jquery-1.10.2.min.js"
		type="text/javascript"></script>
    <script src="/spedia/js/jquery-ui.js" type="text/javascript">
    </script>
  <script>
  $(function() {
    $( "#searchSchool" ).autocomplete({
      minLength: 3,
      source: function( request, response ) {
          $.ajax({
            url: "http://localhost/spedia/autoSuggestSchools.json",
            data: {
              term: request.term
            },
            success: function( data ) {
              response( data );
            }
          });
        },
      focus: function( event, ui ) {
        $( "#searchSchool" ).val( $("#stateList").val() );
        return false;
      },
      select: function( event, ui ) {
    	var url='http://localhost/spedia/search.html?nid='+ui.item.id ;
        window.open(url);
        return false;
      }
    })
    .autocomplete( "instance" )._renderItem = function( ul, item ) {
      return $( "<li>" )
        .append( "<a>" + item.title + "</a>" )
        .appendTo( ul );
    };
  });
  </script>
</head>
<body>
 
<div id="School Name">Type the School Name:</div>
<input id="searchSchool">
<select id="stateList">
<option value="andaman-nicobar">Andaman and Nicobar Islands</option>
<option value="andhra pradesh">Andhra Pradesh</option>
<option value="arunachal pradesh">Arunachal Pradesh</option>
<option value="assam">Assam</option>
<option value="bihar">Bihar</option>
<option value="chandigarh">Chandigarh</option>
<option value="chhattisgarh">Chhattisgarh</option>
<option value="dadra-nagar-haveli">Dadra and Nagar Haveli</option>
<option value="daman-diu">Daman and Diu</option>
<option value="delhi">Delhi</option>
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
</body>
</html>