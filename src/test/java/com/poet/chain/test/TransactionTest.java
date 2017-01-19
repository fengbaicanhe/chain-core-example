package com.poet.chain.test;

import com.chain.api.MockHsm;
import com.chain.api.Transaction;
import com.chain.exception.ChainException;
import com.chain.signing.HsmSigner;
import com.poet.chain.BaseChainCoreTest;
import org.junit.Test;

/**
 * Created by poet on 2017/1/19.
 */
public class TransactionTest extends BaseChainCoreTest {

    @Test
    public void issueAsset() throws ChainException {
        // first we should load key
        // we query the first key and load it
        MockHsm.Key key = new MockHsm.Key.QueryBuilder().execute(super.client).next();

        // load key
        HsmSigner.addKey(key,MockHsm.getSignerClient(super.client));

        String assetAlias = "ast_alias";
        String accountAlias = "account_from_sdk";
        // use exists account and asset to issue
        Transaction.Action.Issue issue = new Transaction.Action.Issue();
        issue.setAmount(10); // issue 10 asset unit
        issue.setAssetAlias(assetAlias); // asset alias

        Transaction.Action.ControlWithAccount accountAction = new Transaction.Action.ControlWithAccount();
        accountAction.setAssetAlias(assetAlias);
        accountAction.setAmount(10);
        accountAction.setAccountAlias(accountAlias);

        Transaction.Template transactionTemplate = new Transaction.Builder()
                .addAction(issue)
                .addAction(accountAction)
                .build(super.client);

        Transaction.Template signed = HsmSigner.sign(transactionTemplate);

        Transaction.SubmitResponse response = Transaction.submit(super.client,signed);
        System.out.println("transaction submited successfully with id: " + response.id);
    }

    @Test
    public void listTransaction() throws ChainException {
        Transaction.Items transactions = new Transaction.QueryBuilder().execute(super.client);
        while (transactions.hasNext()) {
            Transaction tx = transactions.next();
            // you can get all transaction info from tx object
            System.out.println(tx.id + "\t\t" + tx.blockHeight);
        }
    }


}
