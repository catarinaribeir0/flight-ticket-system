<%-- 
    Document   : index
    Created on : Nov 29, 2016, 8:37:51 PM
    Author     : FelipeVieira
--%>
<%@page import="model.Aeroporto"%>
<%@page import="java.util.List"%>
<%@page import="dao.AeroportoDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        
        <jsp:forward page="escolherVoos.jsp"/> 

        <%-- <form action="LoginController" method="POST">
            <p>Usuário: <input type="text" name="email"></p>
            <p>Senha: <input type="password" name="senha"></p>
            <input type="submit" value="Login">            
        </form>

        <form action="VooController" method="POST">
            <%
                AeroportoDao dao = new AeroportoDao();
                List<Aeroporto> aeroportos = dao.getAllAeroportos();

            %><select name="origem">
                <%for (Aeroporto aeroporto : aeroportos) {%>
                <option><%=aeroporto.getNome()%></option><%}
                %>
            </select>
            <select name="destino">
                <%for (Aeroporto aeroporto : aeroportos) {%>
                <option><%=aeroporto.getNome()%></option><%}
                %>
            </select>
            <p> Data de Ida:<input type="date" name="dataIda"></p>
            <p> Data de Volta:<input type="date" name="dataVolta"></p>
            <input type="submit" value="Procurar" />
        </form> --%>
    </body>
</html>
