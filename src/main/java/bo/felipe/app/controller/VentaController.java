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

    @PostMapping("/bs/add/venta")
    public VentaResponse ventaResponse(@RequestBody VentaRequest venta){
        return ventaService.addVenta(venta);
    }

    @PutMapping("/bs/status/venta/{token_ws}")
    public StatusResponse statusVenta(@PathVariable("token_ws")String token){
        return ventaService.statusVenta(token);
    }

}