package com.arloor.pokemon;

import com.google.common.collect.Sets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
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
                    continue;
                }
                List<String> params = new ArrayList<>();
                for (int i = 1; i < split.length; i++) {
                    params.add(split[i]);
                }
                if (TEAM.equals(split[0])) {
                    team(params);
                } else if (ATTACK.equals(split[0])) {
                    attack(params);
                } else {
                    System.out.println("命令错误");
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } while (true);

    }

    private static void attack(List<String> params) {
        Map<String, AtomicInteger> cells = new HashMap<>();
        List<String> toAttack = new ArrayList<>(params);
        List<String> toFangshou = new ArrayList<>(params);
        for (String param : toAttack) {
            if (param.startsWith("太晶")) {
                param = param.substring(2);
                cells.clear();
                Set<String> elements = Domain.弱点.getOrDefault(param, new HashSet<>());
                for (String element : elements) {
                    cells.computeIfAbsent(element, k -> new AtomicInteger(0)).addAndGet(2);
                }
                break;
            } else {
                Set<String> elements = Domain.弱点.getOrDefault(param, new HashSet<>());
                for (String element : elements) {
                    cells.computeIfAbsent(element, k -> new AtomicInteger(0)).addAndGet(2);
                }
            }
        }
        for (String s : toFangshou) {
            if (s.startsWith("太晶")) {
                s = s.substring(2);
            }
            Set<String> needDelete = Domain.优势.getOrDefault(s, new HashSet<>());
            for (String s1 : needDelete) {
                cells.remove(s1);
            }
        }
        System.out.println("可以选用：" + cells.entrySet().stream().sorted(new Comparator<Map.Entry<String, AtomicInteger>>() {
            @Override
            public int compare(Map.Entry<String, AtomicInteger> o1, Map.Entry<String, AtomicInteger> o2) {
                return o2.getValue().get()-o1.getValue().get();
            }
        }).map(entry -> entry.getKey() + ":" + entry.getValue()).collect(Collectors.joining(",")));
    }

    private static void team(List<String> params) {
        Set<String> result = new HashSet<>();
        for (String param : params) {
            result.addAll(Domain.优势.getOrDefault(param, new HashSet<>()));
        }
        System.out.println("该队伍对以下属性效果绝佳：" + String.join(",", result));
        Set<String> strings = Domain.getAll();
        strings.removeAll(result);
        System.out.println("盲点属性有：" + String.join(",", strings));
    }

    public static final void hint() {
        System.out.println("==================================");
        System.out.println("请输入命令：");
    }

    public static final void printUsage() {
        System.out.println("Usage:");
        System.out.println("1.队伍 虫 水 幽灵 毒 草 飞行 一般 妖精 格斗");
        System.out.println("2.攻击 岩石 太晶草");
    }
}
