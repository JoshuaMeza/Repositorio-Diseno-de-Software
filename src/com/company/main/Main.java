package com.company.main;

import com.company.controller.Controller;
import com.company.model.Model;
import com.company.view.List;

/**
 * U1_T6 - Entrega final de unidad
 * @author Joshua Immanuel Meza Magaña
 * @version 1.0.0
 * ─────▄───▄
 * ─▄█▄─█▀█▀█─▄█▄
 * ▀▀████▄█▄████▀▀
 * ─────▀█▀█▀
 */

public class Main {

    public static void main(String[] args) {
        List list = new List();
        Model model = new Model();
        Controller controller = new Controller( list, model );

        list.setVisible(true);
    }
}
