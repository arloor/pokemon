package com.example.pockmon_web.controller;

import com.example.pockmon_web.domain.Domain;
import com.example.pockmon_web.domain.RequestVO;
import com.example.pockmon_web.domain.Res;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
public class Controller {
    @RequestMapping("/api/pokemon")
    public Res serve(@RequestBody List<RequestVO> body) {
        StringBuilder sb=new StringBuilder();
        Map<String, AtomicInteger> cells = new HashMap<>();
        Set<String> toAttack=new HashSet<>();
        for (RequestVO cell : body) {
            if (cell.getIsTaijing()){
                toAttack= Sets.newHashSet(cell.getElement());
                break;
            }else {
                toAttack.add(cell.getElement());
            }
        }
        Set<String> toFangshou=body.stream().map(RequestVO::getElement).collect(Collectors.toSet());
        for (String cell : toAttack) {
            Set<String> toChoose = Domain.弱点.getOrDefault(cell, new HashSet<>());
            for (String s : toChoose) {
                cells.computeIfAbsent(s,k->new AtomicInteger(0)).addAndGet(2);
            }
        }
        Set<String> toDelete = toFangshou.stream().map(cell -> Domain.优势.getOrDefault(cell, new HashSet<>())).flatMap(Collection::stream).collect(Collectors.toSet());
        for (String s : toDelete) {
            cells.remove(s);
        }
        sb.append("推荐："+cells.entrySet().stream().sorted(new Comparator<Map.Entry<String, AtomicInteger>>() {
            @Override
            public int compare(Map.Entry<String, AtomicInteger> o1, Map.Entry<String, AtomicInteger> o2) {
                return o2.getValue().get() - o1.getValue().get();
            }
        }).map(entry -> entry.getKey() + ":" + entry.getValue()).collect(Collectors.joining(",")));
        sb.append("\n");
        sb.append("被count的有："+ String.join(",", toDelete));
        sb.append("\n");
        return Res.builder().msg(sb.toString()).build();
    }
}
