import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

public class Main {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("OpenCV kütüphanesi yüklendi.");
    }

    public static void main(String[] args) {

        String imagePath = "C:\\Users\\merve\\Downloads\\gorsel2.jpg";
        Mat src = Imgcodecs.imread(imagePath);

        if (src.empty()) {
            System.out.println("Görsel yüklenemedi: " + imagePath);
            return;
        }

        double contrastThreshold = 1.5;
        Mat output = ImageProcessor.processImage(src, contrastThreshold);

        Mat resizedOutput = new Mat();
        Size newSize = new Size(800, 600);
        Imgproc.resize(output, resizedOutput, newSize);

        HighGui.imshow("Yazı ve Arka Plan Renkleri Çok Yakın Alanlar", resizedOutput);
        HighGui.waitKey();

        src.release();
        output.release();
        resizedOutput.release();
    }
}
