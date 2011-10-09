<%@ include file="/WEB-INF/headerpage.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head></head>
<body>
    asd
    <%-- <c:url var="ponistiUrl" value="/site/termin/ponistitermin.htm"/> --%>
    <c:redirect
        url="/site/soba/sobainfo.htm?studioId=${studioId}&sobaId=${sobaId}" />
</body>
</html>