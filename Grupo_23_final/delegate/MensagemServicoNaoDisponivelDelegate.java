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
public class MensagemServicoNaoDisponivelDelegate implements JavaDelegate {

    @Autowired
    MailService mailService;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        PreenchePedidoProcessDTO preenchePedidoProcess = (PreenchePedidoProcessDTO) delegateExecution.getVariable("processInstance");
        PreenchePedidoDTO mensagemServicoNaoDisponivelDelegate = preenchePedidoProcess.getPreenchePedido();
        String to = mensagemServicoNaoDisponivelDelegate.getEmail();
        String subject = "[AgileKip]Lugar não disponivel" + mensagemServicoNaoDisponivelDelegate.getNomeCliente();
        Context context = new Context(Locale.getDefault());
        context.setVariable("mensagemServicoNaoDisponivelDelegate", mensagemServicoNaoDisponivelDelegate);
        String content = templateEngine.process("preenchePedidoProcess/mensagemServicoNaoDisponivelDelegateSummary", context);
        mailService.sendEmail(to, subject, content, false, true);
    }
}
