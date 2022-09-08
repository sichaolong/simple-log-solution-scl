package henu.soft.scl.annotationway.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author sichaolong
 * @date 2022/9/8 10:22
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String orderNo;
    private String productName;
    private String purchaseName;
}
