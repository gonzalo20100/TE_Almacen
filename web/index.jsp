<%@page import="com.emergentes.modelo.Almacen"%>
<%@page import="com.emergentes.modelo.ControlAlmacen"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if (session.getAttribute("registroP") == null) {
            ControlAlmacen objeto1 = new ControlAlmacen();
            
            objeto1.insertarProducto(new Almacen(1, "Coca cola", 8.5, 100));
            objeto1.insertarProducto(new Almacen(2, "Pepsi", 11, 50));
            objeto1.insertarProducto(new Almacen(3, "Frack", 6, 100));
            objeto1.insertarProducto(new Almacen(4, "Serranitas", 2.5, 80));
            
            session.setAttribute("registroP", objeto1);
        }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSTL - Productos</title>
    </head>
    <body>
        <h1>Productos</h1>
        <a href="Controller?op=nuevo">Nuevo Producto</a>
        <br><br>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Producto</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Modificar</th>
                <th>Eliminar</th>
            </tr>
            <c:forEach var="item" items="${sessionScope.registroP.getLista()}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.producto}</td>
                    <td>${item.precio}</td>
                    <td>${item.cantidad}</td>
                    <td><a href="Controller?op=modificar&id=${item.id}">Modificar</a></td>
                    <td><a href="Controller?op=eliminar&id=${item.id}">Eliminar</a></td>
                </tr>
            </c:forEach>                       
        </table>
        
    </body>
</html>
