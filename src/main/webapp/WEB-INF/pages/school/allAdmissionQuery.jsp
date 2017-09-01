<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<title>Content Moderation Page</title>
<style>
displaytag table {
	border: 1px solid #666;
	width: 100%;
	margin: 20px 0 20px 0 !important;
	text-align: center;
}

.displaytag th,.displaytag td {
	padding: 2px 4px 2px 4px !important;
	text-align: left;
	vertical-align: top;
}

.displaytag thead tr {
	background-color: #fc0;
}

.displaytag th.sorted {
	background-color: orange;
}

.displaytag th a,.displaytag th a:visited {
	color: black;
}

.displaytag th a:hover {
	text-decoration: underline;
	color: black;
}

.displaytag th.sorted a,.displaytag th.sortable a {
	background-position: right;
	background-repeat: no-repeat;
	display: block;
	width: 100%;
}

.displaytag th.sortable a {
	background-image: url(arrow_off.png);
}

.displaytag th.order1 a {
	background-image: url(arrow_down.png);
}

.displaytag th.order2 a {
	background-image: url(arrow_up.png);
}

.displaytag tr.odd {
	background-color: #fff
}

.displaytag tr.tableRowEven,.displaytag tr.even {
	background-color: #fea
}

.displaytag div.exportlinks {
	background-color: #eee;
	border: 1px dotted #999;
	padding: 2px 4px 2px 4px;
	margin: 2px 0 10px 0;
	width: 95%;
}

.displaytag span.export {
	padding: 0 4px 1px 20px;
	display: inline;
	display: inline-block;
	cursor: pointer;
}

.displaytag span.excel {
	background-image: url(ico_file_excel.png);
	background-repeat: no-repeat;
}

.displaytag span.csv {
	background-image: url(ico_file_csv.png);
	background-repeat: no-repeat;
}

.displaytag span.xml {
	background-image: url(ico_file_xml.png);
	background-repeat: no-repeat;
}

.displaytag span.pdf {
	background-image: url(ico_file_pdf.png);
	background-repeat: no-repeat;
}

.displaytag span.rtf {
	background-image: url(ico_file_rtf.png);
	background-repeat: no-repeat;
}

.displaytag span.pagebanner {
	background-color: #eee;
	border: 1px dotted #999;
	padding: 2px 4px 2px 4px;
	width: 95%;
	margin-top: 10px;
	display: block;
	border-bottom: none;
}

.displaytag span.pagelinks {
	background-color: #eee;
	border: 1px dotted #999;
	padding: 2px 4px 2px 4px;
	width: 95%;
	display: block;
	border-top: none;
	margin-bottom: -5px;
}
</style>
<br></br>
<h1>Admission Application Page</h1>
<br></br>
<display:table name="${contents}" id="row" class="displaytag"
	pagesize="10" requestURI="/spedia/get_all_application.html">
	<display:column property="name" title="Name" />
	<display:column property="email" title="Email" />
	<display:column property="mobile" title="Mobile" />
	<display:column property="message" title="Message" />
	<display:column property="sname" title="School Name" />
</display:table>
