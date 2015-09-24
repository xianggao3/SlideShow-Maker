package ssm.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import properties_manager.PropertiesManager;
import ssm.LanguagePropertyType;
import ssm.view.SlideShowMakerView;

/**
 * This class manages all the data associated with a slideshow.
 * 
 * @author McKilla Gorilla & _____________
 */
public class SlideShowModel {
    SlideShowMakerView ui;
    String title;
    ObservableList<Slide> slides;
    Slide selectedSlide;
  

    public SlideShowMakerView getUi() {
        return ui;
    }

    public void setUi(SlideShowMakerView ui) {
        this.ui = ui;
    }

    
    public SlideShowModel(SlideShowMakerView initUI) {
	ui = initUI;
	slides = FXCollections.observableArrayList();
	reset();	
    }

    // ACCESSOR METHODS
    public boolean isSlideSelected() {
	return selectedSlide != null;
    }
    
    public ObservableList<Slide> getSlides() {
	return slides;
    }
    
    public Slide getSelectedSlide() {
	return selectedSlide;
    }

    public String getTitle() { 
	return title; 
    }
    
    // MUTATOR METHODS
    public void setSelectedSlide(Slide initSelectedSlide) {
	selectedSlide = initSelectedSlide;
    }
    
    public void setTitle(String initTitle) { 
	title = initTitle; 
    }

    // SERVICE METHODS
    
    /**
     * Resets the slide show to have no slides and a default title.
     */
    public void moveUpSlide(){
       
        Slide temp1 = slides.get(slides.indexOf(selectedSlide));
        Slide temp2 = slides.get(slides.indexOf(selectedSlide)-1);
        slides.set(slides.indexOf(selectedSlide)-1, temp1);
        selectedSlide=temp1;
        slides.set(slides.indexOf(selectedSlide)+1,temp2)
        ;
        ui.reloadSlideShowPane(this);
        
    }
    
    public void moveDownSlide(){
        Slide temp1 = slides.get(slides.indexOf(selectedSlide));
        Slide temp2 = slides.get(slides.indexOf(selectedSlide)+1);
        slides.set(slides.indexOf(selectedSlide)+1, temp1);
        selectedSlide=temp1;
        slides.set(slides.indexOf(selectedSlide), temp2);
        ui.reloadSlideShowPane(this);
    }
    public void reset() {
	slides.clear();
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	title = props.getProperty(LanguagePropertyType.DEFAULT_SLIDE_SHOW_TITLE);
	selectedSlide = null;
    }

    public void remove(){
        slides.remove(selectedSlide);
        ui.reloadSlideShowPane(this);
    }
    
    
    /**
     * Adds a slide to the slide show with the parameter settings.
     * @param initImageFileName File name of the slide image to add.
     * @param initImagePath File path for the slide image to add.
     */
    public void addSlide(   String initImageFileName,
			    String initImagePath,
    String caption) {
	Slide slideToAdd = new Slide(initImageFileName, initImagePath);
	slides.add(slideToAdd);
        slideToAdd.setCaption(caption);
	selectedSlide = slideToAdd;
	ui.reloadSlideShowPane(this);
    }
}