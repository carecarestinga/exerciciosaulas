
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

import br.edu.ifrs.restinga.ds.carlos.exercicioslista2.dao.ProdutoDAO;
import br.edu.ifrs.restinga.ds.carlos.exercicioslista2.exception.NaoEncontrado;
import br.edu.ifrs.restinga.ds.carlos.exercicioslista2.exception.RequisicaoInvalida;
import br.edu.ifrs.restinga.ds.carlos.exercicioslista2.modelo.Produto;

/**
 * 
 * @author carlos
 */
 
@RestController
public class Produtos {
    
    @Autowired
    ProdutoDAO produtoDAO;
    
    @RequestMapping(path = "/produtos/", method =RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Produto> listar() {
        return produtoDAO.findAll();
    
    }
    
    @RequestMapping(path = "/produtos/{id}", method =RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<Produto> recuperar(@PathVariable int id)  {
        
        Optional<Produto> findById = produtoDAO.findById(id);
        if(findById.isPresent()) {
        return  ResponseEntity.ok(findById.get());
        }
        else {
            return ResponseEntity.notFound().build();
        
        }
    }
    
    @RequestMapping(path="/produtos/",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Produto inserir(@RequestBody Produto produto) {
        produto.setId(0);
        
        if(produto.getValor()<=0) {
            throw new RequisicaoInvalida("Valor deve ser maior que 0");
        }
        
            
            
        Produto produtoComId = produtoDAO.save(produto);
        return produtoComId;
    }
    
    @RequestMapping(path="/produtos/{id}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable int id, @RequestBody Produto produto) {
        produto.setId(id);
        if(produto.getValor()<=0) {
            throw new RequisicaoInvalida("Valor deve ser maior que 0");
        }
        produtoDAO.save(produto);
    }
    
    @RequestMapping(path = "/produtos/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable int id) {
        
        if(!produtoDAO.existsById(id)) {
            throw new NaoEncontrado("ID não encontrada");
        }
            
        produtoDAO.deleteById(id);
    
    }
    
    
}
