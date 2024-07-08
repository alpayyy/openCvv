import org.opencv.core.Mat;

public abstract class ImageProcessor {
    protected double contrastThreshold;

    public ImageProcessor(double contrastThreshold) {
        this.contrastThreshold = contrastThreshold;
    }//constructor yapılandırıcı

    public abstract Mat processImage(Mat src);
}
