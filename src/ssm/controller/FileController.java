package ssm.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import static ssm.StartupConstants.ICON_NEXT;
import static ssm.StartupConstants.ICON_PREVIOUS;
import static ssm.StartupConstants.PATH_ICONS;
import ssm.model.SlideShowModel;
import ssm.error.ErrorHandler;
import ssm.file.SlideShowFileManager;
import ssm.view.SlideShowMakerView;
import static ssm.StartupConstants.PATH_SLIDE_SHOWS;
/**
 * This class serves as the controller for all file toolbar operations,
 * driving the loading and saving of slide shows, among other things.
 * 
 * @author McKilla Gorilla & _____________
 */
public class FileController {

                int start = 0;
    // WE WANT TO KEEP TRACK OF WHEN SOMETHING HAS NOT BEEN SAVED
    private boolean saved;

    // THE APP UI
    private SlideShowMakerView ui;
    
    // THIS GUY KNOWS HOW TO READ AND WRITE SLIDE SHOW DATA
    private SlideShowFileManager slideShowIO;

    /**
     * This default constructor starts the program without a slide show file being
     * edited.
     *
     * @param initSlideShowIO The object that will be reading and writing slide show
     * data.
     */
    public FileController(SlideShowMakerView initUI, SlideShowFileManager initSlideShowIO) {
        // NOTHING YET
        saved = true;
	ui = initUI;
        slideShowIO = initSlideShowIO;
    }
    
    public void markAsEdited() {
        saved = false;
        ui.updateToolbarControls(saved);
    }

    /**
     * This method starts the process of editing a new slide show. If a pose is
     * already being edited, it will prompt the user to save it first.
     */
    public void handleNewSlideShowRequest() {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToMakeNew = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToMakeNew = promptToSave();
            }

            // IF THE USER REALLY WANTS TO MAKE A NEW COURSE
            if (continueToMakeNew) {
                // RESET THE DATA, WHICH SHOULD TRIGGER A RESET OF THE UI
                SlideShowModel slideShow = ui.getSlideShow();
		slideShow.reset();
                saved = false;

                // REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
                // THE APPROPRIATE CONTROLS
                ui.updateToolbarControls(saved);

                // TELL THE USER THE SLIDE SHOW HAS BEEN CREATED
                // @todo
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            // @todo provide error message
        }
    }

    /**
     * This method lets the user open a slideshow saved to a file. It will also
     * make sure data for the current slideshow is not lost.
     */
    public void handleLoadSlideShowRequest() {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToOpen = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToOpen = promptToSave();
            }

            // IF THE USER REALLY WANTS TO OPEN A POSE
            if (continueToOpen) {
                // GO AHEAD AND PROCEED MAKING A NEW POSE
                promptToOpen();
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            //@todo provide error message
        }
    }

    /**
     * This method will save the current slideshow to a file. Note that we already
     * know the name of the file, so we won't need to prompt the user.
     */
    public boolean handleSaveSlideShowRequest() {
        try {
	    // GET THE SLIDE SHOW TO SAVE
	    SlideShowModel slideShowToSave = ui.getSlideShow();
	    
            // SAVE IT TO A FILE
            slideShowIO.saveSlideShow(slideShowToSave);

            // MARK IT AS SAVED
            saved = true;

            // AND REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
            // THE APPROPRIATE CONTROLS
            ui.updateToolbarControls(saved);
	    return true;
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            // @todo
	    return false;
        }
    }

     /**
     * This method will exit the application, making sure the user doesn't lose
     * any data first.
     */
    public void handleExitRequest() {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToExit = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE
                continueToExit = promptToSave();
            }

            // IF THE USER REALLY WANTS TO EXIT THE APP
            if (continueToExit) {
                // EXIT THE APPLICATION
                System.exit(0);
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            // @todo
        }
    }
    public Image ImgFromPath(){
        String imgpath = ui.getSlideShow().getSlides().get(start).getImagePath()+"/"+ui.getSlideShow().getSlides().get(start).getImageFileName();
        File file1 = new File(imgpath);
        Image img;
        URL fileUrl=null;
                    try {
                        fileUrl = file1.toURI().toURL();
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        img=new Image(fileUrl.toExternalForm());
        return img;
    }
    
    public void handleViewSlideShowRequest(){
        try{
            PropertiesManager props =  PropertiesManager.getPropertiesManager();
            Stage viewSS = new Stage();
            BorderPane borderscene = new BorderPane();
            
            
            Text captionText= new Text("Comments: "+ui.getSlideShow().getSlides().get(start).getCaption());
            Text titleText= new Text(ui.getSlideShow().getTitle());
            titleText.setUnderline(true);
            titleText.setFont(Font.font ("Verdana", 30));
            borderscene.setBottom(captionText);
            HBox top = new HBox(titleText);
            Button prevButton = new Button();
            Button nexButton = new Button();
            
            String prevImg = "file:"+PATH_ICONS+ICON_PREVIOUS;
            String nexImg= "file:"+PATH_ICONS+ICON_NEXT;
            
            Image prevButtonImg = new Image(prevImg);
            Image nexButtonImg = new Image(nexImg);
            
            FlowPane imagPane = new FlowPane(new ImageView(ImgFromPath()));
            borderscene.setCenter(imagPane);
            borderscene.setTop(top);
            top.setAlignment(Pos.CENTER);
            
            prevButton.setGraphic(new ImageView(prevButtonImg));
            prevButton.setDisable(false);
            prevButton.setOnAction(e-> {
                if( start != 0){
                    start--;
                    captionText.setText("Comments: "+ui.getSlideShow().getSlides().get(start).getCaption());
                    imagPane.getChildren().clear();
                    imagPane.getChildren().add(new ImageView(ImgFromPath()));
                    borderscene.setBottom(captionText);
                }
            });
            
            nexButton.setGraphic(new ImageView(nexButtonImg));
            nexButton.setDisable(false);
            nexButton.setOnAction(e-> {
               if(start!= ui.getSlideShow().getSlides().size()){
                   start++;
                   
                    captionText.setText("Comments: "+ui.getSlideShow().getSlides().get(start).getCaption());
                   imagPane.getChildren().clear();
                   imagPane.getChildren().add(new ImageView(ImgFromPath()));
                   borderscene.setBottom(captionText);
               } 
            });
            captionText.setFont(Font.font ("Verdana", 20));
            VBox left = new VBox(prevButton);
            VBox right = new VBox(nexButton);
            borderscene.setLeft(left);
            borderscene.setRight(right);
            left.setAlignment(Pos.CENTER);
            right.setAlignment(Pos.CENTER);
            
            
            Scene hScene = new Scene(borderscene);
            viewSS.setScene(hScene);
            viewSS.show();
            
            boolean continueToView=true;
            if(!saved){
                continueToView=promptToSave();
            }
        }catch(IOException ioe){
            ErrorHandler eH=ui.getErrorHandler();
        }
        
    }

    /**
     * This helper method verifies that the user really wants to save their
     * unsaved work, which they might not want to do. Note that it could be used
     * in multiple contexts before doing other actions, like creating a new
     * pose, or opening another pose, or exiting. Note that the user will be
     * presented with 3 options: YES, NO, and CANCEL. YES means the user wants
     * to save their work and continue the other action (we return true to
     * denote this), NO means don't save the work but continue with the other
     * action (true is returned), CANCEL means don't save the work and don't
     * continue with the other action (false is retuned).
     *
     * @return true if the user presses the YES option to save, true if the user
     * presses the NO option to not save, false if the user presses the CANCEL
     * option to not continue.
     */
    private boolean promptToSave() throws IOException {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        boolean saveWork = true; // @todo change this to prompt

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (saveWork) {
            SlideShowModel slideShow = ui.getSlideShow();
            slideShowIO.saveSlideShow(slideShow);
            saved = true;
        } // IF THE USER SAID CANCEL, THEN WE'LL TELL WHOEVER
        // CALLED THIS THAT THE USER IS NOT INTERESTED ANYMORE
        else if (!true) {
            return false;
        }

        // IF THE USER SAID NO, WE JUST GO ON WITHOUT SAVING
        // BUT FOR BOTH YES AND NO WE DO WHATEVER THE USER
        // HAD IN MIND IN THE FIRST PLACE
        return true;
    }

    /**
     * This helper method asks the user for a file to open. The user-selected
     * file is then loaded and the GUI updated. Note that if the user cancels
     * the open process, nothing is done. If an error occurs loading the file, a
     * message is displayed, but nothing changes.
     */
    private void promptToOpen() {
        // AND NOW ASK THE USER FOR THE COURSE TO OPEN
        FileChooser slideShowFileChooser = new FileChooser();
        slideShowFileChooser.setInitialDirectory(new File(PATH_SLIDE_SHOWS));
        File selectedFile = slideShowFileChooser.showOpenDialog(ui.getWindow());

        // ONLY OPEN A NEW FILE IF THE USER SAYS OK
        if (selectedFile != null) {
            try {
		SlideShowModel slideShowToLoad = ui.getSlideShow();
                slideShowIO.loadSlideShow(slideShowToLoad, selectedFile.getAbsolutePath());
                ui.reloadSlideShowPane(slideShowToLoad);
                saved = true;
                ui.updateToolbarControls(saved);
            } catch (Exception e) {
                ErrorHandler eH = ui.getErrorHandler();
                // @todo
            }
        }
    }

    /**
     * This mutator method marks the file as not saved, which means that when
     * the user wants to do a file-type operation, we should prompt the user to
     * save current work first. Note that this method should be called any time
     * the pose is changed in some way.
     */
    public void markFileAsNotSaved() {
        saved = false;
    }

    /**
     * Accessor method for checking to see if the current pose has been saved
     * since it was last editing. If the current file matches the pose data,
     * we'll return true, otherwise false.
     *
     * @return true if the current pose is saved to the file, false otherwise.
     */
    public boolean isSaved() {
        return saved;
    }
}

