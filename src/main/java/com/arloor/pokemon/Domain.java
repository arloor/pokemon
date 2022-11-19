package com.arloor.pokemon;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Domain {
    public static final Map<String, Set<String>> 优势 = new HashMap<>();
    public static final Map<String, Set<String>> 弱点 = new HashMap<>();
    public static final Set<String> getAll(){
        Set<String> all = new HashSet<>(优势.keySet());
        return Sets.newHashSet(all);
    }

    static {
        优势.put("一般", Sets.newHashSet());
        优势.put("格斗", Sets.newHashSet("一般", "岩石", "钢", "冰", "恶"));
        优势.put("飞行", Sets.newHashSet("格斗", "虫", "草"));
        优势.put("毒", Sets.newHashSet("草", "幽灵"));
        优势.put("地面", Sets.newHashSet("毒", "岩石", "钢", "火", "电"));
        优势.put("岩石", Sets.newHashSet("飞行", "虫", "火", "冰"));
        优势.put("虫", Sets.newHashSet("草", "超能力", "恶"));
        优势.put("幽灵", Sets.newHashSet("幽灵", "超能力"));
        优势.put("钢", Sets.newHashSet("岩石", "冰", "妖精"));
        优势.put("火", Sets.newHashSet("虫", "钢", "草", "冰"));
        优势.put("水", Sets.newHashSet("地面", "岩石", "火"));
        优势.put("草", Sets.newHashSet("地面", "岩石", "水"));
        优势.put("电", Sets.newHashSet("飞行", "水"));
        优势.put("超能力", Sets.newHashSet("格斗", "毒"));
        优势.put("冰", Sets.newHashSet("飞行", "地面", "草", "龙"));
        优势.put("龙", Sets.newHashSet("龙"));
        优势.put("恶", Sets.newHashSet("幽灵", "超能力"));
        优势.put("妖精", Sets.newHashSet("格斗", "龙", "恶"));

        弱点.put("一般", Sets.newHashSet("格斗"));
        弱点.put("格斗", Sets.newHashSet("飞行", "超能力", "妖精"));
        弱点.put("飞行", Sets.newHashSet("岩石", "电", "冰"));
        弱点.put("毒", Sets.newHashSet("地面", "超能力"));
        弱点.put("地面", Sets.newHashSet("水", "草", "冰"));
        弱点.put("岩石", Sets.newHashSet("格斗", "地面", "钢", "水","草"));
        弱点.put("虫", Sets.newHashSet("飞行", "岩石", "火"));
        弱点.put("幽灵", Sets.newHashSet("幽灵", "恶"));
        弱点.put("钢", Sets.newHashSet("格斗", "地面", "火"));
        弱点.put("火", Sets.newHashSet("地面", "岩石", "水"));
        弱点.put("水", Sets.newHashSet("电", "草"));
        弱点.put("草", Sets.newHashSet("飞行", "毒", "虫","火","冰"));
        弱点.put("电", Sets.newHashSet("地面"));
        弱点.put("超能力", Sets.newHashSet("虫", "幽灵","恶"));
        弱点.put("冰", Sets.newHashSet("格斗", "岩石", "钢", "火"));
        弱点.put("龙", Sets.newHashSet("冰","龙","妖精"));
        弱点.put("恶", Sets.newHashSet("格斗", "虫","妖精"));
        弱点.put("妖精", Sets.newHashSet("毒", "钢"));
    }

}
