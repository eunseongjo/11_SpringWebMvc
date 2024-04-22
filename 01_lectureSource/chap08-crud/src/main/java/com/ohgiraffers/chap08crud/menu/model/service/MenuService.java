package com.ohgiraffers.chap08crud.menu.model.service;

import com.ohgiraffers.chap08crud.menu.model.dao.MenuMapper;
import com.ohgiraffers.chap08crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.chap08crud.menu.model.dto.MenuDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    private final MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper){

        this.menuMapper = menuMapper;
    }
    public List<MenuDTO> findAllMenu(){

        return menuMapper.findAllMenu();
    }
    public List<CategoryDTO> findAllCategory(){
        return menuMapper.findAllCategory();
    }

    @Transactional //트렌젝션처리를 해줌 서비스 오기 전에 aop
    public void registNewMenu(MenuDTO newMenu) {

       menuMapper.registNewMenu(newMenu);
    }




    @Transactional
    public void modifyMenu(MenuDTO modifymenu) {
        menuMapper.modifyMenu(modifymenu);
    }
    @Transactional
    public void deleteMenu(MenuDTO deleteMenu) {

        menuMapper.deleteMenu(deleteMenu);

    }
}
