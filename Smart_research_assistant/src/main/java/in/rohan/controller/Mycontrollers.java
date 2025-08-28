package in.rohan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.rohan.RequestFormat.ResearchRequest;
import in.rohan.Service.ResearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/research")
@CrossOrigin(origins = "*")
public class Mycontrollers {
	
	@Autowired
	public ResearchService researchService;
	

	@PostMapping("/process")
	//it means that we would be sending the content and operation to the gemini
	public ResponseEntity<String> processContent(@RequestBody ResearchRequest request)	{
		String result= researchService.generator(request);
		return ResponseEntity.ok(result);
	}
	

}
