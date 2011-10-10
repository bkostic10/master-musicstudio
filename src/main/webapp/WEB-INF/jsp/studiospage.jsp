<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista studija</title>
</head>
<body>
    <div id="container">
        <%@ include file="/WEB-INF/headerpage.jsp"%>
        <div id="main">
            <div id="text">


                <h1>Izaberi Studio:</h1>
                <br />
                <c:url var="dodajStudioUrl" value="/site/studio/dodajstudio.htm" />

                <c:if test="${!empty studios}">
                    <c:forEach items="${studios}" var="studio">
                        <c:url var="studioInfoUrl"
                            value="/site/studio/studioinfo.htm?studioId=${studio.id}" />
                        <div id="studios">
                            <a href="${studioInfoUrl}"><c:out value="${studio.naziv}" />
                            </a>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${empty studios}">
                    Nema studija.
                </c:if>
                <div>${obrisanStudioPoruka}</div>
                <c:if test="${daLiJeAdmin}">
                    <p>
                        <a href="${dodajStudioUrl}">Dodaj studio</a>
                    </p>
                </c:if>


            </div>
        </div>
        <%@ include file="/WEB-INF/footerpage.jsp"%>
    </div>
</body>
</html>