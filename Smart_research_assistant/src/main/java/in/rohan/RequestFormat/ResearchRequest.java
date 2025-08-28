package in.rohan.RequestFormat;

public class ResearchRequest {
	private String content; //this is the required content 
	private String operation;//this is the required operation

	public ResearchRequest() {
	}

	public ResearchRequest(String content, String operation) {

		this.content = content;
		this.operation = operation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "ResearchRequest [content=" + content + ", operation=" + operation + "]";
	}

}
