package in.rohan.Response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class GeminiResponse {
    private List<Candidate> candidates;

    public List<Candidate> getCandidates() {
        return candidates;
    }
    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public String getresponse() {
        if (candidates != null && !candidates.isEmpty()) {
            Candidate candidate = candidates.get(0);
            if (candidate.getContent() != null 
                && candidate.getContent().getParts() != null 
                && !candidate.getContent().getParts().isEmpty()) {
                return candidate.getContent().getParts().get(0).getText();
            }
        }
        return null;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Candidate {
    private Content content;

    public Content getContent() {
        return content;
    }
    public void setContent(Content content) {
        this.content = content;
    }
}


@JsonIgnoreProperties(ignoreUnknown = true)
class Content {
    private List<Part> parts;

    public List<Part> getParts() {
        return parts;
    }
    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}


@JsonIgnoreProperties(ignoreUnknown = true)
class Part {
    private String text;

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
