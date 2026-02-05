package bo.felipe.app.service;

import bo.felipe.app.client.IAmbassadorC;
import bo.felipe.app.client.IDatabaseC;
import bo.felipe.app.model.StatusResponse;
import bo.felipe.app.model.Venta;
import bo.felipe.app.model.VentaRequest;
import bo.felipe.app.model.VentaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class VentaService {

    @Autowired
    IAmbassadorC iAmbassadorC;

    @Autowired
    IDatabaseC iDatabaseC;

    // Añadir venta
    public VentaResponse addVenta(VentaRequest venta){

        VentaResponse ambassadorResponse = iAmbassadorC.addVenta(venta);

        Venta ventaDatabase = new Venta();
        ventaDatabase.setBuyOrder(venta.getBuyOrder());
        ventaDatabase.setSessionId(venta.getSessionId());
        ventaDatabase.setAmount(venta.getAmount());
        ventaDatabase.setReturnUrl(venta.getReturnUrl());
        ventaDatabase.setToken(ambassadorResponse.getToken());
        ventaDatabase.setUrl(ambassadorResponse.getUrl());

        iDatabaseC.addVenta(ventaDatabase);

        return iAmbassadorC.addVenta(venta);
    }

    // Read Venta
    public Venta getVentaByBO(@PathVariable("buy_order")String buy_order){
        return iDatabaseC.getVenta(buy_order);
    }

    // Update Venta
    public Venta updateVenta(String buy_order, Venta venta){
        return iDatabaseC.updateVenta(buy_order, venta);
    }

    // Delete Venta
    public void deleteVenta(@PathVariable("id")Long id){
        iDatabaseC.deleteVenta(id);
    }

    // Confirmar Venta
    public StatusResponse confirmVenta(String token_ws){
        return iAmbassadorC.confirmVenta(token_ws);
    }


}
