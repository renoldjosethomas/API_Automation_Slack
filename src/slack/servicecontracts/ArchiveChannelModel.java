package slack.servicecontracts;

public class ArchiveChannelModel {
	
	private String channelId;
    
	public String getChannelId() { return this.channelId; }
    
    public String setChannelId(String channelId) { this.channelId = channelId; return channelId; }
    
    @Override
    public String toString() {
        return "{channel=" + channelId + "}"; }
}