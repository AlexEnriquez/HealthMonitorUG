package com.grupocisc.healthmonitor.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by andres on 10/8/17.
 */

public class ObjNewPass {
    @Getter
    @Setter
    private String identifier ;
    @Getter
    @Setter
    private String newPassword;

    public ObjNewPass(String identifier, String newPassword) {
        this.identifier = identifier;
        this.newPassword = newPassword;
    }

}
