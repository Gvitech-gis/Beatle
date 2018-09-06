package org.gt.common;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.gt.bean.Fields;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;

import com.vividsolutions.jts.geom.Geometry;



public class Shp2Orcale {
    private final Map<String, String> fMap = new HashMap<String, String>();

    private SimpleFeatureSource featureSource = null;
    private String tableName = "";
    private List<Fields> fields = new ArrayList<Fields>();
    //private CommonMethod cm = new CommonMethod();

    public Shp2Orcale(){
        fMap.put("string", "varchar(64)");
        fMap.put("long", "long");
        fMap.put("double", "number");
        fMap.put("shape", "\"MDSYS\".\"SDO_GEOMETRY\"");
    }

    public void readShape(String shpfile){
        try {
            File file = new File(shpfile);
            ShapefileDataStore shpDataStore = null;

            shpDataStore = new ShapefileDataStore(file.toURL());
            //设置编码
            Charset charset = Charset.forName("GBK");
            shpDataStore.setCharset(charset);
            tableName = shpDataStore.getTypeNames()[0];
            featureSource =  shpDataStore.getFeatureSource (tableName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getShpFields(){
        SimpleFeatureType schema = featureSource.getSchema();
        List<AttributeDescriptor> attrs= schema.getAttributeDescriptors();
        for(int i=0;i<attrs.size();i++){
            AttributeDescriptor attr = attrs.get(i);
            Class<?> cls = attr.getType().getBinding();
            String clsName = cls.getName();
            String type =cls.getTypeName();
            clsName = clsName.substring(clsName.lastIndexOf(".")+1).toLowerCase();
            Fields field = new Fields(attr.getLocalName(), clsName);
            fields.add(field);
        }
    }

    public void createTableSql(String sqlpath){
        //cm.append2File(sqlpath, "--1.drop table\r\nDROP TABLE "+tableName+";");
        //cm.append2File(sqlpath, "\r\n--2.create table\r\n");
        StringBuffer sql = new StringBuffer();
        sql.append("CREATE TABLE "+tableName+" (");
        for(int i=0, size = fields.size();i<size;i++){
            Fields field = fields.get(i);
            String filedname = field.getFieldname().toLowerCase();
            filedname = filedname.equals("the_geom")?"shape":filedname;
            String fieldtype = field.getFieldtype();
            fieldtype = filedname.equals("shape")?"shape":fieldtype;
            sql.append(filedname+" "+fMap.get(fieldtype));
            if(i!=size-1) sql.append(", ");
        }
        sql.append(");");
        //cm.append2File(sqlpath, sql.toString());
    }

    public void insertValueSql(String sqlpath) {
        try {
            //cm.append2File(sqlpath, "\r\n--3.insert data");

            SimpleFeatureCollection result = featureSource.getFeatures();
            SimpleFeatureIterator itertor = result.features();

            StringBuffer sql = new StringBuffer();
            while (itertor.hasNext()){
                SimpleFeature feature = itertor.next();
                StringBuffer _sql = new StringBuffer();
                _sql.append("insert into "+tableName+" values(");
                for(int i=0, size = fields.size();i<size;i++){
                    Fields field = fields.get(i);
                    String filedname = field.getFieldname();
                    filedname = filedname=="the_geom"?"shape":filedname;
                    String fieldtype = field.getFieldtype();

                    if(filedname!="shape"){
                        if(fieldtype.equals("string")){
                            _sql.append("'"+feature.getAttribute(filedname)+"'");
                        }else {
                            _sql.append(feature.getAttribute(filedname));
                        }
                    }else{
                        Geometry geom = (Geometry)feature.getAttribute("the_geom");
                        _sql.append("sdo_geometry('"+geom.toString()+"', 4326)");
                    }
                    if(i!=size-1) _sql.append(", ");
                }
                _sql.append(");\r\n");
                sql.append(_sql);
            }
            //cm.append2File(sqlpath, sql.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
