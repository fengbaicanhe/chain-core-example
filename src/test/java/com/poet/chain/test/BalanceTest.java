package com.poet.chain.test;

import com.chain.api.Balance;
import com.chain.exception.ChainException;
import com.poet.chain.BaseChainCoreTest;
import org.junit.Test;

/**
 * Created by poet on 2017/1/19.
 */
public class BalanceTest extends BaseChainCoreTest {

    @Test
    public void listBalances() throws ChainException {
        Balance.Items balances = new Balance.QueryBuilder().execute(super.client);

        while (balances.hasNext()) {
            Balance b = balances.next();
            System.out.println(b.amount);
        }
    }

}
