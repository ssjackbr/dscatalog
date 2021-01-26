package br.com.ignidigital.dscatalog.services.exceptions;

import java.io.Serializable;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String msg) {
        super (msg);
    }

}
