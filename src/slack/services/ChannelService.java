package slack.services;

import io.restassured.response.Response;
import slack.utils.JsonUtility;

public class ChannelService extends BaseService {

	public JsonUtility model;

	public ChannelService() {
		model = new JsonUtility();
	}
	
	public Response createChannel(String channelName) {
		String url = getAPI("Channel", "CreateChannel");

		request.formParam("token", accessToken);
		request.formParam("name", channelName);
		
		Response response = (Response) request.post(url).then().extract();

		return response;
	}

	public Response joinChannel(String channelName) {
		String url = getAPI("Channel", "JoinChannel");

		request.formParam("token", accessToken);
		request.formParam("name", channelName);
		
		Response response = (Response) request.post(url).then().extract();

		return response;
	}

	public Response renameChannel(String id,String newChannelName) {
		String url = getAPI("Channel", "RenameChannel");

		request.formParam("token", accessToken);
		request.formParam("channel", id);
		request.formParam("name", newChannelName);
		
		Response response = (Response) request.post(url).then().extract();

		return response;
	}

	
	public Response archiveChannel(String id) {
		String url = getAPI("Channel", "ArchiveChannel");

		request.formParam("token", accessToken);
		request.formParam("channel", id);
		
		Response response = (Response) request.get(url).then().extract();

		return response;
	}
	
	public Response getChannelList() {
		String url = String.format(getAPI("Channel", "ListChannel"), "token="+accessToken);

		Response response = (Response) request.get(url).then().extract();

		return response;
	}
}
