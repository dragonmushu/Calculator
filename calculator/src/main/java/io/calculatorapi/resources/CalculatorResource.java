package io.calculatorapi.resources;

import io.calculatorapi.dto.ResultDto;
import io.calculatorapi.enums.OperatorType;
import io.calculatorapi.services.CalculatorService;

import javax.ws.rs.*;
import java.util.List;

@Path("/")
@Produces("application/json")
public class CalculatorResource {
    private CalculatorService calculatorService;

    public CalculatorResource() {
        this.calculatorService = new CalculatorService();
    }

    @POST
    @Path("/evaluate/{operator}")
    @Consumes("application/json")
    public ResultDto Evaluate(@PathParam("operator") OperatorType operator, @QueryParam("firstOperand") Double firstOperand, @QueryParam("secondOperand") Double secondOperand) {
        return this.calculatorService.evaluate(operator, firstOperand, secondOperand);
    }

    @GET
    @Path("/audit")
    public List<ResultDto> Audit() { return this.calculatorService.getAuditLogs(); }
}
