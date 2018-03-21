package com.hujiang.dsp.a8admin.vo.criteria;

import lombok.Data;

/**
 *
 * Created by yangkai on 16/8/28.
 */
@Data
public class Sort implements Sortable {
    private String orderBy;
    private String direction;

    public Sort() {
        this(PropertyColumnDict.CREATE_TIME.column(), Direction.ASC.name());
    }

    public Sort(String orderBy, String direction) {
        this.orderBy = orderBy;
        this.direction = direction;
    }

    @Override
    public String getOrderBy() {
        return PropertyColumnDict.find(orderBy).column();
    }


    @Override
    public String getDirection() {
        return Direction.get(direction).name();
    }

    public enum Direction {
        DESC, ASC;

        public static Direction get(String direction) {
            if (direction == null) return ASC;
            for(Direction dir : values()){
                if(dir.name().equalsIgnoreCase(direction)){
                    return dir;
                }
            }
            return ASC;
        }
    }
}
