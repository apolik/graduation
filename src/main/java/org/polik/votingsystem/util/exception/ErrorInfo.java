package org.polik.votingsystem.util.exception;

import lombok.Data;

/**
 * Created by Polik on 3/29/2022
 */
@Data
public class ErrorInfo {
    private final String url;
    private final ErrorType type;
    private final String detail;
}
