package com.github.smk7758.testOpenCV;

import java.io.ByteArrayInputStream;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Capture extends Thread {
	VideoCapture vc = null;
	Mat mat = null;
	ImageView iv = null;
	Image image = null;

	public Capture(VideoCapture vc, Mat mat, ImageView iv, Image image) {
		if (vc == null) System.out.println("vc null");
			this.vc = vc;
		if (mat == null)  System.out.println("mat null");
			this.mat = mat;
		if (iv == null)  System.out.println("iv null");
			this.iv = iv;
		if (image == null)  System.out.println("image null");
			this.image = image;
	}

	public void run() {
		if (vc.isOpened()) {
			while (Controller.capturing) {
				vc.read(mat);
				if (!mat.empty()) {
					// mat → image
					MatOfByte byteMat = new MatOfByte();
					Highgui.imencode(".bmp", mat, byteMat);
					image = new Image(new ByteArrayInputStream(byteMat.toArray()));
					//set ImageView
					iv.setImage(image);
				} else {
					System.out.println("[Error] Can't capture the camera.");
				}
			}
		}
		System.out.println("Stop.(Capture)");
	}

}
