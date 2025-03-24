package oosd.sait.springrest_libraryapp.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@JsonIgnoreProperties({"detailMessage", "cause", "stackTrace", "stackTraceElement"})
public class NotFoundException extends ResponseStatusException {

    @JsonProperty
    private final Long criterion;
    private final String resource;

    public NotFoundException(Long criterion, String resource) {
        super(HttpStatus.NOT_FOUND);
        this.criterion = criterion;
        this.resource = resource;
    }

    public Long getCriterion() {
        return criterion;
    }

    public String getResource() {
        return resource;
    }
}//class
