package com.ohgiraffers.chap03handlermethod;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;



@Controller
@RequestMapping("/first/*")
public class FirstController {

    /* 핸들러 메소드에 파리미터로 특정 몇 가지 타입을 선언하게 되면 핸들러 메소드 호출 시 인자로 값을 전달해 준다.*/

    /* 컨트롤러 핸들러 메소드의 반환값을 void로 설정하면 요청 주소가 view의 이름이 된다.
    *  => /first/regist 표청이 들어오면 /first/regist 뷰를 응답한다.*/

    @GetMapping("regist")
    public void regist(){ }

    /*1. WebRequest로 요청 파라미터 전달 받기
    *       파라미터 선언부에 WebRequest 타입을 선언하면 해당 메소드 호출 시 인자로 값을 전달해 준다.
    *       핸들러 메소드 매개변수로 HttpServletRequest, HttpServletResponse도 사용 가능하다.
    *       상위 타입인 ServletRequest, ServletResponse도 사용 가능하다.
    *       WebRequest는 HttpServletRequest의 요청 정보를 거의 대부분 그대로 가지고 있는 API로 Servlet에 종속적이지 않다.
    *       WebRequest는 Spring의 일부 이기 때문에 Spring 기반의 프로젝트에서 더 자주 사용 된다.*/

        @PostMapping("regist")
    public String registMenu(Model model, WebRequest request){
        /*WebRequest 객체의 getParameter 등의 메소드를 통해 클라이언트로 부터 전달 된 파라미터를 가져올 수 있다.*/
            String name = request.getParameter("name");
            int price = Integer.parseInt(request.getParameter("price"));
            int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));

            /*클라이언트로부터 전달 받은 값을 통해 응답할 화면의 메세지를 생성한다.*/
            String message = name +"을(를) 신규 메뉴 목록의 " + categoryCode + "번 카테고리에 "+ price+"원으로 등록 하셨습니다.";

            model.addAttribute("message", message);

            return "first/messagePrinter";
        }

        @GetMapping("modify")
    public void modify(){}


    /*2. @RequestParam로 요청 파라미터 전달 받기
    *       요청 파라미터를 매핑하여 호출 시 값을 넣어주는 어노테이션으로 매개 변수 앞에 작성한다.
    *       form의 name 속성값과 매개변수의 이름이 다른 경우 @RequestParam("이름")을 설정하면 된다.
    *       또한 어노테이션은 생략 가능하지만 명시적으로 작성하는 것이 의미 파악에 쉽다.
    *
    *       전달하는 form의 name 속성이 일치하는 것이 없는 경우 400에러가 발생하는데 이는 required속성의 기본 값이 true이기 때문이다.
    *       required 속성을 false로 하게 되면 해당 name값이 존재하지 않아도 null로 처리하며 에러가 발생하지 않는다.
    *
    *       값이 넘어오지 않게 되면 ""빈 문자열이 넘어오게 되는데, 이 때 parsing 관련 에러가 발생할 수 있다.
    *       값이 넘어오지 않는 경우 defaltValue를 이용하게 되면 기본 값을 사용할 수 있다.*/
    @PostMapping("modify")
    public String modifyMenuName(Model model, @RequestParam(required = false) String modifyName, @RequestParam(defaultValue = "o") int modifyPrice){

            String message = modifyName + "메뉴의 가격을 " +modifyPrice + "원으로 가격을 변경하였습니다.";

            model.addAttribute("message", message);

            return "first/messagePrinter";
    }

    @PostMapping("modifyAll")
    public String modifyAll(Model model, @RequestParam Map<String, String> parameters){

            String modifyMenu = parameters.get("modifyName2");
            int modifyPrice = Integer.parseInt(parameters.get("modifyPrice2"));

            String message = "메뉴의 이름을" + modifyMenu + "(으)로, 가격을 " + modifyPrice + "원 으로 변경 하였습니다.";

            model.addAttribute("message", message);

            return "first/messagePrinter";
    }

    @GetMapping("search")
    public void search(){}

    /* 3. @ModelAttribute를 이용하는 방법
     * DTO 같은 커맨드 객체로 전달받는다.
     *
     * @ModelAttribute의 경우 커맨드객체를 생성하여 매개변수로 전달해 준 뒤 해당 인스턴스를 model에 담는다.
     * 화면에서 출력해보면 모델에 담겨진 값을 확인할 수 있다.
     *
     * 경우에 따라 폼에서 입력한 값을 다음 화면으로 바로 전달해야 하는 경우가 발생하는데 이 때 유용하게 사용할 수 있다.
     * @ModelAttribute("모델에담을key값")을 지정할 수 있으며, 지정하지 않으면 타입의 앞글자를 소문자로 한 네이밍 규칙을 따른다.
     *
     * 해당 어노테이션은 생략이 가능하지만 명시적으로 작성하는 것이 좋다.
     * */

    @PostMapping("search")
    public String searchMenu(@ModelAttribute("menu") MenuDTO menu){
        System.out.println(menu);

        return "first/searchResult";
    }

}
