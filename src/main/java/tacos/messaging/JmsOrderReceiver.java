package tacos.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import tacos.Order;

@Profile("jms-template")
@Component("templateOrderReceiver")
public class JmsOrderReceiver implements OrderReceiver {
    private final JmsTemplate jms;

    @Autowired
    public JmsOrderReceiver(JmsTemplate jms) {
        this.jms = jms;
    }


    public Order receiverOrder(){
        return (Order) jms.receiveAndConvert("tacocloud.order.queue");
    }
}

