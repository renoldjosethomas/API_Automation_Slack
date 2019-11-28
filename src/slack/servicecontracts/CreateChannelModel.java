package slack.servicecontracts;

public class CreateChannelModel {
	
	private String channelName;

    public String getChannelName() { return this.channelName; }
    
    public String setChannelName(String channelName) { this.channelName = channelName; return channelName; }
    
    @Override
    public String toString() {
        return "{name=" + channelName + "}"; }
}

