package com.company.controller;

import com.company.model.Model;
import com.company.view.List;

public class Controller {
    private List list;
    private Model model;

    public Controller(List list, Model model) {
        this.list = list;
        this.model = model;
        resetListOutput();
    }

    public void resetListOutput() {
        list.setOutputBox( model.printStudents() );
    }
}
