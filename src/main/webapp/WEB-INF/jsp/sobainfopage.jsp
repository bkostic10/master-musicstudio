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
                <br/>
                <c:url var="studiosUrl" value="/site/studio/studios.htm" />
                <c:url var="studioInfoUrl"
                    value="/site/studio/studioinfo.htm?studioId=${studioId}" />
                <c:url var="obrisiUrl"
                    value="/site/studio/studioinfo/obrisanasoba.htm?studioId=${studioId}&sobaId=${sobaAttribute.id}" />
                <c:url var="sobaInfoUrl"
                    value="/site/soba/sobainfo.htm?studioId=${studioId}&sobaId=${sobaAttribute.id}" />
                <c:url var="pronadjiKorisnikaUrl"
                    value="/site/soba/sobainfo/pronadjitermin.htm?studioId=${studioId}&sobaId=${sobaAttribute.id}" />

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
                <h1>Rezervisani termini:</h1><br/>
                <table>
                    <thead>
                            <tr>

                                <th><div class="null"></div></th>
                                <th><c:out value="${datum_0}"></c:out></th>
                                <th><c:out value="${datum_1}"></c:out></th>
                                <th><c:out value="${datum_2}"></c:out></th>
                                <th><c:out value="${datum_3}"></c:out></th>
                                <th><c:out value="${datum_4}"></c:out></th>
                                <th><c:out value="${datum_5}"></c:out></th>
                                <th><c:out value="${datum_6}"></c:out></th>

                            </tr>
                    </thead>
                    <tbody>
                            <tr>
                                    <th>10h-12h</th>
                                    <td><c:out value="${termin_0_0}"></c:out></td>
                                    <td><c:out value="${termin_1_0}"></c:out></td>
                                    <td><c:out value="${termin_2_0}"></c:out></td>
                                    <td><c:out value="${termin_3_0}"></c:out></td>
                                    <td><c:out value="${termin_4_0}"></c:out></td>
                                    <td><c:out value="${termin_5_0}"></c:out></td>
                                    <td><c:out value="${termin_6_0}"></c:out></td>
                            </tr>
                            <tr>
                                    <th>12h-14h</th>
                                    <td><c:out value="${termin_0_1}"></c:out></td>
                                    <td><c:out value="${termin_1_1}"></c:out></td>
                                    <td><c:out value="${termin_2_1}"></c:out></td>
                                    <td><c:out value="${termin_3_1}"></c:out></td>
                                    <td><c:out value="${termin_4_1}"></c:out></td>
                                    <td><c:out value="${termin_5_1}"></c:out></td>
                                    <td><c:out value="${termin_6_1}"></c:out></td>
                            </tr>
                            <tr>
                                    <th>14h-16h</th>
                                    <td><c:out value="${termin_0_2}"></c:out></td>
                                    <td><c:out value="${termin_1_2}"></c:out></td>
                                    <td><c:out value="${termin_2_2}"></c:out></td>
                                    <td><c:out value="${termin_3_2}"></c:out></td>
                                    <td><c:out value="${termin_4_2}"></c:out></td>
                                    <td><c:out value="${termin_5_2}"></c:out></td>
                                    <td><c:out value="${termin_6_2}"></c:out></td>
                            </tr>
                            <tr>
                                    <th>16h-18h</th>
                                    <td><c:out value="${termin_0_3}"></c:out></td>
                                    <td><c:out value="${termin_1_3}"></c:out></td>
                                    <td><c:out value="${termin_2_3}"></c:out></td>
                                    <td><c:out value="${termin_3_3}"></c:out></td>
                                    <td><c:out value="${termin_4_3}"></c:out></td>
                                    <td><c:out value="${termin_5_3}"></c:out></td>
                                    <td><c:out value="${termin_6_3}"></c:out></td>
                            </tr>
                            <tr>
                                    <th>18h-20h</th>
                                    <td><c:out value="${termin_0_4}"></c:out></td>
                                    <td><c:out value="${termin_1_4}"></c:out></td>
                                    <td><c:out value="${termin_2_4}"></c:out></td>
                                    <td><c:out value="${termin_3_4}"></c:out></td>
                                    <td><c:out value="${termin_4_4}"></c:out></td>
                                    <td><c:out value="${termin_5_4}"></c:out></td>
                                    <td><c:out value="${termin_6_4}"></c:out></td>
                            </tr>
                            <tr>
                                    <th>20h-22h</th>
                                    <td><c:out value="${termin_0_5}"></c:out></td>
                                    <td><c:out value="${termin_1_5}"></c:out></td>
                                    <td><c:out value="${termin_2_5}"></c:out></td>
                                    <td><c:out value="${termin_3_5}"></c:out></td>
                                    <td><c:out value="${termin_4_5}"></c:out></td>
                                    <td><c:out value="${termin_5_5}"></c:out></td>
                                    <td><c:out value="${termin_6_5}"></c:out></td>
                            </tr>
                            <tr>
                                    <th>22h-24h</th>
                                    <td><c:out value="${termin_0_6}"></c:out></td>
                                    <td><c:out value="${termin_1_6}"></c:out></td>
                                    <td><c:out value="${termin_2_6}"></c:out></td>
                                    <td><c:out value="${termin_3_6}"></c:out></td>
                                    <td><c:out value="${termin_4_6}"></c:out></td>
                                    <td><c:out value="${termin_5_6}"></c:out></td>
                                    <td><c:out value="${termin_6_6}"></c:out></td>
                            </tr>
                    </tbody>
                </table>X-rezervisano<br/>O-slobodno


                <c:if test="${daLiJeKorisnik}">
                                
                <h1>Rezervisi termin:</h1>
                <br />
                <form:form id="rezervisiTerminForma" name="rezervisiTerminForma"
                    modelAttribute="terminAttribute" method="POST"
                    action="${sobaInfoUrl}">
                    <div id="errors">${rezervacijaTermina2Poruka}</div>
                    
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
                <h1>Rezervisani termini korisnika:</h1><br />
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
                
                
                
                <c:if test="${daLiJeAdmin}">
                    <p>
                        <a href="${obrisiUrl}">Obrisi sobu</a>
                    </p>     
                    <h1>Pronadji korisnika:</h1><br/>
                    <form:form id="pronadjiTermineKorisnikaForma" name="pronadjiTermineKorisnikaForma"
                        modelAttribute="korisnikAttribute" method="POST"
                        action="${pronadjiKorisnikaUrl}">
                        <div>${terminiKorisnika2Poruka}</div>
                        <div>
                            Korisnicko ime: <br />
                            <form:input name="korIme" path="korIme" />
                            <br />
                            <form:errors name="korIme" path="korIme" cssClass="error"/>
                        </div>
                        <p>
                            <input type="submit" class="btn" value="Pronadji korisnika" />
                        </p>
                    </form:form>
                    <div>${izdavanjeRacunaPoruka}</div>
                    <h1>Termini odabranog korisnika:</h1>
                    <br/>
                    <c:if test="${!empty terminiRacunAttribute}">
                    <table>
                        <thead>
                            <tr>
                                <th width="150">Id</th>
                                <th width="150">Datum</th>
                                <th width="150">Vreme</th>
                                <th width="150">Izdaj racun</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${terminiRacunAttribute}" var="termin">
                                <c:url var="izdajRacunUrl"
                                    value="/site/soba/sobainfo/izdavanjeracuna.htm?studioId=${studioId}&sobaId=${sobaAttribute.id}&terminId=${termin.id.id}" />
                                <tr>
                                    <td><c:out value="${termin.id.id}" />
                                    </td>
                                    <td><c:out value="${termin.datum}" />
                                    </td>
                                    <td><c:out value="${termin.satnica}" />
                                    </td>
                                    <td><a href="${izdajRacunUrl}">Izdaj racun</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    </c:if>
                    <c:if test="${empty terminiRacunAttribute}">
                        Nema termina korisnika.
                    </c:if>
                </c:if>
                <p>
                    <a href="${studioInfoUrl}">Nazad</a>
                </p>


            </div>
        </div>
        <%@ include file="/WEB-INF/footerpage.jsp"%>
    </div>
</body>
</html>