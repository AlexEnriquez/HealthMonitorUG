package com.grupocisc.healthmonitor.entities;



import java.util.List;

import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Andres Onate GrupoLink on 09/04/2015.
 */

public interface IV2ConsultPulsePressure {

    @POST("controlServices/diabetes/patientUsers/queryPressureLog")
    Call<Obj> getConsultPulsePressure(@Body ObjUserdate objUser);


    public class Obj {
        @Getter
        @Setter
        int idCodResult;
        @Getter
        @Setter
        String resultDescription;
        @Getter
        @Setter
        List<rows> rows ;
    }

    public class rows {
        @Getter
        @Setter
        int idRegisterDB;
        @Getter
        @Setter
        float pressureDiastolica;
        @Getter
        @Setter
        float pressureSistolica;
        @Getter
        @Setter
        int pulse;
        @Getter
        @Setter
        String activity;
        @Getter
        @Setter
        String date;
        @Getter
        @Setter
        String observations;

    }
}

