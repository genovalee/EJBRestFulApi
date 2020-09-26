package model;

import java.math.BigDecimal;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.ws.rs.core.Response;

import model.entities.Person;

@Stateless(name = "SessionEJB", mappedName = "REST-EJBModel-SessionEJB")
public class SessionEJBBean implements SessionEJBLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "EJBModel")
    private EntityManager em;

    public SessionEJBBean() {
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public <T> T persistEntity(T entity) {
        em.persist(entity);
        return entity;
    }

    public <T> T mergeEntity(T entity) {
        return em.merge(entity);
    }

    /** <code>select o from Person o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Person> getPersonFindAll() {
        return em.createNamedQuery("Person.findAll", Person.class).getResultList();
    }

    @Override
    public Person getPersonFindById(BigDecimal id) {
        // TODO Implement this method
        return em.createNamedQuery("Person.findById", Person.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public Response addPerson(Person p) {
        em.createNativeQuery("INSERT INTO person (id, age, name) VALUES (?,?,?)")
          .setParameter(1, p.getId())
          .setParameter(2, p.getAge())
          .setParameter(3, p.getName())
          .executeUpdate();
        String resp = "{\"message\":\"資料已新增\",\"status\":\"" + Response.Status.CREATED + "\"}";
        return Response.status(Response.Status.CREATED)
                       .entity(resp)
                       .build();
    }
}
