package com.cant0r.cms.controllers;

import com.cant0r.cms.data.Entry;
import com.cant0r.cms.data.EntryRepository;
import com.cant0r.cms.data.Medium;
import com.cant0r.cms.data.MediumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/mediums")
public class MediumController {
    @Autowired
    private MediumRepository repo;

    @GetMapping
    public long countMediums(@RequestParam boolean count) {
        if(count) {
            try{
                return repo.count();
            }
            catch(Exception e) {
                throw new ResponseStatusException(HttpStatus.OK, "Bad request!!");
            }
        }
        return -1;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Medium createMedium(@RequestBody Medium c) {
        try{
            return repo.save(c);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage().toString());
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Medium updateMedium(@RequestBody Medium c) {
        try{
            return createMedium(c);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage().toString());
        }
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMediumByName(@RequestParam int id) {
        try{
            repo.deleteById(id);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There's nothing to delete!");
        }
    }

    @GetMapping("/all")
    public List<Medium> getAllMediums(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int entries) {
        try{
            Pageable p = PageRequest.of(pageNumber,entries);
            return repo.findAll(p).getContent();
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @GetMapping("/all-params")
    public List<Medium> getAllMediums(
            @RequestParam(defaultValue = "%")String n,
            @RequestParam(defaultValue = "7000000")int s,
            @RequestParam(defaultValue = "%")String containerName,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int entries) {
        try{
            Pageable p = PageRequest.of(pageNumber,entries);
            return repo.findAllByParams(s,n,containerName,p).getContent();
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
