package br.com.digitoglobal.accesscontrol.main;

import br.com.digitoglobal.accesscontrol.service.UserService;
import br.com.digitoglobal.accesscontrol.util.SpringUtils;

public class Main {

    public static void main(String[] args) {

        UserService service = SpringUtils.getBean(UserService.class);
        System.out.println(service);

    }

}
