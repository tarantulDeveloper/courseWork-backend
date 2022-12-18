package kg.beaver.warehouse.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MyError {
    private int statusCode;
    private String message;
}
