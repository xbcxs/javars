package com.xbcxs.research.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EasyexcelMain {

    public static void main(String[] args){

        simpleRead();

        List<Dept> deptList = new ArrayList<>();
        for (Map.Entry<String, Dept> entry : DemoDataListener.deptMap.entrySet()){
            deptList.add(entry.getValue());
        }

        List<User> userList = new ArrayList<>();
        for (Map.Entry<String, User> entry : DemoDataListener.userMap.entrySet()){
            entry.getValue().setMainDeptDeep(null);
            userList.add(entry.getValue());
        }

        simpleWrite(deptList, userList);

    }

    private static void simpleRead() {
        ExcelReader excelReader = EasyExcel.read("C:/aaa.xlsx", DemoData.class, new DemoDataListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }

    public static void simpleWrite(List<Dept> deptList, List<User> userList) {

        ExcelWriter excelWriter = EasyExcel.write("D:/AXiaoShan/fff1.xlsx").build();
        WriteSheet writeSheet1 = EasyExcel.writerSheet(0, "sys_dept").head(Dept.class).build();
        WriteSheet writeSheet2 = EasyExcel.writerSheet(1, "sys_user").head(User.class).build();

        excelWriter.write(deptList, writeSheet1);
        excelWriter.write(userList, writeSheet2);
        excelWriter.finish();

    }
}
