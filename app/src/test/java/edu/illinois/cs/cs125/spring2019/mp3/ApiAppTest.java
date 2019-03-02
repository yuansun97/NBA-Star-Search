package edu.illinois.cs.cs125.spring2019.mp3;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import edu.illinois.cs.cs125.spring2019.mp3.lib.RecognizePhoto;

/**
 * Test suite for the Recognize Photo app.
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/3/">MP3 Documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"})
@PrepareForTest(RecognizePhoto.class)
public class ApiAppTest {

    /** Allow mocking static methods. */
    @Rule
    public PowerMockRule mockRule = new PowerMockRule();

    /** The activity to test. */
    private MainActivity activity;

    /** The data passed to the activity for testing purposes.
     * Not actually JSON, so this would cause a crash if RecognizePhoto wasn't mocked. */
    private static final String JSON_PLACEHOLDER = "UNUSED";

    @Before
    public void getActivity() {
        // Set default stubs
        stub("getWidth", 0);
        stub("getHeight", 0);
        stub("getFormat", "");
        stub("getCaption", "");
        stub("isACat", false);
        stub("isADog", false);
        stub("isRick", false);

        // Start the activity
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    private List<View> getAllViews() {
        List<View> views = new ArrayList<>();
        traverseView(views, activity.findViewById(R.id.mainLayout));
        return views;
    }

    private void traverseView(final List<View> seenSoFar, final View view) {
        seenSoFar.add(view);
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int v = 0; v < group.getChildCount(); v++) {
                traverseView(seenSoFar, group.getChildAt(v));
            }
        }
    }

    private void setImage() {
        Bitmap blankBitmap = Bitmap.createBitmap(60, 60, Bitmap.Config.ARGB_8888);
        activity.updateCurrentBitmap(blankBitmap, true);
    }

    private boolean isShowing(ImageView imageView, int res) {
        return (imageView.getVisibility() == View.VISIBLE
                && imageView.getDrawable() != null
                && Shadows.shadowOf(imageView.getDrawable()).getCreatedFromResId() == res);
    }

    private void stub(final String method, Object value) {
        PowerMockito.stub(PowerMockito.method(RecognizePhoto.class, method)).toReturn(value);
    }

    @Test
    public void testApiRequest() {
        // Set an image
        setImage();

        // Instrument the networking capabilities
        final Uri[] lastUri = {null};
        RequestQueue queueSpy = Mockito.spy(activity.getRequestQueue());
        Mockito.doAnswer(iom -> {
            Request request = iom.getArgumentAt(0, Request.class);
            lastUri[0] = Uri.parse(request.getUrl());
            return request;
        }).when(queueSpy).add(Mockito.any());
        activity.setRequestQueue(queueSpy);

        // Click the upload button
        activity.findViewById(R.id.processImage).performClick();
        Robolectric.flushBackgroundThreadScheduler();
        Assert.assertNotNull("Clicking the upload button didn't make a request", lastUri[0]);
        String foundTargetIn = "";
        for (String q : lastUri[0].getQueryParameterNames()) {
            String[] parts = Objects.requireNonNull(lastUri[0].getQueryParameter(q)).split(",");
            for (String p : parts) {
                if (p.hashCode() == 2598969) {
                    foundTargetIn = q;
                }
            }
        }
        Assert.assertEquals("Information needed to identify cats and dogs wasn't requested " +
                "from the Cognitive Services API - see the API documentation",
                -2138730595, foundTargetIn.hashCode());
    }

    @Test
    public void testMetadataDisplay() {
        String[] possibleFormats = {"PNG", "JPG", "BMP", "GIF"};
        Random random = new Random();
        for (int t = 0; t < 6; t++) {
            // Instrument the metadata-retrieving functions
            int width = 50 + random.nextInt(800);
            stub("getWidth", width);
            int height = 40 + random.nextInt(600);
            stub("getHeight", height);
            String format = possibleFormats[random.nextInt(possibleFormats.length)];
            stub("getFormat", format);

            // Update the UI
            activity.finishProcessImage(JSON_PLACEHOLDER);
            TextView textView = getAllViews().stream().filter(v -> v instanceof TextView)
                    .map(v -> (TextView) v).filter(v -> v.getText().toString().contains(format))
                    .findAny().orElse(null);
            Assert.assertNotNull("No TextView displays the image format", textView);
            Assert.assertEquals("The image metadata TextView isn't visible",
                    View.VISIBLE, textView.getVisibility());
            String metadataText = textView.getText().toString();
            Assert.assertTrue("The metadata label doesn't show the width",
                    metadataText.contains(String.valueOf(width)));
            Assert.assertTrue("The metadata label doesn't show the height",
                    metadataText.contains(String.valueOf(height)));

            // Reset the image
            setImage();
            Assert.assertFalse("updateCurrentBitmap didn't reset the metadata label",
                    textView.getVisibility() == View.VISIBLE && textView.getText().toString().contains(format));
        }
    }

    @Test
    public void testCaptionDisplay() {
        String[] captions =
                {"the Siebel Center on a bright sunny day", "Chuchu and Xyz sleeping",
                 "the main quad in dense fog", "a student working on an exciting MP",
                 "the Vet Med building at night", "the 21 Raven passing the Illini Union"};
        for (String c : captions) {
            // Instrument getCaption
            stub("getCaption", c);

            // Update the UI
            activity.finishProcessImage(JSON_PLACEHOLDER);
            TextView textView = getAllViews().stream().filter(v -> v instanceof TextView)
                    .map(v -> (TextView) v).filter(v -> v.getText().toString().contains(c))
                    .findAny().orElse(null);
            Assert.assertNotNull("No TextView displays the caption", textView);
            Assert.assertEquals("The caption is invisible", View.VISIBLE, textView.getVisibility());

            // Reset the image
            setImage();
            Assert.assertFalse("updateCurrentBitmap didn't reset the caption",
                    textView.getVisibility() == View.VISIBLE && textView.getText().toString().contains(c));
        }
    }

    @Test
    public void testAnimalIndicators() {
        // Find the cat and dog images that appear when the respective animal is recognized
        stub("isACat", true);
        stub("isADog", true);
        activity.finishProcessImage(JSON_PLACEHOLDER);
        ImageView catView = getAllViews().stream()
                .filter(v -> v instanceof ImageView).map(v -> (ImageView) v)
                .filter(v -> isShowing(v, R.mipmap.xyz))
                .findAny().orElse(null);
        Assert.assertNotNull("No ImageView shows a cat when a cat is recognized", catView);
        ImageView dogView = getAllViews().stream()
                .filter(v -> v instanceof ImageView).map(v -> (ImageView) v)
                .filter(v -> v.getDrawable() != null)
                .filter(v -> isShowing(v, R.mipmap.chuchu))
                .findAny().orElse(null);
        Assert.assertNotNull("No ImageView shows a dog when a dog is recognized", dogView);

        // Make sure the ImageViews appear and disappear as appropriate
        Random random = new Random();
        for (int t = 0; t < 6; t++) {
            // Set the return value of the recognition functions randomly
            boolean isCat = random.nextBoolean();
            boolean isDog = random.nextBoolean();
            stub("isACat", isCat);
            stub("isADog", isDog);

            // Update the UI
            activity.finishProcessImage(JSON_PLACEHOLDER);
            Assert.assertEquals("Cat recognition isn't indicated correctly", isCat, isShowing(catView, R.mipmap.xyz));
            Assert.assertEquals("Dog recognition isn't indicated correctly", isDog, isShowing(dogView, R.mipmap.chuchu));

            // Reset the image
            setImage();
            Assert.assertTrue("updateCurrentBitmap didn't reset the cat recognition indicator",
                    catView.getVisibility() != View.VISIBLE || catView.getDrawable() == null);
            Assert.assertTrue("updateCurrentBitmap didn't reset the dog recognition indicator",
                    dogView.getVisibility() != View.VISIBLE || dogView.getDrawable() == null);
        }
    }
}