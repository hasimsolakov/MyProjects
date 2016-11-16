package presenter.core;

import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

public class SlideExtractor {
    private String path;
    public SlideExtractor(String presentationPath){
        this.path = presentationPath;
    }

    public String extractSlidesNotes(){

        FileInputStream is = null;
        try{
            is = new FileInputStream(this.path);
        }
        catch(FileNotFoundException ex){

        }

        PowerPointExtractor extractor = null;
        try{
             extractor = new PowerPointExtractor(is);
        }catch (IOException ex){
            try{
                is.close();
            }catch (IOException ioEx){
                System.out.println("Error while trying to close stream");
            }
        }
        String notes = extractor.getNotes();
        return notes;
    }


    public void extractSlides(){
        FileInputStream is = null;
        try{
            is = new FileInputStream(this.path);
        }
        catch(FileNotFoundException ex){

        }
        HSLFSlideShow ppt = null;
        try{
             ppt = new HSLFSlideShow(is);
        }catch (IOException ex){
            try{
                is.close();
            }catch (IOException ioEx){
                System.out.println("Error while trying to close stream");
            }
        }


        Dimension pgsize = ppt.getPageSize();

        int idx = 1;
        for (HSLFSlide slide : ppt.getSlides()) {

            BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();
            // clear the drawing area
            graphics.setPaint(Color.white);
            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

            // render
            slide.draw(graphics);

            // save the output
            try{
                File yourFile = new File("C:\\Users\\Hashim\\Desktop\\Presentations\\slide-" + idx + ".png");
                if(!yourFile.exists()) {
                    yourFile.createNewFile();
                }

                FileOutputStream out = new FileOutputStream(yourFile);
                javax.imageio.ImageIO.write(img, "png", out);
                out.close();
            }catch (IOException ex){
                System.out.println("Error");
            }

            idx++;
        }
    }

}
