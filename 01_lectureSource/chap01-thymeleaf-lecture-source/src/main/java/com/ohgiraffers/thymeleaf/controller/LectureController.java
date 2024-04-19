package com.ohgiraffers.thymeleaf.controller;

import com.ohgiraffers.thymeleaf.model.dto.MemberDTO;
import com.ohgiraffers.thymeleaf.model.dto.SelectCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("lecture")
public class LectureController {

    @GetMapping("expression")
    public ModelAndView expression(ModelAndView mv){

        mv.addObject("member", new MemberDTO("홍길동",20,'남',"서울시 서초구 "));
        mv.addObject("hello", new MemberDTO("홍길동",20,'남',"서울시 서초구 "));
        mv.setViewName("/lecture/expression");
        return mv;
    }

    @GetMapping("conditional")
    public ModelAndView conditional(ModelAndView mv){

          mv.addObject("num",1);
          mv.addObject("str","바나나");
//        mv.setViewName("/lecture/conditional");

        List<MemberDTO> memberDTOList = new ArrayList<>();
        memberDTOList.add(new MemberDTO("홍길동", 20, '남', "서울시 서초구"));
        memberDTOList.add(new MemberDTO("홍갈동", 20, '남', "서울시 서초구"));
        memberDTOList.add(new MemberDTO("홍굴동", 20, '남', "서울시 서초구"));
        memberDTOList.add(new MemberDTO("홍골동", 20, '남', "서울시 서초구"));
        memberDTOList.add(new MemberDTO("홍김동", 20, '남', "서울시 서초구"));

        mv.addObject("memberList", memberDTOList);
        return mv;

    }

    @GetMapping("etc")
    public ModelAndView etc(ModelAndView mv){

        SelectCriteria selectCriteria = new SelectCriteria(1,1000,97);

        mv.addObject(selectCriteria);
        //selectCriteria로 키값 설정

        return mv;
    }
}
