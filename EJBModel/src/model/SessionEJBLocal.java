package model;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import javax.ws.rs.core.Response;

import model.entities.Person;

@Local
public interface SessionEJBLocal {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    <T> T persistEntity(T entity);

    <T> T mergeEntity(T entity);

    List<Person> getPersonFindAll();
    
    Person getPersonFindById(BigDecimal id);
    
    Response addPerson(Person p);
}
