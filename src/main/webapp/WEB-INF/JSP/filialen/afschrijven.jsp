<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head title='Afschrijven' />
</head>
<body>
	<v:menu />
	<h1>Filiaal afschrijven</h1>
	<form:form modelAttribute='afschrijvenForm'>
		<form:label path='filiaal'>Filiaal:<form:errors
				path='filiaal' />
		</form:label>
		<form:radiobuttons items='${filialen}' itemLabel='naam' itemValue='id'
path='filiaal' element='div'/>
		<input type='submit' value='Afschrijven' />
	</form:form>
</body>
</html>