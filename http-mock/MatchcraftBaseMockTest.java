package com.dtg.esb.api.matchcraft.unit.mock;

import com.github.kristofa.test.http.MockHttpServer;
import com.github.kristofa.test.http.SimpleHttpResponseProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.annotations.*;

import java.io.IOException;

import static org.testng.Assert.assertTrue;


/**
 * Abstract class that contains the startup and teardown methods used to create and stop mock services
 * @author thomasmodeneis
 */
public abstract class MatchcraftBaseMockTest {

    protected static final String UTF_8 = "UTF-8";
    protected static MockHttpServer server;
    protected static SimpleHttpResponseProvider responseProvider;
    protected HttpClient client;
    protected static String baseUrl;

    @BeforeClass
    public void beforeSuite() throws IOException {
        responseProvider = new SimpleHttpResponseProvider();
        server = new MockHttpServer(0, responseProvider);
        final int port = server.start();
        assertTrue(port != -1);
        baseUrl = "http://localhost:" + server.getPort();
    }

    @AfterClass
    public void afterSuite() throws IOException {
        server.stop();
    }

    @BeforeMethod
    public void beforeClass() throws Exception {
        client = new DefaultHttpClient();
        responseProvider.reset();
    }
}