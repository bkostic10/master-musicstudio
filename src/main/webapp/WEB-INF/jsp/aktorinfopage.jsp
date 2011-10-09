<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Aktor info</title>
</head>
<body>
    <div id="container">
        <%@ include file="/WEB-INF/headerpage.jsp"%>
        <div id="main">
            <div id="text">


                <h1>Aktor info:</h1>
                <br />
                <br />
                <div>
                    Ime:
                    <c:out value="${korisnikAttribute.ime}" />
                </div>

                <div>
                    Prezime:
                    <c:out value="${korisnikAttribute.prezime}" />
                </div>
                <div>
                    Licni broj:
                    <c:out value="${korisnikAttribute.licniBroj}" />
                </div>
                <div>
                    Korisnicko ime:
                    <c:out value="${korisnikAttribute.korIme}" />
                </div>
                <div>
                    Privilegija:
                    <c:out value="${privilegija}" />
                </div>
            </div>
        </div>
        <%@ include file="/WEB-INF/footerpage.jsp"%>
    </div>
</body>
</html>