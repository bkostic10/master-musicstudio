<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dodavanje sobe</title>
</head>
<body>
    <div id="container">
        <%@ include file="/WEB-INF/headerpage.jsp"%>
        <div id="main">
            <div id="text">


                <h1>Dodaj sobu:</h1>
                <br />
                <c:url var="dodajSobuUrl"
                    value="/site/soba/dodajsobu.htm?studioId=${studioId}" />
                <c:url var="studioInfoUrl"
                    value="/site/studio/studioinfo.htm?studioId=${studioId}" />
                <form:form id="dodajSobuForma" name="dodajSobuForma"
                    modelAttribute="sobaAttribute" method="POST"
                    action="${dodajSobuUrl}">

                    <div>
                        Naziv: <br />
                        <form:input name="naziv" path="naziv" />
                        <br />
                        <form:errors name="naziv" path="naziv" cssClass="error"/>
                    </div>

                    <div>
                        TipSobe: <br />
                        <form:radiobutton path="tip" value="Probe" />
                        Probe
                        <form:radiobutton path="tip" value="Snimanje" />
                        Snimanje
                    </div>
                    <div>
                        Opis: <br />
                        <form:textarea rows="10" cols="40" name="opis" path="opis" />
                        <br />
                        <form:errors name="opis" path="opis" cssClass="error"/>
                    </div>

                    <p>
                        <input type="submit" class="btn" value="Dodaj sobu" />
                    </p>
                </form:form>
                <p>${dodavanjeSobePoruka}</p>
                <a href="${studioInfoUrl}">Nazad</a>


            </div>
        </div>
        <%@ include file="/WEB-INF/footerpage.jsp"%>
    </div>
</body>
</html>