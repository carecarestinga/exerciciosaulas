
package br.edu.ifrs.restinga.ds.carlos.exercicioslista2.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrs.restinga.ds.carlos.exercicioslista2.modelo.Produto;

/**
 *
 * @author carlos
 */
@Repository
public interface ProdutoDAO extends CrudRepository<Produto, Integer>{
    
}
