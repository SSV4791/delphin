package ru.ssv.delphin.api.model.person;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import ru.ssv.delphin.api.model.person.Task;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TasksResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-05-05T16:58:27.259216+03:00[Europe/Moscow]")
public class TasksResponse   {
  @JsonProperty("personId")
  private String personId;

  @JsonProperty("tasks")
  @Valid
  private List<Task> tasks = null;

  public TasksResponse personId(String personId) {
    this.personId = personId;
    return this;
  }

  /**
   * Get personId
   * @return personId
  */
  @ApiModelProperty(value = "")


  public String getPersonId() {
    return personId;
  }

  public void setPersonId(String personId) {
    this.personId = personId;
  }

  public TasksResponse tasks(List<Task> tasks) {
    this.tasks = tasks;
    return this;
  }

  public TasksResponse addTasksItem(Task tasksItem) {
    if (this.tasks == null) {
      this.tasks = new ArrayList<>();
    }
    this.tasks.add(tasksItem);
    return this;
  }

  /**
   * Get tasks
   * @return tasks
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TasksResponse tasksResponse = (TasksResponse) o;
    return Objects.equals(this.personId, tasksResponse.personId) &&
        Objects.equals(this.tasks, tasksResponse.tasks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(personId, tasks);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TasksResponse {\n");
    
    sb.append("    personId: ").append(toIndentedString(personId)).append("\n");
    sb.append("    tasks: ").append(toIndentedString(tasks)).append("\n");
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

