
package br.edu.ifrs.restinga.ds.carlos.springRest.controller;

import br.edu.ifrs.restinga.ds.carlos.springRest.dao.UsuarioDAO;
import br.edu.ifrs.restinga.ds.carlos.springRest.modelo.Usuario;
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
public class Usuarios {
    
    @Autowired
    UsuarioDAO usuarioDAO;
    
    @RequestMapping(path = "/usuarios/", method =RequestMethod.GET)
    public Iterable<Usuario> listar() {
        return usuarioDAO.findAll();
    
    }
    
    @RequestMapping(path = "/usuarios/{id}", method =RequestMethod.GET)
    public  Optional<Usuario> recuperar(@PathVariable int id)  {
        Optional<Usuario> findById = usuarioDAO.findById(id);
        return  findById;
    }
    
    @RequestMapping(path="/usuarios/",method = RequestMethod.POST)
    public Usuario inserir(@RequestBody Usuario usuario) {
        usuario.setId(0);
        Usuario usuarioComId = usuarioDAO.save(usuario);
        return usuarioComId;
    }
    
    @RequestMapping(path="/usuarios/{id}",method = RequestMethod.PUT)
    public void atualizar(@PathVariable int id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        usuarioDAO.save(usuario);
    }
    
    @RequestMapping(path = "/usuarios/{id}", method = RequestMethod.DELETE)
    public void apagar(@PathVariable int id) {
        usuarioDAO.deleteById(id);
    
    }
    
    
}
