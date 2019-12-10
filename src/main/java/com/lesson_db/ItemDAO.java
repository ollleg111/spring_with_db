package com.lesson_db;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ItemDAO {
    private Class<Item> clazz;

    public Class<Item> getClazz() {
        return clazz;
    }

    public void setClazz(Class<Item> clazz) {
        this.clazz = clazz;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public Item findById(long id) {
        return entityManager.find(clazz, id);
    }

    public List findAll() {
        return entityManager.createQuery("from " + clazz.getName())
                .getResultList();
    }

    public void save(Item entity) {
        entityManager.persist(entity);
    }

    public Item update(Item entity) {
        return entityManager.merge(entity);
    }

    public void deleteItem(Item entity) {
        entityManager.remove(entity);
    }

    public void delete(long entityId) {
        Item entity = findById(entityId);
        deleteItem(entity);
    }
}
