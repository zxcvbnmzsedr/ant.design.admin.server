package com.tianzeng.react.utils;

import com.alibaba.fastjson.JSONArray;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
/**
 * Created by tianzeng on 17-4-27.
 */
public class JsonTreeHelper {
    /************************主调方法类型二*******************************
     *
     *easyui 树数据的类型：
     * 					id1		text		_parentId(id2) ...
     * 					id2		text		_parentId(id2) ...
     * 					id3		text		_parentId(null) ...
     * 					.
     * 					.
     * 					.
     * 构造结果:
     * 					text(id3)
     * 						text(id1)
     * 						text(id2)
     *
     *
     * @return json字符串
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     * @throws SecurityException
     */
    public static String getTreeJsonStrNormal(String[] members,List<Object> data) throws IllegalArgumentException, IllegalAccessException, SecurityException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IntrospectionException {
        //分组
        Map<String, List<Object>> groupedMapRel = groupByGetMethod(data,members[PARENT_节点_INDEX]) ;
        //赋值转换
        Map<String, List<JsonTreeObj>> groupedMapJsonTree = assignment(groupedMapRel,members) ;
        //递归
        Map<String,List<JsonTreeObj>> map = recursiveTree(groupedMapJsonTree) ;

        //还原数据
        currentKeyFlag = TREE_ROOT_NAME ;
        preKeyFlag = TREE_ROOT_NAME ;

        List<JsonTreeObj> jsonList = map.get(currentKeyFlag) ;

        //递归数据,组成json map
        return JSONArray.toJSON(jsonList).toString().replace("\"children\":[],", "");
    }
    /**
     * 将数据根据根节点进行分组
     *
     * @param fieldIndex
     *            根节点成员字段所对应折field索引
     * @param list
     *            数据列表
     * @return 分组好的map数据
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private static Map<String, List<Object>> groupByName(
            Integer fieldIndex, List<Object> list)
            throws IllegalArgumentException, IllegalAccessException {
        // 准备集合
        Map<String, List<Object>> readyMap = new HashMap<String, List<Object>>();

        for (int i = 0; i < list.size(); i++) {
            java.lang.reflect.Field[] field = (java.lang.reflect.Field[]) list
                    .get(i).getClass().getDeclaredFields();
            field[fieldIndex].setAccessible(true);
            List<Object> find = readyMap.get(field[fieldIndex].get(list.get(i))
                    .toString());
            if (readyMap.isEmpty() || find == null) {
                List<Object> similar = new ArrayList<Object>();
                similar.add(list.get(i));
                readyMap.put(field[fieldIndex].get(list.get(i)).toString(),
                        similar);
            } else {
                find.add(list.get(i));
                readyMap.put(field[fieldIndex].get(list.get(i)).toString(),
                        find);
            }

        }

        return readyMap;
    }

    private static int fIndex = 0;

    /**
     * 遍历所有节点(递归调用)
     *
     * @param data
     * @param fieldIndexF
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private static List<JsonTreeObj> recursiveTree(List<Object> data,
                                                   int[] fieldIndexF) throws IllegalArgumentException,
            IllegalAccessException {

        // 先进行一次分组
        Map<String, List<Object>> firstGroupedData = groupByName(
                fieldIndexF[fIndex], data);
        List<JsonTreeObj> jsonTrees = new ArrayList<JsonTreeObj>();
        // 第一次分组的数据
        Iterator<Map.Entry<String, List<Object>>> it = firstGroupedData
                .entrySet().iterator();
        int i = 0;
        List<Object> list;
        // 遍历map
        while (it.hasNext()) {

            Map.Entry<String, List<Object>> entry = it.next();
            jsonTrees.add(new JsonTreeObj(entry.getKey(), entry.getValue()));
            if (fieldIndexF.length > 1 && fIndex < fieldIndexF.length - 2) {
                fIndex++;
                // **.clear()方法要清除指针，所以要用new方法实例化
                list = new ArrayList<Object>(entry.getValue());
                jsonTrees.get(i).getChildren().clear();
                jsonTrees.get(i).getChildren()
                        .addAll((recursiveTree(list, fieldIndexF)));

            }
            i++;
        }
        fIndex--;

        return jsonTrees;
    }
    /**
     * 将JSON串中的字符替换，如："post" -> "text" ,这样可以正常显示文本，否则 undefine
     *
     * @param src
     *            源JSON
     * @param replace
     *            要替换的字段
     * @param changedTxt
     *            替换之后的字段
     * @return 替换完成之后的字段
     */
    private static String replaceText(String src, String replace,
                                      String changedTxt) {
        return src.replaceAll("\"" + replace + "\"", "\"" + changedTxt + "\"");
    }


    /**第0个元素默认是id主键**/
    private final static Integer PRIMARY_KEY_INDEX = 0 ;
    /**第1个元素默认是节点名字**/
    private final static Integer 节点_NAME_INDEX = 1 ;
    /**第2个元素,父节点字段的索引**/
    private final static Integer PARENT_节点_INDEX = 2 ;

    /**树节点的默认状态**/
    private final static String TREE_节点_DEFAULT_STATE = "open" ;
    /**
     * 此方法用于实际数据和构造的json tree 对象赋值转换
     * @param groupedMap
     * @param members
     * @return
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static Map<String, List<JsonTreeObj>> assignment(Map<String, List<Object>> groupedMap,String[] members) throws SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, IntrospectionException {
        Map<String, List<JsonTreeObj>> groupedMapJsonObj = new HashMap<String, List<JsonTreeObj>>();
        Iterator<Map.Entry<String, List<Object>>> it = groupedMap.entrySet().iterator() ;


        while (it.hasNext()) {

            Map.Entry<String, List<Object>> entry = it.next();

            //map->list
            List<Object> valueList = entry.getValue() ;

            //新表
            List<JsonTreeObj> newList = new ArrayList<JsonTreeHelper.JsonTreeObj>() ;
            for (Object object : valueList) {
                JsonTreeObj obj = new JsonTreeObj() ;


                //id赋值
                String id  = getResultByMemberName(object,members[PRIMARY_KEY_INDEX]).toString() ;
                obj.setId(id) ;
                //节点name赋值
                String text = getResultByMemberName(object,members[节点_NAME_INDEX]).toString() ;
                obj.setText(text) ;
                //
                if(entry.getKey().equals(TREE_ROOT_NAME))
                    obj.setState(TREE_节点_DEFAULT_STATE) ;
                newList.add(obj) ;
            }

            groupedMapJsonObj.put(entry.getKey(), newList) ;
        }


        return groupedMapJsonObj ;
    }
    /**根节点的父为空,给它个map key,是这个常量**/
    private final static String TREE_ROOT_NAME = "root" ;
    /**
     * 用xxx类成员来分组 ,返回map数据
     * @param dataList
     * @param memberName
     * @return
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Map<String,List<Object>> groupByGetMethod(List<Object> dataList ,String memberName) throws SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, IntrospectionException {

        Map<String, List<Object>> groupedMap = new HashMap<String, List<Object>>();

        for (int i = 0; i < dataList.size(); i++) {
            Object value = getResultByMemberName(dataList.get(i), memberName) ;
            // 获得成员get方法值,分组依据
            String groupTagValue = (value == null || value.equals("") )? TREE_ROOT_NAME : value.toString();

            // 找数据
            List<Object> findList = groupedMap.get(groupTagValue);

            // 没找到就加数据,增加list
            if (groupedMap.isEmpty() || findList == null) {
                // 做为放在map中的list
                List<Object> tmpList = new ArrayList<Object>();
                tmpList.add(dataList.get(i));
                groupedMap.put(groupTagValue, tmpList);
            }
            // 找到数据就添加数据,增加map
            else {
                findList.add(dataList.get(i));
                // 覆盖map,新list替换旧list
                groupedMap.put(groupTagValue, findList);
            }

        }

        return groupedMap;
    }
    /**保存遍历list->child时的key**/
    private static String currentKeyFlag = TREE_ROOT_NAME ;
    /**保存遍历list时的当前key**/
    private static String preKeyFlag = TREE_ROOT_NAME ;
    /**
     * 递归树
     * @param groupedMap
     * @return
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static Map<String,List<JsonTreeObj>> recursiveTree(Map<String, List<JsonTreeObj>> groupedMap) throws SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        //复制map
        Map<String,List<JsonTreeObj>> copyGroupedMap = new HashMap<String, List<JsonTreeObj>>(groupedMap) ;

        //拿到所有的key,key也是父节点id
        Set<String> keys = groupedMap.keySet() ;

        //复制一个keys
        Set<String> copyKeys = new HashSet<String>(keys) ;

        Iterator<Map.Entry<String, List<JsonTreeObj>>> it = groupedMap.entrySet().iterator() ;
        while (it.hasNext() ) {

            Map.Entry<String, List<JsonTreeObj>> entry = it.next();
            String currentKey = entry.getKey() ;

            //获取根节点，最上的节点
            if(currentKey.equals(currentKeyFlag)) {
                List<JsonTreeObj> list = entry.getValue() ;

                //复制list
                List<JsonTreeObj> copytList = new ArrayList<JsonTreeObj>(list) ;
                for (int  i = 0 ; i < list.size() ; i ++ ) {
                    //map->list中的每一个id已经拿到
                    String id = list.get(i).getId() ;
                    copyKeys.remove(currentKeyFlag) ;

                    //默认不存在
                    boolean flag = false ;
                    for (Object key : keys) {
                        // key和根节点id一样？
                        if (key.equals(id)) {
                            // 暂存，除去此次遍历的map值,要留下其它的
                            Map<String, List<JsonTreeObj>> removeThisMap = new HashMap<String, List<JsonTreeObj>>(groupedMap);
                            removeThisMap.remove(currentKeyFlag);
                            // 进行递归
                            currentKeyFlag = id;
                            preKeyFlag = currentKey ;
                            Map<String, List<JsonTreeObj>> childMap = recursiveTree(removeThisMap);
                            if (childMap != null) {

                                Set<String> childSet = childMap.keySet();
                                //去除包含子节点的map key值
                                copyKeys.removeAll(childSet);
                                //主map不要这个值，否则很乱
//								copyGroupedMap.clear() ;
                                currentKeyFlag = preKeyFlag;
//								preKeyFlag = id;

                                List<JsonTreeObj> childList = childMap.get(id);
                                // 增加子节点
                                List<Object> childrenList = new ArrayList<Object>(childList);
                                //改变副本的值
                                copytList.get(i).setChildren(childrenList);
                                copyGroupedMap.put(currentKey, copytList) ;
                            }
                            flag = true ;
                            //提前结束循环,一个list就挂一个map
                            break ;
                        }

                    }
                    //没有子节点
                    if(!flag) {
                        List<JsonTreeObj> tmpList = new ArrayList<JsonTreeObj>(copyGroupedMap.get(currentKeyFlag) );
                        tmpList.get(i).setChildren(null);
//						copyGroupedMap.clear() ;
                        // TODO @wang'ao 当有一个根节点为空时有问题，不能显示所有根节点
//						Map<String, List<JsonTreeObj>> tmpMap = new HashMap<String, List<JsonTreeObj>>(copyGroupedMap) ;
                        copyGroupedMap.remove(currentKey) ;
                        copyGroupedMap.put(currentKey,tmpList) ;
                    }
//					currentKey = preKeyFlag;
                    preKeyFlag = currentKey ;
                    currentKeyFlag = preKeyFlag;
                }
                //提前结束循环while copyKeys
                break ;
            }
        }

        return copyGroupedMap ;
    }
    /**
     * 通过成员名获得该方法的get方法的返回值
     * @param member
     * @return
     * @throws ClassNotFoundException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object getResultByMemberName(Object obj , String member) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IntrospectionException {

        Class<? extends Object> clazz = obj.getClass();
        Field[] fields = obj.getClass().getDeclaredFields();// 获得属性
        Object o = "";

        for (Field field : fields) {
            String fieldName = field.getName() ;
            if (member.equals(fieldName)) {
                PropertyDescriptor pd = new PropertyDescriptor(fieldName,
                        clazz);
                Method getMethod = pd.getReadMethod();// 获得get方法
                o = getMethod.invoke(obj);// 执行get方法返回一个Object
                break ;
            }

        }
        return o;
    }

    /**
     * 用于页面传递JSON数据的类，id:xx text:xx children:xx
     * 将从数据库查询到的数据，用这个类进行打包，而可以将这个类直接转换成 指定的格式的JSON字符串
     *
     * tip:如有需要，直接继承，并添加一个自定义字段
     *
     * @author wang'ao
     *
     */
    public static class JsonTreeObj {
        /** 必需的id **/
        private String id;
        /** 显示的节点文本 **/
        private String text;
        /** 是否是关闭状态 **/
        private String state;
        /** 子 **/
        private List<Object> children;

        /**
         *
         *            节点的文本名字
         * @param children
         *            内部节点集合
         */
        public JsonTreeObj(String root节点, List<Object> children) {
            /** 保证id不重复 **/
            this.id = Math.random() + "";
            this.state = "closed";
            this.text = root节点;
            this.children = children;
        }

        /**
         *
         */
        public JsonTreeObj() {
            // TODO Auto-generated constructor stub
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<Object> getChildren() {
            return children;
        }

        public void setChildren(List<Object> children) {
            this.children = children;
        }

    }

}

