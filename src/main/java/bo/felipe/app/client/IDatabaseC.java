package bo.felipe.app.client;

import bo.felipe.app.model.Venta;
import bo.felipe.app.model.VentaRequest;
import bo.felipe.app.model.VentaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "svc-database", url = "http://localhost:8083")
public interface IDatabaseC {

    @PostMapping("/db/add/venta")
    VentaResponse addVenta(@RequestBody Venta venta);

    @GetMapping("/db/venta/bo/{buy_order}")
    List<VentaResponse> getVenta(@PathVariable String buy_order);

}
