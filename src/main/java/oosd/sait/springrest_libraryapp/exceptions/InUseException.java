package oosd.sait.springrest_libraryapp.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@JsonIgnoreProperties({"detailMessage", "cause", "stackTrace", "stackTraceElement"})
public class InUseException extends ResponseStatusException {

    @JsonProperty
    private final Long criterion;
    private final String detailMessage;

    public InUseException(Long criterion, String detailMessage) {
        super(HttpStatus.CONFLICT);
        this.criterion = criterion;
        this.detailMessage = detailMessage;

    }

    public Long getCriterion() {
        return criterion;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}//class
