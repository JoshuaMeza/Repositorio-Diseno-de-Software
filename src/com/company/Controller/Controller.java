package com.company.Controller;

import com.company.Model.Model;
import com.company.View.List;

public class Controller {
    private List list;
    private Model model;

    public Controller(List list, Model model) {
        this.list = list;
        this.model = model;
        resetListOutput();
    }

    public void resetListOutput() {
        list.setOutputBox( model.getStudents() );
    }
}
