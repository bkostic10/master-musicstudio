<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Soba info</title>
</head>
<body>
    <div id="container">
        <%@ include file="/WEB-INF/headerpage.jsp"%>
        <div id="main">
            <div id="text">


                <h1>Soba info:</h1>

                <c:url var="studiosUrl" value="/site/studio/studios.htm" />
                <c:url var="studioInfoUrl"
                    value="/site/studio/studioinfo.htm?studioId=${studioId}" />
                <c:url var="obrisiUrl"
                    value="/site/studio/studioinfo/obrisanasoba.htm?studioId=${studioId}&sobaId=${sobaAttribute.id}" />
                <c:url var="sobaInfoUrl"
                    value="/site/soba/sobainfo.htm?studioId=${studioId}&sobaId=${sobaAttribute.id}" />

                <div>
                    Ime sobe:
                    <c:out value="${sobaAttribute.naziv}" />
                </div>

                <div>
                    Tip sobe:
                    <c:out value="${sobaAttribute.tip}" />
                </div>
                <div>
                    Opis: <br />
                    <textarea readonly="readonly" rows="10" cols="40">
                        <c:out value="${sobaAttribute.opis}" />
                    </textarea>
                </div>
                <c:if test="${daLiJeKorisnik}">
                                
                <h1>Rezervisi termin:</h1>

                <form:form id="rezervisiTerminForma" name="rezervisiTerminForma"
                    modelAttribute="terminAttribute" method="POST"
                    action="${sobaInfoUrl}">
                    <div>${rezervacijaTermina2Poruka}</div>
                    <br />
                    <div>
                        Datum: <br />
                        <form:input name="datum" path="datum" />
                        <br />
                        <form:errors name="datum" path="datum" cssClass="error"/>
                    </div>
                    <div>
                        Vreme: <br />
                        <form:select name="satnica" path="satnica" items="${satnica}" />
                    </div>
                    <p>
                        <input type="submit" class="btn" value="Rezervisi" />
                    </p>
                </form:form>
                <div>${rezervacijaTerminaPoruka}</div>
                <br />
                <h1>Rezervisani termini korisnika:</h1>
                <c:if test="${!empty terminiAttribute}">
                <table>
                    <thead>
                        <tr>
                            <th width="150">Id</th>
                            <th width="150">Datum</th>
                            <th width="150">Vreme</th>
                            <th width="150">Ponisti</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${terminiAttribute}" var="termin">
                            <c:url var="ponistiUrl"
                                value="/site/soba/sobainfo/ponistitermin.htm?studioId=${studioId}&sobaId=${sobaAttribute.id}&terminId=${termin.id.id}" />
                            <tr>
                                <td><c:out value="${termin.id.id}" />
                                </td>
                                <td><c:out value="${termin.datum}" />
                                </td>
                                <td><c:out value="${termin.satnica}" />
                                </td>
                                <td><a href="${ponistiUrl}">Ponisti</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                </c:if>
                <c:if test="${empty terminiAttribute}">
                    Nema rezervisanih termina.
                </c:if>
                <div>${ponistavanjeTerminaPoruka}</div>
                <br />
                </c:if>
                <p>
                    <a href="${obrisiUrl}">Obrisi sobu</a>
                </p>
                <p>
                    <a href="${studioInfoUrl}">Nazad</a>
                </p>


            </div>
        </div>
        <%@ include file="/WEB-INF/footerpage.jsp"%>
    </div>
</body>
</html>