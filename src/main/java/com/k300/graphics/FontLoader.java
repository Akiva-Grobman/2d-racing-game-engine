package com.k300.graphics;

import com.k300.utils.Utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontLoader {

    public static Font loadFont(String fontName, int size) {
        try {
            return getFont(fontName, size);
        } catch (NullPointerException e) {
            try {
                return getFont("Minecraft", size);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (FontFormatException fontFormatException) {
                exitProgram(fontFormatException);
            }
        } catch (FontFormatException | IOException e) {
            exitProgram(e);
        }
        return null;
    }

    private static Font getFont(String fontName, int fontSize) throws IOException, FontFormatException {
        return Font.createFont(Font.TRUETYPE_FONT, new File(Utils.class.getResource("/fonts/" + fontName + ".ttf").getPath())).deriveFont(Font.PLAIN, fontSize);
    }

    private static void exitProgram(Exception exception) {
        exception.printStackTrace();
        System.exit(1);
    }

}