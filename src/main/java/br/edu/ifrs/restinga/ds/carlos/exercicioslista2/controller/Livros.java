
package br.edu.ifrs.restinga.ds.carlos.exercicioslista2.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.restinga.ds.carlos.exercicioslista2.dao.LivroDAO;
import br.edu.ifrs.restinga.ds.carlos.exercicioslista2.exception.NaoEncontrado;
import br.edu.ifrs.restinga.ds.carlos.exercicioslista2.exception.RequisicaoInvalida;
import br.edu.ifrs.restinga.ds.carlos.exercicioslista2.modelo.Livro;

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
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<Livro> recuperar(@PathVariable int id)  {
        
        Optional<Livro> findById = livroDAO.findById(id);
        if(findById.isPresent()) {
        return  ResponseEntity.ok(findById.get());
        }
        else {
            return ResponseEntity.notFound().build();
        
        }
    }
    
    @RequestMapping(path="/livros/",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Livro inserir(@RequestBody Livro livro) {
    	livro.setId(0);
        
        if(livro.getAnoPublicacao()<=0) {
        	throw new RequisicaoInvalida("O Ano nao pode ser nulo");
        }
        
            
            
        Livro livroComId = livroDAO.save(livro);
        return livroComId;
    }
    
    @RequestMapping(path="/livros/{id}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable int id, @RequestBody Livro livro) {
    	livro.setId(id);
        if(livro.getAnoPublicacao()<=0) {
            throw new RequisicaoInvalida("O Ano nao pode ser nulo");
        }
        livroDAO.save(livro);
    }
    
    @RequestMapping(path = "/livros/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable int id) {
        
        if(!livroDAO.existsById(id)) {
            throw new NaoEncontrado("ID não encontrada");
        }
            
        livroDAO.deleteById(id);
    
    }
    
    
}
