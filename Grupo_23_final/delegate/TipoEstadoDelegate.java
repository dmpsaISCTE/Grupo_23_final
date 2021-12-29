package com.mycompany.myapp.delegate;

import com.mycompany.myapp.service.dto.PreenchePedidoProcessDTO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class TipoEstadoDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        PreenchePedidoProcessDTO pedidoInterpreteProcess = (PreenchePedidoProcessDTO) delegateExecution.getVariable("processInstance");
        Boolean prioritario = false;
        if (pedidoInterpreteProcess.getPreenchePedido().getTipoLugar().contains("Prioritario")) {
            prioritario = true;
        }
        delegateExecution.setVariable("prioritario", prioritario);
    }
}
