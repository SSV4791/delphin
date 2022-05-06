package ru.ssv.delphin.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ru.ssv.delphin.api.model.Task;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TaskResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-05-06T14:15:57.242208+03:00[Europe/Moscow]")
public class TaskResponse   {
  @JsonProperty("task")
  private Task task;

  public TaskResponse task(Task task) {
    this.task = task;
    return this;
  }

  /**
   * Get task
   * @return task
  */
  @ApiModelProperty(value = "")

  @Valid

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaskResponse taskResponse = (TaskResponse) o;
    return Objects.equals(this.task, taskResponse.task);
  }

  @Override
  public int hashCode() {
    return Objects.hash(task);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaskResponse {\n");
    
    sb.append("    task: ").append(toIndentedString(task)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

