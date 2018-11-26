package magnitude.element;

import io.qameta.allure.Attachment;
import org.apache.commons.io.comparator.NameFileComparator;
import org.junit.Assert;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public class ImageMatcher {

    public static void compareImages(Element currentElement, Path originalLocation, ScreenSaver.ImageFormat imageFormat, File currentImagefile) {
        BufferedImage image2 = null;
        File originalFile = findFileToCompare(currentElement, originalLocation, imageFormat, currentImagefile);
        try {
            image2 = ImageIO.read(originalFile);
        } catch (IllegalArgumentException iae) {
            Assert.fail("previous currentImage isn`t found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert image2 != null;
        ImageDiff imageDiff = new ImageDiffer().makeDiff(currentElement.getElementImage(), image2);
        if (imageDiff.hasDiff()) {
            attachImage(imageDiff.getMarkedImage(), currentElement.getName() + " difference");
            Assert.fail("fail");
        }
    }

    public static ImageDiff  compareImages(File currentImage, File originalImage) {
        BufferedImage current = null;
        BufferedImage original = null;
        try {
            current = ImageIO.read(currentImage);
            original = ImageIO.read(originalImage);
        } catch (IllegalArgumentException iae) {
            Assert.fail("previous currentImage isn`t found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert original != null;
        assert current != null;
        ImageDiff imageDiff = new ImageDiffer().makeDiff(original, current);
        if (imageDiff.hasDiff()) {
            attachImage(imageDiff.getMarkedImage(), currentImage.getName() + " difference");
            Assert.fail("fail");
        }
        return imageDiff;
    }

    private static File findFileToCompare(Element currentElement, Path path, ScreenSaver.ImageFormat imageFormat, File file) {
        if (!Files.exists(path)) {
            return getLastScreen(currentElement, path, imageFormat, file);
        }
        return path.toFile();
    }


    private static File getLastScreen(Element currentElement, Path path, ScreenSaver.ImageFormat imageFormat, File currentFile) {
        File fl = new File(path.toAbsolutePath().toString() + "/");
        File[] directories = fl.listFiles(File::isDirectory);
        assert directories != null;
        Arrays.sort(directories, NameFileComparator.NAME_REVERSE);
        long lastMod = 0;
        long currentMod = currentFile.lastModified();
        File choice = null;
        for (File directory : directories) {
            for (File file :
                    Objects.requireNonNull(directory.listFiles())) {
                if (file.getName().equals(currentElement.getName() + imageFormat)) {
                    if (file.lastModified() > lastMod && file.lastModified() < currentMod) {
                        lastMod = file.lastModified();
                        choice = file;
                    }
                }
            }
        }
        return choice;
    }


    @Attachment("image")
    public static byte[] attachImage(BufferedImage bf) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bf, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    @Attachment("{imageName}")
    public static byte[] attachImage(BufferedImage bf, String imageName) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bf, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}
