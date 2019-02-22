package com.training;

import com.hujiang.basic.framework.core.util.IpUtil;

import org.apache.commons.lang3.StringUtils;

/**
 * Twitter的分布式自增ID器算法snowflake (Java版)
 */
public class SnowFlake {
    public static final SnowFlake BATCH_ID_GENERATOR = new SnowFlake(Long.valueOf(StringUtils.split(IpUtil.getIp(), ".")[3]) / 256L, 0L);
    /** 开始时间截 (2015-01-01) */
    private final long epoch = 1420041600000L;

    /** 机器id所占的位数 */
    private final long workerIdBits = 8L;

    /** 数据标识id所占的位数 */
    private final long datacenterIdBits = 2L;

    /** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /** 支持的最大数据标识id，结果是31 */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /** 序列在id中占的位数 */
    private final long sequenceBits = 12L;

    /** 机器ID向左移12位 */
    private final long workerIdShift = sequenceBits;

    /** 数据标识id向左移17位(12+8) */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /** 时间截向左移22位(8+2+12) */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /** 工作机器ID(0~255) */
    private long workerId;

    /** 数据中心ID(0~3) */
    private long datacenterId;

    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;

    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;

    public SnowFlake(long workerId, long datacenterId){
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                String.format("worker Id can't be greater than %d or less than 0",maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                String.format("datacenter Id can't be greater than %d or less than 0",maxDatacenterId));
        }

        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId() {
        long timestamp = genCurrentMillis();

        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = genNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;
        return ((timestamp - epoch) << timestampLeftShift) |
            (datacenterId << datacenterIdShift) |
            (workerId << workerIdShift) |
            sequence;
    }

    private long genNextMillis(long lastTimestamp) {
        long timestamp = genCurrentMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = genCurrentMillis();
        }
        return timestamp;
    }

    private long genCurrentMillis(){
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        //StringUtils.split(IpUtil.getIp(), ".")[3]) / 256L
        System.out.println(StringUtils.split(IpUtil.getIp(), ".")[3]);
    }

}
