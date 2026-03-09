package com.alura.forohub.controller;

import com.alura.forohub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;


    @PostMapping
    @Transactional
    public void registrar(@RequestBody @Valid DatosRegistroTopico datosRegistro) {
        repository.save(new Topico(datosRegistro));
        System.out.println("¡Tópico guardado y validado con éxito!");
    }
    @GetMapping
    public Page<DatosListadoTopico> listadoTopicos(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = org.springframework.data.domain.Sort.Direction.ASC) Pageable paginacion) {

        return repository.findAll(paginacion).map(DatosListadoTopico::new);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> detallarTopico(@PathVariable Long id) {
        var topicoOptional = repository.findById(id);

        if (topicoOptional.isPresent()) {
            // Si el ID existe, lo empaquetamos en nuestro DTO y devolvemos un 200 OK
            return ResponseEntity.ok(new DatosListadoTopico(topicoOptional.get()));
        }

        // Si el ID no existe en la base de datos, devolvemos un error 404 Not Found
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosListadoTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizar) {
        var topicoOptional = repository.findById(id);

        if (topicoOptional.isPresent()) {
            var topico = topicoOptional.get();
            topico.actualizarDatos(datosActualizar);

            // Retornamos los datos actualizados para que el usuario vea cómo quedó
            return ResponseEntity.ok(new DatosListadoTopico(topico));
        }

        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        var topicoOptional = repository.findById(id);

        if (topicoOptional.isPresent()) {
            repository.deleteById(id);
            // Devuelve un 204 No Content, que es el estándar de éxito al borrar
            return ResponseEntity.noContent().build();
        }

        // Si intentan borrar un ID que no existe, devolvemos 404 Not Found
        return ResponseEntity.notFound().build();
    }
}