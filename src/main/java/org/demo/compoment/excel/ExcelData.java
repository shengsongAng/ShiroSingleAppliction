package org.demo.compoment.excel;


import java.util.ArrayList;
import java.util.List;

public class ExcelData {
    /**
     * 模拟数据库列表的数据
     */
    public static List<UserInfo> getDataList(int first, int last){
        List<UserInfo> list = new ArrayList<>();
        UserInfo u0 = new UserInfo("100","ang0","用户0","男",20,"18600000000");
        UserInfo u1 = new UserInfo("101","ang1","用户1","女",20,"18611111111");
        UserInfo u2 = new UserInfo("102","ang2","用户2","男",20,"18622222222");
        UserInfo u3 = new UserInfo("103","ang3","用户3","女",20,"18633333333");
        UserInfo u4 = new UserInfo("104","ang4","用户4","男",20,"18644444444");
        UserInfo u5 = new UserInfo("105","ang5","用户5","女",20,"18655555555");
        UserInfo u6 = new UserInfo("106","ang6","用户6","男",20,"18666666666");
        UserInfo u7 = new UserInfo("107","ang7","用户7","女",20,"18677777777");
        UserInfo u8 = new UserInfo("108","ang8","用户8","男",20,"18688888888");
        UserInfo u9 = new UserInfo("109","ang9","用户9","女",20,"18699999999");

        list.add(u0);
        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
        list.add(u5);
        list.add(u6);
        list.add(u7);
        list.add(u8);
        list.add(u9);

        if(last>10){
            last=10;
        }
        return list.subList(first,last);
    }
}
