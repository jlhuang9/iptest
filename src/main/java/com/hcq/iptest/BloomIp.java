package com.hcq.iptest;

import java.util.BitSet;

public class BloomIp {
    /**
     * bitSet只能是正数设个size保证hash算的是正数
     */
    private static final int SIZE = 1<<24;
    BitSet bitSet=new BitSet(SIZE);
    Hash[] hashs=new Hash[3];
    private static final int seeds[]=new int[]{3,5,7};
    public static void main(String[] args) {
        String ip="192.168.1.1";
        BloomIp bloomDemo=new BloomIp();
        System.out.println(ip+"是否在列表中： "+bloomDemo.contains(ip));
        bloomDemo.add(ip);
        System.out.println(ip+"是否在列表中： "+bloomDemo.contains(ip));
        ip="192.168.1.2";
        System.out.println(ip+"是否在列表中： "+bloomDemo.contains(ip));
        bloomDemo.add("255.255.255.255");

    }
    public BloomIp(){
        for (int i = 0; i < seeds.length; i++) {
            hashs[i]=new Hash(seeds[i]);
        }
    }
    public void add(String string){
        for(Hash hash:hashs){
            bitSet.set(hash.getHash(string),true);
        }
    }
    public boolean contains(String string){
        boolean have=true;
        for(Hash hash:hashs){
            have&=bitSet.get(hash.getHash(string));
        }
        return have;
    }
    class Hash{
        public int getSeed() {
            return seed;
        }

        private int seed = 0;
        public Hash(int seed){
            this.seed=seed;
        }
        public int getHash(String string){
            int val=0;
            int len=string.length();
            for (int i = 0; i < len; i++) {
                val = val * seed + string.charAt(i);
            }
            return val&(SIZE-1);
        }
    }

}
