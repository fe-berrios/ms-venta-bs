package bo.felipe.app.client;

import bo.felipe.app.model.VentaRequest;
import bo.felipe.app.model.VentaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "svc-ambassador", url = "http://localhost:8080")
public interface IAmbassadorC {

    @PostMapping(path = "/add/venta")
    VentaResponse addVenta(@RequestBody VentaRequest venta);

}
