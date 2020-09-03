package com.objcat.servicea.controller;


import com.objcat.servicea.entity.ChinaMapEntity;
import com.objcat.servicea.entity.StudentEntity;
import com.objcat.servicea.rabbitMq.RabbitMqProducer;
import com.objcat.servicea.utils.CreateWord;
import com.objcat.servicea.utils.TreeUtils;
import com.objcat.servicea.utils.ZXingCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController

@RefreshScope
public class TestController {

    @Autowired
    private RabbitMqProducer rabbitMqProducer;

    @Value("${server.port}")
    private String port;

    @GetMapping("print")
    public String print(){
        return "service-a.print:"+port+":";
    }


    @Value("${name}")
    private String name;

    @RequestMapping("/hello")
    String hello() {
        return name;
    }


    @GetMapping("getTreeJson")
    public Object getTreeJson(){
        List<ChinaMapEntity> mapEntities = new ArrayList<>();
        ChinaMapEntity pentity = new ChinaMapEntity();
        pentity.setId(0L);pentity.setName("中国");
        mapEntities.add(pentity);
        ChinaMapEntity entity = new ChinaMapEntity();
        entity.setName("江苏省");entity.setId(32L);entity.setPid(0L);
        mapEntities.add(entity);
        ChinaMapEntity entity1 = new ChinaMapEntity();
        entity1.setName("南京市");entity1.setId(1L);entity1.setPid(32L);
        mapEntities.add(entity1);
        ChinaMapEntity entity2 = new ChinaMapEntity();
        entity2.setName("盐城市");entity2.setId(2L);entity2.setPid(32L);
        mapEntities.add(entity2);
        ChinaMapEntity entity3 = new ChinaMapEntity();
        entity3.setName("徐州市");entity3.setId(3L);entity3.setPid(32L);
        mapEntities.add(entity3);

        ChinaMapEntity entity4 = new ChinaMapEntity();
        entity4.setName("安徽省");entity4.setId(34L);entity4.setPid(0L);
        mapEntities.add(entity4);
        ChinaMapEntity entity5 = new ChinaMapEntity();
        entity5.setName("合肥市");entity5.setId(1L);entity5.setPid(34L);
        mapEntities.add(entity5);
        ChinaMapEntity entity6 = new ChinaMapEntity();
        entity6.setName("安庆市");entity6.setId(2L);entity6.setPid(34L);
        mapEntities.add(entity6);
        ChinaMapEntity entity7 = new ChinaMapEntity();
        entity7.setName("马鞍山市");entity7.setId(3L);entity7.setPid(34L);
        mapEntities.add(entity7);
        return  TreeUtils.build(mapEntities);
    }

    @PostMapping(value = "testValid")
    public Object testValid(@Validated @RequestBody StudentEntity studentEntity, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            for(ObjectError error : bindingResult.getAllErrors()){
                System.err.println(error.getDefaultMessage());
            }
            return bindingResult.getAllErrors();
        }
        return studentEntity.toString();
    }

    @GetMapping("/getWord")
    public void getWord(HttpServletResponse response) throws Exception {
        StudentEntity studentEntity1 = new StudentEntity("45011106220021","张三,男","南京市","□视力表 □电脑验光");
        StudentEntity studentEntity2 = new StudentEntity("45011106220022","李四,男","镇江市","□视力表 □电脑验光");
        StudentEntity studentEntity3 = new StudentEntity("45011106220023","王五,男","芜湖市","□视力表 □电脑验光");
        StudentEntity studentEntity4 = new StudentEntity("45011106220024","张飞,男","安庆市","□视力表 □电脑验光");
        StudentEntity studentEntity5 = new StudentEntity("45011106220025","关羽,男","马鞍山市","□视力表 □电脑验光");
        StudentEntity studentEntity6 = new StudentEntity("45011106220025","关羽,男","马鞍山市","□视力表 □电脑验光");
        StudentEntity studentEntity7 = new StudentEntity("45011106220025","关羽,男","马鞍山市","□视力表 □电脑验光");
        StudentEntity studentEntity8 = new StudentEntity("45011106220025","关羽,男","马鞍山市","□视力表 □电脑验光");
        StudentEntity studentEntity9 = new StudentEntity("45011106220025","关羽,男","马鞍山市","□视力表 □电脑验光");
        StudentEntity studentEntity10 = new StudentEntity("45011106220025","关羽,男","马鞍山市","□视力表 □电脑验光");

        List<StudentEntity> noteList = new ArrayList<StudentEntity>();
        noteList.add(studentEntity1);noteList.add(studentEntity2);noteList.add(studentEntity3);
        noteList.add(studentEntity4);noteList.add(studentEntity5);noteList.add(studentEntity6);
        noteList.add(studentEntity7);noteList.add(studentEntity8);noteList.add(studentEntity9);
        noteList.add(studentEntity10);

        List<com.lowagie.text.Image> list = ZXingCode.getQRCode(noteList);
//        CreateWord.createDocContext(list,response);//生成带有二维码的word
        CreateWord.createPDFContext(list,response);//生成带有二维码的pdf
    }

    @GetMapping(value = "/testRabbit")
    public String testIntercepter(){
        StudentEntity studentEntity = new StudentEntity("45011106220021","张三,男","南京市","□视力表 □电脑验光");
        rabbitMqProducer.send(studentEntity,9);
        rabbitMqProducer.send(studentEntity,6);
        System.out.println("发送成功"+new Date());
        return "OK";
    }



}
