package push2.apns.connection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApnsConnectionConfig {

    private int maxTotalConnections = 20;
    private int maxIdleConnections = 20;
    private int minIdleConnections = 5;

    public ApnsConnectionConfig(int maxTotalConnections, int maxIdleConnections, int minIdleConnections) {
        this.maxTotalConnections = maxTotalConnections;
        this.maxIdleConnections = maxIdleConnections;
        this.minIdleConnections = minIdleConnections;
    }
}
