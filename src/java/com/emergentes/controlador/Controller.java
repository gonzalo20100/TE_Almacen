package com.emergentes.controlador;

import com.emergentes.modelo.Almacen;
import com.emergentes.modelo.ControlAlmacen;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Almacen objProducto = new Almacen();
        int id;
        int pos;
        String op = request.getParameter("op");
        
        // objTarea = objProducto
        // gestorTareas = ControlAlmacen
        // miTarea = miProducto
        // agenda = registroP
        
        if (op.equals("nuevo")) {
            HttpSession ses = request.getSession();
            ControlAlmacen registroP = (ControlAlmacen) ses.getAttribute("registroP");
            objProducto.setId(registroP.obtieneId());
            request.setAttribute("op", op);
            request.setAttribute("miProducto", objProducto);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if (op.equals("modificar")) {
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            ControlAlmacen registroP = (ControlAlmacen) ses.getAttribute("registroP");
            pos = registroP.ubicarProducto(id);
            objProducto = registroP.getLista().get(pos);
            
            request.setAttribute("op", op);
            request.setAttribute("miProducto", objProducto);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if (op.equals("eliminar")) {
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            ControlAlmacen registroP = (ControlAlmacen) ses.getAttribute("registroP");
            pos = registroP.ubicarProducto(id);
            registroP.eliminarProducto(pos);
            ses.setAttribute("registroP", registroP);
            response.sendRedirect("index.jsp");
        }  
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Almacen objProducto = new Almacen();
        int pos;
        String op = request.getParameter("op");
        
        if (op.equals("grabar")) {
            // recuperar valores del formulario
            // Verificar si es nuevo o una modificacion
            objProducto.setId(Integer.parseInt(request.getParameter("id")));
            objProducto.setProducto(request.getParameter("producto"));
            objProducto.setPrecio(Double.parseDouble(request.getParameter("precio")));
            objProducto.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            
            HttpSession ses = request.getSession();
            ControlAlmacen registroP = (ControlAlmacen) ses.getAttribute("registroP");
            
                String opg = request.getParameter("opg");
                if (opg.equals("nuevo")) {
                registroP.insertarProducto(objProducto);
            }
                else{
                    pos = registroP.ubicarProducto(objProducto.getId());
                    registroP.modificarProducto(pos, objProducto);
                    
                }
                ses.setAttribute("registroP", registroP);
                response.sendRedirect("index.jsp");
                
        }
    }

}
