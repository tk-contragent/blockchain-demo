package net.todko.blockchaindemo;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class SimpleBlockchain {

    public static ArrayList<Block> blockchain = new ArrayList<>();

    public static void main(String[] args) {
        //add our blocks to the blockchain ArrayList:

        blockchain.add(new Block("Hi I am the first block", "0"));
        blockchain.add(new Block("Yo I am the second block",blockchain.get(blockchain.size()-1).hash));
        blockchain.add(new Block("Hey I am the third block",blockchain.get(blockchain.size()-1).hash));

        System.out.println("Blockchain is Valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        //loop through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
}