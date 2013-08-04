<!DOCTYPE html>
<html lang="en">
<head>
<title>Atmosphere Demo Application</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr>
	<div style="padding-left: 30px;">
		<select id="slist" class="selectpicker span3 applymargin">
			<option value="default">-- pick one --</option>
			<option value="ct">Computers and Technology</option>
			<option value="sports">Sports</option>
			<option value="politics">Politics</option>
			<option value="fashion">Fashion</option>
			<option value="books">Books and Literature</option>
		</select>
		<button class="btn" id="btnSubscribe">Subscribe</button>
		<button class="btn" id="btnUnSubscribe">Unsubscribe</button>
	</div>
	<div id="feedsDiv">
		<p id="noFeedsWarning"> You have not subscribed to any feeds </p>
		<table class="table table-striped table-condensed" id="feedsTable">
			<thead>
				<tr>
					<th>Message</th>
					<th>Posted By</th>
					<th>Posted Date</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>

	</div>
	<hr>
	<jsp:include page="footer.jsp" />
</body>
</html>