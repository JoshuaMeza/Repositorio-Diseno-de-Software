package com.company.main;

import com.company.controller.Controller;
import com.company.model.Model;
import com.company.view.List;
import com.company.view.Login;

/**
 * U1_T6 - Entrega final de unidad
 * @author Joshua Immanuel Meza Magaña
 * @version 1.0.3
 * ─────▄───▄
 * ─▄█▄─█▀█▀█─▄█▄
 * ▀▀████▄█▄████▀▀
 * ─────▀█▀█▀
 */

public class Main {

    public static void main(String[] args) {
        List list = new List();
        Login login = new Login();
        Model model = new Model();
        Controller controller = new Controller( list, model, login );

        list.setVisible(false);
        login.setVisible(true);
    }
}
