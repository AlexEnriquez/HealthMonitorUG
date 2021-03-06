package com.grupocisc.healthmonitor.Glucose.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.grupocisc.healthmonitor.HealthMonitorApplicattion;

import com.grupocisc.healthmonitor.Glucose.activities.GlucoseActivity;
import com.grupocisc.healthmonitor.Glucose.activities.GlucoseRegistyActivity;
import com.grupocisc.healthmonitor.Glucose.adapters.GlucoseRecommendationsAdapter;
import com.grupocisc.healthmonitor.R;
import com.grupocisc.healthmonitor.entities.IPushNotification;
import com.grupocisc.healthmonitor.Utils.Utils;

import java.util.List;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GlucoseRecommendationsFragment extends Fragment  {

    private String TAG = "GlucoseRecoFragment";
    private RecyclerView recyclerView;
    private GlucoseRecommendationsAdapter adapter;
    private static List<IPushNotification.Recommendation> rowsRecommendations;
    private IPushNotification.RecommendationRequest recomendacionRequest;
    Call<IPushNotification.RecommendationRequest> call_1;
    Call<IPushNotification.RecommendationRequest> call_2;
    Call<IPushNotification.RecommendationRequest> call_3;
    Call<IPushNotification.RecommendationRequest> call_4;


    //private OnFragmentInteractionListener mListener;

    public GlucoseRecommendationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.notifications_tips_fragment, container, false);
        recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
        rowsRecommendations = new ArrayList<>();
        selectRecomendationsDB();
        return contentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }


    public  void selectRecomendationsDB(){

        try {
            //validar si en la tabla ahi datos mayor a 0



            String usuario = "";
            if(Utils.getAsmaFromPreference(getActivity())!=null) {
                usuario = Utils.getEmailFromPreference(getActivity());
            }



                IPushNotification notifiMensajes = HealthMonitorApplicattion.getApplication().getRetrofitAdapter().create(IPushNotification.class);

                call_1 = notifiMensajes.getRecommendations(new IPushNotification.ParamRequest(usuario,5));
                call_1.enqueue(new Callback<IPushNotification.RecommendationRequest>() {
                    @Override
                    public void onResponse(Call<IPushNotification.RecommendationRequest> call, Response<IPushNotification.RecommendationRequest> response) {
                        if(response.isSuccessful()) {
                            Log.e(TAG, "Tips Respuesta exitosa");
                            recomendacionRequest = response.body();
                            if (recomendacionRequest != null) {
                                if(recomendacionRequest.rows!=null){
                                    if(recomendacionRequest.rows.size() > 0 ){
                                        int idx=0;
                                        for (IPushNotification.rows registro : recomendacionRequest.rows) {
                                            idx++;
                                            IPushNotification.Recommendation rowIngresa  = new IPushNotification.Recommendation();
                                            rowIngresa.id = idx;
                                            rowIngresa.content = registro.recommendations;
                                            rowsRecommendations.add(rowIngresa);
                                        }
                                        //showLayout();
                                        //setear el adaptador con los datos
                                        int size = rowsRecommendations.size();
                                        for(int i = 0 ; i < size ; i++){
                                            Log.e(TAG,"id:" + rowsRecommendations.get(i).id +"-" + rowsRecommendations.get(i).content);
                                        }

                                    }
                                }

                            }else{
                                //showRetry();
                            }
                        }
                        else
                        {    //showRetry();
                            Log.e(TAG, "Tips Error en la petición");
                        }

                        //Invocar servicio contentFiltering
                        selectEstadisticas();
                    }

                    @Override
                    public void onFailure(Call<IPushNotification.RecommendationRequest> call, Throwable t) {
                        //showRetry();
                    }
                });





        } catch (Exception e) {
            Log.e(TAG, "Error" + e.toString());
        }
    }

    public  void selectEstadisticas(){

        try {
            //validar si en la tabla ahi datos mayor a 0



            String usuario = "";
            if(Utils.getAsmaFromPreference(getActivity())!=null) {
                usuario = Utils.getEmailFromPreference(getActivity());
            }



            IPushNotification notifiMensajes = HealthMonitorApplicattion.getApplication().getRetrofitAdapter().create(IPushNotification.class);

            call_3 = notifiMensajes.getEstadisticas(new IPushNotification.ParamRequest(usuario,5));
            call_3.enqueue(new Callback<IPushNotification.RecommendationRequest>() {
                @Override
                public void onResponse(Call<IPushNotification.RecommendationRequest> call, Response<IPushNotification.RecommendationRequest> response) {
                    if(response.isSuccessful()) {
                        Log.e(TAG, "Tips Respuesta exitosa");
                        recomendacionRequest = response.body();
                        if (recomendacionRequest != null) {
                            if(recomendacionRequest.rows!=null){
                                if(recomendacionRequest.rows.size() > 0 ){
                                    int idx=0;
                                    for (IPushNotification.rows registro : recomendacionRequest.rows) {
                                        idx++;
                                        IPushNotification.Recommendation rowIngresa  = new IPushNotification.Recommendation();
                                        rowIngresa.id = idx;
                                        rowIngresa.content = registro.recommendations;
                                        rowsRecommendations.add(rowIngresa);
                                    }
                                    //showLayout();
                                    //setear el adaptador con los datos
                                    int size = rowsRecommendations.size();
                                    for(int i = 0 ; i < size ; i++){
                                        Log.e(TAG,"id:" + rowsRecommendations.get(i).id +"-" + rowsRecommendations.get(i).content);
                                    }

                                }
                            }

                        }else{
                            //showRetry();
                        }
                    }
                    else
                    {    //showRetry();
                        Log.e(TAG, "Tips Error en la petición");
                    }

                    //Invocar servicio contentFiltering
                    selectDatamining();
                }

                @Override
                public void onFailure(Call<IPushNotification.RecommendationRequest> call, Throwable t) {
                    //showRetry();
                }
            });





        } catch (Exception e) {
            Log.e(TAG, "Error" + e.toString());
        }
    }

    public  void selectDatamining(){

        try {
            //validar si en la tabla ahi datos mayor a 0



            String usuario = "";
            if(Utils.getAsmaFromPreference(getActivity())!=null) {
                usuario = Utils.getEmailFromPreference(getActivity());
            }



            IPushNotification notifiMensajes = HealthMonitorApplicattion.getApplication().getRetrofitAdapter().create(IPushNotification.class);

            call_4 = notifiMensajes.getDatamining(new IPushNotification.ParamRequest(usuario,5));
            call_4.enqueue(new Callback<IPushNotification.RecommendationRequest>() {
                @Override
                public void onResponse(Call<IPushNotification.RecommendationRequest> call, Response<IPushNotification.RecommendationRequest> response) {
                    if(response.isSuccessful()) {
                        Log.e(TAG, "Tips Respuesta exitosa");
                        recomendacionRequest = response.body();
                        if (recomendacionRequest != null) {
                            if(recomendacionRequest.rows!=null){
                                if(recomendacionRequest.rows.size() > 0 ){
                                    int idx=0;
                                    for (IPushNotification.rows registro : recomendacionRequest.rows) {
                                        idx++;
                                        IPushNotification.Recommendation rowIngresa  = new IPushNotification.Recommendation();
                                        rowIngresa.id = idx;
                                        rowIngresa.content = registro.recommendations;
                                        rowsRecommendations.add(rowIngresa);
                                    }
                                    //showLayout();
                                    //setear el adaptador con los datos
                                    int size = rowsRecommendations.size();
                                    for(int i = 0 ; i < size ; i++){
                                        Log.e(TAG,"id:" + rowsRecommendations.get(i).id +"-" + rowsRecommendations.get(i).content);
                                    }

                                }
                            }

                        }else{
                            //showRetry();
                        }
                    }
                    else
                    {    //showRetry();
                        Log.e(TAG, "Tips Error en la petición");
                    }

                    //Invocar servicio contentFiltering
                    selectFiltros();
                }

                @Override
                public void onFailure(Call<IPushNotification.RecommendationRequest> call, Throwable t) {
                    //showRetry();
                }
            });





        } catch (Exception e) {
            Log.e(TAG, "Error" + e.toString());
        }
    }

    public  void selectFiltros(){

        try {
            //validar si en la tabla ahi datos mayor a 0



            String usuario = "";
            if(Utils.getAsmaFromPreference(getActivity())!=null) {
                usuario = Utils.getEmailFromPreference(getActivity());
            }



            IPushNotification notifiMensajes = HealthMonitorApplicattion.getApplication().getRetrofitAdapter().create(IPushNotification.class);

            call_2 = notifiMensajes.getFiltering(new IPushNotification.ParamRequest(usuario,5));
            call_2.enqueue(new Callback<IPushNotification.RecommendationRequest>() {
                @Override
                public void onResponse(Call<IPushNotification.RecommendationRequest> call, Response<IPushNotification.RecommendationRequest> response) {
                    if(response.isSuccessful()) {
                        Log.e(TAG, "Tips Respuesta exitosa");
                        recomendacionRequest = response.body();
                        if (recomendacionRequest != null) {
                            if(recomendacionRequest.rows!=null){
                                if(recomendacionRequest.rows.size() > 0 ){
                                    int idx=0;
                                    for (IPushNotification.rows registro : recomendacionRequest.rows) {
                                        idx++;
                                        IPushNotification.Recommendation rowIngresa  = new IPushNotification.Recommendation();
                                        rowIngresa.id = idx;
                                        rowIngresa.content = registro.recommendations;
                                        rowsRecommendations.add(rowIngresa);
                                    }
                                    //showLayout();
                                    //setear el adaptador con los datos
                                    int size = rowsRecommendations.size();
                                    for(int i = 0 ; i < size ; i++){
                                        Log.e(TAG,"id:" + rowsRecommendations.get(i).id +"-" + rowsRecommendations.get(i).content);
                                    }

                                }
                            }

                        }else{
                            //showRetry();
                        }
                    }
                    else
                    {    //showRetry();
                        Log.e(TAG, "Tips Error en la petición");
                    }


                    //Si no hay recomendaciones de ningun ws agregar por defecto
                    /*if(rowsRecommendations.size()==0){
                        IPushNotification.Recommendation r1 = new IPushNotification.Recommendation();
                        r1.content = "Su glucosa está dentro de los parámetros establecidos";
                        IPushNotification.Recommendation r2 = new IPushNotification.Recommendation();
                        r2.content = "Debe evitar comidas con azucar";
                        rowsRecommendations.add(r1);
                        rowsRecommendations.add(r2);
                    }*/
                    callsetAdapter(); // Muestra los datos recuperados

                }

                @Override
                public void onFailure(Call<IPushNotification.RecommendationRequest> call, Throwable t) {
                    //showRetry();
                }
            });





        } catch (Exception e) {
            Log.e(TAG, "Error" + e.toString());
        }
    }

    public void callsetAdapter(){
        //validacion si se a iniciado el adapter
        if (adapter != null){
            //actuliza la data del apdater
            Log.e(TAG,"adapter != null");
            adapter.updateData(rowsRecommendations);

        }else {//es nulo
            //crea la lista adapter
            Log.e(TAG,"adapter  null");
            adapter = new GlucoseRecommendationsAdapter(this, rowsRecommendations);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
        //selectDataGlucoseDB();
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.isVisible()) {
            if (isVisibleToUser) {
                GlucoseActivity.fab.setVisibility(View.GONE);
            } else {
                GlucoseActivity.fab.setVisibility(View.VISIBLE);
            }
        }
    }

    //    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
