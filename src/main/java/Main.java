import controller.Controller;
import model.Model;
import view.List;
import view.Login;

/**
 * Final Project
 * @author Joshua Immanuel Meza Magaña
 * @version 1.0.5
 * ─────▄───▄
 * ─▄█▄─█▀█▀█─▄█▄
 * ▀▀████▄█▄████▀▀
 * ─────▀█▀█▀
 */

public class Main {
    public static void main(String[] args) {
        new Controller( new List(), Model.getInstance(), new Login() ).start();
    }
}
