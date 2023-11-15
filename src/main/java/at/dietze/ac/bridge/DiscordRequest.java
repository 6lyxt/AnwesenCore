package at.dietze.ac.bridge;

import java.util.Date;

public class DiscordRequest {

    private String data;

    private long timestamp;

    /**
     * @param data
     */
    public DiscordRequest(String data) {
        this.timestamp = System.currentTimeMillis() / 1000L;
        this.data = data;
    }

    /**
     * @return
     */
    public String getData() {
        return data;
    }

    /**
     * @return
     */
    public long getTimestamp() {
        return timestamp;
    }
}
