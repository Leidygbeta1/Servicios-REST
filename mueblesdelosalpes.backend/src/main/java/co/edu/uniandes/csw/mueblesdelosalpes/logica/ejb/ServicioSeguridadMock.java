/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ ServicioSeguridadMock.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package co.edu.uniandes.csw.mueblesdelosalpes.logica.ejb;

import co.edu.uniandes.csw.mueblesdelosalpes.persistencia.mock.ServicioPersistenciaMock;
import co.edu.uniandes.csw.mueblesdelosalpes.dto.Usuario;
import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.AutenticacionException;
import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.OperacionInvalidaException;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioSeguridadMockLocal;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioPersistenciaMockLocal;
import javax.ejb.Stateless;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que se encarga de la autenticación y gestión de usuarios en el sistema.
 */
@Stateless
public class ServicioSeguridadMock implements IServicioSeguridadMockLocal {

    private IServicioPersistenciaMockLocal persistencia;

    public ServicioSeguridadMock() {
        persistencia = new ServicioPersistenciaMock();
    }

    /**
     * Registra el ingreso de un usuario al sistema.
     * @param nombre
     * @param contraseña
     * @return 
     * @throws co.edu.uniandes.csw.mueblesdelosalpes.excepciones.AutenticacionException
     */
    @Override
    public Usuario ingresar(String nombre, String contraseña) throws AutenticacionException {
        Usuario u = (Usuario) persistencia.findById(Usuario.class, nombre);

        if (u != null) {
            if (u.getLogin().equals(nombre) && u.getContraseña().equals(contraseña)) {
                return u;
            } else {
                throw new AutenticacionException("Contraseña incorrecta.");
            }
        } else {
            throw new AutenticacionException("El usuario no existe.");
        }
    }

    /**
     * Devuelve la lista de todos los usuarios registrados.
     * @return 
     */
    @Override
    public List<Usuario> obtenerUsuarios() {
        return persistencia.findAll(Usuario.class);
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * @param usuario
     */
    @Override
    public void crearUsuario(Usuario usuario) {
        if (usuario == null || usuario.getLogin() == null || usuario.getContraseña() == null) {
            throw new IllegalArgumentException("El usuario o sus credenciales no pueden ser nulos");
        }
        try {
            persistencia.create(usuario);
        } catch (OperacionInvalidaException ex) {
            Logger.getLogger(ServicioSeguridadMock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Actualiza los datos de un usuario existente.
     * @param usuario
     */
    @Override
    public void actualizarUsuario(Usuario usuario) {
        persistencia.update(usuario);
    }

    /**
     * Elimina un usuario del sistema.
     * @param login
     */
    @Override
    public void eliminarUsuario(String login) {
        Usuario usuario = (Usuario) persistencia.findById(Usuario.class, login);
        if (usuario != null) {
            try {
                persistencia.delete(usuario);  // Manejo de excepción
            } catch (OperacionInvalidaException e) {
                System.out.println("Error al eliminar usuario: " + e.getMessage());
            }
        }
    }
}

