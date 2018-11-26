package magnitude.element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

public class ScreenSaver {
    private Path outputDirectory;
    private static Logger log = Logger.getLogger(ScreenSaver.class.getName());

    public ScreenSaver(Path outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public ScreenSaver(String path){
        if (!path.endsWith("/"))
            path = path + "/";
        initDirectory(path);
    }

    private void initDirectory(String path){
        outputDirectory = Paths.get(path);
        if (!Files.exists(outputDirectory)) {
            try {
                Files.createDirectories(outputDirectory);
            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        }
    }

    private void initDirectory(){
        if (!Files.exists(outputDirectory)) {
            try {
                Files.createDirectories(outputDirectory);
            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        }
    }

    public File saveImage(BufferedImage image,String fileName, ImageFormat imageFormat){
        checkDirectory();
        try {
            log.info("Save image " + fileName + imageFormat.getFormatName() + " to "
                    + outputDirectory.toAbsolutePath().toString());
            ImageIO.write(image, "png", new File(outputDirectory + "/" + fileName + imageFormat.getFormatName()));
            return new File(outputDirectory  + fileName + imageFormat.getFormatName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void checkDirectory() {
        if(outputDirectory == null){
            throw new NullPointerException("Output directory is not initialized");
        }else {
            initDirectory();
        }
    }

    public enum ImageFormat{
        PNG(".png"),
        JPEG(".jpg");

        private String formatName;

        ImageFormat(String formatName){
            this.formatName = formatName;
        }

        public String getFormatName() {
            return formatName;
        }
    }
}
