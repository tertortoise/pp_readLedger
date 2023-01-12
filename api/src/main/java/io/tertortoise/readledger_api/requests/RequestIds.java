package io.tertortoise.readledger_api.requests;

import java.util.List;
import java.util.UUID;


import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestIds {

    @Size(min = 1, message = "at least one id should be provided")
    private List<UUID> ids;
}
