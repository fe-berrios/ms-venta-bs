package bo.felipe.app.controller;

import bo.felipe.app.model.StatusResponse;
import bo.felipe.app.model.Venta;
import bo.felipe.app.model.VentaRequest;
import bo.felipe.app.model.VentaResponse;
import bo.felipe.app.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class VentaController {

    @Autowired
    VentaService ventaService;

    // C
    @PostMapping("/bs/add/venta")
    public VentaResponse ventaResponse(@RequestBody VentaRequest venta){
        return ventaService.addVenta(venta);
    }

    // R
    @GetMapping("/bs/venta/{buy_order}")
    public Venta getVentaByBO(@PathVariable("buy_order")String buy_order){
        return ventaService.getVentaByBO(buy_order);
    }

    // U
    @PutMapping("/bs/update/venta/{buy_order}")
    public Venta updateVenta(@PathVariable("buy_order")String buy_order, @RequestBody Venta venta){
        return  ventaService.updateVenta(buy_order, venta);
    }

    // D
    @DeleteMapping("/bs/delete/venta/{id}")
    public void deleteVenta(@PathVariable("id")Long id){
        ventaService.deleteVenta(id);
    }

    // Confirm Venta
    @PutMapping("/bs/confirm/venta/{token_ws}")
    public StatusResponse confirmVenta(@PathVariable("token_ws")String token){
        return ventaService.confirmVenta(token);
    }

}