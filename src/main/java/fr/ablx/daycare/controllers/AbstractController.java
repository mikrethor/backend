package fr.ablx.daycare.controllers;

import fr.ablx.daycare.errors.DayCareException;
import fr.ablx.daycare.jpa.Element;
import org.slf4j.Logger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractController<T extends Element> extends MainController {

    public abstract CrudRepository<T, Long> getRepository();

    public abstract Logger getLogger();

    public T getElementById(Long id) {
        return getRepository().findOne(id);
    }

    protected List<T> getAllElements() {
        List<T> list = new ArrayList<>();
        getRepository().findAll().forEach(list::add);
        return list;
    }

    protected void delete(Long id) throws DayCareException {
        try {
            getRepository().delete(id);
        } catch (Exception e) {
            getLogger().error(e.getMessage(),e);
            throw new DayCareException("Error when deleting element!");
        }
    }

    protected void deleteAll() throws DayCareException {
        try {
            getRepository().findAll().forEach(element->getRepository().delete(element));
        } catch (Exception e) {
            getLogger().error(e.getMessage(), e);
            throw new DayCareException("Error when deleting elements!");
        }
    }

    public ResponseEntity<?> create(T element) throws DayCareException {
        try {
            element = getRepository().save(element);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(element.getId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            getLogger().error("Error when creating element!", e);
            return ResponseEntity.noContent().build();
        }
    }

}
