package learning.techgenii.querydsljpa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The interface Crud controller.
 *
 * @param <T> The command object to be used
 * @param <I> The identifier type
 */
public interface CrudController<T,I> {

    /**
     * Create response entity.
     *
     * @param t the t
     * @return the response entity
     * @throws JsonProcessingException the json processing exception
     */
    @PostMapping("/create")
    ResponseEntity create(@RequestBody T t) throws JsonProcessingException;


    /**
     * Update response entity.
     *
     * @param t the t
     * @return the response entity
     */
    @PutMapping("/update")
    ResponseEntity update(@RequestBody T t);

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("")
    ResponseEntity get(@RequestParam I id);

    /**
     * Delete.
     *
     * @param id the id
     */
    @DeleteMapping("")
    void delete(@RequestParam I id);

}
