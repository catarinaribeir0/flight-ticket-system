<%-- 
    Document   : escolherAssentos
    Created on : Dec 10, 2016, 2:23:46 PM
    Author     : FelipeVieira
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lefecat - Escolher Assento</title>
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

        <form action="PassagemController" method="POST">
            <c:if test="${not empty assentosIda}">
                <p> Escolha o assento do voo de Ida: </p>
                <c:forEach items="${assentosIda}" var="assento">

                    <c:if test="${not assento.ocupado}">
                        <input type="checkbox" name=assentoIda value=${assento.idAssento}> Assento: ${assento.numeroAssento} |
                        Status: 
                        <c:if test="${not assento.ocupado}">Livre</c:if>
                        <c:if test="${assento.ocupado}">Ocupado</c:if>
                        | Categoria: ${assento.tipoAssento} | PREÇO: R$ ${assento.peso * vooIda.precoBase}
                        <br>
                        <br>
                    </c:if>

                    <c:if test="${assento.ocupado}">
                        <input type="checkbox" name=assentoIda disabled value=${assento.idAssento}> Assento: ${assento.numeroAssento} |
                        Status: 
                        <c:if test="${not assento.ocupado}">Livre</c:if>
                        <c:if test="${assento.ocupado}">Ocupado</c:if>
                        | Categoria: ${assento.tipoAssento} | PREÇO: R$ ${assento.peso * vooIda.precoBase}
                        <br>
                        <br>
                    </c:if>

                </c:forEach>
            </c:if>

            <c:if test="${not empty assentosVolta}">
                <p> Escolha o assento do voo de Volta: </p>
                <c:forEach items="${assentosVolta}" var="assento">

                    <c:if test="${not assento.ocupado}">
                        <input type="checkbox" name=assentoVolta value=${assento.idAssento}> Assento: ${assento.numeroAssento} |
                        Status: 
                        <c:if test="${not assento.ocupado}">Livre</c:if>
                        <c:if test="${assento.ocupado}">Ocupado</c:if>
                        | Categoria: ${assento.tipoAssento} | PREÇO: ${assento.peso * vooVolta.precoBase}
                        <br>
                        <br>
                    </c:if>

                    <c:if test="${assento.ocupado}">
                        <input type="checkbox" name=assentoVolta disabled value=${assento.idAssento}> Assento: ${assento.numeroAssento} |
                        Status: 
                        <c:if test="${not assento.ocupado}">Livre</c:if>
                        <c:if test="${assento.ocupado}">Ocupado</c:if>
                        | Categoria: ${assento.tipoAssento} | PREÇO: ${assento.peso * vooVolta.precoBase}
                        <br>
                        <br>
                    </c:if>

                </c:forEach>
            </c:if>

            <input type="submit" value="OK" />
        </form>

    </body>
</html>
