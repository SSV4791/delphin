package ru.ssv.delphin.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import ru.ssv.delphin.api.model.Person;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PersonsResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-05-06T14:15:57.242208+03:00[Europe/Moscow]")
public class PersonsResponse   {
  @JsonProperty("persons")
  @Valid
  private List<Person> persons = null;

  public PersonsResponse persons(List<Person> persons) {
    this.persons = persons;
    return this;
  }

  public PersonsResponse addPersonsItem(Person personsItem) {
    if (this.persons == null) {
      this.persons = new ArrayList<>();
    }
    this.persons.add(personsItem);
    return this;
  }

  /**
   * Get persons
   * @return persons
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<Person> getPersons() {
    return persons;
  }

  public void setPersons(List<Person> persons) {
    this.persons = persons;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonsResponse personsResponse = (PersonsResponse) o;
    return Objects.equals(this.persons, personsResponse.persons);
  }

  @Override
  public int hashCode() {
    return Objects.hash(persons);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonsResponse {\n");
    
    sb.append("    persons: ").append(toIndentedString(persons)).append("\n");
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

