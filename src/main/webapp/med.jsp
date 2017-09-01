<%@ page import="com.spedia.utils.WebConstants"%>
<!doctype html>
<html lang="en">
<head>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />
<script src="<%=WebConstants.JS_URL%>js/jquery-1.10.2.min.js"
	type="text/javascript"></script>
<script src="<%=WebConstants.JS_URL%>js/jquery-ui.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var appUrl = "http://schoolspedia.com/";
</script>
</head>
<body>
 
<div class="ui-widget">
  <label for="city">Type Your Medicine: </label>
  <input id="medicine" >
</div>
 
<div class="ui-widget" style="margin-top:2em; font-family:Arial">
  Result:
  <div id="log" style="height: 200px; width: 300px; overflow: auto;" class="ui-widget-content"></div>
</div>
   <script>
  $(function() {
    function log( message ) {
      $( "<div>" ).text( message ).prependTo( "#log" );
      $( "#log" ).scrollTop( 0 );
    }
 
    $( "#medicine" ).autocomplete({
      source: function( request, response ) {
        $.ajax({
          url: appUrl+"spedia/autoSuggestMed.json",
          data: {
            q: request.term
          },
          success: function( data ) {
            response( data );
          }
        });
      },
      minLength: 2,
      select: function( event, ui ) {
        log( ui.item ?
          "Selected: " + ui.item.label :
          "Nothing selected, input was " + this.value);
      },
      open: function() {
        $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
      },
      close: function() {
        $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
      }
    });
  });
  </script>
</body>
</html>