package bo.felipe.app.client;

import bo.felipe.app.model.Venta;
import bo.felipe.app.model.VentaRequest;
import bo.felipe.app.model.VentaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "svc-database", url = "http://localhost:8083")
public interface IDatabaseC {

    // C
    @PostMapping("/db/add/venta")
    VentaResponse addVenta(@RequestBody Venta venta);

    // R
    @GetMapping("/db/venta/bo/{buy_order}")
    Venta getVenta(@PathVariable String buy_order);

    // U
    @PutMapping("/db/update/venta/{buy_order}")
    Venta updateVenta(@PathVariable("buy_order") String buy_order, @RequestBody Venta venta);

    // D
    @DeleteMapping("/db/delete/venta/{id}")
    void deleteVenta(@PathVariable("id")Long id);

}
