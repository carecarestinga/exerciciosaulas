
package br.edu.ifrs.restinga.ds.carlos.springRest.dao;

import br.edu.ifrs.restinga.ds.carlos.springRest.modelo.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Integer>{
    
}
