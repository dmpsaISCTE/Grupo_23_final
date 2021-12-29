package com.mycompany.myapp.delegate;

import com.mycompany.myapp.service.MailService;
import com.mycompany.myapp.service.dto.PreenchePedidoDTO;
import com.mycompany.myapp.service.dto.PreenchePedidoProcessDTO;
import java.util.Locale;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public class MensagemPrioridadeDelegate implements JavaDelegate {

    @Autowired
    MailService mailService;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        PreenchePedidoProcessDTO preenchePedidoProcess = (PreenchePedidoProcessDTO) delegateExecution.getVariable("processInstance");
        PreenchePedidoDTO mensagemPrioridadeDelegate = preenchePedidoProcess.getPreenchePedido();
        String to = mensagemPrioridadeDelegate.getEmail();
        String subject = "[AgileKip]Prioritario" + mensagemPrioridadeDelegate.getNomeCliente();
        Context context = new Context(Locale.getDefault());
        context.setVariable("mensagemPrioridadeDelegate", mensagemPrioridadeDelegate);
        String content = templateEngine.process("preenchePedidoProcess/mensagemPrioridadeDelegateSummary", context);
        mailService.sendEmail(to, subject, content, false, true);
    }
}
