package ec.edu.ups.icc.labevaluation.supplies.exceptions;

import ec.edu.ups.icc.labevaluation.core.exceptions.base.ApplicationException;
import org.springframework.http.HttpStatus;

public class SupplyConflictException extends ApplicationException {
    public SupplyConflictException(String message) {
        super(HttpStatus.CONFLICT, "SUPPLY_CONFLICT", message);
    }
}