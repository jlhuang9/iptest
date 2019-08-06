package com.hcq.iptest;

import java.util.BitSet;

public class BitIp {
    /**
     * int 范围是 -2^31——2^31-1
     * bitset只能存正数0-2^31-1，所以要两个bitset
     */
    private static final int SIZE = 1 << 31 - 1;
    /**
     * 这里存正数
     */
    BitSet aSet = new BitSet(SIZE);
    /**
     * 这里存负数
     */
    BitSet bSet = new BitSet(SIZE);

    public static void main(String[] args) {
        String ip = "192.168.1.1";
        BitIp bitIp = new BitIp();
        System.out.println(ip + "是否在列表中： " + bitIp.contains(ip));
        bitIp.add(ip);
        System.out.println(ip + "是否在列表中： " + bitIp.contains(ip));
        bitIp.delete(ip);
        System.out.println(ip + "是否在列表中： " + bitIp.contains(ip));
        ip = "192.168.1.2";
        System.out.println(ip + "是否在列表中： " + bitIp.contains(ip));

    }

    public void add(String ip) {
        int i = ip2Int(ip);
        BitSet tempSet = i >= 0 ? aSet : bSet;
        tempSet.set(i >= 0 ? i : -(i + 1), true);
    }


    public void delete(String ip) {
        int i = ip2Int(ip);
        BitSet tempSet = i >= 0 ? aSet : bSet;
        tempSet.set(i >= 0 ? i : -(i + 1), false);
    }

    public boolean contains(String ip) {
        int i = ip2Int(ip);
        BitSet tempSet = i >= 0 ? aSet : bSet;
        return tempSet.get(i >= 0 ? i : -(i + 1));
    }

    public static int ip2Int(String ip) {
        String[] split = ip.split("\\.");
        int result = 0;
        for (String s : split) {
            int i = Integer.parseInt(s);
            result = (result << 8) + i;
        }
        return result;
    }

}
