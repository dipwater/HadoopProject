package com.dipwater.accountAnalyze;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class KeyComparator extends WritableComparator {

	protected KeyComparator(Class<? extends WritableComparable> keyClass,
			boolean createInstances) {
		super(keyClass, createInstances);
		// TODO 自动生成的构造函数存根
	}
//    protected KeyComparator() {
//        super(IntPair.class, true);
//    }
//
//    public int compare(WritableComparable w1, WritableComparable w2) {
//        IntPair ip1 = (IntPair) w1;
//        IntPair ip2 = (IntPair) w2;
//        int cmp = IntPair.compare(ip1.getFirst(), ip2.getFirst());
//        if (cmp != 0) {
//            return cmp;
//        }
//        return -IntPair.compare(ip1.getSecond(), ip2.getSecond()); // reverse
//    }
}
