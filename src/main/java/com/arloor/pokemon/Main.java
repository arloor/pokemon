package com.arloor.pokemon;

import com.google.common.collect.Sets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final String TEAM = "队伍";
    private static final String ATTACK = "攻击";

    public static void main(String[] args) {
        printUsage();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        do {
            hint();
            try {
                line = br.readLine();
                String[] split = line.split("[ ,]");
                if (split.length <= 1) {
                    System.out.println("命令长度太短");
                }
                List<String> params = new ArrayList<>();
                for (int i = 1; i < split.length; i++) {
                    params.add(split[i]);
                }
                if (TEAM.equals(split[0])){
                    team(params);
                }
                if (ATTACK.equals(split[0])){
                    attack(params);
                }
                System.out.println();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }while (true);

    }

    private static void attack(List<String> params) {
        Set<String> duplicates = new HashSet<>();
        boolean needShowDuplicates=false;
        for (String param : params) {
           Set<String> elements=Domain.弱点.getOrDefault(param,new HashSet<>());
            System.out.println(param+" 的弱点有："+String.join(",", elements));
            if (duplicates.size()==0){
                duplicates.addAll(elements);
            }else {
                needShowDuplicates=true;
                duplicates.retainAll(elements);
            }
        }
        if (needShowDuplicates&&duplicates.size()!=0){
            System.out.println("多倍弱点有："+String.join(",", duplicates));
        }
    }

    private static void team(List<String> params) {
        Set<String> result=new HashSet<>();
        for (String param : params) {
            result.addAll(Domain.优势.getOrDefault(param,new HashSet<>()));
        }
        System.out.println("该队伍对以下属性效果绝佳："+ String.join(",", result));
        Set<String> strings = Domain.getAll();
        strings.removeAll(result);
        System.out.println("盲点属性有："+String.join(",", strings));
    }

    public static final void hint(){
        System.out.println("请输入命令：");
    }

    public static final void printUsage() {
        System.out.println("Usage:");
        System.out.println("1. 队伍 草 钢 岩石 水");
        System.out.println("2. 攻击 草 钢");
    }
}
