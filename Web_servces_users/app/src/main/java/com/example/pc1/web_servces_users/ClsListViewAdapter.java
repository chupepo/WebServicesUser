package com.example.pc1.web_servces_users;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import res.ClienteRest;

/**
 * Created by pc1 on 25/02/2016.
 */
public class ClsListViewAdapter extends BaseAdapter {
    private List<ClienteRest> clientesRest;
    private Context context;
    private ImageView imagAvatar;
    private TextView txtTypo;
    private TextView txtUrl;
    //ImageView imagAvatar,TextView txtTypo, TextView txtUrl
    public ClsListViewAdapter(List<ClienteRest> clientesRest,Context context){
        this.clientesRest = clientesRest;
        this.context = context;
        //this.txtUrl = txtUrl;
    }

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.items_list_view,null);
        }

        ClienteRest cr = this.clientesRest.get(position);

        ImageView imageView = (ImageView) view.findViewById(R.id.imgUser);
        TextView txtLogin = (TextView) view.findViewById(R.id.txtLogin);
        TextView txtType = (TextView) view.findViewById(R.id.txtTypo);

        txtLogin.setText(cr.getLogin());
        txtType.setText(cr.getType());


        //imageView.setImageDrawable(Drawable.createFromPath(cr.getAvatar_url()));
        //imageView.setImageURI(Uri.parse(cr.getAvatar_url()));

        return view;
    }

}
