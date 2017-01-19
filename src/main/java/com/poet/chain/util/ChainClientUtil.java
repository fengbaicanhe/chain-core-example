package com.poet.chain.util;

import com.chain.exception.BadURLException;
import com.chain.http.Client;

/**
 * Created by poet on 2017/1/18.
 */
public abstract class ChainClientUtil {

    public static final String DEFAULT_URL = "http://localhost:1999";

    /**
     * Get chain core client by DEFAULT_URL
     * @param accessToken chain core access token
     * @return
     */
    public static Client getClient(String accessToken){
        return getClient(accessToken,DEFAULT_URL);
    }

    /**
     * Get chain core client by a specify url
     * @param accessToken chain core access token
     * @param url chain core url
     * @return
     */
    public static Client getClient(String accessToken,String url){
        try {
            Client client = new Client(url,accessToken);
            return client;
        } catch (BadURLException e) {
            throw new RuntimeException("Create client error! " + e.getMessage());
        }
    }
}
