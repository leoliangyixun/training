package push.model.po;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface ShardingPo {
    default String getSharding() {
        return new SimpleDateFormat("yyyyMM").format(new Date());
    }
}
