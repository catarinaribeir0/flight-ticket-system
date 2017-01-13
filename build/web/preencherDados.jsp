<%-- 
    Document   : confirmarCompra
    Created on : Dec 10, 2016, 11:09:52 PM
    Author     : FelipeVieira
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lefecat - ConfirmarCompra</title>
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
        <form action="escolherVoos.jsp">
            <input type="submit" value="Voltar">
        </form>

        <p>============================================================</p>

        <form action="DadosController" method="POST">
            <c:if test="${not empty assentosIdaEscolhidos}">

                <font face="Verdana" color="blue" size="3" >Voo de ${aeroportoOrigemIda.nome} para ${aeroportoDestinoIda.nome}</font>

                <p>============================================================</p>

                <c:forEach items="${assentosIdaEscolhidos}" var="assento">
                    <p>Assento: ${assento.numeroAssento} | Categoria: ${assento.tipoAssento} | Preço: R$ ${assento.peso * vooIda.precoBase}</p>               
                    <p>Data do voo: ${vooIda.dataVoo}</p>
                    <p>Hora do voo: ${vooIda.horaVoo}</p>
                    <p>Nome Completo: <input type="text" name="nome${assento.idAssento}"></p>
                    <p>Data de Nascimento: <input type ="date" name="dataNasc${assento.idAssento}"></p>
                    <p>Nacionalidade: <input type ="text" name="nacionalidade${assento.idAssento}"></p>
                    <p>RG: <input type ="text" name="rg${assento.idAssento}"></p>
                    <p>Passaporte: <input type ="text" name="passaporte${assento.idAssento}"></p>
                    <p>Deficiência: <input type ="radio" name="deficiencia${assento.idAssento}" value="true"> Sim <input type ="radio" name="deficiencia${assento.idAssento}" value="false"> Não</p>

                    <p>============================================================</p>
                </c:forEach>
            </c:if>

            <c:if test="${not empty assentosVoltaEscolhidos}">

                <font face="Verdana" color="blue" size="3" >Voo de ${aeroportoOrigemVolta.nome} para ${aeroportoDestinoVolta.nome}</font>

                <p>============================================================</p>

                <c:forEach items="${assentosVoltaEscolhidos}" var="assento">
                    <p>Assento: ${assento.numeroAssento} | Categoria: ${assento.tipoAssento} | Preço: R$ ${assento.peso * vooVolta.precoBase}</p>
                    <p>Data do voo: ${vooVolta.dataVoo}</p>
                    <p>Hora do voo: ${vooVolta.horaVoo}</p>
                    <p>Nome Completo: <input type="text" name="nome${assento.idAssento}"></p>
                    <p>Data de Nascimento: <input type ="date" name="dataNasc${assento.idAssento}"></p>
                    <p>Nacionalidade: <input type ="text" name="nacionalidade${assento.idAssento}"></p>
                    <p>RG: <input type ="text" name="rg${assento.idAssento}"></p>
                    <p>Passaporte: <input type ="text" name="passaporte${assento.idAssento}"></p>
                    <p>Deficiência: <input type ="radio" name="deficiencia${assento.idAssento}"> Sim <input type ="radio" name="deficiencia${assento.idAssento}"> Não</p>

                    <p>============================================================</p>

                </c:forEach>
            </c:if>

            <p>PREÇO TOTAL: ${precoTotal}</p>

            <p>============================================================</p>

            <input type="submit" value="Confirmar Dados" />
        </form>
    </body>
</html>
