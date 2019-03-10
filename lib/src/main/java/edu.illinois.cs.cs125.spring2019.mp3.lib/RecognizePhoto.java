package edu.illinois.cs.cs125.spring2019.mp3.lib;

import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Image recognition class.
 * Each function takes the JSON returned by the Microsoft Cognitive Services API
 * and extracts some piece of information from it.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/3/">MP3 Documentation</a>
 */
public final class RecognizePhoto {
    /**
     * Get the image width.
     *
     * @param jsonString the JSON string returned by the Microsoft Cognitive Services API
     * @return the width of the image or 0 on failure
     */
    public static int getWidth(final String jsonString) {
        if (jsonString == null) {
            return 0;
        }
        JsonParser parser = new JsonParser();
        JsonObject rootObject = parser.parse(jsonString).getAsJsonObject();
        int width = rootObject.getAsJsonObject("metadata").get("width").getAsInt();
        return width;
    }

    /**
     * Get the image height.
     *
     * @param jsonString the JSON string returned by the Microsoft Cognitive Services API
     * @return the height of the image or 0 on failure
     */
    public static int getHeight(final String jsonString) {
        if (jsonString == null) {
            return 0;
        }
        JsonParser parser = new JsonParser();
        JsonObject rootObject = parser.parse(jsonString).getAsJsonObject();
        int height = rootObject.getAsJsonObject("metadata").get("height").getAsInt();
        return height;
    }

    /**
     * Get the image file type.
     *
     * @param jsonString the JSON string returned by the Microsoft Cognitive Services API
     * @return the type of the image or null
     */
    public static String getFormat(final String jsonString) {
        if (jsonString == null) {
            return null;
        }
        JsonParser parser = new JsonParser();
        JsonObject rootObject = parser.parse(jsonString).getAsJsonObject();
        String format = rootObject.getAsJsonObject("metadata").get("format").getAsString();
        return format;
    }

    /**
     * Get the caption describing the image.
     *
     * If multiple captions are provided the function will return the first one.
     *
     * @param jsonString the JSON string returned by the Microsoft Cognitive Services API
     * @return the caption describing the image created by the Microsoft or null on failure
     */
    public static String getCaption(final String jsonString) {
        if (jsonString == null) {
            return null;
        }
        JsonParser parser = new JsonParser();
        JsonObject rootObject = parser.parse(jsonString).getAsJsonObject();
        JsonArray captionArray = rootObject.getAsJsonObject("description").getAsJsonArray("captions");
        return captionArray.get(0).getAsJsonObject().get("text").getAsString();
    }

    /**
     * Determine whether the image contains a dog.
     *
     * @param jsonString the JSON string returned by the Microsoft Cognitive Services API
     * @param minConfidence the minimum confidence required for this determination
     * @return a boolean indicating whether the image contains a dog or false on failure
     */
    public static boolean isADog(final String jsonString, final double minConfidence) {
        if (jsonString == null) {
            return false;
        }
        JsonParser parser = new JsonParser();
        JsonObject rootObject = parser.parse(jsonString).getAsJsonObject();
        JsonArray tagsArray = rootObject.getAsJsonArray("tags");
        for (int i = 0; i < tagsArray.size(); ++i) {
            if (tagsArray.get(i).getAsJsonObject().get("name").getAsString().equals("dog")) {
                if (tagsArray.get(i).getAsJsonObject().get("confidence").getAsDouble() >= minConfidence) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determine whether the image contains a cat.
     *
     * @param jsonString the JSON string returned by the Microsoft Cognitive Services API
     * @param minConfidence the minimum confidence required for this determination
     * @return a boolean indicating whether the image contains a cat or false on failure
     */
    public static boolean isACat(final String jsonString, final double minConfidence) {
        if (jsonString == null) {
            return false;
        }
        JsonParser parser = new JsonParser();
        JsonObject rootObject = parser.parse(jsonString).getAsJsonObject();
        JsonArray tagsArray = rootObject.getAsJsonArray("tags");
        for (int i = 0; i < tagsArray.size(); ++i) {
            if (tagsArray.get(i).getAsJsonObject().get("name").getAsString().equals("cat")) {
                if (tagsArray.get(i).getAsJsonObject().get("confidence").getAsDouble() >= minConfidence) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if image contains Rick Astley..
     *
     * @param jsonString the JSON string returned by the Microsoft Cognitive Services API
     * @return true if you've Rickrolled yourself
     */
    public static boolean isRick(final String jsonString) {
        if (jsonString == null) {
            return false;
        }
        JsonParser parser = new JsonParser();
        JsonObject rootObject = parser.parse(jsonString).getAsJsonObject();
        JsonArray tagsArray = rootObject.getAsJsonArray("tags");
        for (int i = 0; i < tagsArray.size(); ++i) {
            if (tagsArray.get(i).getAsString().contains("rick")) {
                return true;
            }
        }
        return false;
    }

}
