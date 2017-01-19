package com.poet.chain.test;

import com.chain.api.Account;
import com.chain.api.ControlProgram;
import com.chain.api.MockHsm;
import com.chain.exception.ChainException;
import com.poet.chain.BaseChainCoreTest;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by poet on 2017/1/19.
 */
public class AccountTest extends BaseChainCoreTest {

    @Test
    public void createAccount() throws ChainException {
        // for create a new account,
        // you first need one or more xPub,
        // you can create a new HSM key or using exists one
        // here we create a new HSM key
        MockHsm.Key key = null;
        key = MockHsm.Key.create(super.client, "key_from_sdk");

        assertNotNull(key);

        Account.Builder builder = new Account.Builder();
        Account account = builder.setAlias("account_from_sdk") // set the account alias
                .addTag("custom_tag", "tag val") // add account tag,you can add more by this method
                .setQuorum(1)  // set quorum
                .addRootXpub(key.xpub) // you can add more root xpub by this method,or use setRootXpubs method to set a xpub list
                .create(super.client);
        System.out.println("account created successfully with id: " + account.id);
    }

    @Test
    public void listAccounts() throws ChainException {
        Account.Items accounts = new Account.QueryBuilder()
                // you can add filter condition here,like below
//                .addFilterParameter("alias='lisi'")
                .execute(super.client);

        while (accounts.hasNext()) {
            Account account = accounts.next();
            System.out.println(account.alias + "\t\t" + account.id);
        }
    }
    
    @Test
    public void createControlProgram() throws ChainException {
        ControlProgram controlProgram = new ControlProgram.Builder()
                .controlWithAccountByAlias("account_from_sdk")
                .create(super.client);
        System.out.println("create control program success,control program: " + controlProgram.controlProgram);
    }

}
