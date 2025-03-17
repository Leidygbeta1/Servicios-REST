/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ IServicioSeguridadLocal.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * Autor: Juan Sebastián Urrego
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces;


import co.edu.uniandes.csw.mueblesdelosalpes.dto.Usuario;
import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.AutenticacionException;
import java.util.List;

import javax.ejb.Local;

@Local
public interface IServicioSeguridadMockLocal
{
    /**
     * Registra el ingreso de un usuario al sistema.
     */
    public Usuario ingresar(String nombre, String contraseña) throws AutenticacionException;

    /**
     * Devuelve la lista de todos los usuarios registrados.
     */
    public List<Usuario> obtenerUsuarios();

    /**
     * Registra un nuevo usuario en el sistema.
     */
    public void crearUsuario(Usuario usuario);

    /**
     * Actualiza los datos de un usuario existente.
     */
    public void actualizarUsuario(Usuario usuario);

    /**
     * Elimina un usuario del sistema.
     */
    public void eliminarUsuario(String login);
}

