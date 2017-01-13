<%-- 
    Document   : usuarioHome
    Created on : Dec 11, 2016, 6:40:19 PM
    Author     : FelipeVieira
--%>

<%@page import="dao.PassagemDao"%>
<%@page import="model.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lefecat - Home usuário</title>

        <style >
            table, th {
                border: 1px solid black;

            }
        </style>
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
        <form action="escolherVoos.jsp">
            <input type="submit" value="Voltar">
        </form>

    <center>
        <form action="CheckinController" method="POST">
            <input type="submit" value="Fazer check-in / Alterar assento">

            <p>============================================================</p>

            <c:if test="${not empty passagensCompradas}">
                <p> Passagens para fazer Check-in: </p>

                <p>============================================================</p>

                <c:forEach items="${passagensCompradas}" var="passagem">
                    <c:if test="${passagem.checkin == false}">
                        <input type ="radio" name="checkin" value="${passagem.idPassagem}"> Escolha esta passagem para fazer o check-in ou alterar assento

                        <p>Viagem de ${passagem.aeroportoOrigem.nome} para ${passagem.aeroportoDestino.nome}</p>
                        <p>Assento: ${passagem.assento.numeroAssento} | Categoria: ${passagem.assento.tipoAssento} | Preço: R$ ${passagem.preco}</p>               
                        <p>Número da passagem ${passagem.idPassagem}</p>
                        <b><p> Número do Voo ${passagem.voo.id}</p>
                            <p>Data do voo: ${passagem.voo.dataVoo}</p>
                            <p>Hora do voo: ${passagem.voo.horaVoo}</p></b>
                        <p>Passageiro: ${passagem.nome}</p>
                        <p>Data de Nascimento: ${passagem.dataNasc}</p>
                        <p>Nacionalidade: ${passagem.nacionalidade}</p>
                        <p>RG: ${passagem.rg}</p>
                        <p>Passaporte: ${passagem.passaporte}</p>
                        <c:if test="${not empty passagem.telefone}">
                            <p>Telefone: ${passagem.telefone}</p>
                        </c:if>
                        <c:choose>
                            <c:when test="${passagem.deficiencia == 'true'}">
                                <p>Possui deficiência</p>
                            </c:when>
                            <c:otherwise>
                                <p>Não possui deficiência</p>
                            </c:otherwise>
                        </c:choose>


                        <p>============================================================</p>
                    </c:if>
                </c:forEach>

                <p>Bilhetes</p>

                <p>============================================================</p>

                <c:forEach items="${passagensCompradas}" var="passagem">
                    <table style="width:50%" >
                        <c:if test="${passagem.checkin == true}">

                            <tr bgcolor="#d7e8ff">
                                <th colspan="4" ><h3>Lefecat Airlines ✈</h3></th>
                            </tr>
                            <tr>
                                <th rowspan="4" width=25%>Assento: ${passagem.assento.numeroAssento}</th>
                            </tr>
                            <tr>
                                <th>Nome: ${passagem.nome}</th>

                                <th>De: ${passagem.aeroportoOrigem.nome} 
                                    <br> Para: ${passagem.aeroportoDestino.nome}</th>

                            </tr>
                            <tr>
                                <th>Tipo: ${passagem.assento.tipoAssento}</th>
                                <th>Voo: ${passagem.voo.id}</th>


                            </tr>
                            <tr>
                                <th>RG: ${passagem.rg}</th>
                                <th>Data: ${passagem.voo.dataVoo} Horário: ${passagem.voo.horaVoo}</th>
                            </tr>

                        </c:if>
                    </table>
                </c:forEach>
            </form>
        </c:if>
        <br>
        <c:if test="${empty passagensCompradas}"> 
            <p>Você não possui passagens compradas!</p>

            <p>============================================================</p>

        </c:if>
        <br>
    </center>



</body>
</html>
