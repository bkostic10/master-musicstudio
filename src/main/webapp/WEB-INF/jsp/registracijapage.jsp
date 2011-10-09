
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registracija</title>
</head>
<body>
    <div id="container">
        <%@ include file="/WEB-INF/headerpage.jsp"%>
        <div id="main">
            <div id="text">


                <h1>Registracija korisnika:</h1>

                <c:url var="pocetnaUrl" value="/site/main/pocetna.htm" />
                <c:url var="registracijaUrl" value="/site/korisnik/registracija.htm" />
                <form:form id="registracijaForma" name="registracijaForma"
                    modelAttribute="korisnikAttribute" method="POST"
                    action="${registracijaUrl}">
                    <div>${registracija2Poruka}</div>
                    <br />
                    <div>
                        Ime: <br />
                        <form:input name="ime" path="ime" />
                        <br />
                        <form:errors name="ime" path="ime" cssClass="error" />
                    </div>
                    <div>
                        Prezime: <br />
                        <form:input name="prezime" path="prezime" />
                        <br />
                        <form:errors name="prezime" path="prezime" cssClass="error" />
                    </div>
                    <div>
                        Licni broj: <br />
                        <form:input name="licniBroj" path="licniBroj" />
                        <br />
                        <form:errors name="licniBroj" path="licniBroj" cssClass="error" />
                    </div>
                    <div>
                        Korisnicko ime: <br />
                        <form:input name="korIme" path="korIme" />
                        <br />
                        <form:errors name="korIme" path="korIme" cssClass="error" />
                    </div>
                    <div>
                        Lozinka: <br />
                        <form:password name="lozinka" path="lozinka" />
                        <br />
                        <form:errors name="lozinka" path="lozinka" cssClass="error" />
                    </div>
                    <div>
                        Prekucaj lozinku: <br />
                        <form:password name="lozinka2" path="lozinka2" />
                        <br />
                            <form:errors name="lozinka2" path="lozinka2" cssClass="error" />
                    </div>

                    <p>
                        <input type="submit" class="btn" value="Registruj se" />
                    </p>
                </form:form>
                <div>${registracijaPoruka}</div>
                <br /> <a href="${pocetnaUrl}">Vrati se na pocetnu stranu</a>


            </div>
        </div>
        <%@ include file="/WEB-INF/footerpage.jsp"%>
    </div>
</body>
</html>