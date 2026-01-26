package bo.felipe.app.client;

import bo.felipe.app.model.Venta;
import bo.felipe.app.model.VentaRequest;
import bo.felipe.app.model.VentaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "svc-database", url = "http://localhost:8083")
public interface IDatabaseC {

    @PostMapping("/db/add/venta")
    VentaResponse addVenta(@RequestBody Venta venta);

}
