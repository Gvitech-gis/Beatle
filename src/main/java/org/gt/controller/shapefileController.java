package org.gt.controller;

import org.gt.common.Shp2Orcale;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class shapefileController {
	@RequestMapping("/hello")
    public String index() {
		
		Shp2Orcale shp2orcl =new Shp2Orcale();
        String shppath = "D:\\temp\\鞍山\\矢量（2000经纬度）\\10000\\GBOULN.shp";
        String sqlpath = "D:\\temp\\capital.sql";
        try{
            long start = System.currentTimeMillis();
            //读取shp文件
            shp2orcl.readShape(shppath);
            shp2orcl.getShpFields();
            shp2orcl.createTableSql(sqlpath);
            shp2orcl.insertValueSql(sqlpath);
            System.out.println("共耗时"+(System.currentTimeMillis() - start)+"ms");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "Hello World";
    }
	@RequestMapping("/")
    public String indexs() {
        return "index";
    }
}
