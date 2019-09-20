package com.xbcxs.common.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaosh
 * @date 2019/9/10
 */
public class TreeFormat {

    public static void main(String[] args) {
        // 数据库查询所有节点
        List<Map> originalList = null;
        String topNodeId = null;
        buildTree(topNodeId, originalList);
    }

    /**
     * 将有结构的List数据构建成JSON树结构数据
     *    {
     *        "id": "",
     *        "name": "",
     *        "children": [
     *            {
     *            "id": "",
     *            "name": "",
     *            "children": []
     *            },
     *        ]
     *    }
     * @param topNodeId
     * @param originalList
     * @return
     */
    public static List<Map> buildTree(String topNodeId, List<Map> originalList) {
        // 构建虚拟顶层节点
        List<Map> topList = new ArrayList<>();
        Map topMap = new HashMap();
        topMap.put("id", topNodeId);
        topList.add(topMap);
        recursionBuildTree(topList, originalList);
        return (List<Map>)topMap.get("children");
    }

    private static void recursionBuildTree(List<Map> parentList, List<Map> originalList) {
        for (Map parentMap : parentList) {
            // 广度遍历获取该节点所有一级子节点
            String parentId = String.valueOf(parentMap.get("id"));
            List<Map> childrenList = new ArrayList<>();
            for(Map originalMap : originalList){
                if(parentId.equals(String.valueOf(originalMap.get("parentId")))){
                    childrenList.add(originalMap);
                }
            }
            if(!childrenList.isEmpty()){
                parentMap.put("children", childrenList);
                originalList.removeAll(childrenList);
                recursionBuildTree(childrenList, childrenList);
            }
        }
    }
}
