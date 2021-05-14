package AppData;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Model extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("TOTP");
        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

//    private static void preload() throws IOException {
//        File myfile = new File("src/AppData/code.txt");
//        Scanner fin = new Scanner(myfile);
//        secret = fin.nextLine();
//        System.out.println(secret);
//        Controller.code.setText("Hello World");
//    }
}
