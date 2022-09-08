package henu.soft.scl.annotationway.controller;

import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import henu.soft.scl.annotationway.enums.LogRecordType;
import henu.soft.scl.annotationway.pojo.Order;
import henu.soft.scl.annotationway.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sichaolong
 * @date 2022/9/8 10:22
 */

@RestController
@Slf4j
public class OrderController {


    @Autowired
    OrderServiceImpl orderService;


    @RequestMapping("/create")
    public boolean createOrder( ) {
        Order order = new Order("11111111","IPhone 14","");
        log.info("【controller 开始创建订单...】===>{},商品名称：{}",order.getOrderNo(),order.getProductName());
        boolean res = orderService.createOrder(order);

        return res;
    }


}
