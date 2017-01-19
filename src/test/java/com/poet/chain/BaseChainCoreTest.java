package com.poet.chain;

import com.chain.exception.BadURLException;
import com.chain.http.Client;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Created by poet on 2017/1/19.
 */
public class BaseChainCoreTest {

    protected Client client;

    public static final String CHAIN_CORE_URL = "http://192.168.1.70:1999";
    public static final String ACCESS_TOKEN = "java-sdk:c991a3d1dec7892f3447cb3cbf77848b663cb1f5001ade766096cf2e183c71b3";

    @Before
    public void initClient() {
        try {
            client = new Client(CHAIN_CORE_URL, ACCESS_TOKEN);
        } catch (BadURLException e) {
            e.printStackTrace();
        }
        assertNotNull(client);
    }


}
