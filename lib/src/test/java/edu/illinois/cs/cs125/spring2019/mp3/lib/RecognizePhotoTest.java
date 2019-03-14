package edu.illinois.cs.cs125.spring2019.mp3.lib;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test suite for the RecognizePhoto test.
 * <p>
 * The provided test suite is correct and complete. You should not need to modify it. However, you
 * should understand it.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/3/">MP3 Documentation</a>
 */
public class RecognizePhotoTest {

    /** Timeout for image recognition tests. Solution takes up to 89 ms. */
    private static final int RECOGNIZE_TEST_TIMEOUT = 890;

    /**
     * Test width extraction.
     */
    @Test(timeout = RECOGNIZE_TEST_TIMEOUT)
    public void testWidth() {
        for (RecognizePhotoTestInput input : PRECOMPUTED_RESULTS) {
            Assert.assertEquals("incorrect width:", input.width, RecognizePhoto.getWidth(input.json));
        }
    }

    /**
     * Test height extraction.
     */
    @Test(timeout = RECOGNIZE_TEST_TIMEOUT)
    public void testHeight() {
        for (RecognizePhotoTestInput input : PRECOMPUTED_RESULTS) {
            Assert.assertEquals("incorrect height:", input.height, RecognizePhoto.getHeight(input.json));
        }
    }

    /**
     * Test type extraction.
     */
    @Test(timeout = RECOGNIZE_TEST_TIMEOUT)
    public void testFormat() {
        for (RecognizePhotoTestInput input : PRECOMPUTED_RESULTS) {
            Assert.assertEquals("incorrect type:", input.type, RecognizePhoto.getFormat(input.json));
        }
    }

    /**
     * Test caption extraction.
     */
    @Test(timeout = RECOGNIZE_TEST_TIMEOUT)
    public void testCaption() {
        for (RecognizePhotoTestInput input : PRECOMPUTED_RESULTS) {
            Assert.assertEquals("incorrect caption:", input.caption, RecognizePhoto.getCaption(input.json));
        }
    }

    /**
     * Test dog identification.
     */
    @Test(timeout = RECOGNIZE_TEST_TIMEOUT)
    public void testDog() {
        for (RecognizePhotoTestInput input : PRECOMPUTED_RESULTS) {
            double lowConfidence = input.dogConfidence - 0.05;
            if (lowConfidence < 0.0) {
                lowConfidence = 0;
            }
            Assert.assertEquals("photo should be recognized as a dog:",
                input.isADog, RecognizePhoto.isADog(input.json, lowConfidence));
            if (input.isADog && input.dogConfidence < 1.0) {
                double highConfidence = input.dogConfidence + 0.05;
                if (highConfidence > 1.0) {
                    highConfidence = 1.0;
                }
                Assert.assertFalse("photo should not be recognized as a dog at this confidence level:",
                    RecognizePhoto.isADog(input.json, highConfidence));
            }
        }
    }

    /**
     * Test cat identification.
     */
    @Test(timeout = RECOGNIZE_TEST_TIMEOUT)
    public void testCat() {
        for (RecognizePhotoTestInput input : PRECOMPUTED_RESULTS) {
            double lowConfidence = input.catConfidence - 0.05;
            if (lowConfidence < 0.0) {
                lowConfidence = 0;
            }
            Assert.assertEquals("photo should be recognized as a cat:",
                input.isACat, RecognizePhoto.isACat(input.json, lowConfidence));
            if (input.isACat && input.catConfidence < 1.0) {
                double highConfidence = input.catConfidence + 0.05;
                if (highConfidence > 1.0) {
                    highConfidence = 1.0;
                }
                Assert.assertFalse("photo should not be recognized as a cat at this confidence level:",
                    RecognizePhoto.isACat(input.json, highConfidence));
            }
        }
    }

    /**
     * Test Rick Astley identification.
     */
    @Test(timeout = RECOGNIZE_TEST_TIMEOUT)
    public void testRick() {
        for (RecognizePhotoTestInput input : PRECOMPUTED_RESULTS) {
            Assert.assertEquals("photo should be recognized as Rick Astley:",
                    input.isRick, RecognizePhoto.isRick(input.json));
            if (input.isRick) {
                Assert.assertTrue("photo should be recognized as Rick Astley",
                        RecognizePhoto.isRick(input.json));
            }
        }
    }

    /**
     * Class for storing pre-computed results and test inputs.
     */
    public static class RecognizePhotoTestInput {

        /** JSON string to use for testing. */
        String json;

        /** Precomputed image width. */
        int width;

        /** Precomputed image height. */
        int height;

        /** Precomputed image type. */
        String type;

        /** Precomputed image caption. */
        String caption;

        /** Precomputed whether the image is a dog. */
        boolean isADog;

        /** Precomputed dog confidence level. */
        double dogConfidence;

        /** Precomputed whether the image is a cat. */
        boolean isACat;

        /** Precomputed whether the image is a cat. */
        double catConfidence;

        /** Precomputed whether the image is Rick Astley. */
        boolean isRick;

        RecognizePhotoTestInput(String setJSON, int setWidth, int setHeight,
                                String setType, String setCaption,
                                boolean setIsADog, boolean setIsACat,
                                double setDogConfidence, double setCatConfidence,
                                boolean setIsRick) {
            json = setJSON;
            width = setWidth;
            height = setHeight;
            type = setType;
            caption = setCaption;
            isADog = setIsADog;
            isACat = setIsACat;
            dogConfidence = setDogConfidence;
            catConfidence = setCatConfidence;
            isRick = setIsRick;
        }
    }

    /** Pre-computed list of inputs to use for testing. */
    private static final List<RecognizePhotoTestInput> PRECOMPUTED_RESULTS = //
            new ArrayList<>();

    static {
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput(null, 0, 0,
                null, null, false, false, 0.9, 0.9, false));
        /* BEGIN AUTOGENERATED CODE */
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": [" +
"    {" +
"      \"name\": \"animal_dog\"," +
"      \"score\": 0.9921875" +
"    }" +
"  ]," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.005129534285515547," +
"    \"racyScore\": 0.009625027887523174" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"White\"," +
"    \"dominantColorBackground\": \"White\"," +
"    \"dominantColors\": [" +
"      \"White\"" +
"    ]," +
"    \"accentColor\": \"9F612D\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 0," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"dog\"," +
"      \"confidence\": 0.9955965876579285" +
"    }," +
"    {" +
"      \"name\": \"sitting\"," +
"      \"confidence\": 0.9698488712310791" +
"    }," +
"    {" +
"      \"name\": \"animal\"," +
"      \"confidence\": 0.9645843505859375" +
"    }," +
"    {" +
"      \"name\": \"mammal\"," +
"      \"confidence\": 0.9310492873191833," +
"      \"hint\": \"animal\"" +
"    }," +
"    {" +
"      \"name\": \"white\"," +
"      \"confidence\": 0.6601004004478455" +
"    }," +
"    {" +
"      \"name\": \"tan\"," +
"      \"confidence\": 0.11082706600427628" +
"    }," +
"    {" +
"      \"name\": \"colored\"," +
"      \"confidence\": 0.10263378173112869" +
"    }," +
"    {" +
"      \"name\": \"cute\"," +
"      \"confidence\": 0.022528348028406725" +
"    }," +
"    {" +
"      \"name\": \"retriever\"," +
"      \"confidence\": 0.018918432544025438" +
"    }," +
"    {" +
"      \"name\": \"puppy\"," +
"      \"confidence\": 0.01682238989595776" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"dog\"," +
"      \"sitting\"," +
"      \"animal\"," +
"      \"mammal\"," +
"      \"brown\"," +
"      \"white\"," +
"      \"looking\"," +
"      \"orange\"," +
"      \"laying\"," +
"      \"close\"," +
"      \"large\"," +
"      \"pink\"," +
"      \"table\"," +
"      \"field\"," +
"      \"cat\"," +
"      \"standing\"," +
"      \"frisbee\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"a close up of a dog\"," +
"        \"confidence\": 0.951685393160483" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": []," +
"  \"requestId\": \"42729a39-1aa3-4cb8-8a3f-78b97a0bb6a9\"," +
"  \"metadata\": {" +
"    \"width\": 400," +
"    \"height\": 452," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        400, 452,
        "Jpeg", "a close up of a dog",
        true, false,
        0.9955965876579285, 0, false));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": []," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.0033038652036339045," +
"    \"racyScore\": 0.0042144134640693665" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"White\"," +
"    \"dominantColorBackground\": \"Green\"," +
"    \"dominantColors\": [" +
"      \"Green\"" +
"    ]," +
"    \"accentColor\": \"AA7B21\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 0," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"grass\"," +
"      \"confidence\": 0.9999998807907104" +
"    }," +
"    {" +
"      \"name\": \"dog\"," +
"      \"confidence\": 0.999998927116394" +
"    }," +
"    {" +
"      \"name\": \"outdoor\"," +
"      \"confidence\": 0.9979544878005981" +
"    }," +
"    {" +
"      \"name\": \"frisbee\"," +
"      \"confidence\": 0.9407489895820618" +
"    }," +
"    {" +
"      \"name\": \"running\"," +
"      \"confidence\": 0.7554457187652588" +
"    }," +
"    {" +
"      \"name\": \"carrying\"," +
"      \"confidence\": 0.5024933218955994" +
"    }," +
"    {" +
"      \"name\": \"puppy\"," +
"      \"confidence\": 0.13123120029275037" +
"    }," +
"    {" +
"      \"name\": \"retriever\"," +
"      \"confidence\": 0.12378309813648516" +
"    }," +
"    {" +
"      \"name\": \"cute\"," +
"      \"confidence\": 0.11739336170685916" +
"    }," +
"    {" +
"      \"name\": \"park\"," +
"      \"confidence\": 0.10198748048786538" +
"    }," +
"    {" +
"      \"name\": \"golden retriever\"," +
"      \"confidence\": 0.08688679497589363" +
"    }," +
"    {" +
"      \"name\": \"animal\"," +
"      \"confidence\": 0.08132042880432405" +
"    }," +
"    {" +
"      \"name\": \"ball\"," +
"      \"confidence\": 0.07495576875720666" +
"    }," +
"    {" +
"      \"name\": \"play\"," +
"      \"confidence\": 0.07251539973903598" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"grass\"," +
"      \"dog\"," +
"      \"outdoor\"," +
"      \"frisbee\"," +
"      \"running\"," +
"      \"mouth\"," +
"      \"field\"," +
"      \"brown\"," +
"      \"small\"," +
"      \"white\"," +
"      \"green\"," +
"      \"carrying\"," +
"      \"game\"," +
"      \"holding\"," +
"      \"sitting\"," +
"      \"playing\"," +
"      \"park\"," +
"      \"wearing\"," +
"      \"standing\"," +
"      \"air\"," +
"      \"blue\"," +
"      \"hat\"," +
"      \"ball\"," +
"      \"laying\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"a brown and white dog carrying a frisbee in its mouth\"," +
"        \"confidence\": 0.8047038586446695" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": []," +
"  \"requestId\": \"bbe88a27-4cb1-448f-a362-3e7121bf7b18\"," +
"  \"metadata\": {" +
"    \"width\": 720," +
"    \"height\": 720," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        720, 720,
        "Jpeg", "a brown and white dog carrying a frisbee in its mouth",
        true, false,
        0.999998927116394, 0, false));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": []," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.013577578589320183," +
"    \"racyScore\": 0.03179845213890076" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"White\"," +
"    \"dominantColorBackground\": \"White\"," +
"    \"dominantColors\": [" +
"      \"White\"" +
"    ]," +
"    \"accentColor\": \"8C653F\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 1," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"dog\"," +
"      \"confidence\": 0.9999785423278809" +
"    }," +
"    {" +
"      \"name\": \"animal\"," +
"      \"confidence\": 0.9558623433113098" +
"    }," +
"    {" +
"      \"name\": \"brown\"," +
"      \"confidence\": 0.8315028548240662" +
"    }," +
"    {" +
"      \"name\": \"mammal\"," +
"      \"confidence\": 0.7671583890914917," +
"      \"hint\": \"animal\"" +
"    }," +
"    {" +
"      \"name\": \"wild dog\"," +
"      \"confidence\": 0.35969141125679016," +
"      \"hint\": \"animal\"" +
"    }," +
"    {" +
"      \"name\": \"malinois\"," +
"      \"confidence\": 0.28980869520342706" +
"    }," +
"    {" +
"      \"name\": \"german shepherd\"," +
"      \"confidence\": 0.1328122170303175" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"dog\"," +
"      \"animal\"," +
"      \"brown\"," +
"      \"standing\"," +
"      \"grass\"," +
"      \"frisbee\"," +
"      \"large\"," +
"      \"mouth\"," +
"      \"playing\"," +
"      \"holding\"," +
"      \"walking\"," +
"      \"water\"," +
"      \"white\"," +
"      \"man\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"a brown and white dog playing with a frisbee\"," +
"        \"confidence\": 0.4490080498933228" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": []," +
"  \"requestId\": \"1429ff11-4f86-484b-b833-4bb4e416c5f0\"," +
"  \"metadata\": {" +
"    \"width\": 780," +
"    \"height\": 558," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        780, 558,
        "Jpeg", "a brown and white dog playing with a frisbee",
        true, false,
        0.9999785423278809, 0, false));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": [" +
"    {" +
"      \"name\": \"animal_cat\"," +
"      \"score\": 0.99609375" +
"    }" +
"  ]," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.014120662584900856," +
"    \"racyScore\": 0.017783870920538902" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"White\"," +
"    \"dominantColorBackground\": \"White\"," +
"    \"dominantColors\": [" +
"      \"White\"" +
"    ]," +
"    \"accentColor\": \"346797\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 0," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"cat\"," +
"      \"confidence\": 0.9859598875045776" +
"    }," +
"    {" +
"      \"name\": \"indoor\"," +
"      \"confidence\": 0.964738667011261" +
"    }," +
"    {" +
"      \"name\": \"sitting\"," +
"      \"confidence\": 0.9601377248764038" +
"    }," +
"    {" +
"      \"name\": \"bed\"," +
"      \"confidence\": 0.7588863372802734" +
"    }," +
"    {" +
"      \"name\": \"laying\"," +
"      \"confidence\": 0.6615778207778931" +
"    }," +
"    {" +
"      \"name\": \"kitten\"," +
"      \"confidence\": 0.2945560426894475" +
"    }," +
"    {" +
"      \"name\": \"kitty\"," +
"      \"confidence\": 0.0585522868200603" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"cat\"," +
"      \"indoor\"," +
"      \"sitting\"," +
"      \"bed\"," +
"      \"laying\"," +
"      \"table\"," +
"      \"small\"," +
"      \"book\"," +
"      \"blanket\"," +
"      \"lying\"," +
"      \"white\"," +
"      \"gray\"," +
"      \"little\"," +
"      \"grey\"," +
"      \"black\"," +
"      \"blue\"," +
"      \"stuffed\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"a cat lying on a bed\"," +
"        \"confidence\": 0.8977793707851496" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": []," +
"  \"requestId\": \"4a92a5fb-1fb3-4537-9542-52235d33e8bd\"," +
"  \"metadata\": {" +
"    \"width\": 1024," +
"    \"height\": 683," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        1024, 683,
        "Jpeg", "a cat lying on a bed",
        false, true,
        0, 0.9859598875045776, false));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": [" +
"    {" +
"      \"name\": \"animal_dog\"," +
"      \"score\": 0.9921875" +
"    }" +
"  ]," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.03684443607926369," +
"    \"racyScore\": 0.044406648725271225" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"Black\"," +
"    \"dominantColorBackground\": \"White\"," +
"    \"dominantColors\": []," +
"    \"accentColor\": \"81684A\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 0," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"wild dog\"," +
"      \"confidence\": 0.9955096244812012," +
"      \"hint\": \"animal\"" +
"    }," +
"    {" +
"      \"name\": \"animal\"," +
"      \"confidence\": 0.9916014075279236" +
"    }," +
"    {" +
"      \"name\": \"mammal\"," +
"      \"confidence\": 0.9663373231887817," +
"      \"hint\": \"animal\"" +
"    }," +
"    {" +
"      \"name\": \"dog\"," +
"      \"confidence\": 0.4687063634556219" +
"    }," +
"    {" +
"      \"name\": \"wildlife\"," +
"      \"confidence\": 0.4493380240901828" +
"    }," +
"    {" +
"      \"name\": \"cute\"," +
"      \"confidence\": 0.12150067929630788" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"canine\"," +
"      \"animal\"," +
"      \"mammal\"," +
"      \"dog\"," +
"      \"sitting\"," +
"      \"black\"," +
"      \"small\"," +
"      \"looking\"," +
"      \"standing\"," +
"      \"brown\"," +
"      \"white\"," +
"      \"field\"," +
"      \"man\"," +
"      \"cat\"," +
"      \"water\"," +
"      \"air\"," +
"      \"laying\"," +
"      \"blue\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"a dog looking at the camera\"," +
"        \"confidence\": 0.8714153173902714" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": []," +
"  \"requestId\": \"e8673ff5-b637-4a3d-8c44-2dbc9f709380\"," +
"  \"metadata\": {" +
"    \"width\": 1024," +
"    \"height\": 678," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        1024, 678,
        "Jpeg", "a dog looking at the camera",
        true, false,
        0.4687063634556219, 0, false));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": [" +
"    {" +
"      \"name\": \"animal_cat\"," +
"      \"score\": 0.99609375" +
"    }" +
"  ]," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.024521540850400925," +
"    \"racyScore\": 0.028603248298168182" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"Grey\"," +
"    \"dominantColorBackground\": \"Grey\"," +
"    \"dominantColors\": [" +
"      \"Grey\"" +
"    ]," +
"    \"accentColor\": \"526979\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 0," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"cat\"," +
"      \"confidence\": 0.9993564486503601" +
"    }," +
"    {" +
"      \"name\": \"ground\"," +
"      \"confidence\": 0.9976531863212585" +
"    }," +
"    {" +
"      \"name\": \"animal\"," +
"      \"confidence\": 0.9906987547874451" +
"    }," +
"    {" +
"      \"name\": \"outdoor\"," +
"      \"confidence\": 0.9892269372940063" +
"    }," +
"    {" +
"      \"name\": \"mammal\"," +
"      \"confidence\": 0.9353015422821045," +
"      \"hint\": \"animal\"" +
"    }," +
"    {" +
"      \"name\": \"domestic cat\"," +
"      \"confidence\": 0.5286005139350891" +
"    }," +
"    {" +
"      \"name\": \"grey\"," +
"      \"confidence\": 0.43479788303375244" +
"    }," +
"    {" +
"      \"name\": \"gray\"," +
"      \"confidence\": 0.4021807014942169" +
"    }," +
"    {" +
"      \"name\": \"close\"," +
"      \"confidence\": 0.2678662836551666" +
"    }," +
"    {" +
"      \"name\": \"dirt\"," +
"      \"confidence\": 0.2604784667491913" +
"    }," +
"    {" +
"      \"name\": \"stone\"," +
"      \"confidence\": 0.2142198532819748" +
"    }," +
"    {" +
"      \"name\": \"kitten\"," +
"      \"confidence\": 0.049043853685837" +
"    }," +
"    {" +
"      \"name\": \"cute\"," +
"      \"confidence\": 0.015072199237683595" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"cat\"," +
"      \"animal\"," +
"      \"outdoor\"," +
"      \"mammal\"," +
"      \"sitting\"," +
"      \"grass\"," +
"      \"camera\"," +
"      \"bench\"," +
"      \"looking\"," +
"      \"standing\"," +
"      \"wooden\"," +
"      \"grey\"," +
"      \"gray\"," +
"      \"small\"," +
"      \"close\"," +
"      \"dirt\"," +
"      \"brown\"," +
"      \"stone\"," +
"      \"face\"," +
"      \"green\"," +
"      \"park\"," +
"      \"white\"," +
"      \"eyes\"," +
"      \"field\"," +
"      \"laying\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"a cat that is looking at the camera\"," +
"        \"confidence\": 0.8948752044128083" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": []," +
"  \"requestId\": \"d8021f3d-a92a-4dad-bcda-2213062e5ea8\"," +
"  \"metadata\": {" +
"    \"width\": 960," +
"    \"height\": 694," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        960, 694,
        "Jpeg", "a cat that is looking at the camera",
        false, true,
        0, 0.9993564486503601, false));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": [" +
"    {" +
"      \"name\": \"people_portrait\"," +
"      \"score\": 0.6640625," +
"      \"detail\": {" +
"        \"celebrities\": [" +
"          {" +
"            \"name\": \"Rick Astley\"," +
"            \"confidence\": 0.9999333620071411," +
"            \"faceRectangle\": {" +
"              \"left\": 186," +
"              \"top\": 113," +
"              \"width\": 203," +
"              \"height\": 203" +
"            }" +
"          }" +
"        ]" +
"      }" +
"    }" +
"  ]," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.0017796078464016318," +
"    \"racyScore\": 0.0028087510727345943" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"Black\"," +
"    \"dominantColorBackground\": \"Black\"," +
"    \"dominantColors\": [" +
"      \"Black\"," +
"      \"Brown\"" +
"    ]," +
"    \"accentColor\": \"A04D2B\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 0," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"person\"," +
"      \"confidence\": 0.9996961355209351" +
"    }," +
"    {" +
"      \"name\": \"man\"," +
"      \"confidence\": 0.9917594790458679" +
"    }," +
"    {" +
"      \"name\": \"suit\"," +
"      \"confidence\": 0.6084223985671997" +
"    }," +
"    {" +
"      \"name\": \"dark\"," +
"      \"confidence\": 0.3226150572299957" +
"    }," +
"    {" +
"      \"name\": \"microphone\"," +
"      \"confidence\": 0.22360587120056152" +
"    }," +
"    {" +
"      \"name\": \"male\"," +
"      \"confidence\": 0.15438883006572723" +
"    }," +
"    {" +
"      \"name\": \"music\"," +
"      \"confidence\": 0.15438883006572723" +
"    }," +
"    {" +
"      \"name\": \"jazz\"," +
"      \"confidence\": 0.08464870310830316" +
"    }," +
"    {" +
"      \"name\": \"comedy\"," +
"      \"confidence\": 0.06029267261510587" +
"    }," +
"    {" +
"      \"name\": \"live music\"," +
"      \"confidence\": 0.048624495089781665" +
"    }," +
"    {" +
"      \"name\": \"panel\"," +
"      \"confidence\": 0.04116844892917728" +
"    }," +
"    {" +
"      \"name\": \"concert\"," +
"      \"confidence\": 0.03910055008973617" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"person\"," +
"      \"man\"," +
"      \"suit\"," +
"      \"holding\"," +
"      \"wearing\"," +
"      \"looking\"," +
"      \"shirt\"," +
"      \"camera\"," +
"      \"dark\"," +
"      \"black\"," +
"      \"front\"," +
"      \"talking\"," +
"      \"smiling\"," +
"      \"phone\"," +
"      \"sitting\"," +
"      \"standing\"," +
"      \"hair\"," +
"      \"young\"," +
"      \"red\"," +
"      \"white\"," +
"      \"blue\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"Rick Astley wearing a suit and tie talking on a cell phone\"," +
"        \"confidence\": 0.6570330305123531" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": [" +
"    {" +
"      \"age\": 47," +
"      \"gender\": \"Male\"," +
"      \"faceRectangle\": {" +
"        \"left\": 186," +
"        \"top\": 113," +
"        \"width\": 203," +
"        \"height\": 203" +
"      }" +
"    }" +
"  ]," +
"  \"requestId\": \"8994e39e-9f89-4f9c-8722-5035005e4367\"," +
"  \"metadata\": {" +
"    \"width\": 640," +
"    \"height\": 480," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        640, 480,
        "Jpeg", "Rick Astley wearing a suit and tie talking on a cell phone",
        false, false,
        0, 0, true));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": [" +
"    {" +
"      \"name\": \"animal_cat\"," +
"      \"score\": 0.99609375" +
"    }" +
"  ]," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.004838177002966404," +
"    \"racyScore\": 0.006482611410319805" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"White\"," +
"    \"dominantColorBackground\": \"White\"," +
"    \"dominantColors\": [" +
"      \"White\"," +
"      \"Grey\"" +
"    ]," +
"    \"accentColor\": \"7D694E\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 0," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"cat\"," +
"      \"confidence\": 1" +
"    }," +
"    {" +
"      \"name\": \"sitting\"," +
"      \"confidence\": 0.982131838798523" +
"    }," +
"    {" +
"      \"name\": \"animal\"," +
"      \"confidence\": 0.9183487296104431" +
"    }," +
"    {" +
"      \"name\": \"domestic cat\"," +
"      \"confidence\": 0.8848752975463867" +
"    }," +
"    {" +
"      \"name\": \"mammal\"," +
"      \"confidence\": 0.8501901030540466," +
"      \"hint\": \"animal\"" +
"    }," +
"    {" +
"      \"name\": \"grey\"," +
"      \"confidence\": 0.6676760911941528" +
"    }," +
"    {" +
"      \"name\": \"gray\"," +
"      \"confidence\": 0.5448676943778992" +
"    }," +
"    {" +
"      \"name\": \"kitten\"," +
"      \"confidence\": 0.07691905183103363" +
"    }," +
"    {" +
"      \"name\": \"tabby\"," +
"      \"confidence\": 0.05396911971559457" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"cat\"," +
"      \"sitting\"," +
"      \"animal\"," +
"      \"mammal\"," +
"      \"grey\"," +
"      \"gray\"," +
"      \"looking\"," +
"      \"white\"," +
"      \"striped\"," +
"      \"front\"," +
"      \"black\"," +
"      \"standing\"," +
"      \"camera\"," +
"      \"green\"," +
"      \"laying\"," +
"      \"brown\"," +
"      \"face\"," +
"      \"perched\"," +
"      \"suitcase\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"a cat that is looking at the camera\"," +
"        \"confidence\": 0.9461488054935209" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": []," +
"  \"requestId\": \"2b57c63e-e111-44dc-bfe0-15c0092457f7\"," +
"  \"metadata\": {" +
"    \"width\": 590," +
"    \"height\": 428," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        590, 428,
        "Jpeg", "a cat that is looking at the camera",
        false, true,
        0, 1, false));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": [" +
"    {" +
"      \"name\": \"animal_cat\"," +
"      \"score\": 0.99609375" +
"    }" +
"  ]," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.005833952222019434," +
"    \"racyScore\": 0.010359684005379677" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"Brown\"," +
"    \"dominantColorBackground\": \"White\"," +
"    \"dominantColors\": [" +
"      \"Brown\"," +
"      \"White\"" +
"    ]," +
"    \"accentColor\": \"644732\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 0," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"animal\"," +
"      \"confidence\": 0.9853090643882751" +
"    }," +
"    {" +
"      \"name\": \"mammal\"," +
"      \"confidence\": 0.9753100872039795," +
"      \"hint\": \"animal\"" +
"    }," +
"    {" +
"      \"name\": \"cat\"," +
"      \"confidence\": 0.962669849395752" +
"    }," +
"    {" +
"      \"name\": \"outdoor\"," +
"      \"confidence\": 0.9575169682502747" +
"    }," +
"    {" +
"      \"name\": \"domestic cat\"," +
"      \"confidence\": 0.9392395615577698" +
"    }," +
"    {" +
"      \"name\": \"laying\"," +
"      \"confidence\": 0.6036772727966309" +
"    }," +
"    {" +
"      \"name\": \"close\"," +
"      \"confidence\": 0.3152227997779846" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"animal\"," +
"      \"mammal\"," +
"      \"cat\"," +
"      \"outdoor\"," +
"      \"grass\"," +
"      \"sitting\"," +
"      \"laying\"," +
"      \"looking\"," +
"      \"green\"," +
"      \"close\"," +
"      \"front\"," +
"      \"brown\"," +
"      \"face\"," +
"      \"lying\"," +
"      \"field\"," +
"      \"yellow\"," +
"      \"black\"," +
"      \"standing\"," +
"      \"eyes\"," +
"      \"mouth\"," +
"      \"blue\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"a close up of a cat\"," +
"        \"confidence\": 0.9605882776505439" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": []," +
"  \"requestId\": \"674be15c-f9a8-450c-8df0-f095e7e09868\"," +
"  \"metadata\": {" +
"    \"width\": 1024," +
"    \"height\": 683," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        1024, 683,
        "Jpeg", "a close up of a cat",
        false, true,
        0, 0.962669849395752, false));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": [" +
"    {" +
"      \"name\": \"animal_dog\"," +
"      \"score\": 0.9921875" +
"    }" +
"  ]," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.006163438782095909," +
"    \"racyScore\": 0.0065084840171039104" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"Brown\"," +
"    \"dominantColorBackground\": \"Orange\"," +
"    \"dominantColors\": [" +
"      \"Brown\"," +
"      \"Orange\"" +
"    ]," +
"    \"accentColor\": \"AF4E1C\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 0," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"dog\"," +
"      \"confidence\": 0.9999990463256836" +
"    }," +
"    {" +
"      \"name\": \"indoor\"," +
"      \"confidence\": 0.9985591769218445" +
"    }," +
"    {" +
"      \"name\": \"sitting\"," +
"      \"confidence\": 0.9763092398643494" +
"    }," +
"    {" +
"      \"name\": \"laying\"," +
"      \"confidence\": 0.9293516874313354" +
"    }," +
"    {" +
"      \"name\": \"brown\"," +
"      \"confidence\": 0.9189934730529785" +
"    }," +
"    {" +
"      \"name\": \"animal\"," +
"      \"confidence\": 0.8781068325042725" +
"    }," +
"    {" +
"      \"name\": \"white\"," +
"      \"confidence\": 0.6953939199447632" +
"    }," +
"    {" +
"      \"name\": \"mammal\"," +
"      \"confidence\": 0.5888296365737915," +
"      \"hint\": \"animal\"" +
"    }," +
"    {" +
"      \"name\": \"tan\"," +
"      \"confidence\": 0.33211442828178406" +
"    }," +
"    {" +
"      \"name\": \"beagle\"," +
"      \"confidence\": 0.33211442828178406" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"dog\"," +
"      \"indoor\"," +
"      \"sitting\"," +
"      \"laying\"," +
"      \"brown\"," +
"      \"animal\"," +
"      \"white\"," +
"      \"looking\"," +
"      \"lying\"," +
"      \"small\"," +
"      \"tan\"," +
"      \"table\"," +
"      \"cute\"," +
"      \"large\"," +
"      \"black\"," +
"      \"living\"," +
"      \"water\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"a brown and white dog lying on the ground\"," +
"        \"confidence\": 0.8820316998791962" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": []," +
"  \"requestId\": \"c3870a32-6bf0-46fa-ac2f-6fe53f47c52e\"," +
"  \"metadata\": {" +
"    \"width\": 600," +
"    \"height\": 600," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        600, 600,
        "Jpeg", "a brown and white dog lying on the ground",
        true, false,
        0.9999990463256836, 0, false));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": [" +
"    {" +
"      \"name\": \"animal_cat\"," +
"      \"score\": 0.99609375" +
"    }" +
"  ]," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.007540326565504074," +
"    \"racyScore\": 0.00914519838988781" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"Grey\"," +
"    \"dominantColorBackground\": \"Black\"," +
"    \"dominantColors\": [" +
"      \"Grey\"" +
"    ]," +
"    \"accentColor\": \"90743B\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 0," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"cat\"," +
"      \"confidence\": 0.9995347261428833" +
"    }," +
"    {" +
"      \"name\": \"indoor\"," +
"      \"confidence\": 0.9736036658287048" +
"    }," +
"    {" +
"      \"name\": \"animal\"," +
"      \"confidence\": 0.965035080909729" +
"    }," +
"    {" +
"      \"name\": \"mammal\"," +
"      \"confidence\": 0.9434781670570374," +
"      \"hint\": \"animal\"" +
"    }," +
"    {" +
"      \"name\": \"domestic cat\"," +
"      \"confidence\": 0.9033340811729431" +
"    }," +
"    {" +
"      \"name\": \"staring\"," +
"      \"confidence\": 0.19599340856075287" +
"    }," +
"    {" +
"      \"name\": \"eyes\"," +
"      \"confidence\": 0.012710628124310354" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"cat\"," +
"      \"indoor\"," +
"      \"animal\"," +
"      \"mammal\"," +
"      \"sitting\"," +
"      \"looking\"," +
"      \"front\"," +
"      \"laptop\"," +
"      \"camera\"," +
"      \"standing\"," +
"      \"small\"," +
"      \"striped\"," +
"      \"brown\"," +
"      \"table\"," +
"      \"black\"," +
"      \"close\"," +
"      \"laying\"," +
"      \"computer\"," +
"      \"white\"," +
"      \"mirror\"," +
"      \"room\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"a close up of a cat\"," +
"        \"confidence\": 0.9817711765661916" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": []," +
"  \"requestId\": \"aae66b0f-b900-47ab-9fca-4c9374c1ed06\"," +
"  \"metadata\": {" +
"    \"width\": 250," +
"    \"height\": 308," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        250, 308,
        "Jpeg", "a close up of a cat",
        false, true,
        0, 0.9995347261428833, false));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": [" +
"    {" +
"      \"name\": \"people_\"," +
"      \"score\": 0.5703125," +
"      \"detail\": {" +
"        \"celebrities\": [" +
"          {" +
"            \"name\": \"Rick Astley\"," +
"            \"confidence\": 1," +
"            \"faceRectangle\": {" +
"              \"left\": 105," +
"              \"top\": 74," +
"              \"width\": 107," +
"              \"height\": 107" +
"            }" +
"          }" +
"        ]" +
"      }" +
"    }," +
"    {" +
"      \"name\": \"people_portrait\"," +
"      \"score\": 0.35546875," +
"      \"detail\": {" +
"        \"celebrities\": [" +
"          {" +
"            \"name\": \"Rick Astley\"," +
"            \"confidence\": 1," +
"            \"faceRectangle\": {" +
"              \"left\": 105," +
"              \"top\": 74," +
"              \"width\": 107," +
"              \"height\": 107" +
"            }" +
"          }" +
"        ]" +
"      }" +
"    }" +
"  ]," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.0034434599801898003," +
"    \"racyScore\": 0.003935506101697683" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"Black\"," +
"    \"dominantColorBackground\": \"Black\"," +
"    \"dominantColors\": [" +
"      \"Black\"," +
"      \"Brown\"" +
"    ]," +
"    \"accentColor\": \"5F666C\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 0," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"person\"," +
"      \"confidence\": 0.9998736381530762" +
"    }," +
"    {" +
"      \"name\": \"man\"," +
"      \"confidence\": 0.9888108968734741" +
"    }," +
"    {" +
"      \"name\": \"indoor\"," +
"      \"confidence\": 0.9843391180038452" +
"    }," +
"    {" +
"      \"name\": \"suit\"," +
"      \"confidence\": 0.5960198640823364" +
"    }," +
"    {" +
"      \"name\": \"posing\"," +
"      \"confidence\": 0.3855721652507782" +
"    }," +
"    {" +
"      \"name\": \"headshot\"," +
"      \"confidence\": 0.19617465738117792" +
"    }," +
"    {" +
"      \"name\": \"event\"," +
"      \"confidence\": 0.10917961761242806" +
"    }," +
"    {" +
"      \"name\": \"healthcare\"," +
"      \"confidence\": 0.10558714658815763" +
"    }," +
"    {" +
"      \"name\": \"business\"," +
"      \"confidence\": 0.0810477079080033" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"person\"," +
"      \"man\"," +
"      \"indoor\"," +
"      \"suit\"," +
"      \"sitting\"," +
"      \"table\"," +
"      \"laptop\"," +
"      \"front\"," +
"      \"holding\"," +
"      \"posing\"," +
"      \"smiling\"," +
"      \"looking\"," +
"      \"people\"," +
"      \"couple\"," +
"      \"computer\"," +
"      \"young\"," +
"      \"standing\"," +
"      \"glasses\"," +
"      \"restaurant\"," +
"      \"large\"," +
"      \"red\"," +
"      \"woman\"," +
"      \"group\"," +
"      \"room\"," +
"      \"white\"," +
"      \"plate\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"Rick Astley in a suit and tie smiling at the camera\"," +
"        \"confidence\": 0.9883401056277384" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": [" +
"    {" +
"      \"age\": 46," +
"      \"gender\": \"Male\"," +
"      \"faceRectangle\": {" +
"        \"left\": 105," +
"        \"top\": 74," +
"        \"width\": 107," +
"        \"height\": 107" +
"      }" +
"    }" +
"  ]," +
"  \"requestId\": \"20c54fcc-4714-4678-9388-e007f3508539\"," +
"  \"metadata\": {" +
"    \"width\": 300," +
"    \"height\": 300," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        300, 300,
        "Jpeg", "Rick Astley in a suit and tie smiling at the camera",
        false, false,
        0, 0, true));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": [" +
"    {" +
"      \"name\": \"animal_dog\"," +
"      \"score\": 0.99609375" +
"    }" +
"  ]," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.010993529111146927," +
"    \"racyScore\": 0.01199583150446415" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"Brown\"," +
"    \"dominantColorBackground\": \"Grey\"," +
"    \"dominantColors\": [" +
"      \"Brown\"," +
"      \"Grey\"," +
"      \"Black\"" +
"    ]," +
"    \"accentColor\": \"793C18\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 0," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"dog\"," +
"      \"confidence\": 0.9999997615814209" +
"    }," +
"    {" +
"      \"name\": \"indoor\"," +
"      \"confidence\": 0.9962719678878784" +
"    }," +
"    {" +
"      \"name\": \"sitting\"," +
"      \"confidence\": 0.9837114214897156" +
"    }," +
"    {" +
"      \"name\": \"brown\"," +
"      \"confidence\": 0.9766542315483093" +
"    }," +
"    {" +
"      \"name\": \"animal\"," +
"      \"confidence\": 0.9186526536941528" +
"    }," +
"    {" +
"      \"name\": \"looking\"," +
"      \"confidence\": 0.9071007370948792" +
"    }," +
"    {" +
"      \"name\": \"tan\"," +
"      \"confidence\": 0.6632648706436157" +
"    }," +
"    {" +
"      \"name\": \"mammal\"," +
"      \"confidence\": 0.644765317440033," +
"      \"hint\": \"animal\"" +
"    }," +
"    {" +
"      \"name\": \"yellow\"," +
"      \"confidence\": 0.6417189836502075" +
"    }," +
"    {" +
"      \"name\": \"staring\"," +
"      \"confidence\": 0.4861971437931061" +
"    }," +
"    {" +
"      \"name\": \"golden retriever\"," +
"      \"confidence\": 0.16303233183999058" +
"    }," +
"    {" +
"      \"name\": \"retriever\"," +
"      \"confidence\": 0.14263857928682316" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"dog\"," +
"      \"indoor\"," +
"      \"sitting\"," +
"      \"brown\"," +
"      \"animal\"," +
"      \"looking\"," +
"      \"wearing\"," +
"      \"tan\"," +
"      \"yellow\"," +
"      \"standing\"," +
"      \"staring\"," +
"      \"sticking\"," +
"      \"head\"," +
"      \"large\"," +
"      \"hanging\"," +
"      \"hat\"," +
"      \"orange\"," +
"      \"mouth\"," +
"      \"front\"," +
"      \"seat\"," +
"      \"black\"," +
"      \"laying\"," +
"      \"mirror\"," +
"      \"eyes\"," +
"      \"white\"," +
"      \"living\"," +
"      \"blue\"," +
"      \"room\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"a brown and white dog looking at the camera\"," +
"        \"confidence\": 0.8428045587038471" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": []," +
"  \"requestId\": \"faa78b34-5b6d-4810-8c06-797e91248f27\"," +
"  \"metadata\": {" +
"    \"width\": 1024," +
"    \"height\": 681," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        1024, 681,
        "Jpeg", "a brown and white dog looking at the camera",
        true, false,
        0.9999997615814209, 0, false));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": [" +
"    {" +
"      \"name\": \"people_portrait\"," +
"      \"score\": 0.71484375," +
"      \"detail\": {" +
"        \"celebrities\": [" +
"          {" +
"            \"name\": \"Rick Astley\"," +
"            \"confidence\": 1," +
"            \"faceRectangle\": {" +
"              \"left\": 115," +
"              \"top\": 101," +
"              \"width\": 263," +
"              \"height\": 263" +
"            }" +
"          }" +
"        ]" +
"      }" +
"    }" +
"  ]," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.0055147456005215645," +
"    \"racyScore\": 0.005906342063099146" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"White\"," +
"    \"dominantColorBackground\": \"White\"," +
"    \"dominantColors\": [" +
"      \"White\"" +
"    ]," +
"    \"accentColor\": \"446187\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 0," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"person\"," +
"      \"confidence\": 0.9991788268089294" +
"    }," +
"    {" +
"      \"name\": \"necktie\"," +
"      \"confidence\": 0.9946388602256775" +
"    }," +
"    {" +
"      \"name\": \"man\"," +
"      \"confidence\": 0.9819944500923157" +
"    }," +
"    {" +
"      \"name\": \"wall\"," +
"      \"confidence\": 0.9751732349395752" +
"    }," +
"    {" +
"      \"name\": \"clothing\"," +
"      \"confidence\": 0.9609314799308777" +
"    }," +
"    {" +
"      \"name\": \"indoor\"," +
"      \"confidence\": 0.9202376008033752" +
"    }," +
"    {" +
"      \"name\": \"suit\"," +
"      \"confidence\": 0.8918760418891907" +
"    }," +
"    {" +
"      \"name\": \"wearing\"," +
"      \"confidence\": 0.8845120668411255" +
"    }," +
"    {" +
"      \"name\": \"looking\"," +
"      \"confidence\": 0.8091537952423096" +
"    }," +
"    {" +
"      \"name\": \"shirt\"," +
"      \"confidence\": 0.6818498969078064" +
"    }," +
"    {" +
"      \"name\": \"posing\"," +
"      \"confidence\": 0.5478703379631042" +
"    }," +
"    {" +
"      \"name\": \"hair\"," +
"      \"confidence\": 0.41582050919532776" +
"    }," +
"    {" +
"      \"name\": \"staring\"," +
"      \"confidence\": 0.2889099717140198" +
"    }," +
"    {" +
"      \"name\": \"dressed\"," +
"      \"confidence\": 0.27766820788383484" +
"    }," +
"    {" +
"      \"name\": \"portrait\"," +
"      \"confidence\": 0.27766820788383484" +
"    }," +
"    {" +
"      \"name\": \"boy\"," +
"      \"confidence\": 0.10416471314739152" +
"    }," +
"    {" +
"      \"name\": \"face\"," +
"      \"confidence\": 0.08912161792706463" +
"    }," +
"    {" +
"      \"name\": \"headshot\"," +
"      \"confidence\": 0.06731545403346006" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"person\"," +
"      \"necktie\"," +
"      \"man\"," +
"      \"clothing\"," +
"      \"indoor\"," +
"      \"suit\"," +
"      \"wearing\"," +
"      \"looking\"," +
"      \"shirt\"," +
"      \"photo\"," +
"      \"dress\"," +
"      \"young\"," +
"      \"posing\"," +
"      \"camera\"," +
"      \"front\"," +
"      \"hair\"," +
"      \"black\"," +
"      \"dressed\"," +
"      \"neck\"," +
"      \"bow\"," +
"      \"white\"," +
"      \"standing\"," +
"      \"glasses\"," +
"      \"red\"," +
"      \"blue\"," +
"      \"purple\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"Rick Astley wearing a suit and tie looking at the camera\"," +
"        \"confidence\": 0.9624158640211975" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": [" +
"    {" +
"      \"age\": 30," +
"      \"gender\": \"Male\"," +
"      \"faceRectangle\": {" +
"        \"left\": 115," +
"        \"top\": 101," +
"        \"width\": 263," +
"        \"height\": 263" +
"      }" +
"    }" +
"  ]," +
"  \"requestId\": \"ef0031cc-774f-4fac-92b9-9f425e6a49fe\"," +
"  \"metadata\": {" +
"    \"width\": 600," +
"    \"height\": 451," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        600, 451,
        "Jpeg", "Rick Astley wearing a suit and tie looking at the camera",
        false, false,
        0, 0, true));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": [" +
"    {" +
"      \"name\": \"animal_dog\"," +
"      \"score\": 0.99609375" +
"    }" +
"  ]," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.004553782753646374," +
"    \"racyScore\": 0.011935974471271038" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"Brown\"," +
"    \"dominantColorBackground\": \"White\"," +
"    \"dominantColors\": [" +
"      \"White\"," +
"      \"Brown\"" +
"    ]," +
"    \"accentColor\": \"A56626\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 1," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"dog\"," +
"      \"confidence\": 0.9992470741271973" +
"    }," +
"    {" +
"      \"name\": \"animal\"," +
"      \"confidence\": 0.8903746604919434" +
"    }," +
"    {" +
"      \"name\": \"mammal\"," +
"      \"confidence\": 0.8541259765625," +
"      \"hint\": \"animal\"" +
"    }," +
"    {" +
"      \"name\": \"brown\"," +
"      \"confidence\": 0.8377056121826172" +
"    }," +
"    {" +
"      \"name\": \"bath\"," +
"      \"confidence\": 0.1754416972398758" +
"    }," +
"    {" +
"      \"name\": \"winter\"," +
"      \"confidence\": 0.024549419666850335" +
"    }," +
"    {" +
"      \"name\": \"golden retriever\"," +
"      \"confidence\": 0.02198359880202149" +
"    }," +
"    {" +
"      \"name\": \"snow\"," +
"      \"confidence\": 0.01924302145157882" +
"    }," +
"    {" +
"      \"name\": \"puppy\"," +
"      \"confidence\": 0.016633002175978275" +
"    }," +
"    {" +
"      \"name\": \"beach\"," +
"      \"confidence\": 0.013519402115031866" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"dog\"," +
"      \"water\"," +
"      \"animal\"," +
"      \"mammal\"," +
"      \"brown\"," +
"      \"sitting\"," +
"      \"looking\"," +
"      \"standing\"," +
"      \"small\"," +
"      \"mouth\"," +
"      \"boat\"," +
"      \"hanging\"," +
"      \"reflection\"," +
"      \"mirror\"," +
"      \"yellow\"," +
"      \"air\"," +
"      \"white\"," +
"      \"tub\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"a dog hanging out of the water\"," +
"        \"confidence\": 0.7750095600695419" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": []," +
"  \"requestId\": \"22125b58-f7d6-4624-87a9-ccb9b3d0ff16\"," +
"  \"metadata\": {" +
"    \"width\": 1024," +
"    \"height\": 604," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        1024, 604,
        "Jpeg", "a dog hanging out of the water",
        true, false,
        0.9992470741271973, 0, false));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": [" +
"    {" +
"      \"name\": \"people_portrait\"," +
"      \"score\": 0.96484375," +
"      \"detail\": {" +
"        \"celebrities\": [" +
"          {" +
"            \"name\": \"Rick Astley\"," +
"            \"confidence\": 0.9967072606086731," +
"            \"faceRectangle\": {" +
"              \"left\": 0," +
"              \"top\": 171," +
"              \"width\": 210," +
"              \"height\": 211" +
"            }" +
"          }" +
"        ]" +
"      }" +
"    }" +
"  ]," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.0026297036092728376," +
"    \"racyScore\": 0.0032960567623376846" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"Black\"," +
"    \"dominantColorBackground\": \"Black\"," +
"    \"dominantColors\": [" +
"      \"Black\"" +
"    ]," +
"    \"accentColor\": \"1F66AC\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 0," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"person\"," +
"      \"confidence\": 0.9987305998802185" +
"    }," +
"    {" +
"      \"name\": \"man\"," +
"      \"confidence\": 0.997159481048584" +
"    }," +
"    {" +
"      \"name\": \"dark\"," +
"      \"confidence\": 0.31873801350593567" +
"    }," +
"    {" +
"      \"name\": \"jazz\"," +
"      \"confidence\": 0.11983058298277846" +
"    }," +
"    {" +
"      \"name\": \"music\"," +
"      \"confidence\": 0.09064172927021873" +
"    }," +
"    {" +
"      \"name\": \"panel\"," +
"      \"confidence\": 0.048582257179997757" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"person\"," +
"      \"man\"," +
"      \"looking\"," +
"      \"wearing\"," +
"      \"holding\"," +
"      \"black\"," +
"      \"suit\"," +
"      \"dark\"," +
"      \"front\"," +
"      \"sitting\"," +
"      \"face\"," +
"      \"shirt\"," +
"      \"blue\"," +
"      \"red\"," +
"      \"glasses\"," +
"      \"hair\"," +
"      \"white\"," +
"      \"standing\"," +
"      \"head\"," +
"      \"phone\"," +
"      \"room\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"Rick Astley looking at the camera\"," +
"        \"confidence\": 0.8251054080266677" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": [" +
"    {" +
"      \"age\": 42," +
"      \"gender\": \"Male\"," +
"      \"faceRectangle\": {" +
"        \"left\": 0," +
"        \"top\": 171," +
"        \"width\": 210," +
"        \"height\": 211" +
"      }" +
"    }" +
"  ]," +
"  \"requestId\": \"76db9368-5cb7-4ee7-ab69-d95c12c3f798\"," +
"  \"metadata\": {" +
"    \"width\": 378," +
"    \"height\": 567," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        378, 567,
        "Jpeg", "Rick Astley looking at the camera",
        false, false,
        0, 0, true));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": [" +
"    {" +
"      \"name\": \"animal_dog\"," +
"      \"score\": 0.99609375" +
"    }" +
"  ]," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.006460438948124647," +
"    \"racyScore\": 0.006941306870430708" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"Brown\"," +
"    \"dominantColorBackground\": \"Yellow\"," +
"    \"dominantColors\": [" +
"      \"Brown\"," +
"      \"Yellow\"" +
"    ]," +
"    \"accentColor\": \"A77A24\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 0," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"dog\"," +
"      \"confidence\": 0.9999980926513672" +
"    }," +
"    {" +
"      \"name\": \"floor\"," +
"      \"confidence\": 0.9986059069633484" +
"    }," +
"    {" +
"      \"name\": \"indoor\"," +
"      \"confidence\": 0.9976356029510498" +
"    }," +
"    {" +
"      \"name\": \"sitting\"," +
"      \"confidence\": 0.9444596767425537" +
"    }," +
"    {" +
"      \"name\": \"looking\"," +
"      \"confidence\": 0.8810808062553406" +
"    }," +
"    {" +
"      \"name\": \"brown\"," +
"      \"confidence\": 0.8668311834335327" +
"    }," +
"    {" +
"      \"name\": \"yellow\"," +
"      \"confidence\": 0.7132032513618469" +
"    }," +
"    {" +
"      \"name\": \"mammal\"," +
"      \"confidence\": 0.5202550888061523," +
"      \"hint\": \"animal\"" +
"    }," +
"    {" +
"      \"name\": \"staring\"," +
"      \"confidence\": 0.20055967569351196" +
"    }," +
"    {" +
"      \"name\": \"tan\"," +
"      \"confidence\": 0.15230432152748108" +
"    }," +
"    {" +
"      \"name\": \"golden retriever\"," +
"      \"confidence\": 0.15230432152748108" +
"    }," +
"    {" +
"      \"name\": \"retriever\"," +
"      \"confidence\": 0.07177673405849491" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"dog\"," +
"      \"floor\"," +
"      \"indoor\"," +
"      \"sitting\"," +
"      \"looking\"," +
"      \"brown\"," +
"      \"yellow\"," +
"      \"standing\"," +
"      \"large\"," +
"      \"laying\"," +
"      \"kitchen\"," +
"      \"table\"," +
"      \"white\"," +
"      \"room\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"a dog sitting on the floor looking at the camera\"," +
"        \"confidence\": 0.8290490334950423" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": []," +
"  \"requestId\": \"097d4a98-e63c-45e6-91a7-2496bb643092\"," +
"  \"metadata\": {" +
"    \"width\": 1280," +
"    \"height\": 720," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        1280, 720,
        "Jpeg", "a dog sitting on the floor looking at the camera",
        true, false,
        0.9999980926513672, 0, false));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": []," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.061068058013916016," +
"    \"racyScore\": 0.08013501018285751" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"White\"," +
"    \"dominantColorBackground\": \"White\"," +
"    \"dominantColors\": [" +
"      \"White\"" +
"    ]," +
"    \"accentColor\": \"934F38\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 0," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"cat\"," +
"      \"confidence\": 0.99893718957901" +
"    }," +
"    {" +
"      \"name\": \"indoor\"," +
"      \"confidence\": 0.9307379722595215" +
"    }," +
"    {" +
"      \"name\": \"animal\"," +
"      \"confidence\": 0.9198971390724182" +
"    }," +
"    {" +
"      \"name\": \"mammal\"," +
"      \"confidence\": 0.9128739833831787," +
"      \"hint\": \"animal\"" +
"    }," +
"    {" +
"      \"name\": \"laying\"," +
"      \"confidence\": 0.8274523615837097" +
"    }," +
"    {" +
"      \"name\": \"domestic cat\"," +
"      \"confidence\": 0.7233095169067383" +
"    }," +
"    {" +
"      \"name\": \"cute\"," +
"      \"confidence\": 0.3334092543753905" +
"    }," +
"    {" +
"      \"name\": \"kitten\"," +
"      \"confidence\": 0.16853326302533875" +
"    }," +
"    {" +
"      \"name\": \"kitty\"," +
"      \"confidence\": 0.10399304865883886" +
"    }," +
"    {" +
"      \"name\": \"sleeping\"," +
"      \"confidence\": 0.06639958214422148" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"cat\"," +
"      \"indoor\"," +
"      \"animal\"," +
"      \"mammal\"," +
"      \"laying\"," +
"      \"white\"," +
"      \"looking\"," +
"      \"lying\"," +
"      \"gray\"," +
"      \"brown\"," +
"      \"bed\"," +
"      \"close\"," +
"      \"sleeping\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"a close up of a cat\"," +
"        \"confidence\": 0.9077087972888354" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": []," +
"  \"requestId\": \"99e42a20-a22b-4be0-b4c3-0194a6f08e15\"," +
"  \"metadata\": {" +
"    \"width\": 3888," +
"    \"height\": 2592," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        3888, 2592,
        "Jpeg", "a close up of a cat",
        false, true,
        0, 0.99893718957901, false));
        PRECOMPUTED_RESULTS.add(new RecognizePhotoTestInput("{" +
"  \"categories\": [" +
"    {" +
"      \"name\": \"people_portrait\"," +
"      \"score\": 0.640625," +
"      \"detail\": {" +
"        \"celebrities\": [" +
"          {" +
"            \"name\": \"Rick Astley\"," +
"            \"confidence\": 0.999998927116394," +
"            \"faceRectangle\": {" +
"              \"left\": 396," +
"              \"top\": 170," +
"              \"width\": 241," +
"              \"height\": 241" +
"            }" +
"          }" +
"        ]" +
"      }" +
"    }" +
"  ]," +
"  \"adult\": {" +
"    \"isAdultContent\": false," +
"    \"isRacyContent\": false," +
"    \"adultScore\": 0.0037619583308696747," +
"    \"racyScore\": 0.004649181384593248" +
"  }," +
"  \"color\": {" +
"    \"dominantColorForeground\": \"Black\"," +
"    \"dominantColorBackground\": \"Black\"," +
"    \"dominantColors\": [" +
"      \"Black\"" +
"    ]," +
"    \"accentColor\": \"C1620A\"," +
"    \"isBwImg\": false," +
"    \"isBWImg\": false" +
"  }," +
"  \"imageType\": {" +
"    \"clipArtType\": 1," +
"    \"lineDrawingType\": 0" +
"  }," +
"  \"tags\": [" +
"    {" +
"      \"name\": \"person\"," +
"      \"confidence\": 0.9992660880088806" +
"    }," +
"    {" +
"      \"name\": \"man\"," +
"      \"confidence\": 0.9676637053489685" +
"    }," +
"    {" +
"      \"name\": \"music\"," +
"      \"confidence\": 0.8259361982345581" +
"    }," +
"    {" +
"      \"name\": \"wearing\"," +
"      \"confidence\": 0.8072571754455566" +
"    }," +
"    {" +
"      \"name\": \"suit\"," +
"      \"confidence\": 0.7226000428199768" +
"    }," +
"    {" +
"      \"name\": \"microphone\"," +
"      \"confidence\": 0.42393386363983154" +
"    }," +
"    {" +
"      \"name\": \"dark\"," +
"      \"confidence\": 0.3319269120693207" +
"    }," +
"    {" +
"      \"name\": \"male\"," +
"      \"confidence\": 0.195512592792511" +
"    }," +
"    {" +
"      \"name\": \"concert\"," +
"      \"confidence\": 0.04522305096095155" +
"    }," +
"    {" +
"      \"name\": \"live music\"," +
"      \"confidence\": 0.032428598145893404" +
"    }," +
"    {" +
"      \"name\": \"stage\"," +
"      \"confidence\": 0.028630066648357932" +
"    }," +
"    {" +
"      \"name\": \"panel\"," +
"      \"confidence\": 0.02362481966887681" +
"    }" +
"  ]," +
"  \"description\": {" +
"    \"tags\": [" +
"      \"person\"," +
"      \"man\"," +
"      \"necktie\"," +
"      \"wearing\"," +
"      \"suit\"," +
"      \"microphone\"," +
"      \"holding\"," +
"      \"dark\"," +
"      \"shirt\"," +
"      \"black\"," +
"      \"standing\"," +
"      \"young\"," +
"      \"red\"," +
"      \"blue\"," +
"      \"mouth\"," +
"      \"white\"," +
"      \"phone\"," +
"      \"street\"" +
"    ]," +
"    \"captions\": [" +
"      {" +
"        \"text\": \"Rick Astley wearing a microphone\"," +
"        \"confidence\": 0.9176284455907547" +
"      }" +
"    ]" +
"  }," +
"  \"faces\": [" +
"    {" +
"      \"age\": 39," +
"      \"gender\": \"Male\"," +
"      \"faceRectangle\": {" +
"        \"left\": 396," +
"        \"top\": 170," +
"        \"width\": 241," +
"        \"height\": 241" +
"      }" +
"    }" +
"  ]," +
"  \"requestId\": \"8fd9e7ee-f48f-41b7-aedf-004b6338f209\"," +
"  \"metadata\": {" +
"    \"width\": 1024," +
"    \"height\": 576," +
"    \"format\": \"Jpeg\"" +
"  }" +
"}",
        1024, 576,
        "Jpeg", "Rick Astley wearing a microphone",
        false, false,
        0, 0, true));
        /* END AUTOGENERATED CODE */
    }
}
