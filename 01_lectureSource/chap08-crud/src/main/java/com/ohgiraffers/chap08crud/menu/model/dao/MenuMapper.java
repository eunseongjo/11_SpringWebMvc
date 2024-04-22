package com.ohgiraffers.chap08crud.menu.model.dao;

import com.ohgiraffers.chap08crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.chap08crud.menu.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<MenuDTO> findAllMenu();

    List<CategoryDTO> findAllCategory();

    void registNewMenu(MenuDTO newMenu);

    void modifyMenu(MenuDTO modifyMenu);

    void deleteMenu(MenuDTO deleteMenu);
}
