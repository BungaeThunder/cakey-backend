package bungae.thunder.cakey.report.exception;

import bungae.thunder.cakey.common.error.exception.EntityNotFoundException;

public class ReportNotFoundException extends EntityNotFoundException {

    public ReportNotFoundException(String message) {
        super(message);
    }
}
