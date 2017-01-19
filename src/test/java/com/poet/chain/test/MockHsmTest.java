package com.poet.chain.test;

import com.chain.api.MockHsm;
import com.chain.exception.ChainException;
import com.poet.chain.BaseChainCoreTest;
import org.junit.Test;

/**
 * Created by poet on 2017/1/19.
 */
public class MockHsmTest extends BaseChainCoreTest {

    /**
     * Create a new key in the Mock HSM
     */
    @Test
    public void createHsmKey() throws ChainException {

        // using MockHsm.Key.create to create HSM key
        // the second param is the key alias
        // note: the key alias can not be duplicate
        MockHsm.Key key = MockHsm.Key.create(super.client, "mock-key");
        System.out.println("Mock HSM key create success!");
    }

    /**
     * list all Hsm keys
     */
    @Test
    public void listHsmKeys() throws ChainException {
        MockHsm.Key.QueryBuilder queryBuilder = new MockHsm.Key.QueryBuilder();

        // you can add filter conditions to queryBuilder

        MockHsm.Key.Items keys = queryBuilder.execute(super.client);
        while (keys.hasNext()) {
            MockHsm.Key k = keys.next();
            System.out.println(k.alias + "\t" + k.xpub);
        }

    }

}
