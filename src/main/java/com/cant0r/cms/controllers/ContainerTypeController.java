package com.cant0r.cms.controllers;

import com.cant0r.cms.data.*;
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
@RequestMapping(value = "/api/types")
public class ContainerTypeController {

    @Autowired
    public ContainerTypeRepository repo;
    @Autowired
    private ContainerRepository containerRepo;
    @Autowired
    private MediumRepository mediumRepo;
    @Autowired
    private EntryRepository entryRepository;

    @GetMapping
    public long countContainerTypes(@RequestParam boolean count) {
        if (count) {
            try {
                long l = repo.count();
                return l;
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.OK, "Bad request!!");
            }
        }
        return -1;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContainerType createContainerType(@RequestBody ContainerType c) {
        try {
            return repo.save(c);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage().toString());
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ContainerType updateContainerType(@RequestBody ContainerType c) {
        try {
            return createContainerType(c);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage().toString());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteContainerTypeByName(@PathVariable("id") String cName) {
        try {
            for (var container : containerRepo.findAllByType_NameEquals(cName)) {
                for(var medium: mediumRepo.findAllByContainer_Id(container.getId()))
                    entryRepository.deleteAllByMedium_Id(medium.getId());
                mediumRepo.deleteAllByContainer_Id(container.getId());
            }
            containerRepo.deleteAllByType_Name(cName);
            repo.deleteById(cName);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.OK, "There's nothing to delete! " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<ContainerType> getAllCTs(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int entries) {
        try {
            Pageable p = PageRequest.of(pageNumber, entries);
            List<ContainerType> l = repo.findAll(p).getContent();
            return l;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/all-params")
    public List<ContainerType> getAllCTs(
            @RequestParam(defaultValue = "") String n,
            @RequestParam(defaultValue = "") String d,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int entries) {
        try {
            Pageable p = PageRequest.of(pageNumber, entries);
            return repo.findAllByParams(n, d, p).getContent();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
