package service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import model.SessionEJBLocal;
import model.entities.Person;
@Stateless
@Path("persons")
public class PersonImpl {
    public PersonImpl() {
        super();
    }
    @EJB(beanName="SessionEJB")
    SessionEJBLocal mySessionBean;

    @GET
    @Produces("application/json")
    @Path("/list")
    public List<Person> getPersonFindAll(){
        return mySessionBean.getPersonFindAll();
    }

    @GET
    @Produces("application/json")
    @Path("/person")
    public Person getPersonFindById(@QueryParam("id") BigDecimal id){
        return mySessionBean.getPersonFindById(id);
    }

    @POST
    @Produces("application/json")
    @Path("/add")
    public Response addPerson(Person p){
        return mySessionBean.addPerson(p);
    }
}

