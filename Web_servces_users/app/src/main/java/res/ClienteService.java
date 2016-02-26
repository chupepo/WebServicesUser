package res;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by pc1 on 25/02/2016.
 */
public interface ClienteService {
    @GET("/users")
    void getCliente(Callback<List<ClienteRest>> callback);
}
