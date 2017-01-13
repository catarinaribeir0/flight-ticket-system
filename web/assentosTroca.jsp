<%-- 
    Document   : assentosTroca
    Created on : Dec 13, 2016, 12:43:42 AM
    Author     : FelipeVieira
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lefecat - Assentos Troca</title>
    </head>
    <body>

    <c:if test="${not empty mensagemDeErro}">
        <p> ${mensagemDeErro} </p>
    </c:if>

    <p><font face="Verdana" color="dark blue" size="3"> Bem vindo, ${loggedUser.nome} ${loggedUser.sobrenome}!</font></p>

    <form action="Logout" method="POST">
        <input type="submit" value="Logout">
    </form>

    <br>
    <form action="usuarioHome.jsp">
        <input type="submit" value="Home">
    </form>

    <br>
    <form action="checkin.jsp">
        <input type="submit" value="Voltar">
    </form>

    <b><p>Número do seu assento: ${passagemCheckin.assento.numeroAssento}</p></b>
    <form action="TrocarAssentoController" method="POST">
        <p> Escolha o assento de troca: </p>
        <c:forEach items="${assentosTroca}" var="assento">

            <c:if test="${not assento.ocupado}">
                <input type="radio" name=idAssento value=${assento.idAssento}> Assento: ${assento.numeroAssento} |
                Status: 
                <c:if test="${not assento.ocupado}">Livre</c:if>
                <c:if test="${assento.ocupado}">Ocupado</c:if>
                | Categoria: ${assento.tipoAssento} | PREÇO: R$ ${assento.peso * precoBase}
                <br>
                <br>
            </c:if>

            <c:if test="${assento.ocupado}">
                <input type="radio" name=idAssento disabled value=${assento.idAssento}> Assento: ${assento.numeroAssento} |
                Status: 
                <c:if test="${not assento.ocupado}">Livre</c:if>
                <c:if test="${assento.ocupado}">Ocupado</c:if>
                | Categoria: ${assento.tipoAssento} | PREÇO: R$ ${assento.peso * precoBase}
                <br>
                <br>
            </c:if>

        </c:forEach>

        <font size="1"><p>Caso escolha uma assento com preço diferente, será creditado como desconto
            para uma próxima viagem ou será debitado do seu cartão a diferença de valor.</p></font>

        <input type="submit" value="OK" />
    </form>

</body>
</html>
