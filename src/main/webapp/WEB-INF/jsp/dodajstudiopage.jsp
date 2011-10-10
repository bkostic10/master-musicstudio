<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dodavanje studija</title>
</head>
<body>
    <div id="container">
        <%@ include file="/WEB-INF/headerpage.jsp"%>
        <div id="main">
            <div id="text">


                <h1>Dodaj studio:</h1>
                <br/>
                <c:url var="dodajStudioUrl" value="/site/studio/dodajstudio.htm" />
                <c:url var="studiosUrl" value="/site/studio/studios.htm" />
                <form:form id="dodajStudioForma" name="dodajStudioForma"
                    modelAttribute="studioAttribute" method="POST"
                    action="${dodajStudioUrl}">
                    <div>
                        Ime studija: <br />
                        <form:input name="naziv" path="naziv" />
                        <br />
                        <form:errors name="naziv" path="naziv" cssClass="error"/>
                    </div>

                    <div>
                        Adresa studija: <br />
                        <form:input name="ulica" path="ulica" />
                        <br />
                        <form:errors name="ulica" path="ulica" cssClass="error"/>
                    </div>
                    <p>
                        <input type="submit" class="btn" value="Dodaj studio" />
                    </p>
                </form:form>
                <p>${dodavanjeStudijaPoruka}</p>
                <a href="${studiosUrl}">Nazad</a>


            </div>
        </div>
        <%@ include file="/WEB-INF/footerpage.jsp"%>
    </div>
</body>
</html>