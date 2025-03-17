/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniandes.csw.mueblesdelosalpes.servicios;


import co.edu.uniandes.csw.mueblesdelosalpes.dto.Usuario;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.ejb.ServicioSeguridadMock;
import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.AutenticacionException;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioSeguridadMockLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/seguridad")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SeguridadService {

    @EJB
    private IServicioSeguridadMockLocal seguridadMock;

    // Obtener todos los usuarios (ejemplo de GET)
    @GET
    @Path("/usuarios")
    public Response obtenerUsuarios() {
        List<Usuario> usuarios = seguridadMock.obtenerUsuarios();
        return Response.ok(usuarios).build();
    }

    // Iniciar sesión (ejemplo de POST)
    @POST
    @Path("/login")
    public Response login(Usuario usuario) {
        try {
            Usuario autenticado = seguridadMock.ingresar(usuario.getLogin(), usuario.getContraseña());
            return Response.ok(autenticado).build();
        } catch (AutenticacionException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    // Crear un nuevo usuario (ejemplo de POST)
    @POST
    @Path("/usuarios")
    public Response crearUsuario(Usuario usuario) {
        seguridadMock.crearUsuario(usuario);
        return Response.status(Response.Status.CREATED).entity("Usuario creado correctamente").build();
    }

    // Actualizar usuario (ejemplo de PUT)
    @PUT
    @Path("/usuarios/{login}")
    public Response actualizarUsuario(@PathParam("login") String login, Usuario usuario) {
        usuario.setLogin(login);  // Usamos login en lugar de id
        seguridadMock.actualizarUsuario(usuario);
        return Response.ok("Usuario actualizado correctamente").build();
    }

    // Eliminar usuario (ejemplo de DELETE)
    @DELETE
    @Path("/usuarios/{login}")
    public Response eliminarUsuario(@PathParam("login") String login) {
        seguridadMock.eliminarUsuario(login);
        return Response.ok("Usuario eliminado correctamente").build();
    }
}


