package boxsystem.product_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 4, max = 20, message = "O nome deve ter entre 4 e 20 caracteres")
    public String name;

    @NotBlank(message = "A categoria é obrigatório")
    public String category;

   @NotNull
    public Double price;

    @NotNull
    public Double quantity;
}
