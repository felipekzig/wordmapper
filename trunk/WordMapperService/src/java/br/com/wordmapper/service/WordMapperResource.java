package br.com.wordmapper.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.wordmapper.utils.Dictionary;
import br.com.wordmapper.utils.UsersSingUp;
import java.net.URLDecoder;

/**
 * REST Web Service
 *
 * @author Felipe e Bruno
 * http://localhost:8080/WordMapperService/resources/WordMapper/1/%7B%22Word%22:%22need%22,%22IdTpOperation%22:1,%22IdMainDict%22:%22wn%22%7D
 */
@Path("WordMapper")
public class WordMapperResource {

    private static final int DEFINE_OPERATION = 0;
    private static final int SINGUP_OPERATION = 1;

    @GET
    @Path("{IdOperation}/{json}")
    @Produces("application/json")
    public String getJson(@PathParam("IdOperation") int idOperation, @PathParam("json") String json) {
        switch (idOperation) {
            case DEFINE_OPERATION:
                return this._defineOperation(URLDecoder.decode(json));

            case SINGUP_OPERATION:
                return this._singUpOperation(URLDecoder.decode(json));
        }
        return this.getErrorJson("A operção solicitada é inválida ou não foi implementada.");
    }

    private String _defineOperation(String json) {
        try {
            Dictionary dict = new Dictionary(json);

            dict.execute();

            return dict.getResponse();
        } catch (Exception e) {
            return this.getErrorJson(e.getLocalizedMessage());
        }
    }

    private String _singUpOperation(String json) {
        UsersSingUp user = new UsersSingUp(json);

        if (!user.execute()) {
            return this.getErrorJson(user.error);
        }

        return user.getResponse();
    }

    public String getErrorJson(String msg) {
        return "{\"Erro\":\"" + msg + "\"}";
    }
}