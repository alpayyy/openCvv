package eclips.azat;

import java.util.ArrayList;
import java.util.List;

public class BGR {
	public static boolean Any(List<BGR> list,BGR bgr) {
		int i=0;
		for(BGR _rgb:list) {
			if(bgr.get_RED()==_rgb.get_RED() && bgr.get_BLUE()==_rgb.get_BLUE() && bgr.get_GREEN()==_rgb.get_GREEN()) {
				i++;
			}
			
		}
		return i>1;
	}
private double _RED, _GREEN, _BLUE;

public double get_RED() {
	return _RED;
}

public void set_RED(double _RED) {
	this._RED = _RED;
}

public double get_GREEN() {
	return _GREEN;
}

public void set_GREEN(double _GREEN) {
	this._GREEN = _GREEN;
}

public double get_BLUE() {
	return _BLUE;
}

public void set_BLUE(double _BLUE) {
	this._BLUE = _BLUE;
}

public BGR(double blue, double green,double red) {
	this._RED=red;
	this._GREEN=green;
	this._BLUE=blue;
}
public BGR(double[] bgr) {
//	this._BLUE=bgr[0];
//	this._GREEN=bgr[1];
//	this._RED=bgr[2];
	for(int i=0;i<bgr.length;i++) {
		if(i==0) {
			this._BLUE=bgr[0];
			
		}
		else if(i==1) {
			this._GREEN=bgr[1];
		}
		else if(i==2) {
			this._RED=bgr[2];
		}
		else {
			break;
		}
	}
}



}
