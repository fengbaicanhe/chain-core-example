package com.poet.chain.test;

import com.chain.api.Asset;
import com.chain.api.MockHsm;
import com.chain.exception.ChainException;
import com.poet.chain.BaseChainCoreTest;
import org.junit.Test;

/**
 * Created by poet on 2017/1/19.
 */
public class AssetTest extends BaseChainCoreTest {

    @Test
    public void createAsset() throws ChainException {
        // for create a new asset
        // you first need one or more xPub
        // you can create a new one by MockHsm or using exists one

        // here we use the first xpub
        MockHsm.Key key = new MockHsm.Key.QueryBuilder().execute(super.client).next();

        Asset asset = new Asset.Builder()
                .setQuorum(1)       // set quorum
                .addRootXpub(key.xpub)  // add a rootXpub,you can add more by this method
                .addTag("tag_from_sdk","tag_val")   // add a asset tag,you can add more by this method
                .addDefinitionField("defi_from_sdk","defi val") // add a asset definition,you can add more by this method
                .setAlias("ast_alias") // set asset's alias
                .create(super.client);

        System.out.println("Asset created successfully with asset id: " + asset.id);
    }

    @Test
    public void listAssets() throws ChainException {
        Asset.Items assets = new Asset.QueryBuilder().execute(super.client);
        while (assets.hasNext()) {
            Asset asset = assets.next();
            System.out.println(asset.alias + "\t\t" + asset.id);
        }
    }

}
