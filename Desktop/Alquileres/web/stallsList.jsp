<%-- 
    Document   : stallsList
    Created on : Nov 8, 2017, 2:47:50 AM
    Author     : Fernando M. de Lima
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Controllers.*"%>
<%@page import="Model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    StallManager con = new StallManager();
    ArrayList stallsList = con.GetStalls();
    %>
    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="links.jsp"></jsp:include>
    </head>
    <body>
        <h1>Listado de Clientes</h1>
        
        <table border="1" class="table table-striped table-hover table-dark">
            <tr>
                <th>Id Puesto</th><th>Piso</th><th>Cantidad de Sillas</th><th>Ventana</th><th>Estado</th><th>Precio Base</th>
            </tr>
            <% for (Object stall : stallsList) {
                    Stall s = (Stall) stall;
            %>
            <tr>
                <td><%= s.getIdStall() %></td>
                <td><%= s.getFloor() %></td>
                <td><%= s.getChairsAmount() %></td>
                <% if (s.isWithWindows()) { %>
                <td>Tiene</td>
                <% } else { %>
                <td>No Tiene</td>
                <% } if (s.isAvailable()) { %>
                <td>Disponible</td>
                <% } else { %>
                <td>No Disponible</td>
                <% } %>
                <td>$<%= s.getBasePrice()%></td>
            </tr>
            <% } %>
        </table>
    </body>
</html>