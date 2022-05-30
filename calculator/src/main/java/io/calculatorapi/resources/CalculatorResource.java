package io.calculatorapi.resources;

import io.calculatorapi.dto.ResultDto;
import io.calculatorapi.enums.OperatorType;
import io.calculatorapi.operators.Operator;
import io.calculatorapi.services.CalculatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import java.util.List;

@Path("/")
@Produces("application/json")
@Api(tags = {"pet"})
public class CalculatorResource {
    private CalculatorService calculatorService;

    public CalculatorResource() {
        this.calculatorService = new CalculatorService();
    }

    @POST
    @Path("/evaluate/{operator}")
    @Consumes("application/json")
    @ApiOperation(value = "Find pet by ID",
            notes = "Returns a pet when 0 < ID <= 10.  ID > 10 or nonintegers will simulate API error conditions",
            response = ResultDto.class)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Pet not found") })
    public ResultDto Evaluate(@PathParam("operator") OperatorType operator, @QueryParam("firstOperand") Double firstOperand, @QueryParam("secondOperand") Double secondOperand) {
        return this.calculatorService.evaluate(operator, firstOperand, secondOperand);
    }

    @GET
    @Path("/audit")
    public List<ResultDto> Audit() { return this.calculatorService.getAuditLogs(); }
}
