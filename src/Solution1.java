import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.List;

public class Solution1 extends ImageProcessor {

    public Solution1(double contrastThreshold) {
        super(contrastThreshold);
    }

    @Override
    public Mat processImage(Mat src) {
        Mat gray = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);

        Mat blurred = new Mat();
        Imgproc.GaussianBlur(gray, blurred, new Size(11, 11), 0);

        Mat edges = new Mat();
        Imgproc.Canny(blurred, edges, 30, 90);

        List<MatOfPoint> contours = new java.util.ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        Mat output = new Mat();
        src.copyTo(output);

        for (MatOfPoint contour : contours) {
            Rect rect = Imgproc.boundingRect(contour);
            Mat roi = new Mat(src, rect);
            Scalar meanColorRect = Core.mean(roi);
            double[] meanRectColor = {meanColorRect.val[2], meanColorRect.val[1], meanColorRect.val[0]};

            if (ContrastUtils.calculateContrast(meanRectColor, Core.mean(src).val) <= contrastThreshold) {
                Imgproc.rectangle(output, rect, new Scalar(0, 0, 255), 2);
                System.out.println("Solution1 - Rectangle Coordinates: [Top-Left: (" + rect.x + ", " + rect.y + "), Bottom-Right: (" + (rect.x + rect.width) + ", " + (rect.y + rect.height) + ")]");
            }
            roi.release();
        }

        gray.release();
        blurred.release();
        edges.release();
        hierarchy.release();

        return output;
    }
}
