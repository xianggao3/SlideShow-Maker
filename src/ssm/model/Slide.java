package ssm.model;

/**
 * This class represents a single slide in a slide show.
 * 
 * @author McKilla Gorilla & _____________
 */
public class Slide {
    String imageFileName;
    String imagePath;
    String caption;
    /**
     * Constructor, it initializes all slide data.
     * @param initImageFileName File name of the image.
     * 
     * @param initImagePath File path for the image.
     * 
     */
    public Slide(String initImageFileName, String initImagePath) {
	imageFileName = initImageFileName;
	imagePath = initImagePath;
        
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
       
    }
    
    // ACCESSOR METHODS
    public String getImageFileName() { return imageFileName; }
    public String getImagePath() { return imagePath; }
    
    // MUTATOR METHODS
    public void setImageFileName(String initImageFileName) {
	imageFileName = initImageFileName;
    }
    
    public void setImagePath(String initImagePath) {
	imagePath = initImagePath;
    }
    
    public void setImage(String initPath, String initFileName) {
	imagePath = initPath;
	imageFileName = initFileName;
    }
}
