
package br.edu.ifrs.restinga.ds.carlos.springRest.dao;

import br.edu.ifrs.restinga.ds.carlos.springRest.modelo.Livro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos
 */
@Repository
public interface LivroDAO extends CrudRepository<Livro, Integer>{
    
}
