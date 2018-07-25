package com.DS.Controller;
import com.DS.Model.TestModel;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/***
 * 
 * @author jeff qiu
 * 用于测试
 *
 */
public class TestController extends Controller {
    /***
     *   默认的执行方式
     *   http://localhost:82/test
     */
    public void index() {
        renderText("Hello JFinal World.");//传达普通文本
    }
    /***
     *  通过model方式增加数据
     *  http://localhost:82/test/save?title=hh&content=hh
     */
    //@ActionKey("/save")
    public void save() {
        String  title=getPara("title");
        String  content=getPara("content");
        TestModel b=new TestModel();
        b.set("title", title);
        b.set("content", content);
        b.save();
        render("finsh.jsp");
    }

    /***
     * 通过Db+Record方式增加数据
     * http://localhost:82/test/saveByDb?title=hh4&content=hh4
     */
    public void saveByDb() {
        String  title=getPara("title");
        String  content=getPara("content");
        Record record = new Record();
        record.set("title",title);
        record.set("content", content);
        Db.save("blog",record);
        render("finsh.jsp");
    }
    /***
     * 查询全部数据
     * http://localhost:82/test/getData
     */
    public void getData(){
        List<TestModel> blogs=TestModel.dao.find("select * from blog");
        renderJson(blogs);//传递json数据

    }

    /***
     *  通过model方式删除数据
     *  http://localhost:82/test/delByIdModel?id=8
     */
    public void delByIdModel(){
        String  id=getPara("id");
        System.out.println("要删除的数据的id:"+id);
      //  通过model方式删除数据
        if(TestModel.dao.deleteById(Integer.parseInt(id))){
            System.out.println("删除成功");
            render("success.jsp");
        }else{
            System.out.println("删除失败");
            render("fail.jsp");
        }
    }

    /***
     *  通过Db方式删除数据
     *  http://localhost:82/test/delByIdAndDb?id=8
     */
    public void delByIdAndDb(){
        String  id=getPara("id");
        System.out.println("要删除的数据的id:"+id);
       if( Db.deleteById("blog",Integer.parseInt(id))){
           render("success.jsp");
       }else{
           render("fail.jsp");
       }

    }

    /***
     * 更新数据
     * http://localhost:82/test/update?id=2&title=ttt&content=con
     */
    public void update(){
        String  title=getPara("title");
        String  content=getPara("content");
        String  id=getPara("id");
        System.out.println("要更新的数据的id:"+id+" title:"+title+" content:"+content);
        Record record = new Record();
        record=Db.findById("blog",Integer.parseInt(id)).set("title",title).set("content",content);
        Db.update("blog",record);
        render("finsh.jsp");
    }
   
    /***
     * 测试dataTables
     */
    public void goDataTables(){
    	   render("dataTables.html");
    }
    
    /***
     * dataTables表格数据的填充
     */
    public void getTable(){
        List<TestModel> blogs=TestModel.dao.find("select * from blog");
        Map map = new HashMap();
        map.put("data", blogs);//这个data属性名很重要，填充的数据默认寻找data
        renderJson(map);
    }
    /***
     * 测试传值
     */
    public void renderData(){
    	List<String>  list = new ArrayList<String>();
    	List<TestModel> blogs=TestModel.dao.find("select * from blog");
        Map map = new HashMap();
        map.put("data", blogs);//这个data属性名很重要，填充的数据默认寻找data
        list.add("列表1");
        list.add("列表2");
        list.add("列表3");
        list.add("列表4");
        list.add("列表5");
        setAttr("testlist",list );
        setAttr("abc",map );
       
         render("dataHandle/edit.html");
    }
   
}