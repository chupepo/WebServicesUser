package com.example.pc1.web_servces_users;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by pc1 on 26/02/2016.
 */
public class InfoClienteRest extends AppCompatActivity{

    /*Declaración de atributos privados*/
    private ImageView imageView;
    private TextView txtLogin;
    private TextView txtUrl;
    private TextView txtHtmlUrl;
    private TextView txtSiteAdmin;
    private TextView txtTipo;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_cliente_rest);

        /*Se inicializan los atributos de la clase */
        txtLogin= (TextView)findViewById(R.id.txtLogin);
        txtUrl= (TextView)findViewById(R.id.txtUrl);
        txtHtmlUrl= (TextView)findViewById(R.id.txtHtmlUrl);
        txtSiteAdmin= (TextView)findViewById(R.id.txtSiteAmin);
        txtTipo= (TextView)findViewById(R.id.txtTipo);
        imageView = (ImageView)findViewById(R.id.imgAvatarUser);

        /*Se setean los datos de la vista con cada uno de sus valores que se obtuvieron del web service */
        txtLogin.setText(getIntent().getExtras().getString("login"));
        txtUrl.setText(getIntent().getExtras().getString("url"));
        txtHtmlUrl.setText(getIntent().getExtras().getString("html_url"));
        txtSiteAdmin.setText(getIntent().getExtras().getString("site_admin"));
        txtTipo.setText(getIntent().getExtras().getString("tipo"));
        Picasso.with(getBaseContext()).load(getIntent().getExtras().getString("avatarUrl")).into(imageView);

    }
}
