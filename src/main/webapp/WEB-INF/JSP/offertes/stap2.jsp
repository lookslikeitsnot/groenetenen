<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Aanvraag offerte (stap 2)' />
</head>
<body>
	<v:menu />
	<h1>Aanvraag offerte</h1>
	<h2>Stap 2</h2>
	<form:form modelAttribute='offerte'>
		<form:label path='oppervlakte'>Oppervlakte:
<form:errors path='oppervlakte' />
		</form:label>
		<form:input path='oppervlakte' autofocus='true' required='required'
			type='number' min='1' />
		<c:forEach items='${offerte.gazontypes}' var='entry'>
			<div class='rij'>
				<form:checkbox path='gazontypes[${entry.key}]'
					label="${entry.key.toString().toLowerCase()}" />
			</div>
		</c:forEach>
		<form:errors cssClass='fout'/>
		<input type='submit' value='Vorige stap' name='vorige' formnovalidate>
		<input type='submit' value='Bevestigen' name='bevestigen' id="toFocus">
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