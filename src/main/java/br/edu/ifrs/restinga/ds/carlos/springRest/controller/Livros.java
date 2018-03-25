
package br.edu.ifrs.restinga.ds.carlos.springRest.controller;

import br.edu.ifrs.restinga.ds.carlos.springRest.dao.LivroDAO;
import br.edu.ifrs.restinga.ds.carlos.springRest.modelo.Livro;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author carlos
 */
@RestController
public class Livros {
    
    @Autowired
    LivroDAO livroDAO;
    
    @RequestMapping(path = "/livros/", method =RequestMethod.GET)
    public Iterable<Livro> listar() {
        return livroDAO.findAll();
    
    }
    
    @RequestMapping(path = "/livros/{id}", method =RequestMethod.GET)
    public  Optional<Livro> recuperar(@PathVariable int id)  {
        Optional<Livro> findById = livroDAO.findById(id);
        return  findById;
    }
    
    @RequestMapping(path="/livros/",method = RequestMethod.POST)
    public Livro inserir(@RequestBody Livro livro) {
        livro.setId(0);
        Livro livroComId = livroDAO.save(livro);
        return livroComId;
    }
    
    @RequestMapping(path="/livros/{id}",method = RequestMethod.PUT)
    public void atualizar(@PathVariable int id, @RequestBody Livro livro) {
        livro.setId(id);
        livroDAO.save(livro);
    }
    
    @RequestMapping(path = "/livros/{id}", method = RequestMethod.DELETE)
    public void apagar(@PathVariable int id) {
        livroDAO.deleteById(id);
    
    }
    
    
}
