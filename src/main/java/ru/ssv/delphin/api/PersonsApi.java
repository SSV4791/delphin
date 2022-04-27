/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.2.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package ru.ssv.delphin.api;

import ru.ssv.delphin.api.model.person.PersonResponse;
import ru.ssv.delphin.api.model.person.PersonsResponse;
import ru.ssv.delphin.api.model.person.TasksResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-05-05T16:58:27.259216+03:00[Europe/Moscow]")
@Validated
@Api(value = "persons", description = "the persons API")
public interface PersonsApi {

    /**
     * GET /persons
     * Получение списка пользователей
     *
     * @return Успешный ответ (status code 200)
     *         or Not Found (status code 404)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     */
    @ApiOperation(value = "", nickname = "getAllPersons", notes = "Получение списка пользователей", response = PersonsResponse.class, tags={ "Persons", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Успешный ответ", response = PersonsResponse.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/persons",
        produces = { "application/json" }
    )
    ResponseEntity<PersonsResponse> getAllPersons();


    /**
     * GET /persons/{personId}/tasks
     * Получение списка задач для пользователя
     *
     * @param personId Идентификатор пользователя (required)
     * @return Успешный ответ (status code 200)
     *         or Not Found (status code 404)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     */
    @ApiOperation(value = "", nickname = "getAllTasksByPersonId", notes = "Получение списка задач для пользователя", response = TasksResponse.class, tags={ "All Tasks by PersonId", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Успешный ответ", response = TasksResponse.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/persons/{personId}/tasks",
        produces = { "application/json" }
    )
    ResponseEntity<TasksResponse> getAllTasksByPersonId(@ApiParam(value = "Идентификатор пользователя",required=true) @PathVariable("personId") String personId);


    /**
     * GET /persons/{personId}
     * Получение пользоввателя по id
     *
     * @param personId Идентификатор пользователя (required)
     * @return Успешный ответ (status code 200)
     *         or Not Found (status code 404)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     */
    @ApiOperation(value = "", nickname = "getById", notes = "Получение пользоввателя по id", response = PersonResponse.class, tags={ "Person", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Успешный ответ", response = PersonResponse.class),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/persons/{personId}",
        produces = { "application/json" }
    )
    ResponseEntity<PersonResponse> getById(@ApiParam(value = "Идентификатор пользователя",required=true) @PathVariable("personId") String personId);

}
