package ru.ssv.delphin.controller.handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder(setterPrefix = "with")
@Getter
public class ErrorResponse {

  @JsonProperty("url")
  private String url;

  @JsonProperty("code")
  private Integer code;

  @JsonProperty("message")
  private String message;

  @JsonProperty("text")
  private List<String> text;

  @JsonProperty("trace")
  private String trace;
}

