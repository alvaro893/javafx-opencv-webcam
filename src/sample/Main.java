package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.opencv.core.Core;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    /*
     Note add this static line and the library in opencv/build/java/opencv320.jar
     and opencv/build/java/x64/opencv_java64.dll as native jni library

     on Linux is 'opencv/build/bin/opencv320.jar' and the jni library 'opencv/build/lib/' instead
     Build opencv in linux follow this link http://opencv-java-tutorials.readthedocs.io/en/latest/01-installing-opencv-for-java.html
    */
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        launch(args);
    }
}
