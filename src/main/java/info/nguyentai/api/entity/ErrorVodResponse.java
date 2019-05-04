package info.nguyentai.api.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@JacksonXmlRootElement(localName = "errorResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ErrorVodResponse implements Serializable  {

	private static final long serialVersionUID = 509570260471737405L;
	
	@JsonProperty("result")
	private Stream result;
}
