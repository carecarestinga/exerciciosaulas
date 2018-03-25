
package br.edu.ifrs.restinga.ds.carlos.exercicioslista2.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.restinga.ds.carlos.exercicioslista2.modelo.Usuario;

/**
 *
 * @author carlos
 */
@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Integer>{
    
}
