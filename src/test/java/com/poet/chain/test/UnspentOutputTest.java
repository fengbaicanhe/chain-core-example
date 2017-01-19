package com.poet.chain.test;

import com.chain.api.UnspentOutput;
import com.chain.exception.ChainException;
import com.poet.chain.BaseChainCoreTest;
import org.junit.Test;

/**
 * Created by poet on 2017/1/19.
 */
public class UnspentOutputTest extends BaseChainCoreTest {

    @Test
    public void listUnspentOutput() throws ChainException {
        UnspentOutput.Items ustOpts = new UnspentOutput.QueryBuilder().execute(super.client);
        while (ustOpts.hasNext()) {
            UnspentOutput uo = ustOpts.next();
            // you can get any info about unspent output in uo object
            System.out.println(uo.accountAlias + "\t" + uo.assetAlias + "\t" + uo.amount);
        }
    }

}
