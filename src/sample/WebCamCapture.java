package sample;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.io.ByteArrayInputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by Alvaro on 07/05/2017.
 */
public class WebCamCapture {
    private VideoCapture capture;
    private ScheduledExecutorService timer;
    private ImageView imageView;

    private Runnable frameGrabber = this::getFrame;
    private int colorProfile = Imgproc.COLOR_BGR2GRAY;

    public WebCamCapture(ImageView currentFrame) {
        this.capture = new VideoCapture(0);
        this.imageView = currentFrame;
        this.timer = Executors.newSingleThreadScheduledExecutor();
        timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);
        System.out.print("WebCamCapture");

    }

    private void getFrame(){
        if (capture.isOpened()){
            Mat frame = new Mat();
            capture.read(frame);
            System.out.print("frame" + frame.toString());
            Imgproc.cvtColor(frame, frame, colorProfile);

            convertToImageView(frame);

        }
    }

    private void convertToImageView(Mat frame){
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", frame, buffer);
        Image imageToShow = new Image(new ByteArrayInputStream(buffer.toArray()));

        // run it in the main Thread
        Platform.runLater((Runnable) () -> imageView.setImage(imageToShow));
    }

    public void stop(){
        capture.release();
    }

    public void changeColor(){
        colorProfile = Imgproc.COLOR_BGR2RGB;
    }
}
