<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Greska</title>
</head>
<body>

    <h3>Stranica ne postoji ili nemate pravo pristupa.</h3>
    <c:url var="pocetnaUrl" value="/site/main/pocetna.htm" />

    <a href="${pocetnaUrl}">Vrati se na pocetnu stranu</a>
</body>
</html>