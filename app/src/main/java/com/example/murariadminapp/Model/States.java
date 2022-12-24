package com.example.murariadminapp.Model;

import java.util.List;

public class States {
    public String[] getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states.toArray(new String[states.size()]);
    }

    public static String[] states;
}
