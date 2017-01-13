<%-- 
    Document   : checkin
    Created on : Dec 13, 2016, 12:16:24 AM
    Author     : FelipeVieira
--%>

<%@page import="model.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lefecat - Checkin</title>
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
            <input type="submit" value="Voltar">
        </form>
    <center>
        <form action="BilheteController" method="POST">
            <input type="submit" value="Confirmar Checkin">
            <p> Contato (opcional): <input type ="text" name="telefone"></p>
        </form>
        <br>
        <form action="PrepararTrocaAssentoController" method="POST">
            <input type="submit" value="Alterar assento">
        </form>

        <p>============================================================</p>

        <font><p> Passagem: </font>

        <p>============================================================</p>

        

        <p>Viagem de ${passagemCheckin.aeroportoOrigem.nome} para ${passagemCheckin.aeroportoDestino.nome}</p>
        <p>Assento: ${passagemCheckin.assento.numeroAssento} | Categoria: ${passagemCheckin.assento.tipoAssento} | Preço: R$ ${passagemCheckin.preco}</p>   
        <p>Identificador: ${passagemCheckin.idPassagem}</p>
        <p>Passageiro: ${passagemCheckin.nome}</p>
        <p>Data de Nascimento: ${passagemCheckin.dataNasc}</p>
        <p>Nacionalidade: ${passagemCheckin.nacionalidade}</p>
        <p>RG: ${passagemCheckin.rg}</p>
        <p>Passaporte: ${passagemCheckin.passaporte}</p>
        <p>Data do voo: ${passagemCheckin.voo.dataVoo}</p>
        <p>Hora do voo: ${passagemCheckin.voo.horaVoo}</p>
        <c:choose>
            <c:when test="${passagemCheckin.deficiencia == 'true'}">
                <p>Possui deficiência</p>
            </c:when>
            <c:otherwise>
                <p>Não possui deficiência</p>
            </c:otherwise>
        </c:choose>
        <p>Preço: R$ ${passagemCheckin.preco}</p>

        <p>============================================================</p>

</html>
