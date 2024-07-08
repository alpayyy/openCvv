import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import eclips.azat.ContrastUtils2;

import java.util.ArrayList;
import java.util.List;

public class Solution2  extends ImageProcessor{
	  public Solution2(double contrastThreshold) {
	        super(contrastThreshold);
	    }

	    @Override
	    public Mat processImage(Mat src) {
//	        Mat gray = new Mat();
//	        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
	        
	        
	        //Copying original image
	        Mat outputImage = new Mat();
			src.copyTo(outputImage);
			
	        Mat blurred = new Mat();
	        Imgproc.GaussianBlur(src, blurred, new Size(1,1), 0);

	        Mat edges = new Mat();
	        Imgproc.Canny(blurred, edges, 25, 100);

	        List<MatOfPoint> contours = new java.util.ArrayList<>();
	        Mat hierarchy = new Mat();
	        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
	        List<Rect> rectangles=new ArrayList<Rect>();
	        
	        for(MatOfPoint contour:contours) {
	        	Rect rect=Imgproc.boundingRect(contour);
	        	rectangles.add(rect);
	        	
	        }
	        List<Rect> lowContRects=new ArrayList<Rect>();
	        Mat roi;
	        for(Rect rect:rectangles) {
	        	roi=new Mat(src,rect);
	        	double contrast=ContrastUtils2.GetContrast(roi);
	        	if (contrast != 0 && contrast < contrastThreshold) {
					lowContRects.add(rect);
					Imgproc.rectangle(outputImage, rect, new Scalar(255,153,255));
				}
	        }
//	        for (MatOfPoint contour : contours) {
//	            Rect rect = Imgproc.boundingRect(contour);
//	            Mat roi = new Mat(src, rect);
//	            Scalar meanColorRect = Core.mean(roi);
//	            double[] meanRectColor = {meanColorRect.val[2], meanColorRect.val[1], meanColorRect.val[0]};
//
//	            if (ContrastUtils.calculateContrast(meanRectColor, Core.mean(src).val) <= contrastThreshold) {
//	                Imgproc.rectangle(output, rect, new Scalar(0, 255, 0), 2);
//	                System.out.println("Solution2 - Rectangle Coordinates: [Top-Left: (" + rect.x + ", " + rect.y + "), Bottom-Right: (" + (rect.x + rect.width) + ", " + (rect.y + rect.height) + ")]");
//	            }
//	            roi.release();
//	        }

	       
	        blurred.release();
	        edges.release();
	        hierarchy.release();

	        return outputImage;
	    }
}
