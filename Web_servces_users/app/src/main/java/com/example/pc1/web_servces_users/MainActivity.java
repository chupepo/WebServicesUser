package com.example.pc1.web_servces_users;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import res.ClienteRest;
import res.ClienteService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /*Se declaran las variables de la clase*/
    private TextView txtListaCliente;
    private ListView list;
    private ClsListViewAdapter clsListViewAdapter;
    private RestAdapter restAdapter;
    private ClienteService clienteService;
    private int totalItemCount2;
    private AbsListView view2;

    //Declaracion de constante para consuymir el web service
    private static String URL="https://api.github.com/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtListaCliente = (TextView)findViewById(R.id.txtListaClientes);
        list = (ListView)findViewById(R.id.list);

        /*Metodo inicia la aplicacion y cargar elweb service y muestra los datos*/
        startWithTheApp();

        /*Este ScrollListener seencarga deescuchar cuando se ocupaagregar mas Item ListView*/
        list.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, final int totalItemCount) {
                totalItemCount2 = totalItemCount;
                view2 = view;
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
                    restAdapter = new RestAdapter.Builder().setEndpoint("https://api.github.com").build();
                    clienteService = restAdapter.create(ClienteService.class);

                    clienteService.getCliente(new Callback<List<ClienteRest>>() {
                        @Override
                        public void success(List<ClienteRest> clienteRests, Response response) {


                        }

                        @Override
                        public void failure(RetrofitError error) {
                            txtListaCliente.setText(error.getMessage());
                        }
                    });
                    //view.getItemIdAtPosition(totalItemCount-1)
                    Toast.makeText(MainActivity.this, "Ejemplo del ScrollListener", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*Este metodo se encargar de iniciar la aplicacion*/
    public void startWithTheApp(){

        restAdapter = new RestAdapter.Builder().setEndpoint("https://api.github.com").build();
        clienteService = restAdapter.create(ClienteService.class);

        clienteService.getCliente(new Callback<List<ClienteRest>>() {
            @Override
            public void success(List<ClienteRest> clienteRests, Response response) {
                List<ClienteRest> arrayClienteList = new ArrayList<ClienteRest>();

                /*Este metodo carga los primeros 10 elementos del web service*/
                //ArrayList<ClienteRest> arrayClentest = runListClientesResAndGetTheTenClientesRest(clienteRests);

                //list.setAdapter(clsListViewAdapter)
                clsListViewAdapter = new ClsListViewAdapter(clienteRests, getApplicationContext());

                list.setAdapter(clsListViewAdapter);
                list.setOnItemClickListener(MainActivity.this);
            }

            @Override
            public void failure(RetrofitError error) {
                txtListaCliente.setText(error.getMessage());
            }
        });
    }

    /*
    * Este metododetecta cuando un suario hace click sobre cualquiera de los items del ListView y carga los datos
    * en una nueva vista
    * */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final long idClienteResClicked = id;

        clienteService.getCliente(new Callback<List<ClienteRest>>() {
            @Override
            public void success(List<ClienteRest> clienteRests, Response response) {

                ClsListViewAdapter clsListViewAdapter = new ClsListViewAdapter(clienteRests, getApplicationContext());

                /*Se encuentra al clienteRest al que el usuario dio click*/
                ClienteRest clienteRest = runListClientesResAndGetOne(clienteRests, idClienteResClicked);

                /*Se prepara para ostra la otra vista*/
                Intent i = new Intent(MainActivity.this,InfoClienteRest.class);

                /*Se manda informacion a la otra activity para para cargarlo con los datos adecuados*/
                i.putExtra("avatarUrl",clienteRest.getAvatar_url());
                i.putExtra("idr",clienteRest.getId());
                i.putExtra("login", clienteRest.getLogin());
                i.putExtra("site_admin", clienteRest.getSite_admin());
                i.putExtra("url", clienteRest.getUrl());
                i.putExtra("html_url", clienteRest.getHtml_url());
                i.putExtra("tipo", clienteRest.getType());

                startActivity(i);
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("me cai");
                txtListaCliente.setText(error.getMessage());
            }
        });

        Toast.makeText(MainActivity.this,idClienteResClicked+id+"",Toast.LENGTH_SHORT).show();
    }

    /*Este metodo  recorre todola lista que se obtiene del llamado al webservice y retorna el que andamos buscndo*/
    private ClienteRest runListClientesResAndGetOne(List<ClienteRest> listClienteRest, long idClienteRest){

        ClienteRest clienteRest = null;
        for (int i = 0; i < listClienteRest.size() ;i++ ) {

            if(listClienteRest.get(i).getId() == idClienteRest){
                clienteRest = new ClienteRest();
                clienteRest.setAvatar_url(listClienteRest.get(i).getAvatar_url());
                clienteRest.setLogin(listClienteRest.get(i).getLogin());
                clienteRest.setId(listClienteRest.get(i).getId());
                clienteRest.setSite_admin(listClienteRest.get(i).getSite_admin());
                clienteRest.setUrl(listClienteRest.get(i).getUrl());
                clienteRest.setHtml_url(listClienteRest.get(i).getHtml_url());
                clienteRest.setType(listClienteRest.get(i).getType());
                break;
            }
        }
        return clienteRest;
    }

    /*Este metodo recorre y envia los primeros 10 registros que se obtuvieron del web service*/
    private ArrayList<ClienteRest> runListClientesResAndGetTheTenClientesRest(List<ClienteRest> listClienteRest){
        ArrayList<ClienteRest> ArrayListCliente = new ArrayList<ClienteRest>();
        ClienteRest clienteRest = null;
        for (int i = 0; i < listClienteRest.size() ;i++ ) {

            if(i<10){
                ArrayListCliente.add(listClienteRest.get(i));

            }else{
                break;
            }
        }
        return ArrayListCliente;
    }
}