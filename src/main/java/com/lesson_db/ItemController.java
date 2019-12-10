package com.lesson_db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/item")
public class ItemController {
    private ItemDAO itemDao;

    @Autowired
    public ItemController(ItemDAO itemDao) {
        this.itemDao = itemDao;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/save",
            produces = "text/plain")
    public ResponseEntity<String> save(Item item) {
        try {
            itemDao.save(item);
            return new ResponseEntity<>(" Item was saved ", HttpStatus.CREATED);

        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/find",
            produces = "text/plain")
    public ResponseEntity<String> findById(long id) {
        try {
            itemDao.findById(id);
            return new ResponseEntity<>(" Item was found ", HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/update",
            produces = "text/plain")
    public ResponseEntity<String> update(Item item) {
        try {
            itemDao.update(item);
            return new ResponseEntity<>(" Item was updated ", HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/delete",
            produces = "text/plain")
    public ResponseEntity<String> delete(long id) {
        try {
            itemDao.delete(id);
            return new ResponseEntity<>(" Item was deleted ", HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
