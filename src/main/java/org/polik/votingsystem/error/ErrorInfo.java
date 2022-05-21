package org.polik.votingsystem.error;

import lombok.Value;

import java.sql.Timestamp;

/**
 * Created by Polik on 5/20/2022
 */
@Value
public class ErrorInfo {
    Timestamp timestamp;
    int status;
    String error;
    String path;
}
