package bo.felipe.app.service;

import bo.felipe.app.client.IAmbassadorC;
import bo.felipe.app.model.VentaRequest;
import bo.felipe.app.model.VentaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    @Autowired
    IAmbassadorC iAmbassadorC;

    public VentaResponse addVenta(VentaRequest venta){
        return iAmbassadorC.addVenta(venta);
    }

}
