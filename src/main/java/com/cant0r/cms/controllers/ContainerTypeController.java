package com.cant0r.cms.controllers;

import com.cant0r.cms.data.Container;
import com.cant0r.cms.data.ContainerType;
import com.cant0r.cms.data.ContainerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/types")
public class ContainerTypeController {

    @Autowired
    public ContainerTypeRepository repo;

    @GetMapping
    public long countContainerTypes(@RequestParam boolean count) {
        if(count) {
            try{
                long l = repo.count();
                return l;
            }
            catch(Exception e) {
                throw new ResponseStatusException(HttpStatus.OK, "Bad request!!");
            }
        }
        return -1;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContainerType createContainerType(@RequestBody ContainerType c) {
        try{
            return repo.save(c);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage().toString());
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ContainerType updateContainerType(@RequestBody ContainerType c) {
        try{
            return createContainerType(c);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage().toString());
        }
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteContainerTypeByName(@PathVariable("id") String cName) {
        try{
            repo.deleteById(cName);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.OK, "There's nothing to delete!");
        }
    }

    @GetMapping("/all")
    public List<ContainerType> getAllCTs(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int entries) {
        try{
            Pageable p = PageRequest.of(pageNumber,entries);
            List<ContainerType> l = repo.findAll(p).getContent();
            return l;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @GetMapping("/all-params")
    public List<ContainerType> getAllCTs(
            @RequestParam(defaultValue = "")String n,
            @RequestParam(defaultValue = "")String d,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int entries) {
        try{
            Pageable p = PageRequest.of(pageNumber,entries);
            return repo.findAllByParams(n,d,p).getContent();
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
