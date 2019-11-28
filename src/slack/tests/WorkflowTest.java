package slack.tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import slack.services.ChannelService;

import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;

public class WorkflowTest extends BaseTest {

	private ChannelService serviceChannel;

	private WorkflowTest() {
		serviceChannel = new ChannelService();
	}

	@Test(dataProvider = "TestData")
	private void workflowTest(String channelName, String newChannelName) {

		// Act
		Response response = serviceChannel.createChannel(channelName);
		System.out.println(response.asString());
		
		resultsBag.put("id", response.jsonPath().getString("channel.id"));
		System.out.println("id = " + response.jsonPath().getString("channel.id"));
		
		response = serviceChannel.joinChannel(channelName);
		
		//Rename & Assert Channel
		response = serviceChannel.renameChannel(resultsBag.get("id"),newChannelName);
		
		response = serviceChannel.getChannelList();
		String path = "channels.name[" + getChannelIndex(response,resultsBag.get("id")) + "]";
		Assert.assertEquals(response.jsonPath().getString(path), newChannelName);
		
		//Archive & Assert
		
		response = serviceChannel.archiveChannel(resultsBag.get("id"));
		response = serviceChannel.getChannelList();
		path = "channels.is_archived[" + getChannelIndex(response,resultsBag.get("id")) + "]";
		
		Assert.assertEquals(response.jsonPath().getString(path), "true");

  	}
	
	
	//Finds Channel ID and returns corresponding Channel Name
	private String getChannelIndex(Response response, String id) {
		List<String> idList = response.jsonPath().getList("channels.id");
		int index = idList.indexOf(id);
		
		return index+"";
	}

	// Retrieves Data from CSV and runs the test multiple times for every set or
	// combination of data
	@DataProvider(name = "TestData")
	private Iterator<Object[]> dataProvider() {
		return getTestData("Channel", "WorkflowTest");
	}
}
