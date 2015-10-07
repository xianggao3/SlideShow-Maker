package ssm.file;

import java.io.IOException;
import static ssm.StartupConstants.PATH_SLIDE_SHOWS;
import ssm.model.SlideShowModel;
import static ssm.file.SlideShowFileManager.JSON_EXT;
import static ssm.file.SlideShowFileManager.SLASH;

/**
 *
 * @author McKillaGorilla
 */
public class JsonTester {
    static SlideShowModel slideShow;
    static String TEST_TITLE = "The Test Slide Show";
    public static void main(String[] args) {
	slideShow = new SlideShowModel(null);
	slideShow.setTitle(TEST_TITLE);
	
	// ADD THREE SLIDES
	slideShow.addSlide("Hello","HelloPic.png","./images/");
	slideShow.addSlide("Clever Comment","DopeyPic.jpg","./images/pics/");
	slideShow.addSlide("Goodbye","FinalSlide.gif","./images/");
	
	SlideShowFileManager fileManager = new SlideShowFileManager();
	try {
	    fileManager.saveSlideShow(slideShow);
	    System.out.println("SLIDE SHOW SAVED");
	}
	catch(IOException ioe) {
	    ioe.printStackTrace();
	    System.exit(-1);
	}

	SlideShowModel newSlideShow = new SlideShowModel(null);
	try {
	    fileManager.loadSlideShow(slideShow, PATH_SLIDE_SHOWS + SLASH + TEST_TITLE + JSON_EXT);
	    System.out.println("SLIDE SHOW LOADED");
	}
	catch(IOException ioe) {
	    ioe.printStackTrace();
	    System.exit(-1);
	}
    }
}
