package com.example.saturn.services.Job;

import com.example.saturn.models.SaleOrder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;


//public class SaleOrderProcessHandler implements Runnable {
//    String saleOrder;
////    @Autowired
////    private MongoTemplate template;
//    public SaleOrderProcessHandler(String order) {
//        this.saleOrder = order;
//    }
//    @Override
//    public void run() {
//        try {
//            // Bắt đầu xử lý request đến
//            System.out.println(Thread.currentThread().getName() + " Starting process " + saleOrder);
////            template.save(new TestSo(saleOrder));
//            // cho ngủ 500 milis để ví dụ là quá trình xử lý mất 1 s
//            Thread.sleep(5000);
//            // Kết thúc xử lý request
//            System.out.println(Thread.currentThread().getName() + " Finished process " + saleOrder);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
