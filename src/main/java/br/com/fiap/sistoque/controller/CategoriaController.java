package br.com.fiap.sistoque.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.sistoque.model.Categoria;
import br.com.fiap.sistoque.repository.CategoriaRepository;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    CategoriaRepository repository;

    @GetMapping
    public List<Categoria> index() {
        return repository.findAll();
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Categoria create(@RequestBody Categoria categoria) { // binding
        log.info("cadastrando categoria {} ", categoria);
        repository.save(categoria);
        return categoria;
    }


    @GetMapping("{id}")
    public ResponseEntity<Categoria> show(@PathVariable Long id) {
        log.info("buscando categoria por id {}", id);

        return repository
            .findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build()); //O orElse serve para substituir a logica de if para retornar o respose entity de ok com objeto ou falar que ta nulo

    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        log.info("apagando categoria");

        verificarSeExisteCategoria(id);

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    private void verificarSeExisteCategoria(Long id) {
        repository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Não existe categoria com o id informado. Consulte a lista em /categoria"));
    }


    // private Optional<Categoria> getCategoriaBydId(Long id) {
    //     var categoriaEncontrada = repository
    //             .stream()
    //             .filter(c -> c.id().equals(id))
    //             .findFirst();
    //     return categoriaEncontrada;
    // }


    @PutMapping("{id}")
    public ResponseEntity<Categoria> update (@PathVariable Long id, @RequestBody Categoria categoria) {
        log.info("atualizando categoria com id {} para {}", id, categoria);
        

        repository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "não existe essa categoria"));


        // BeanUtils.copyProperties(categoria, categoriaAtualizada);

        categoria.setId(id);
        repository.save(categoria);
        return ResponseEntity.ok(categoria);
    }


}

/*
 * static -> arquivos entregaveis pro cliente (imagem, javascript)
 * template -> vamos processar e mandar pro cliente (telas)
 * 
 * @RequestMapping -> requisição da página vai ser mapeada para esse método
 * 
 * @ResponseBody -> retorna resposta no corpo
 * 
 * @Controller -> em todas as controllers |
 * 
 * CTRL + SHIFT + P -> ADD STARTERS ->
 * 
 * devTools -> dependencia para salvar e ja recarregar a página
 * 
 * produces = "application/json" -> tipo de resposta é json, tipo de resposta
 */

/*
 * log info ->
 * Controller -> recebe as requisições
 */