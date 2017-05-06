package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {
    private WebCamCapture webCamCapture;
    @FXML
    private Button startButton;
    @FXML
    private ImageView cameraFrame;
    @FXML
    private Button stopButton;

    @FXML
    protected void startCamera(ActionEvent event){
        webCamCapture = new WebCamCapture(this.cameraFrame);
    }

    @FXML
    protected void stopCamera(ActionEvent event){
        if(webCamCapture != null)
            webCamCapture.stop();
    }

    @FXML
    protected void changeColor(ActionEvent event){
        if(webCamCapture != null)
            webCamCapture.changeColor();
    }
}
