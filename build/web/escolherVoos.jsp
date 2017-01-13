<%-- 
    Document   : escolherVoos
    Created on : Dec 3, 2016, 7:09:19 PM
    Author     : FelipeVieira
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Aeroporto"%>
<%@page import="java.util.List"%>
<%@page import="dao.AeroportoDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lefecat - Escolher Voo</title>
    </head>
    <body>

        <c:if test="${not empty mensagemDeErro}">
            <p> ${mensagemDeErro} </p>
        </c:if>

        <c:choose>
            <c:when test="${empty loggedUser}">
                <p><font face="Verdana" color="dark blue" size="3"> Bem vindo ao Lefecat Airlines! ✈</font></p>
                <form action="LoginController" method="POST">
                    <p>Usuário: <input type="text" name="email"></p>
                    <p>Senha: <input type="password" name="senha"></p>
                    <input type="submit" value="Login">            
                </form>
            </c:when>
            <c:otherwise>
                <p><font face="Verdana" color="dark blue" size="3"> Bem vindo, ${loggedUser.nome} ${loggedUser.sobrenome}!</font></p>
                <form action="Logout" method="POST">
                    <input type="submit" value="Logout">
                </form>
                <br>

                <form action="CompraController" method="GET">
                    <input type="submit" value="Home">
                </form></form>
            <br>

        </c:otherwise>
    </c:choose>

    <form action="VooController" method="POST">
        <%                AeroportoDao dao = new AeroportoDao();
            List<Aeroporto> aeroportos = dao.getAllAeroportos();

        %>
        <center>
            De:
            <select name="origem">
                <%for (Aeroporto aeroporto : aeroportos) {%>
                <option><%=aeroporto.getNome()%></option>
                <%}%>
            </select>
            Para:
            <select name="destino">
                <%for (Aeroporto aeroporto : aeroportos) {%>
                <option><%=aeroporto.getNome()%></option>
                <%}%>
            </select>
            <p> Data de Ida:<input type="date" name="dataIda"></p>
            <p> Data de Volta:<input type="date" name="dataVolta"></p>

            <p>Adultos:
            <select name="qtdAdulto">
                <option>0</option>
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>                
            </select>
            Crianças:
            <select name="qtdCrianca">
                <option>0</option>
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>                
            </select>
            Bebês:
            <select name="">
                <option>0</option>
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>                
            </select></p>
            
            
            <input type="submit" value="Procurar" />
    </form>

    <c:if test="${not empty voosIda or not empty voosVolta}">

        <p><font face="Arial" size="4" color="blue"> ${status} </font></p>

        <form action='AssentoController' method='POST'>

            <c:if test="${not empty voosIda}">
                <p> Voos ida:  </p>
                <c:forEach items="${voosIda}" var="voo">
                    <input type="radio" name=vooida value=${voo.id}/> Voo: ${voo.id} |
                    Aeroporto Origem: ${aeroportoOrigemIda.nome} | Aeroporto Destino: ${aeroportoDestinoIda.nome} |
                    Horário: ${voo.horaVoo} | Data ${voo.dataVoo} | A partir de: R$${voo.precoBase}
                    <br>
                </c:forEach>
            </c:if>


            <c:if test="${not empty voosVolta}"> 
                <p> Voos volta:  </p>                       
                <c:forEach items="${voosVolta}" var="voo">
                    <input type="radio" name=voovolta value=${voo.id}/> Voo: ${voo.id} |
                    Aeroporto Origem: ${aeroportoOrigemVolta.nome} | Aeroporto Destino: ${aeroportoDestinoVolta.nome} |
                    Horário: ${voo.horaVoo} | Data ${voo.dataVoo} | A partir de: R$${voo.precoBase}
                    <br>
                </c:forEach>
            </c:if>
            <br>
            <input type="submit" value="OK">
            </center>
        </form>
    </c:if>

</body>
</html>