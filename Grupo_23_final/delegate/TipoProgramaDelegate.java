package com.mycompany.myapp.delegate;

import com.mycompany.myapp.service.dto.PreenchePedidoProcessDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class TipoProgramaDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        PreenchePedidoProcessDTO preenchePedidoProcess = (PreenchePedidoProcessDTO) delegateExecution.getVariable("processInstance");
        Boolean restaurante = false;
        if (preenchePedidoProcess.getPreenchePedido().getTipoLugar().contains("Restaurante")) {
            restaurante = true;
        }
        delegateExecution.setVariable("restaurante", restaurante);
    }
}
