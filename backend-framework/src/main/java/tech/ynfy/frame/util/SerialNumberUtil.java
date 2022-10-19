package tech.ynfy.frame.util;

import java.util.concurrent.atomic.AtomicInteger;

public class SerialNumberUtil {


    private static final AtomicInteger SERIAL = new AtomicInteger(Integer.MAX_VALUE);

    /**
     * 17位时间戳
     */
    private static final int SHIFTS_FOR_TIMESTAMP = 16;

    /**
     *  8位mechId, 商户号, 最大支持255-MASK_FOR_UNION
     */
    private static final int SHIFTS_FOR_UNION = 2;

    /**
     * 10位type位, 用于区分商户下的业务类型, 最大支持1023-MASK_FOR_TYPE
     */
    private static final int SHIFTS_FOR_TYPE = 2;

    /**
     * 系统服务id 4位
     */
    private static final int SHIFTS_FOR_NODE = 18;

    /**
     * 系统订单流水号 24位, 最大支持16777215-SHIFTS_FOR_SERIAL
     */
    private static final long MASK_FOR_SERIAL = 16777215L; // (1 << SHIFTS_FOR_SERIAL) - 1;


    /**
     * @param mechId
     * @param type
     * @return
     */
    public static Long next(long mechId, long type) {
        long second = System.currentTimeMillis() / 1000;
        long serial = SERIAL.incrementAndGet();
        long nodeRandom = (long) (Math.random() * 262144);
        long secondShift = second << (63 - SHIFTS_FOR_TIMESTAMP);
        long unionShift = mechId << (63 - SHIFTS_FOR_TIMESTAMP - SHIFTS_FOR_UNION);
        long typeShift = type << (63 - SHIFTS_FOR_TIMESTAMP - SHIFTS_FOR_UNION - SHIFTS_FOR_TYPE);
//        0x1000000
        long nodeShift = nodeRandom << (63 - SHIFTS_FOR_TIMESTAMP - SHIFTS_FOR_UNION - SHIFTS_FOR_TYPE - SHIFTS_FOR_NODE);
        long number = secondShift | unionShift | typeShift | nodeShift | (serial & MASK_FOR_SERIAL);
        return number;
    }

    public static Long next(){
        return next(1L,1L);
    }


    
}
