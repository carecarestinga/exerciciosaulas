
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

import br.edu.ifrs.restinga.ds.carlos.exercicioslista2.dao.UsuarioDAO;
import br.edu.ifrs.restinga.ds.carlos.exercicioslista2.exception.NaoEncontrado;
import br.edu.ifrs.restinga.ds.carlos.exercicioslista2.exception.RequisicaoInvalida;
import br.edu.ifrs.restinga.ds.carlos.exercicioslista2.modelo.Usuario;

/**
 *
 * @author carlos
 */

@RestController
public class Usuarios {
    
    @Autowired
    UsuarioDAO usuarioDAO;
    
    @RequestMapping(path = "/usuarios/", method =RequestMethod.GET)
    public Iterable<Usuario> listar() {
        return usuarioDAO.findAll();
    
    }
    
    @RequestMapping(path = "/usuarios/{id}", method =RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<Usuario> recuperar(@PathVariable int id)  {
        
        Optional<Usuario> findById = usuarioDAO.findById(id);
        if(findById.isPresent()) {
        return  ResponseEntity.ok(findById.get());
        }
        else {
            return ResponseEntity.notFound().build();
        
        }
    }
    
    @RequestMapping(path="/usuarios/",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario inserir(@RequestBody Usuario usuario) {
    	usuario.setId(0);
        
        if(usuario.getCpf() <= 0) {
        	throw new RequisicaoInvalida("O CPF nao pode ser nulo");
        }
        
            
            
        Usuario usuarioComId = usuarioDAO.save(usuario);
        return usuarioComId;
    }
    
    @RequestMapping(path="/usuarios/{id}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable int id, @RequestBody Usuario usuario) {
    	usuario.setId(id);
        if(usuario.getCpf() <= 0) {
        	throw new RequisicaoInvalida("O CPF nao pode ser nulo");
        }
        usuarioDAO.save(usuario);
    }
    
    @RequestMapping(path = "/usuarios/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable int id) {
        
        if(!usuarioDAO.existsById(id)) {
            throw new NaoEncontrado("ID não encontrada");
        }
            
        usuarioDAO.deleteById(id);
    
    }
    
    
}
