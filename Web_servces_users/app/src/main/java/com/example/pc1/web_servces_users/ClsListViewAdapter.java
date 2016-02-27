package com.example.pc1.web_servces_users;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import res.ClienteRest;

/**
 * Created by pc1 on 25/02/2016.
 */
public class ClsListViewAdapter extends BaseAdapter {

    /*Declaración de atributos privados*/
    private List<ClienteRest> clientesRest;
    private Context context;
    private ImageView imagAvatar;

    /* Se decllara el construtor de la clase */
    public ClsListViewAdapter(List<ClienteRest> clientesRest,Context context){
        this.clientesRest = clientesRest;
        this.context = context;
    }

    /*Se cran los metodos getters and setters*/
    public ImageView getImagAvatar() {
        return imagAvatar;
    }

    public void setImagAvatar(ImageView imagAvatar) {
        this.imagAvatar = imagAvatar;
    }

    @Override
    public int getCount() {
        return clientesRest.size();
    }

    @Override
    public Object getItem(int position) {
        return clientesRest.get(position);
    }

    @Override
    public long getItemId(int position) {

        return clientesRest.get(position).getId();
    }

    /*
    * Este metodo genera cada uno de los Items del ListView
    * ademas carga los campos con su respectivo valor
    * */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        /*Preguntamos si el view se encuentra nulo si es asi lo cargamos la informacion correspondiente */
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.items_list_view,null);
        }

        /*Se crea un nuevo objeto de tipo ClienteRest */
        ClienteRest cr = this.clientesRest.get(position);

        /*Se asignan las variables de las vistas*/
        ImageView imageView = (ImageView) view.findViewById(R.id.imgUser);
        TextView txtLogin = (TextView) view.findViewById(R.id.txtLogin);
        TextView txtType = (TextView) view.findViewById(R.id.txtTypo);

        /*Se setea el valor de la variables con su información correspondiente*/
        txtLogin.setText(cr.getLogin());
        txtType.setText(cr.getType());

        /*La libreria picasso nos ayuda a que la imagen obtenida del web service se muestre en nuestra vista*/
        Picasso.with(context).load(cr.getAvatar_url()).into(imageView);

        view.setId(position);
        return view;
    }

}