<%-- 
    Document   : bilhete
    Created on : Dec 13, 2016, 11:23:50 AM
    Author     : FelipeVieira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lefecat - Bilhete</title>

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
    <form action="usuarioHome.jsp">
        <input type="submit" value="Voltar">
    </form>

    <center>
        
        <p><h3>Bilhete para impressão</h3></p>

        <table style="width:50%" >
            <tr bgcolor="#d7e8ff">
                <th colspan="4" ><h3>Lefecat Airlines ✈</h3></th>
            </tr>
            <tr>
                <th rowspan="4" width=25%>Assento: ${passagemCheckin.assento.numeroAssento}</th>
            </tr>
            <tr>
                <th>Nome: ${passagemCheckin.nome}</th>

                <th>De: ${passagemCheckin.aeroportoOrigem.nome} 
                    <br> Para: ${passagemCheckin.aeroportoDestino.nome}</th>

            </tr>
            <tr>
                <th>Tipo: ${passagemCheckin.assento.tipoAssento}</th>
                <th>Voo: ${passagemCheckin.voo.id}</th>
                
                
            </tr>
            <tr>
                <th>RG: ${passagemCheckin.rg}</th>
                <th>Data: ${passagemCheckin.voo.dataVoo} Horário: ${passagemCheckin.voo.horaVoo}</th>
            </tr>

        </table>

    </center>
</body>
</html>
