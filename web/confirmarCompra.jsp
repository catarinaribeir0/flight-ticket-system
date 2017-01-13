<%-- 
    Document   : confirmarCompra
    Created on : Dec 11, 2016, 4:36:25 PM
    Author     : FelipeVieira
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lefecat - Confirmar compra</title>
    </head>
    <body>

        <c:if test="${not empty mensagemDeErro}">
            <p> ${mensagemDeErro} </p>
        </c:if>

        <p><font face="Verdana" color="dark blue" size="3"> Bem vindo, ${loggedUser.nome} ${loggedUser.sobrenome} !</font></p>
        <form action="Logout" method="POST">
            <input type="submit" value="Logout">
        </form>
        <br>
        <form action="usuarioHome.jsp">
            <input type="submit" value="Home">
        </form>

        <p>============================================================</p>

        <c:forEach items="${passagens}" var="passagem">
            <p>Viagem de ${passagem.aeroportoOrigem.nome} para ${passagem.aeroportoDestino.nome}</p>
            <p>Passageiro: ${passagem.nome}</p>
            <p>Data de Nascimento: ${passagem.dataNasc}</p>
            <p>Nacionalidade: ${passagem.nacionalidade}</p>
            <p>RG: ${passagem.rg}</p>
            <p>Passaporte: ${passagem.passaporte}</p>
            <c:choose>
                <c:when test="${passagem.deficiencia == 'true'}">
                    <p>Possui deficiência</p>
                </c:when>
                <c:otherwise>
                    <p>Não possui deficiência</p>
                </c:otherwise>
            </c:choose>

            <p>============================================================</p>

        </c:forEach>
        <form action="CompraController" method="POST">
            <p>Dados de Pagamento</p>
            <p>Bandeira: <input type ="radio" name="bandeira"> MASTERCARD <input type ="radio" name="bandeira"> VISA <input type ="radio" name="bandeira"> HIPERCARD <input type ="radio" name="bandeira"> ELO <input type ="radio" name="bandeira"> AMERICAN EXPRESS</p>
            <p>Nome no Cartão: <input type="text" name="nomeCartao"></p>
            <p>Número Cartão: <input type="text" name="numeroCartao"></p>
            <p>Validade: <input type="text" name="validadeMes">/<input type="text" name="validadeAno"> Exemplo: 05/2019</p>
            <p>Código de Segurança: <input type="text" name="codigoSeguranca"></p>

            <p>============================================================</p>

            <input type="submit" value="Confirmar Pagamento" />
        </form>
    </body>
</html>
