package com.example.pc1.web_servces_users;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

import res.ClienteRest;
import res.ClienteService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    /*Variables para manipular los controles de la UI*/
    private EditText etResponse;
    private TextView tvIsConnected;
    private TextView txtValues;
    public JSONObject jsonObject;
    public ArrayAdapter<String> adapter;
    public ArrayList<String> arrayList;

    private TextView txtListaCliente;

    public String[] list2;
    InputStream is = null;

    String line = null;
    String result = null;
    //Declaracion de constante para consuymir el web service
    private static String URL="https://api.github.com/users";
    private String saveReaponseUsers;
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtListaCliente = (TextView)findViewById(R.id.txtListaClientes);
        list = (ListView)findViewById(R.id.list);

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("https://api.github.com").build();
        ClienteService clienteService = restAdapter.create(ClienteService.class);

        clienteService.getCliente(new Callback<List<ClienteRest>>() {
            @Override
            public void success(List<ClienteRest> clienteRests, Response response) {

                ClsListViewAdapter clsListViewAdapter = new ClsListViewAdapter(clienteRests,getApplicationContext());
                list.setAdapter(clsListViewAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                txtListaCliente.setText(error.getMessage());
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
}


