package in.rohan.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.rohan.RequestFormat.ResearchRequest;
import in.rohan.Response.GeminiResponse;

@Service
public class ResearchService {
	@Value("${gemini.api.url}")
	private String apiurl;
	
	@Value("${gemini.api.key}")
	private String apikey;
	
	//this is used to call the external api
	private WebClient webClient;
	public ResearchService(WebClient.Builder webclienBuilder) {
		this.webClient=webclienBuilder.build();
	} 

	// this would be our important method for the working
	public String generator(ResearchRequest req) {		
		// 1. build the prompt
		String prompt=buildPrompt(req); //this is the final prompt which need to be parsed
		
		//still we have not made the query to the ai model 
		//now we had made the prompt using the "content" and the "operation"
		// 2. query the Ai model api
		//the actual formating of the query is in the mentioned pattern
		Map<String,Object> requestbody= 
				Map.of("contents",new Object[] {
						Map.of("parts",new Object[] {
								Map.of("text",prompt)	
						})
				}
		);
		
		
		String response= webClient.post()
				.uri(apiurl+apikey)
				.bodyValue(requestbody)
				.retrieve()
		        .bodyToMono(String.class)
		        .block();
		//now i have got the response but it is in the json format 
		//and i have to only extract the "text" part from that
		// 3. parse the response
		// 4. return the response
		 
		return extracttextformat(response);
		 //response is also in its format 
	}
	public String extracttextformat(String response) {
	      try {
	    	  ObjectMapper mapper= new ObjectMapper();
	    	  GeminiResponse result=mapper.readValue(response, GeminiResponse.class);
	    	  if(result.getresponse()!=null) {
	    		  return result.getresponse();
	    	  }
	    	  else {
	    		  String str="no relative content founds..";
	    		  return str;
	    	  }	    	  
	      }
	      catch(Exception e) {
	    	 return e.getMessage();
	      }
	}

	public String buildPrompt(ResearchRequest req) {
		// we would be setting up the "prompt" over here
		// means the prompt according to the specified "operation"
		StringBuilder prompt = new StringBuilder();
		//this is the complete way for the formation of the prompt over here 
		//we made a prompt completely using the content and the operation	
		switch (req.getOperation()) {
		case "summarize":
			prompt.append("provide the clear and the concise summary of the following text in a few sentences:\n\n");
			break;

		case "suggestion":
			prompt.append("Based on the following content: suggest the related topics and further	reading:\n\n");
			break;

		default:
			throw new IllegalArgumentException("unknown operation: " + req.getOperation());

		}

        prompt.append(req.getContent());
		return prompt.toString();
	}
}
