import org.opencv.core.Mat;

public abstract class ImageProcessor {
	 protected double contrastThreshold;

	    public ImageProcessor(double contrastThreshold) {
	        this.contrastThreshold = contrastThreshold;
	    }

	    public abstract Mat processImage(Mat src);
}
