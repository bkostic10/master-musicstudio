<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Studio info</title>
</head>
<body>
    <div id="container">
        <%@ include file="/WEB-INF/headerpage.jsp"%>
        <div id="main">
            <div id="text">


                <h1>Studio info:</h1>
                <c:url var="studiosUrl" value="/site/studio/studios.htm" />
                <c:url var="dodajSobuUrl"
                    value="/site/soba/dodajsobu.htm?studioId=${studioAttribute.id}" />
                <c:url var="obrisiUrl"
                    value="/site/studio/studios/obrisanstudio.htm?studioId=${studioAttribute.id}" />
                <div>
                    Ime studija:
                    <c:out value="${studioAttribute.naziv}" />
                </div>
                <div>
                    Adresa studija:
                    <c:out value="${studioAttribute.ulica}" />
                </div>
                <div>
                    Soba za probe:
                    <c:out value="${probe}" />
                </div>
                <div>
                    Soba za snimanje:
                    <c:out value="${snimanje}" />
                </div>
                <c:if test="${daLiJeAdmin}">
                <p>
                    <a href="${obrisiUrl}">Obrisi studio</a>
                </p>
                </c:if>
                <h1>Sobe:</h1>
                <c:if test="${!empty studioAttribute.sobe}">
                    <table>
                        <thead>
                            <tr>
                                <th width="150">Naziv</th>
                                <th width="150">Tip</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${studioAttribute.sobe}" var="soba">
                                <c:url var="sobaInfoUrl"
                                    value="/site/soba/sobainfo.htm?studioId=${studioAttribute.id}&sobaId=${soba.id}" />
                                <tr>
                                    <td><a href="${sobaInfoUrl}"><c:out
                                        value="${soba.naziv}" />
                                    </a>
                                    </td>
                                    <td><c:out value="${soba.tip}" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${empty studioAttribute.sobe}">
                    Nema soba.
                </c:if>
                <div>${obrisanaSobaPoruka}</div>
                <c:if test="${daLiJeAdmin}">
                        <p>
                            <a href="${dodajSobuUrl}">Dodaj sobu</a>
                        </p>
                </c:if>
                <p>
                    <a href="${studiosUrl}">Nazad</a>
                </p>


            </div>
        </div>
        <%@ include file="/WEB-INF/footerpage.jsp"%>
    </div>
</body>
</html>