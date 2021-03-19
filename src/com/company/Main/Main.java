package com.company.Main;

import com.company.Controller.Controller;
import com.company.Model.Model;
import com.company.View.List;

public class Main {

    public static void main(String[] args) {
        List list = new List();
        Model model = new Model();
        Controller controller = new Controller( list, model );

        list.setVisible(true);
    }
}
