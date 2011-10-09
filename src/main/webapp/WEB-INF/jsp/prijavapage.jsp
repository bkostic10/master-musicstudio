<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Prijava</title>
</head>
<body>
    <div id="container">
        <%@ include file="/WEB-INF/headerpage.jsp"%>
        <div id="main">
            <div id="text">


                <h1>Prijava:</h1>
                <c:url var="registracijaUrl" value="/site/korisnik/registracija.htm" />
                <div id="errors">
                    <p id="login-error">${error}</p>
                </div>
                <form action="../../j_spring_security_check" method="post">

                    <div>
                        Korisnicko ime<br /> <input id="j_username" name="j_username"
                                type="text" />
                    </div>

                    <div>
                        Lozinka<br /> <input id="j_password" name="j_password"
                                type="password" />
                    </div>

                    <p>
                            <input type="submit" class="btn" value="Prijava" />
                    </p>

                </form>
                <a href="${registracijaUrl}">Registracija</a>


            </div>
        </div>
        <%@ include file="/WEB-INF/footerpage.jsp"%>
    </div>
</body>
</html>