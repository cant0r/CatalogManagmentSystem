package com.cant0r.cms.controllers;

import com.cant0r.cms.data.Entry;
import com.cant0r.cms.data.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/entries")
public class EntryController {
    @Autowired
    private EntryRepository repo;


    @GetMapping
    public long countEntries(@RequestParam boolean count) {
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
    public Entry createEntry(@RequestBody Entry c) {
        try{
            return repo.save(c);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage().toString());
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Entry updateEntry(@RequestBody Entry c) {
        try{
            return createEntry(c);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage().toString());
        }
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEntryByName(@PathVariable("id") int id) {
        try{

            repo.deleteById(id);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.OK, "There's nothing to delete!");
        }
    }

    @GetMapping("/all")
    public List<Entry> getAllEntry(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int entries) {
        try{
            Pageable p = PageRequest.of(pageNumber,entries);
            return repo.findAll(p).getContent();
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @GetMapping("/all-params")
    public List<Entry> getAllEntry(
            @RequestParam(defaultValue = "")String n,
            @RequestParam(defaultValue = "7000000")int s,
            @RequestParam(defaultValue = "")String discName,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int entries) {
        try{
            Pageable p = PageRequest.of(pageNumber,entries);
            return repo.findAllByParams(s,n,discName,p).getContent();
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
