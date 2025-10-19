package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.MyList;
import org.acme.repositories.MyListRepository;

import java.util.List;
import java.util.UUID;

@Path("/api/my-list")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MyListResource {
    
    @Inject
    MyListRepository myListRepository;
    
    @GET
    public List<MyList> getAll() {
        return myListRepository.listAll();
    }
    
    @POST
    @Transactional
    public MyList createMylist(MyList newMyList) {
        myListRepository.persist(newMyList);
        return newMyList;
    }
}
