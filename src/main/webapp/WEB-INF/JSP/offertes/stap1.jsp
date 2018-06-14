<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Aanvraag offerte (stap 1)' />
</head>
<body>
	<v:menu />
	<h1>Aanvraag offerte</h1>
	<h2>Stap 1</h2>
	<c:url value='/offertes' var='url' />
	<form:form action='${url}' modelAttribute='offerte'>
		<form:label path='voornaam'>Voornaam:<form:errors
				path='voornaam' />
		</form:label>
		<form:input path='voornaam' autofocus='true' required='required' />
		<form:label path='familienaam'>Familienaam:
<form:errors path='familienaam' />
		</form:label>
		<form:input path='familienaam' required='required' />
		<form:label path='emailAdres'>E-mail adres:
<form:errors path='emailAdres' />
		</form:label>
		<form:input path='emailAdres' required='required' type='email' />
		<div>Telefoonnummer(s):</div>
		<c:forEach items='${offerte.telefoonNrs}' varStatus='status'>
			<div class='rij'>
				<form:input path='telefoonNrs[${status.index}]' type='tel' />
				<form:errors path='telefoonNrs[${status.index}]' cssClass='fout' />
			</div>
		</c:forEach>
		<input type='submit' value='Nog een telefoonnummer'
			name='nogeennummer' formnovalidate>
		<input type='submit' value='Volgende stap' name='volgende'
			id="toFocus">
	</form:form>
	<script>
		document.addEventListener("keydown", function(event) {
			//event.preventDefault();
			if (event.keyCode === 13) {
				event.preventDefault();
				document.getElementById("toFocus").click();
			}
		});
	</script>
</body>
</html>