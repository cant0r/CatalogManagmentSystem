package com.cant0r.cms.controllers;

import com.cant0r.cms.data.Container;
import com.cant0r.cms.data.ContainerRepository;
import com.cant0r.cms.data.EntryRepository;
import com.cant0r.cms.data.MediumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@Transactional
@RequestMapping(value = "/api/containers")
public class ContainerController {
    @Autowired
    private ContainerRepository repo;
    @Autowired
    private MediumRepository mediumRepository;
    @Autowired
    private EntryRepository entryRepository;

    @GetMapping
    public long countContainers(@RequestParam boolean count) {
        if (count) {
            try {
                return repo.count();
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.OK, "Bad request!!");
            }
        }
        return -1;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Container createContainer(@RequestBody Container c) {
        try {
            return repo.save(c);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage().toString());
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Container updateContainer(@RequestBody Container c) {
        try {
            return createContainer(c);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage().toString());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteContainerById(@PathVariable("id") int c_id) {
        try {
            for (var item : mediumRepository.findAllByContainer_Id(c_id)) {
                System.out.println("medium_id: " + item.getId());
                entryRepository.deleteAllByMedium_Id(item.getId());
            }
            mediumRepository.deleteAllByContainer_Id(c_id);
            repo.deleteById(c_id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.OK, "There's nothing to delete! ");
        }
    }

    @GetMapping("/all")
    public List<Container> getAll(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int entries) {
        try {
            Pageable p = PageRequest.of(pageNumber, entries);
            return repo.findAll(p).getContent();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/all-params")
    public List<Container> getAll(
            @RequestParam(defaultValue = "") String t,
            @RequestParam(defaultValue = "") String n,
            @RequestParam(defaultValue = "") String l,
            @RequestParam(defaultValue = "60000000") int c,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int entries) {
        try {
            Pageable p = PageRequest.of(pageNumber, entries);
            return repo.findAllByParams(t, n, l, c, p).getContent();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
