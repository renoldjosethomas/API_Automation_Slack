package slack.servicecontracts;

public class RenameChannelModel {
	
	private String channelId;
	private String newName;
    
    public String getChannelId() { return this.channelId; }
    public String getNewName() { return this.newName; }
    
    public String setChannelId(String channelId) { this.channelId = channelId; return channelId; }
    public String setNewName(String newName) { this.newName = newName; return newName; }
    
    @Override
    public String toString() {
    	return "{channel=" + channelId + ",name=" + newName + "}"; }
}