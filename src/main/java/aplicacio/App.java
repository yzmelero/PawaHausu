package aplicacio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import presentacio.MenuPrincipal;
import aplicacio.model.Usuari;
import presentacio.MenuFamilia;
import presentacio.MenuProveidor;
import presentacio.MenuReferencia;

/**
Classe principal de l'aplicació JavaFX.
 * Aquesta classe gestiona l'inici de l'aplicació, carrega les vistes 
 * i gestiona la navegació entre elles.
 * @author ngallardo
 * @version 10/2024.1
 */
public class App extends Application {

    private static Scene scene;

    /**
     * descripció: Inicia l'aplicació i configura la primera escena.
     * @param stage El stage principal de l'aplicació.
     * @throws IOException Si hi ha un error al carregar la vista inicial.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("iniciSessio"), 800, 650);
        stage.setScene(scene);
        stage.setTitle("PawaHausu");
        stage.show();
    }

    
    /**
     * Estableix la vista actual de l'aplicació.
     * Carrega el FXML corresponent i, si és necessari, passa l'usuari autenticat
     * als controladors de les vistes.
     *
     * @param fxml El nom del fitxer FXML a carregar.
     * @param usuari L'objecte Usuari a passar al controlador de la vista.
     * @throws IOException Si hi ha un error al carregar el FXML.
     */
    public static void setRoot(String fxml, Usuari usuari) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/cat/copernic/projecte1_equip1/" + fxml + ".fxml"));
        Parent root = fxmlLoader.load();

        if (fxml.equals("menuPrincipal")) {
            MenuPrincipal controller = fxmlLoader.getController();
            controller.setUsuari(usuari); 
        }

        if (fxml.equals("menuFamilia")) {
            MenuFamilia controller = fxmlLoader.getController();
            controller.setUsuari(usuari); 
        }
        
        if (fxml.equals("menuReferencia")) {
            MenuReferencia controller = fxmlLoader.getController();
            controller.setUsuari(usuari); 
        }
        
        if (fxml.equals("menuProveidor")) {
            MenuProveidor controller = fxmlLoader.getController();
            controller.setUsuari(usuari);  // Aquí passem l'objecte Usuari
        }
        scene.setRoot(root);
    }

    /**
     * Estableix la vista actual de l'aplicació sense passar l'usuari.
     * Aquesta sobrecàrrega del mètode es pot utilitzar per tancar la sessió.
     *
     * @param fxml El nom del fitxer FXML a carregar.
     * @throws IOException Si hi ha un error al carregar el FXML.
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    
    /**
     * Carrega un fitxer FXML.
     * @param fxml El nom del fitxer FXML a carregar.
     * @return El node arrel carregat des del fitxer FXML.
     * @throws IOException Si hi ha un error al carregar el FXML.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/cat/copernic/projecte1_equip1/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    
    /**
     * Punt d'entrada de l'aplicació.
     * @param args Arguments de la línia de comandes.
     */
    public static void main(String[] args) {
        launch();
    }

}
