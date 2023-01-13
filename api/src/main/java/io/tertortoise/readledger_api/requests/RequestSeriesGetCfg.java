package io.tertortoise.readledger_api.requests;

import lombok.Data;

@Data
public class RequestSeriesGetCfg {

    private Boolean includeComments;

    private Boolean includeBooks;


}
