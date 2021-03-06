package com.grupocisc.healthmonitor.Settings.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.grupocisc.healthmonitor.BuildConfig;
import com.grupocisc.healthmonitor.R;
import com.grupocisc.healthmonitor.Utils.Utils;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity { // implements OnMapReadyCallback

    @BindView(R.id.txt_version_name)
    TextView txt_version_name;

    @BindView(R.id.tvAdvertisement)
    TextView tvAdvertisement;
    //private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);

        Utils.SetStyleToolbarLogo(this);

        String versionName = BuildConfig.VERSION_NAME;
        //txt_version_name = (TextView) findViewById(R.id.txt_version_name);
        txt_version_name.setText("Health Monitor V "+ versionName );

        tvAdvertisement.setText("El fin de esta aplicación es " +
                "únicamente con propósitos de mejoramiento " +
                "y acondicionamiento de la salud, su uso no " +
                "está indicado para diagnosticar enfermedades " +
                "o para ser una cura.");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //SupportMapFragment advertisementFragment = (SupportMapFragment) getSupportFragmentManager()
        //        .findFragmentById(R.id.advertisement);

        //TextView tv_advertisement =(TextView) advertisementFragment.getView();
        //tv_advertisement.setText("");
        //mapFragment.getMapAsync(this);


    }

    //se ejecuta al seleccionar el icon back del toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            this.finish();
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-2.1826184030722544, -79.8977279663086);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Universidad de Guayaquil"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//
//        // Zoom in, animating the camera.
//        mMap.animateCamera(CameraUpdateFactory.zoomIn());
//
//        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
//
//
//    }

}
