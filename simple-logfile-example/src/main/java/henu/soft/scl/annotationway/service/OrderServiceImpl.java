package henu.soft.scl.annotationway.service;

import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import henu.soft.scl.annotationway.enums.LogRecordType;
import henu.soft.scl.annotationway.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sichaolong
 * @date 2022/9/8 11:01
 */

@Service
@Slf4j
public class OrderServiceImpl {

    @LogRecord(
            fail = "创建订单失败，失败原因：「{{#_errorMsg}}」",
            success = "{{#order.purchaseName}}下了一个订单,购买商品「{{#order.productName}}」,测试变量「{{#innerOrder.productName}}」,下单结果:{{#_ret}}",
            type = LogRecordType.ORDER, bizNo = "{{#order.orderNo}}")
    public boolean createOrder(Order order ) {
        log.info("【service 开始创建订单...】===>{},商品名称：{}",order.getOrderNo(),order.getProductName());
        // db insert order
        Order order1 = new Order();
        order1.setProductName("内部变量测试");
        order1.setPurchaseName("测试购买者");

        LogRecordContext.putVariable("innerOrder", order1);
        return true;
    }
}
