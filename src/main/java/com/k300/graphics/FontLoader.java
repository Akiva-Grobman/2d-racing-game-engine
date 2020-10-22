package com.k300.graphics;

import com.k300.utils.Utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontLoader {

    public static Font loadFont(String fontName, int size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(Utils.class.getResource("/fonts/" + fontName + ".ttf").getPath())).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

}