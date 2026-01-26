package bo.felipe.app.service;

import bo.felipe.app.client.IAmbassadorC;
import bo.felipe.app.client.IDatabaseC;
import bo.felipe.app.model.Venta;
import bo.felipe.app.model.VentaRequest;
import bo.felipe.app.model.VentaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    @Autowired
    IAmbassadorC iAmbassadorC;

    @Autowired
    IDatabaseC iDatabaseC;

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

}
