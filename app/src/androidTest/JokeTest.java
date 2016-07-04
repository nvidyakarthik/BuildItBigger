
/**
 * Created by Vidya on 6/28/2016.
 */

import android.test.ApplicationTestCase;
import android.app.Application;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @see <a href="https://gist.github.com/he9lin/2195897">How to test AsyncTask in Android</a>
 */
public class JokeTest extends ApplicationTestCase<Application> implements OnJokeReceivedListener {

    CountDownLatch signal;
    String joke;

    public JokeTest() {
        super(Application.class);
    }

    public void testJoke() {
        try {
            signal = new CountDownLatch(1);
            new EndpointsAsyncTask().execute(this);
            signal.await(10, TimeUnit.SECONDS);
            assertNotNull("joke is null", joke);
            assertFalse("joke is empty", joke.isEmpty());
        } catch (Exception ex) {
            fail();
        }
    }

    @Override
    public void onReceived(String joke) {
        this.joke = joke;
        signal.countDown();
    }
}