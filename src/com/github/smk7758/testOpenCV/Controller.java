package com.github.smk7758.testOpenCV;

import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {
	public static boolean capturing = true;
	public static int camera_number = 0;
	Capture capture = null;
	Image image = null;
	VideoCapture vc = null;
	@FXML
	ImageView iv;
	@FXML
	Button button_stop;

	public void initialize() {
		Mat mat = new Mat();
		vc = new VideoCapture(camera_number);
		vc.open(camera_number);

		// Thread
		capture = new Capture(vc, mat, iv, image);
		capture.setDaemon(true);
		capture.start();
	}

	@FXML
	public void onButtonStop() {
		capturing = false;
		vc.release();
		if (capture.isAlive()) capture.interrupt();
		System.out.println("Stop.(Controller)");
		System.exit(0);
	}
}
