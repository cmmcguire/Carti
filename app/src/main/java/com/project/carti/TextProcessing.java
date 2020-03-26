package com.project.carti;

import com.google.firebase.ml.vision.text.FirebaseVisionText;

import java.util.List;
import java.util.regex.Pattern;

import android.graphics.Rect;
import android.graphics.Point;

import androidx.appcompat.app.AppCompatActivity;


public class TextProcessing extends AppCompatActivity {

    // uses regex to match string to a valid price
    private boolean isPrice (String str) {

        Pattern pattern = Pattern.compile("[0-9]+[.][0-9][0-9]");

        if (str == null) {
            return false;
        }

        return pattern.matcher(str).matches();
    }

    // process text blocks, lines, and elements to attempt to find a price
    protected String parseForPrice(List<FirebaseVisionText.TextBlock> blocks) {

        // stores the tallest detected element that matches price regex
        String largestDetectedPrice = "";

        // stores the height of tallest detected price element for comparison
        int largestDetectedHeight = 0;

        // iterate through blocks --> lines --> elements
        for (FirebaseVisionText.TextBlock block: blocks) {
            for (FirebaseVisionText.Line line: block.getLines()) {
                for (FirebaseVisionText.Element element: line.getElements()) {

                    // set to current element
                    // NOTE: element is a word-equivalent (such as a price)
                    String elementText = element.getText();

                    if (isPrice(elementText)) {

                        System.out.println(elementText);

                        // check size by finding height of bounding box rectangle
                        Point[] elementCornerPoints = element.getCornerPoints();
                        Rect elementFrame = element.getBoundingBox();

                        System.out.println(elementFrame.height());

                        if (elementFrame.height() > largestDetectedHeight) {

                            largestDetectedPrice = elementText;
                        }
                    }

                    // else can ignore
                }
            }
        }

        return largestDetectedPrice;
    }


    // converts price string to double
    protected double convertToDouble(String str) {

        double number;
        number = Double.parseDouble(str);
        return number;
    }

}

