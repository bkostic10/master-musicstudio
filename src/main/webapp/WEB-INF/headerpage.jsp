<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--link rel="stylesheet" type="text/css"
	href="<!--c:url value="/themes/style.css"/>" /-->
<c:url var="registracijaUrl" value="/site/korisnik/registracija.htm" />
<c:url var="pocetnaUrl" value="/site/main/pocetna.htm" />
<c:url var="prijavaUrl" value="/site/auth/prijava.htm" />
<c:url var="odjavaUrl" value="/site/auth/odjava.htm" />
<c:url var="studiosUrl" value="/site/studio/studios.htm" />
<c:url var="aktorInfoUrl" value="/site/korisnik/aktorinfo.htm" />
<!-- header -->
<div id="header">
<div id="logo">
    <a href="${pocetnaUrl}">Music Studio</a>
</div>
<div id="menu">
    <a href="${pocetnaUrl}">Pocetna</a>
    <c:if test="${daLiJeLogovan}">
        <a href="${studiosUrl}">Lista Studija</a>
        <a href="${aktorInfoUrl}">Aktor info</a>
    </c:if>
    <c:if test="${!daLiJeLogovan}">
        <a href="${registracijaUrl}">Registracija</a>
        <a href="${prijavaUrl}">Prijava</a>
    </c:if>
    <c:if test="${daLiJeLogovan}">
        <a href="${odjavaUrl}">Odjava</a>
    </c:if>
</div></div>