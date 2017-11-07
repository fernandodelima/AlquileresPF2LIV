<%-- 
    Document   : client
    Created on : Nov 6, 2017, 8:36:18 PM
    Author     : Fernando M. de Lima
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Controllers.*"%>
<%@page import="Model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    DocumentTypeManager con = new DocumentTypeManager();
    ArrayList types = con.GetTypes();
    %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="links.jsp"></jsp:include>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>
        <form action="GestorVentas" method="POST">
            <p>
                <label>Nombre:</label><input type="text" name="clientName" />
            </p>
            <p>
                <label>Apellido:</label><input type="text" name="clientSurename" />
            </p>
            <p>
                <label>Mail:</label><input type="text" name="clientMail" />
            </p>
            <p>
                <label>Telefono:</label><input type="text" name="clientPhone" />
            </p>
            <p>
                <label>Documento:</label><input type="text" name="clientDocument" />
            </p>
            <p>
                <label>Tipo de documento:</label><select name="clientDocumentType">
                <% 
                for(Object type : types)
                {
                    DocumentType c = (DocumentType)type;
                %>
                <option value="<%= c.getIdDocumentType()%>"><%= c.getDocumentType()%></option>
                <% } %>
            </select>
            </p>
            <input type="submit" value="Aceptar" class="btn btn-primary" />
        </form>
    </body>
</html>
