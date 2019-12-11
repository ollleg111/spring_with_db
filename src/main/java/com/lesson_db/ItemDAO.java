package com.lesson_db;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ItemDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private Item item;

    public ItemDAO(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item findById(long id) {
        return entityManager.find(Item.class, id);
    }

    public void save(Item item) {
        entityManager.persist(item);
    }

    public Item update(Item item) {
        return entityManager.merge(item);
    }

    public void delete(long id) {
        Item entity = findById(id);
        deleteItem(entity);
    }

    //хз
    public void deleteItem(Item item) {
        entityManager.remove(item);
    }

    //хз
    public List findAll() {
        Query query = entityManager.createNativeQuery("SELECT * FROM ITEM", Item.class);
        return query.getResultList();
    }
}
