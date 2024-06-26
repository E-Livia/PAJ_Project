package com.example.calendar_backend.ejb;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.example.calendar_backend.models.User;
import com.example.calendar_backend.services.UserService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EjbUserController {

    @EJB
    private UserService userService;

    @POST
    public Response createUser(User user) {
        userService.createUser(user);
        return Response.ok().build();
    }

    @GET
    @Path("/{username}")
    public Response getUserByUsername(@PathParam("username") String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

    @PUT
    @Path("/{username}")
    public Response updateUser(@PathParam("username") String username, User user) {
        User existingUser = userService.getUserByUsername(username);
        if (existingUser == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        user.setUserId(existingUser.getUserId()); // Ensure ID consistency
        userService.updateUser(user);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{username}")
    public Response deleteUserByUsername(@PathParam("username") String username) {
        userService.deleteUserByUsername(username);
        return Response.ok().build();
    }

    @GET
    @Path("/checkUsername/{username}")
    public Response checkUsernameUnique(@PathParam("username") String username) {
        boolean isUnique = userService.isUsernameUnique(username);
        return isUnique ? Response.ok().build() : Response.status(Response.Status.CONFLICT).build();
    }

    @GET
    @Path("/checkEmail/{email}")
    public Response checkEmailUnique(@PathParam("email") String email) {
        boolean isUnique = userService.isEmailUnique(email);
        return isUnique ? Response.ok().build() : Response.status(Response.Status.CONFLICT).build();
    }
}